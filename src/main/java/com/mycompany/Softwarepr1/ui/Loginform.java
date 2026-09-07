/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Softwarepr1.ui;
import javax.swing.JOptionPane;

import com.mycompany.Softwarepr1.util.*;
import com.mycompany.Softwarepr1.models.*;

import javax.persistence.EntityManager;
import java.util.List;

public class Loginform extends javax.swing.JFrame {
    private int failedAttempts = 0; // This stays in memory as long as the window is open
       
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Loginform.class.getName());

    //Creates new form Loginform    
    public Loginform() {
        initComponents();
        // Test database connection at startup and show status to the user
        DatabaseUtil.testConnection();
    }
    
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passTxt = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        regBtn = new javax.swing.JButton();
        InvalidINFO = new javax.swing.JLabel();
        InvalidINFO.setVisible(false);
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 70, 140));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Hotel Booking App");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel3)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Username ");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        userTxt.addActionListener(this::userTxtActionPerformed);

        jLabel2.setText("Password :");

        passTxt.addActionListener(this::passTxtActionPerformed);

        loginBtn.setText("Login");
        loginBtn.addActionListener(this::loginBtnActionPerformed);

        regBtn.setText("Register");
        regBtn.addActionListener(this::regBtnActionPerformed);

        InvalidINFO.setForeground(new java.awt.Color(255, 0, 51));
        InvalidINFO.setText("Invalid username or password");

        jLabel4.setForeground(new java.awt.Color(0, 102, 204));
        jLabel4.setText("Forgot Password?");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InvalidINFO)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(loginBtn)
                        .addGap(18, 18, 18)
                        .addComponent(regBtn))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(passTxt)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel4))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(InvalidINFO)
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginBtn)
                    .addComponent(regBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTxtActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
    javax.swing.JOptionPane.showMessageDialog(this, "Redirecting to Password reset tab ");
   

    // 2. Open the Reset Form and close the Login form
    new ResetPasswordForm(userTxt.getText().trim()).setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void passTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passTxtActionPerformed
                
    }//GEN-LAST:event_passTxtActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed

        String username = userTxt.getText().trim();
        String password = new String(passTxt.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            InvalidINFO.setText("Please enter username and password.");
            InvalidINFO.setVisible(true);
            return;
        }

        EntityManager em = null;
        try {
            em = DatabaseUtil.createEntityManager();

            List<User> results = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :user", User.class)
                .setParameter("user", username)
                .getResultList();

            if (results.isEmpty()) {
                // User doesn't exist — don't count as a failed login attempt
                InvalidINFO.setText("Username not found. Please register.");
                InvalidINFO.setVisible(true);
            } else if (PasswordUtil.verifyPassword(password, results.get(0).getPassword())) {
                // Correct password — login success
                failedAttempts = 0;
                InvalidINFO.setVisible(false);
                new Homepage(results.get(0)).setVisible(true);
                this.dispose();
            } else {
                // Wrong password for an existing user
                failedAttempts++;
                InvalidINFO.setText("Invalid password. Attempt " + failedAttempts + " of 3.");
                InvalidINFO.setVisible(true);
                passTxt.setText("");

                if (failedAttempts >= 3) {
                    JOptionPane.showMessageDialog(this, "3 failed attempts! Redirecting to Reset Password page...");
                    new ResetPasswordForm(username).setVisible(true);
                    this.dispose();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }

    }//GEN-LAST:event_loginBtnActionPerformed

    private void regBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regBtnActionPerformed
     // 1. Create the Registration window
    new Registrationform().setVisible(true);
    
    // 2. Kill the login window
    this.dispose();
    }//GEN-LAST:event_regBtnActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Loginform().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel InvalidINFO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passTxt;
    private javax.swing.JButton regBtn;
    private javax.swing.JTextField userTxt;
    // End of variables declaration//GEN-END:variables
}
