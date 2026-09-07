package com.mycompany.Softwarepr1.ui;

import javax.swing.JOptionPane;
import com.mycompany.Softwarepr1.models.*;
import com.mycompany.Softwarepr1.util.DatabaseUtil;
import com.mycompany.Softwarepr1.util.PasswordUtil;
import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Moaaz
 */
public class USERPROFILE extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(USERPROFILE.class.getName());
    private User currentUser;
    private int failedAttempts = 0;

    /**
     * Creates new form USERPROFILE.
     * Note: Use USERPROFILE(User) constructor to load a user's profile.
     * The Homepage handles the DB lookup before opening this form.
     */
    public USERPROFILE() {
        initComponents();
        // Disable update button when no user is loaded (prevents NullPointerException)
        UPDATEINFO.setEnabled(false);
    }

    /**
     * Creates form with logged-in user data pre-filled.
     */
    public USERPROFILE(User loggedInUser) {
        initComponents();
        this.currentUser = loggedInUser;

        // Override close behavior: go back to Homepage instead of exiting
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new Homepage(loggedInUser).setVisible(true);
                dispose();
            }
        });

        prefillCurrentValues();
    }



    /**
     * Pre-fills all current user values into the form fields.
     */
    private void prefillCurrentValues() {
        if (currentUser == null) return;

        // Show current username (read-only)
        CURRENTUSERNAME.setText(currentUser.getUsername());
        CURRENTUSERNAME.setEditable(false);

        // Pre-fill new fields with current values so user only changes what they need
        NEWUSERNAME.setText(currentUser.getUsername());
        NEWMAIL.setText(currentUser.getEmail() != null ? currentUser.getEmail() : "");

        // Wire the Update button (remove old listeners first to prevent duplicates)
        for (java.awt.event.ActionListener al : UPDATEINFO.getActionListeners()) {
            UPDATEINFO.removeActionListener(al);
        }
        UPDATEINFO.setEnabled(true);
        UPDATEINFO.addActionListener(e -> updateUserInfo());

        // Add clickable "Forgot Password?" link
        javax.swing.JLabel forgotPassLabel = new javax.swing.JLabel("Forgot Password?");
        forgotPassLabel.setForeground(new java.awt.Color(0, 102, 204));
        forgotPassLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgotPassLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(USERPROFILE.this, "Redirecting to Password Reset...");
                new ResetPasswordForm(currentUser.getUsername()).setVisible(true);
                dispose();
            }
        });

        // Place it below the current password field area
        getContentPane().add(forgotPassLabel);
        pack();
        // Position it after packing so we know the frame size
        int frameWidth = getContentPane().getWidth();
        forgotPassLabel.setBounds((frameWidth / 2) - 50, getContentPane().getHeight() - 30, 150, 20);
        setSize(getWidth(), getHeight() + 40);
    }

    private void updateUserInfo() {
        String currentPass = new String(CURRENTPASSWORD.getPassword());
        String newUsername = NEWUSERNAME.getText().trim();
        String newPass = new String(NEWPASSWORD.getPassword());
        String confirmPass = new String(CONFIRMNEWPASSWORD.getPassword());
        String newEmail = NEWMAIL.getText().trim();

        // 1. Must verify current password first
        if (currentPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your current password to make changes.");
            return;
        }

        if (!PasswordUtil.verifyPassword(currentPass, currentUser.getPassword())) {
            failedAttempts++;
            if (failedAttempts >= 3) {
                JOptionPane.showMessageDialog(this,
                        "3 failed attempts! Redirecting to Password Reset...");
                new ResetPasswordForm(currentUser.getUsername()).setVisible(true);
                this.dispose();
                return;
            }
            JOptionPane.showMessageDialog(this,
                    "Current password is incorrect. Attempt " + failedAttempts + " of 3.");
            CURRENTPASSWORD.setText("");
            return;
        }

        // Password verified — reset failed attempts counter
        failedAttempts = 0;

        // 2. Validate all fields — same rules as Registration

        // Username and Email are required
        if (newUsername.isEmpty() || newEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and Email fields cannot be empty.");
            return;
        }

        // Password: if left empty, keep the existing password
        boolean changingPassword = !newPass.isEmpty();
        if (changingPassword) {
            if (PasswordUtil.verifyPassword(newPass, currentUser.getPassword())) {
                JOptionPane.showMessageDialog(this, "New password cannot be the same as your old password. Please enter a different password.");
                return;
            }
            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "New passwords do not match!");
                return;
            }
            if (newPass.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters.");
                return;
            }
        }

        // Email validation
        if (!newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
            return;
        }
        boolean changingEmail = !newEmail.equals(currentUser.getEmail());

        // 3. Update in database
        EntityManager em = DatabaseUtil.createEntityManager();
        try {
            em.getTransaction().begin();

            // Re-fetch user from DB to ensure we have the latest
            List<User> results = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :user", User.class)
                .setParameter("user", currentUser.getUsername())
                .getResultList();

            if (results.isEmpty()) {
                em.getTransaction().rollback();
                JOptionPane.showMessageDialog(this, "User not found in database.");
                return;
            }

            User userToUpdate = results.get(0);

            // Check if new username is already taken (only if changed)
            if (!newUsername.equals(currentUser.getUsername())) {
                List<User> existing = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :user", User.class)
                    .setParameter("user", newUsername)
                    .getResultList();
                if (!existing.isEmpty()) {
                    em.getTransaction().rollback();
                    JOptionPane.showMessageDialog(this, "Username '" + newUsername + "' is already taken.");
                    return;
                }
            }

            // Update fields
            userToUpdate.setUsername(newUsername);

            // Only update email if the user entered a new one
            if (changingEmail) {
                userToUpdate.setEmail(newEmail);
            }

            // Only update password if the user entered a new one
            if (changingPassword) {
                userToUpdate.setPassword(PasswordUtil.hashPassword(newPass));
            }

            em.getTransaction().commit();

            // Update the in-memory user object
            currentUser.setUsername(newUsername);
            if (changingEmail) {
                currentUser.setEmail(newEmail);
            }
            if (changingPassword) {
                currentUser.setPassword(userToUpdate.getPassword());
            }

            JOptionPane.showMessageDialog(this, "Profile updated successfully!");

            // Refresh the form with updated values
            CURRENTUSERNAME.setText(newUsername);
            NEWUSERNAME.setText(newUsername);
            NEWMAIL.setText(currentUser.getEmail() != null ? currentUser.getEmail() : "");
            CURRENTPASSWORD.setText("");
            NEWPASSWORD.setText("");
            CONFIRMNEWPASSWORD.setText("");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(this, "Error updating profile: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CURRENTUSERNAME = new javax.swing.JTextField();
        NEWUSERNAME = new javax.swing.JTextField();
        CURRENTPASSWORD = new javax.swing.JPasswordField();
        NEWPASSWORD = new javax.swing.JPasswordField();
        CONFIRMNEWPASSWORD = new javax.swing.JPasswordField();
        NEWMAIL = new javax.swing.JTextField();
        UPDATEINFO = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(27, 26, 64));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("User Profile ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(486, 486, 486)
                .addComponent(jLabel1)
                .addContainerGap(554, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabel2.setText("Current username :");

        jLabel3.setText("New User Name :");

        jLabel4.setText("Current password :");

        jLabel5.setText("New password :");

        jLabel6.setText("Confirm new password :");

        jLabel7.setText("New email :");

        UPDATEINFO.setText("Update info");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CONFIRMNEWPASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NEWUSERNAME))
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CURRENTUSERNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CURRENTPASSWORD))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(NEWPASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61)
                        .addComponent(UPDATEINFO))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NEWMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CURRENTUSERNAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(NEWUSERNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(CURRENTPASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(UPDATEINFO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(NEWPASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CONFIRMNEWPASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(NEWMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            // Test database connection first
            if (!DatabaseUtil.testConnection()) {
                System.exit(1);
                return;
            }

            // Prompt for username before opening profile
            String inputUsername = javax.swing.JOptionPane.showInputDialog(
                    null,
                    "Enter your username to load your profile:",
                    "Load User Profile",
                    javax.swing.JOptionPane.PLAIN_MESSAGE);

            if (inputUsername == null || inputUsername.trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "No username entered. Exiting.");
                System.exit(0);
                return;
            }

            javax.persistence.EntityManager em = null;
            try {
                em = DatabaseUtil.createEntityManager();
                java.util.List<User> results = em.createQuery(
                        "SELECT u FROM User u WHERE u.username = :user", User.class)
                        .setParameter("user", inputUsername.trim())
                        .getResultList();

                if (results.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null,
                            "User '" + inputUsername.trim() + "' not found.",
                            "User Not Found",
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                    return;
                }

                new USERPROFILE(results.get(0)).setVisible(true);

            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Error: " + e.getMessage(),
                        "Database Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            } finally {
                if (em != null) {
                    try { em.close(); } catch (Exception ignored) {}
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField CONFIRMNEWPASSWORD;
    private javax.swing.JPasswordField CURRENTPASSWORD;
    private javax.swing.JTextField CURRENTUSERNAME;
    private javax.swing.JTextField NEWMAIL;
    private javax.swing.JPasswordField NEWPASSWORD;
    private javax.swing.JTextField NEWUSERNAME;
    private javax.swing.JButton UPDATEINFO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
