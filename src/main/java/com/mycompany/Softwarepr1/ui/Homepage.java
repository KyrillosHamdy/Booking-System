package com.mycompany.Softwarepr1.ui;

import javax.swing.JOptionPane;
import com.mycompany.Softwarepr1.models.*;
import com.mycompany.Softwarepr1.util.*;
/**
 *
 * @author Moaaz
 */
public class Homepage extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Homepage.class.getName());

    /**
     * Creates new form Homeform
     */
    private User loggedInUser;
    private Hotel[] hotelCatalogue;
    private Hotel[] suiteCatalogue;
    private javax.swing.JPanel bookingsContainer; // dynamic scrollable container inside MYBOOKINGS
    /**
     * ID of the booking being paid via the PAYMENT tab; -1 when none is pending.
     */
    private int pendingPaymentBookingId = -1;

    // We add "User loggedInUser" as a parameter
    public Homepage(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        initComponents();

        // --- Wrap HOME tab in a JScrollPane for scrolling ---
        int homeIndex = tabs.indexOfComponent(HOME);
        String homeTitle = homeIndex != -1 ? tabs.getTitleAt(homeIndex) : "Home";

        javax.swing.JScrollPane homeScrollPane = new javax.swing.JScrollPane(HOME);
        homeScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        homeScrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        homeScrollPane.setBorder(null);
        homeScrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling speed

        // Re-insert the scrollable HOME at the same tab position
        if (homeIndex != -1) {
            tabs.insertTab(homeTitle, null, homeScrollPane, null, homeIndex);
            tabs.setSelectedIndex(0);
        }

        // Hide the old SCROLLBAR — JScrollPane handles scrolling natively

        // --- User State ---
        if (loggedInUser != null) {
            welcomeUserLbl.setText("Hello, " + loggedInUser.getFirstName() + "!");
            welcomeUserLbl.setVisible(true);
            welcomeUserLbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            loginBtn.setVisible(false);
            regBtn.setVisible(false);

            // Click username to open User Profile
            welcomeUserLbl.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    new USERPROFILE(loggedInUser).setVisible(true);
                    dispose();
                }
            });

        } else {
            welcomeUserLbl.setVisible(false);
            loginBtn.setVisible(true);
            regBtn.setVisible(true);

        }

        // --- Hotel catalogue, labels, book-button wiring, dynamic bookings panel ---
        seedHotelsToDatabase();
        hotelCatalogue = Hotel.getHotelCatalogue();
        suiteCatalogue = Hotel.getSuiteCatalogue();
        updateHotelCardLabels();
        wireBookButtons();
        buildDynamicBookingsPanel();
    }

    private void seedHotelsToDatabase() {
        javax.persistence.EntityManager em = null;
        try {
            em = DatabaseUtil.createEntityManager();
            long count = (long) em.createQuery("SELECT COUNT(h) FROM Hotel h").getSingleResult();
            if (count == 0) {
                em.getTransaction().begin();
                for (Hotel h : Hotel.getHotelCatalogue()) {
                    em.persist(h);
                }
                for (Hotel h : Hotel.getSuiteCatalogue()) {
                    em.persist(h);
                }
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel31 = new javax.swing.JLabel();
        Feedback2 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jRadioButton66 = new javax.swing.JRadioButton();
        jRadioButton67 = new javax.swing.JRadioButton();
        jRadioButton68 = new javax.swing.JRadioButton();
        jRadioButton69 = new javax.swing.JRadioButton();
        jRadioButton70 = new javax.swing.JRadioButton();
        jLabel45 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jRadioButton71 = new javax.swing.JRadioButton();
        jRadioButton72 = new javax.swing.JRadioButton();
        jRadioButton73 = new javax.swing.JRadioButton();
        jRadioButton74 = new javax.swing.JRadioButton();
        jRadioButton75 = new javax.swing.JRadioButton();
        jRadioButton76 = new javax.swing.JRadioButton();
        jRadioButton77 = new javax.swing.JRadioButton();
        jRadioButton78 = new javax.swing.JRadioButton();
        jRadioButton79 = new javax.swing.JRadioButton();
        jRadioButton80 = new javax.swing.JRadioButton();
        jRadioButton81 = new javax.swing.JRadioButton();
        jRadioButton82 = new javax.swing.JRadioButton();
        jRadioButton83 = new javax.swing.JRadioButton();
        jRadioButton84 = new javax.swing.JRadioButton();
        jRadioButton85 = new javax.swing.JRadioButton();
        jRadioButton86 = new javax.swing.JRadioButton();
        jRadioButton87 = new javax.swing.JRadioButton();
        jRadioButton88 = new javax.swing.JRadioButton();
        jRadioButton89 = new javax.swing.JRadioButton();
        jRadioButton90 = new javax.swing.JRadioButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        HOME = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        bookhotel1 = new javax.swing.JButton();
        bookhotel2 = new javax.swing.JButton();
        bookhotel3 = new javax.swing.JButton();
        bookhotel4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        regBtn = new javax.swing.JButton();
        loginBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jComboBox1 = new javax.swing.JComboBox<>();
        welcomeUserLbl = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jSpinner2 = new javax.swing.JSpinner();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        userprofile = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        booksuites1 = new javax.swing.JButton();
        booksuites2 = new javax.swing.JButton();
        booksuites3 = new javax.swing.JButton();
        booksuites4 = new javax.swing.JButton();
        PAYMENT = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        ABOUTUS = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jPanel23 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        MYBOOKINGS = new javax.swing.JPanel();
        Feedback1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        commentTextArea = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        ratingComboBox = new javax.swing.JComboBox<>();
        recommendationComboBox = new javax.swing.JComboBox<>();
        roomQualityComboBox = new javax.swing.JComboBox<>();
        bookingProcessComboBox = new javax.swing.JComboBox<>();

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));
        jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));

        jLabel31.setText("jLabel31");

        jLabel42.setText("Please rate your overall experience ");

        jLabel43.setText("How likely are you to recommend us to a friend or colleague?");

        jLabel44.setText("How easy was it to  book desired hotel ?");

        jRadioButton66.setText("Very good");

        jRadioButton67.setText("Excellent");

        jRadioButton68.setText("Bad");
        jRadioButton68.addActionListener(this::jRadioButton68ActionPerformed);

        jRadioButton69.setText("neutral");

        jRadioButton70.setText("Good");
        jRadioButton70.addActionListener(this::jRadioButton70ActionPerformed);

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel45.setText("What’s the  things we could improve ?");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jButton5.setText("Send");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jRadioButton71.setText("Very good");

        jRadioButton72.setText("Excellent");

        jRadioButton73.setText("Bad");
        jRadioButton73.addActionListener(this::jRadioButton73ActionPerformed);

        jRadioButton74.setText("neutral");

        jRadioButton75.setText("Good");
        jRadioButton75.addActionListener(this::jRadioButton75ActionPerformed);

        jRadioButton76.setText("Very good");

        jRadioButton77.setText("Excellent");

        jRadioButton78.setText("Bad");
        jRadioButton78.addActionListener(this::jRadioButton78ActionPerformed);

        jRadioButton79.setText("neutral");

        jRadioButton80.setText("Good");
        jRadioButton80.addActionListener(this::jRadioButton80ActionPerformed);

        jRadioButton81.setText("Very good");

        jRadioButton82.setText("Excellent");

        jRadioButton83.setText("Bad");
        jRadioButton83.addActionListener(this::jRadioButton83ActionPerformed);

        jRadioButton84.setText("neutral");

        jRadioButton85.setText("Good");
        jRadioButton85.addActionListener(this::jRadioButton85ActionPerformed);

        jRadioButton86.setText("Very good");

        jRadioButton87.setText("Excellent");

        jRadioButton88.setText("Bad");
        jRadioButton88.addActionListener(this::jRadioButton88ActionPerformed);

        jRadioButton89.setText("neutral");

        jRadioButton90.setText("Good");
        jRadioButton90.addActionListener(this::jRadioButton90ActionPerformed);

        jLabel46.setText("How easy was it to  book desired hotel ?");

        jLabel47.setText("How easy was it to  book desired hotel ?");

        jLabel48.setText("How easy was it to  book desired hotel ?");

        javax.swing.GroupLayout Feedback2Layout = new javax.swing.GroupLayout(Feedback2);
        Feedback2.setLayout(Feedback2Layout);
        Feedback2Layout.setHorizontalGroup(
                Feedback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Feedback2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(664, 664, 664))
                        .addGroup(Feedback2Layout.createSequentialGroup()
                                .addGroup(Feedback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addGroup(Feedback2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel42)
                                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(Feedback2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jRadioButton88,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton89,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton90,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton86,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton87,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(Feedback2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                                .addGap(99, 99, 99)
                                                                .addGroup(Feedback2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jRadioButton73,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton74,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton75,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton71,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton72,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel43,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 342,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(Feedback2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                                .addGap(165, 165, 165)
                                                                .addGroup(Feedback2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jRadioButton79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton84,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton69,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton76,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton81,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton66,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton77,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton67,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton82,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton85,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton80,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton70,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton68,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton78,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jRadioButton83,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                98,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel44)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel46)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel47)
                                                                .addGap(26, 26, 26)
                                                                .addComponent(jLabel48))))
                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 520,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(140, Short.MAX_VALUE)));
        Feedback2Layout.setVerticalGroup(
                Feedback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Feedback2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(Feedback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                .addGroup(Feedback2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel42)
                                                        .addComponent(jLabel43)
                                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel48))
                                                .addGap(3, 3, 3)
                                                .addComponent(jRadioButton88, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jRadioButton89, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jRadioButton90, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jRadioButton86, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jRadioButton87, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(jRadioButton73, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jRadioButton74, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jRadioButton75, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jRadioButton71, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jRadioButton72, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(Feedback2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jRadioButton68,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton78,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton83,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(Feedback2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jRadioButton79,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton84,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton69,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(Feedback2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jRadioButton85,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton80,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton70,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(Feedback2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jRadioButton76,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton81,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton66,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(Feedback2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jRadioButton77,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton67,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jRadioButton82,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(Feedback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addComponent(jButton5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25,
                                                        Short.MAX_VALUE))
                                        .addGroup(Feedback2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel45)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        jPanel5.setToolTipText("");
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/image/10d8b1005b02b4725cb47083f768dd41-660524290.jpg"))); // NOI18N
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 250));

        jLabel50.setText("Starting from EGP 5,500");
        jPanel7.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, -1, 20));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Apparthotel Sonnenhof");
        jPanel7.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 340, 330));

        jPanel10.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/HRG-Hilton-Plaza.jpg"))); // NOI18N
        jLabel18.setToolTipText("");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 250));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Apparthotel Sonnenhof");
        jPanel10.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        jLabel32.setText("Starting from EGP 5,500");
        jPanel10.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 340, 330));

        jPanel16.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/steigene.jpg"))); // NOI18N
        jPanel16.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 250));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Apparthotel Sonnenhof");
        jPanel16.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        jLabel49.setText("Starting from EGP 5,500");
        jPanel16.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));

        jPanel5.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, 340, 330));

        jPanel8.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/image/pexels-photo-1001965-1688326600 (1).jpg"))); // NOI18N
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 250));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Apparthotel Sonnenhof");
        jPanel8.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        jLabel54.setText("Starting from EGP 5,500");
        jPanel8.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, -1, -1));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, 330));

        bookhotel1.setText("Book");
        jPanel5.add(bookhotel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, -1, -1));

        bookhotel2.setText("Book");
        jPanel5.add(bookhotel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, -1, -1));

        bookhotel3.setText("Book");
        jPanel5.add(bookhotel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 350, -1, -1));

        bookhotel4.setText("Book");
        jPanel5.add(bookhotel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 350, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(31, 41, 55));
        jLabel6.setText("Hotels");

        jPanel1.setBackground(new java.awt.Color(0, 108, 228));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Find your next stay :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        regBtn.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        regBtn.setForeground(new java.awt.Color(0, 0, 204));
        regBtn.setText("Register");
        regBtn.addActionListener(this::regBtnActionPerformed);
        jPanel1.add(regBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 80, 40));

        loginBtn.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(0, 0, 204));
        loginBtn.setText("Login");
        loginBtn.addActionListener(this::loginBtnActionPerformed);
        jPanel1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 20, 80, 40));

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Choose your destination ");
        jTextField1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField1.setCaretColor(new java.awt.Color(255, 153, 0));
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jTextField2.setEditable(false);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Check out ");
        jTextField2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField2.setCaretColor(new java.awt.Color(255, 153, 0));
        jTextField2.addActionListener(this::jTextField2ActionPerformed);

        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("Room selection");
        jTextField3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField3.setCaretColor(new java.awt.Color(255, 153, 0));
        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        jTextField11.setEditable(false);
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setText("Check in ");
        jTextField11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField11.setCaretColor(new java.awt.Color(255, 153, 0));
        jTextField11.addActionListener(this::jTextField11ActionPerformed);

        jTextField12.setEditable(false);
        jTextField12.setText("Choose your desired city ");
        jTextField12.addActionListener(this::jTextField12ActionPerformed);

        jTextField13.setEditable(false);
        jTextField13.setText("Number of guests ");
        jTextField13.addActionListener(this::jTextField13ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92,
                                        Short.MAX_VALUE)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 88,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 83,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 105,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 131,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField13)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 1140, 50));

        jSpinner1.setModel(new javax.swing.SpinnerDateModel());
        jSpinner1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, 130, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "1 Adult", "1 Room", "2 Adults", "1 Room", "Big Family (2 Adults-2Kids)",
                        "Big Family (2 Adults-4 Kids)", "Standard", "Suite", "Deluxe" }));
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 130, 190, -1));

        welcomeUserLbl.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        welcomeUserLbl.setForeground(new java.awt.Color(255, 255, 255));
        welcomeUserLbl.setText("Welcome, Guest");
        jPanel1.add(welcomeUserLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 80, 120, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Japan", "Germany", "Italy",
                "Indonesia", "South Korea", "Spain", "USA", "South Africa", "UAE", "France", "Japan", "Turkey", "UK",
                "Czech Republic", "Netherlands", "Brazil", "Morocco", "New Zealand", "Iceland", "Singapore" }));
        jComboBox4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox4.addActionListener(this::jComboBox4ActionPerformed);
        jPanel1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 140, -1));

        jSpinner2.setModel(new javax.swing.SpinnerDateModel());
        jSpinner2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 130, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Japan", "Rome", "Dresden", "Bali",
                "Seoul", "Barcelona", "New York", "Cape town", "Dubai", "Paris", "Istanbul", "London", "Pargue",
                "Amsterdam", "Rio de Janeiro", "Marrakech", "Queenstown", "Reykjavik", "Singapore" }));
        jComboBox5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        jPanel1.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "11", "112", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jComboBox6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 130, -1, -1));

        userprofile.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        userprofile.setForeground(new java.awt.Color(0, 0, 255));
        userprofile.setText("Userprofile");
        userprofile.addActionListener(this::userprofileActionPerformed);
        jPanel1.add(userprofile, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 20, 100, 40));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(31, 41, 55));
        jLabel53.setText("Suites");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1424, Short.MAX_VALUE));
        jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
        jPanel31Layout.setVerticalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel31Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(14, Short.MAX_VALUE)));

        jPanel19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel19.setPreferredSize(new java.awt.Dimension(1420, 363));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel28.setBackground(new java.awt.Color(255, 51, 51));
        jPanel28.setPreferredSize(new java.awt.Dimension(340, 310));

        jLabel84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Suite4.jpg"))); // NOI18N

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Ocean View Suit");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel89.setText("EGP 8,500");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel84)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
        jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 240,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel89)));

        jPanel19.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, -1, -1));

        jPanel29.setBackground(new java.awt.Color(255, 0, 153));
        jPanel29.setPreferredSize(new java.awt.Dimension(340, 310));

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Suite1.jpg"))); // NOI18N
        jLabel83.setText("jLabel83");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Executive Suite");

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel90.setText("EGP 7,200");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 337,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 142,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92))
                        .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 238,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel90)
                                .addGap(0, 0, Short.MAX_VALUE)));

        jPanel19.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 8, -1, -1));

        jPanel30.setBackground(new java.awt.Color(255, 102, 102));
        jPanel30.setPreferredSize(new java.awt.Dimension(340, 310));

        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Suits2.jpg"))); // NOI18N

        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Panoramic Skyline Suite");

        jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel95.setText("EGP 12,500");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel86)
                                .addGap(55, 55, 55))
                        .addGroup(jPanel30Layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel95)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel30Layout.setVerticalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 240,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel86)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel95)
                                .addGap(0, 0, Short.MAX_VALUE)));

        jPanel19.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 8, -1, -1));

        jPanel32.setBackground(new java.awt.Color(153, 153, 0));
        jPanel32.setPreferredSize(new java.awt.Dimension(340, 310));

        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Suits3.jpg"))); // NOI18N
        jLabel87.setText("jLabel87");

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Grand Balcony Suite");

        jLabel103.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel103.setText("EGP 9,800");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel88))
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                                .addGap(139, 139, 139)
                                                .addComponent(jLabel103, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(134, 134, 134)));
        jPanel32Layout.setVerticalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel32Layout.createSequentialGroup()
                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 240,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel88)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)
                                .addGap(26, 26, 26)));

        jPanel19.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(1093, 8, 322, -1));

        booksuites1.setText("jButton10");
        booksuites1.addActionListener(this::booksuites1ActionPerformed);
        jPanel19.add(booksuites1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, -1, -1));

        booksuites2.setText("jButton11");
        jPanel19.add(booksuites2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, -1, -1));

        booksuites3.setText("jButton12");
        jPanel19.add(booksuites3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 330, -1, -1));

        booksuites4.setText("jButton13");
        jPanel19.add(booksuites4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 330, -1, -1));

        javax.swing.GroupLayout HOMELayout = new javax.swing.GroupLayout(HOME);
        HOME.setLayout(HOMELayout);
        HOMELayout.setHorizontalGroup(
                HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(HOMELayout.createSequentialGroup()
                                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 89,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 12, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HOMELayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                HOMELayout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                544, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE));
        HOMELayout.setVerticalGroup(
                HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(HOMELayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 382,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 391,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(379, 379, 379)
                                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        tabs.addTab("Home", HOME);

        jPanel12.setBackground(new java.awt.Color(16, 24, 23));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Credit card number :");

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("CVV :");

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Postal code");

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Expiray date");

        jButton2.setText("Pay now");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Credit card holder name");

        jTextField9.addActionListener(this::jTextField9ActionPerformed);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/card.png"))); // NOI18N

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/american-express.png"))); // NOI18N

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/visa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addGap(251, 251, 251)
                                                .addComponent(jButton2))
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addGroup(jPanel12Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                                .addComponent(jLabel26,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 139,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTextField9,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 225,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                                .addGroup(jPanel12Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                                                .addComponent(jLabel23,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        43,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTextField8,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        71,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(37, 37, 37)
                                                                                .addComponent(jLabel24)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTextField7,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        71,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                                                .addGap(6, 6, 6)
                                                                                .addComponent(jLabel22)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTextField6,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        190,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(226, 226, 226)
                                                                .addComponent(jLabel27)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel29,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 78,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel30,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 86,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                                .addComponent(jLabel25)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jComboBox2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jComboBox3,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel12Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 16,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel24)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel23))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel25)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel26))
                                .addGap(42, 42, 42)
                                .addComponent(jButton2)
                                .addContainerGap(123, Short.MAX_VALUE)));

        javax.swing.GroupLayout PAYMENTLayout = new javax.swing.GroupLayout(PAYMENT);
        PAYMENT.setLayout(PAYMENTLayout);
        PAYMENTLayout.setHorizontalGroup(
                PAYMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        PAYMENTLayout.setVerticalGroup(
                PAYMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PAYMENTLayout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 748, Short.MAX_VALUE)));

        tabs.addTab("Payment", PAYMENT);

        jPanel6.setPreferredSize(new java.awt.Dimension(900, 300));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hotel background.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 700,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE)));

        jLabel21.setText("Quality Assured");

        jLabel64.setText("Best Prices");

        jLabel65.setText("24/7 Support");

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/quality.png"))); // NOI18N
        jLabel67.setText("jLabel67");

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/best-price.png"))); // NOI18N

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/customer-service.png"))); // NOI18N
        jLabel69.setText("jLabel69");
        jLabel69.setPreferredSize(new java.awt.Dimension(91, 58));

        jTextArea4.setEditable(false);
        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setText(
                "Our Story\n \nOur mission is to build a top-tier hotel booking platform that makes travel an absolute breeze. We connect you to your dream \ndestinations by offering a seamless, hassle-free booking experience and  personalized options that make every trip\nunforgettable.\n\nOur vision is to redefine the travel industry through innovation and convenience. \nWe're dedicated to customizing your journey, helping you create amazing memories,\n and removing all the stress from planning your perfect getaway so you can focus on the adventure.\n");
        jScrollPane4.setViewportView(jTextArea4);

        jPanel23.setBackground(new java.awt.Color(255, 102, 0));
        jPanel23.setPreferredSize(new java.awt.Dimension(135, 20));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("500 +");

        jLabel78.setText("Hotels");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel70))
                                .addContainerGap()));
        jPanel23Layout.setVerticalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel70)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 14,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanel24.setBackground(new java.awt.Color(255, 102, 0));
        jPanel24.setPreferredSize(new java.awt.Dimension(135, 20));

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("20,000+");

        jLabel80.setText("Guests");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                                .addContainerGap(35, Short.MAX_VALUE)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel24Layout.createSequentialGroup()
                                                        .addComponent(jLabel76)
                                                        .addGap(30, 30, 30))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel24Layout.createSequentialGroup()
                                                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(43, 43, 43)))));
        jPanel24Layout.setVerticalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel80)
                                .addContainerGap()));

        jPanel26.setBackground(new java.awt.Color(255, 102, 0));
        jPanel26.setPreferredSize(new java.awt.Dimension(135, 20));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("50+");

        jLabel81.setText("Cities");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                                .addContainerGap(52, Short.MAX_VALUE)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)));
        jPanel26Layout.setVerticalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel26Layout.createSequentialGroup()
                                .addContainerGap(12, Short.MAX_VALUE)
                                .addComponent(jLabel77)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel81)
                                .addContainerGap()));

        jPanel27.setBackground(new java.awt.Color(255, 102, 0));
        jPanel27.setPreferredSize(new java.awt.Dimension(135, 20));

        jLabel79.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/star (1).png"))); // NOI18N
        jLabel79.setText("4.8");

        jLabel82.setText("Rating");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 65,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel27Layout.createSequentialGroup()
                                                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(8, 8, 8)))
                                .addContainerGap(36, Short.MAX_VALUE)));
        jPanel27Layout.setVerticalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel79)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel82)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanel13.setBackground(new java.awt.Color(51, 102, 255));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whatsapp (1).png"))); // NOI18N
        jLabel36.setText("jLabel36");

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/call icon 2026-04-23 115534.png"))); // NOI18N

        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/email.png"))); // NOI18N

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("+20 113 524 632");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("+20 122 658 395");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("support@booking.com");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel57)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel58)
                                        .addComponent(jLabel59)
                                        .addComponent(jLabel60))
                                .addGap(0, 59, Short.MAX_VALUE)));
        jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel56)
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel58)))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel36)
                                        .addComponent(jLabel59))
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel57)
                                                .addGap(118, 118, 118))
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel60)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)))));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70)
                                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(48, 48, 48)
                                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 1461,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                .addGroup(jPanel20Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                                .addGap(47, 47, 47)
                                                                .addGroup(jPanel20Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel67,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                91,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel21))
                                                                .addGap(150, 150, 150)
                                                                .addGroup(jPanel20Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel68,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                101,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                                                .addGap(6, 6, 6)
                                                                                .addComponent(jLabel64,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        64,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(122, 122, 122)
                                                                .addGroup(jPanel20Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel69,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel65,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                81,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jScrollPane4,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 690,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(123, 123, 123)
                                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(327, Short.MAX_VALUE)));
        jPanel20Layout.setVerticalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                .addGroup(jPanel20Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addGroup(jPanel20Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel68)
                                                                        .addComponent(jLabel67,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                58,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel20Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel64)
                                                                        .addComponent(jLabel21)))
                                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                                .addGap(11, 11, 11)
                                                                .addComponent(jLabel69,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel65)))
                                                .addGap(29, 29, 29)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 183,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 212,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(144, 144, 144)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 164,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addGroup(jPanel20Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout ABOUTUSLayout = new javax.swing.GroupLayout(ABOUTUS);
        ABOUTUS.setLayout(ABOUTUSLayout);
        ABOUTUSLayout.setHorizontalGroup(
                ABOUTUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ABOUTUSLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 700,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ABOUTUSLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
        ABOUTUSLayout.setVerticalGroup(
                ABOUTUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ABOUTUSLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 491,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 354, Short.MAX_VALUE)));

        tabs.addTab("About Us", ABOUTUS);

        javax.swing.GroupLayout MYBOOKINGSLayout = new javax.swing.GroupLayout(MYBOOKINGS);
        MYBOOKINGS.setLayout(MYBOOKINGSLayout);
        MYBOOKINGSLayout.setHorizontalGroup(
                MYBOOKINGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1432, Short.MAX_VALUE));
        MYBOOKINGSLayout.setVerticalGroup(
                MYBOOKINGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1157, Short.MAX_VALUE));

        tabs.addTab("My Bookings", MYBOOKINGS);

        Feedback1.setBackground(new java.awt.Color(255, 204, 255));
        Feedback1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Feedback Questions",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 51, 51))); // NOI18N
        Feedback1.setForeground(new java.awt.Color(204, 0, 0));

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(204, 0, 0));
        jLabel28.setText("How would you rate your overall experience with our service?");

        jLabel37.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 0, 0));
        jLabel37.setText("How likely are you to recommend us to a friend or colleague?");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel38.setText("Do you have any additional comments or suggestions on how we can improve?\"");

        commentTextArea.setColumns(20);
        commentTextArea.setRows(5);
        jScrollPane2.setViewportView(commentTextArea);

        jButton3.setText("Send");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jLabel39.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(204, 0, 0));
        jLabel39.setText("How would you rate the quality and comfort of your room?");

        jLabel40.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(204, 0, 0));
        jLabel40.setText("How easy and seamless was the booking process?\"");

        jLabel41.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 0, 0));
        jLabel41.setText("Booking Process ?");

        ratingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "5 - Excellent", "4 - Very Good", "3 - Good", "2 - Fair", "1 - Poor", " ", " " }));

        recommendationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "5 - Excellent", "4 - Very Good", "3 - Good", "2 - Fair", "1 - Poor", " ", " " }));

        roomQualityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "5 - Excellent", "4 - Very Good", "3 - Good", "2 - Fair", "1 - Poor", " ", " " }));

        bookingProcessComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "5 - Excellent", "4 - Very Good", "3 - Good", "2 - Fair", "1 - Poor", " ", " " }));

        javax.swing.GroupLayout Feedback1Layout = new javax.swing.GroupLayout(Feedback1);
        Feedback1.setLayout(Feedback1Layout);
        Feedback1Layout.setHorizontalGroup(
                Feedback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Feedback1Layout.createSequentialGroup()
                                .addGroup(Feedback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Feedback1Layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(jLabel28)
                                                .addGap(31, 31, 31)
                                                .addComponent(jLabel37)
                                                .addGap(56, 56, 56)
                                                .addComponent(jLabel39)
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel40)
                                                .addGap(351, 351, 351)
                                                .addComponent(jLabel41))
                                        .addGroup(Feedback1Layout.createSequentialGroup()
                                                .addGap(130, 130, 130)
                                                .addComponent(ratingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(224, 224, 224)
                                                .addComponent(recommendationComboBox,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(321, 321, 321)
                                                .addComponent(roomQualityComboBox,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(181, 181, 181)
                                                .addComponent(bookingProcessComboBox,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Feedback1Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel38))
                                        .addGroup(Feedback1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        Feedback1Layout.setVerticalGroup(
                Feedback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Feedback1Layout.createSequentialGroup()
                                .addGroup(Feedback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Feedback1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel41))
                                        .addGroup(Feedback1Layout.createSequentialGroup()
                                                .addGroup(Feedback1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel28)
                                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(Feedback1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(ratingComboBox,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(recommendationComboBox,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(roomQualityComboBox,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(bookingProcessComboBox,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(Feedback1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(Feedback1Layout.createSequentialGroup()
                                                                .addGap(126, 126, 126)
                                                                .addComponent(jLabel38)
                                                                .addGap(37, 37, 37)
                                                                .addComponent(jScrollPane2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 96,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(Feedback1Layout.createSequentialGroup()
                                                                .addGap(231, 231, 231)
                                                                .addComponent(jButton3)))))
                                .addGap(0, 796, Short.MAX_VALUE)));

        tabs.addTab("Feedback", Feedback1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1432,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(39, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1192,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton68ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton68ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton68ActionPerformed

    private void jRadioButton70ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton70ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton70ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed
        String feedback = jTextArea3.getText().trim();
        if (feedback.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please write your feedback before submitting.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Thank you for your feedback!");
        jTextArea3.setText("");
    }// GEN-LAST:event_jButton5ActionPerformed

    private void jRadioButton73ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton73ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton73ActionPerformed

    private void jRadioButton75ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton75ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton75ActionPerformed

    private void jRadioButton78ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton78ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton78ActionPerformed

    private void jRadioButton80ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton80ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton80ActionPerformed

    private void jRadioButton83ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton83ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton83ActionPerformed

    private void jRadioButton85ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton85ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton85ActionPerformed

    private void jRadioButton88ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton88ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton88ActionPerformed

    private void jRadioButton90ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton90ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jRadioButton90ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        String commentText = commentTextArea.getText().trim();

        try {
            Integer ratingVal = parseFeedbackRating(ratingComboBox.getSelectedItem());
            Integer recVal = parseFeedbackRating(recommendationComboBox.getSelectedItem());
            Integer roomVal = parseFeedbackRating(roomQualityComboBox.getSelectedItem());
            Integer bookingVal = parseFeedbackRating(bookingProcessComboBox.getSelectedItem());

            if (ratingVal == null || recVal == null || roomVal == null || bookingVal == null || commentText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please provide ratings for all questions and leave a comment.");
                return;
            }

            Feedback feedback = new Feedback();

            javax.persistence.EntityManager em = DatabaseUtil.createEntityManager();
            try {
                em.getTransaction().begin();

                if (this.loggedInUser != null) {
                    User managedUser = em.merge(this.loggedInUser);
                    feedback.setUser(managedUser);
                }

                feedback.setRating(ratingVal);
                feedback.setRecommendation(recVal);
                feedback.setRoomQuality(roomVal);
                feedback.setBookingProcess(bookingVal);
                feedback.setComment(commentText);
                feedback.setSubmittedAt(java.time.LocalDateTime.now());

                em.persist(feedback);
                em.getTransaction().commit();

                JOptionPane.showMessageDialog(this, "Thank you for your feedback!");
                commentTextArea.setText("");
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                JOptionPane.showMessageDialog(this, "Error saving feedback: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (em != null)
                    em.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred processing your feedback.");
        }
    }// GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // Strip dashes and spaces so users can type "1234-5678-9012-3456" or "1234 5678
        // 9012 3456"
        String cardNumber = jTextField6.getText().trim().replaceAll("[\\s\\-]", "");
        String cvv = jTextField8.getText().trim();
        String postalCode = jTextField7.getText().trim();
        String holderName = jTextField9.getText().trim();
        String expiryMonth = (String) jComboBox2.getSelectedItem();
        String expiryYear = (String) jComboBox3.getSelectedItem();

        // Validate required fields
        if (cardNumber.isEmpty() || cvv.isEmpty() || postalCode.isEmpty() || holderName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all payment fields.");
            return;
        }
        if (!holderName.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Credit card holder name can only contain English letters and spaces.");
            return;
        }
        if (!cardNumber.matches("^[0-9]{13,19}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid credit card number between 13 and 19 digits.");
            return;
        }
        // Edited by Tester
        if (cardNumber.matches("^0+$")){
                JOptionPane.showMessageDialog(this, "Please enter a valid credit card number. It cannot be all zeros.");
                return;
        }
        if (!cvv.matches("^[0-9]{3,4}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid CVV.");
            return;
        }
        // Edited by Tester
        if (cvv.matches("^0+$")){
                JOptionPane.showMessageDialog(this, "Please enter a valid CVV. It cannot be all zeros.");
                return;
        }
        if (postalCode.matches("^0+$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid postal code. It cannot be all zeros.");
            return;
        }

        // Save payment to database
        javax.persistence.EntityManager em = null;
        try {
            em = DatabaseUtil.createEntityManager();
            em.getTransaction().begin();

            Payment payment = new Payment();
            payment.setCardNumber(cardNumber);
            payment.setCvv(cvv);
            payment.setPostalCode(postalCode);
            payment.setExpiryMonth(expiryMonth);
            payment.setExpiryYear(expiryYear);
            payment.setCardHolderName(holderName);
            payment.setPaymentDate(java.time.LocalDateTime.now());

            // Link to logged-in user if available
            if (loggedInUser != null) {
                // Merge detached user into current session to avoid DetachedEntityException
                User managedUser = em.merge(loggedInUser);
                payment.setUser(managedUser);
            }

            em.persist(payment);
            em.getTransaction().commit();

            // Clear form fields
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField9.setText("");

            // If this payment was triggered from a booking card, remove that booking
            if (pendingPaymentBookingId != -1) {
                javax.persistence.EntityManager em2 = null;
                try {
                    em2 = DatabaseUtil.createEntityManager();
                    em2.getTransaction().begin();
                    Booking paidBooking = em2.find(Booking.class, pendingPaymentBookingId);
                    if (paidBooking != null) {
                        paidBooking.setStatus("Paid");
                    }
                    em2.getTransaction().commit();
                } catch (Exception ex2) {
                    if (em2 != null && em2.getTransaction().isActive())
                        em2.getTransaction().rollback();
                    ex2.printStackTrace();
                } finally {
                    if (em2 != null)
                        em2.close();
                }
                pendingPaymentBookingId = -1; // reset sentinel

                // Rebuild the bookings panel so the paid card disappears
                buildDynamicBookingsPanel();

                // Show confirmation and navigate back to My Bookings
                JOptionPane.showMessageDialog(this,
                        "\u2705  Payment successful!\n\nThank you, " + holderName + "!\n" +
                                "Your booking has been confirmed and paid.",
                        "Payment Confirmed",
                        JOptionPane.INFORMATION_MESSAGE);
                tabs.setSelectedComponent(MYBOOKINGS);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Payment saved successfully!\nThank you, " + holderName + "!",
                        "Payment Saved",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(this, "Error saving payment: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null)
                em.close();
        }
    }// GEN-LAST:event_jButton2ActionPerformed

    private void booksuites1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_booksuites1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_booksuites1ActionPerformed

    private void userprofileActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_userprofileActionPerformed
        // --- Open User Profile directly from database ---
        String inputUsername = javax.swing.JOptionPane.showInputDialog(
                this,
                "Enter your username to load your profile:",
                "Load User Profile",
                javax.swing.JOptionPane.PLAIN_MESSAGE);

        if (inputUsername == null || inputUsername.trim().isEmpty()) {
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
                javax.swing.JOptionPane.showMessageDialog(this,
                        "User '" + inputUsername.trim() + "' not found in the database.",
                        "User Not Found",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            new USERPROFILE(results.get(0)).setVisible(true);
            dispose();

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error loading user: " + ex.getMessage(),
                    "Database Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        } finally {
            if (em != null) {
                try {
                    em.close();
                } catch (Exception ignored) {
                }
            }
        }
    }// GEN-LAST:event_userprofileActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox4ActionPerformed
        String country = (String) jComboBox4.getSelectedItem();
        if (country == null)
            return;
        String[] cities;
        switch (country) {
            case "Japan":
                cities = new String[] { "Tokyo", "Kyoto", "Osaka" };
                break;
            case "Germany":
                cities = new String[] { "Berlin", "Dresden", "Munich" };
                break;
            case "Italy":
                cities = new String[] { "Rome", "Milan", "Venice" };
                break;
            case "Indonesia":
                cities = new String[] { "Bali", "Jakarta" };
                break;
            case "South Korea":
                cities = new String[] { "Seoul", "Busan" };
                break;
            case "Spain":
                cities = new String[] { "Barcelona", "Madrid" };
                break;
            case "USA":
                cities = new String[] { "New York", "Los Angeles", "Chicago" };
                break;
            case "South Africa":
                cities = new String[] { "Cape Town", "Johannesburg" };
                break;
            case "UAE":
                cities = new String[] { "Dubai", "Abu Dhabi" };
                break;
            case "France":
                cities = new String[] { "Paris", "Lyon" };
                break;
            case "Turkey":
                cities = new String[] { "Istanbul", "Ankara" };
                break;
            case "UK":
                cities = new String[] { "London", "Manchester" };
                break;
            case "Czech Republic":
                cities = new String[] { "Prague", "Brno" };
                break;
            case "Netherlands":
                cities = new String[] { "Amsterdam", "Rotterdam" };
                break;
            case "Brazil":
                cities = new String[] { "Rio de Janeiro", "São Paulo" };
                break;
            case "Morocco":
                cities = new String[] { "Marrakech", "Casablanca" };
                break;
            case "New Zealand":
                cities = new String[] { "Queenstown", "Auckland" };
                break;
            case "Iceland":
                cities = new String[] { "Reykjavik" };
                break;
            case "Singapore":
                cities = new String[] { "Singapore" };
                break;
            default:
                cities = new String[] { "Select City" };
                break;
        }
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(cities));
    }// GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField1ActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loginBtnActionPerformed

        new Loginform().setVisible(true);
        this.dispose(); // Close homepage to go to login // TODO add ynew Loginform().setVisible(true);
    }// GEN-LAST:event_loginBtnActionPerformed

    private void regBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_regBtnActionPerformed

        new Registrationform().setVisible(true);
        this.dispose();
    }// GEN-LAST:event_regBtnActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField9ActionPerformed

    private Integer parseFeedbackRating(Object item) {
        if (item == null)
            return null;
        String s = item.toString().trim();
        if (s.isEmpty())
            return null;
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("\\d+").matcher(s);
        if (m.find()) {
            return Integer.parseInt(m.group());
        }
        return null;
    }

    // --- Helper methods for dynamic UI components ---

    private javax.swing.JPanel createAboutUsPanel() {
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout(0, 0));
        panel.setBackground(new java.awt.Color(249, 250, 251));

        // Header
        javax.swing.JPanel header = new javax.swing.JPanel(new java.awt.BorderLayout());
        header.setBackground(new java.awt.Color(0, 108, 228));
        header.setPreferredSize(new java.awt.Dimension(0, 80));
        javax.swing.JLabel title = new javax.swing.JLabel("About Us", javax.swing.SwingConstants.CENTER);
        title.setFont(new java.awt.Font("Segoe UI", 1, 28));
        title.setForeground(java.awt.Color.WHITE);
        header.add(title, java.awt.BorderLayout.CENTER);
        panel.add(header, java.awt.BorderLayout.NORTH);

        return panel;

    };

    private void addViewDealButton(javax.swing.JPanel card) {
        javax.swing.JButton btn = new javax.swing.JButton("View Deal");
        btn.setFont(new java.awt.Font("Segoe UI", 1, 11));
        btn.setForeground(java.awt.Color.WHITE);
        btn.setBackground(new java.awt.Color(217, 119, 6));
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.addActionListener(evt -> tabs.setSelectedComponent(PAYMENT));
        card.add(btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 100, 25));
    };

    private javax.swing.JPanel createRoomsPanel() {
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout(0, 0));
        panel.setBackground(new java.awt.Color(245, 245, 245));

        // Header
        javax.swing.JPanel header = new javax.swing.JPanel(new java.awt.BorderLayout());
        header.setBackground(new java.awt.Color(0, 108, 228));
        header.setPreferredSize(new java.awt.Dimension(0, 80));
        javax.swing.JLabel title = new javax.swing.JLabel("Rooms & Suites", javax.swing.SwingConstants.CENTER);
        title.setFont(new java.awt.Font("Segoe UI", 1, 28));
        title.setForeground(java.awt.Color.WHITE);
        header.add(title, java.awt.BorderLayout.CENTER);
        panel.add(header, java.awt.BorderLayout.NORTH);

        // Room cards container
        javax.swing.JPanel cards = new javax.swing.JPanel(new java.awt.GridLayout(1, 3, 20, 0));
        cards.setBackground(new java.awt.Color(245, 245, 245));
        cards.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 40, 30, 40));

        String[][] rooms = {
                { "Standard Room", "EGP 2,500 / night",
                        "A cozy and comfortable room with all essential amenities for a relaxing stay.",
                        "\u2713 Free Wi-Fi  \u2713 Air Conditioning  \u2713 TV  \u2713 Mini Fridge" },
                { "Deluxe Room", "EGP 4,800 / night",
                        "Spacious room with premium furnishings, city view, and enhanced comfort.",
                        "\u2713 King Bed  \u2713 City View  \u2713 Room Service  \u2713 Bathtub  \u2713 Free Wi-Fi" },
                { "Presidential Suite", "EGP 12,000 / night",
                        "The ultimate luxury experience with a private lounge, jacuzzi, and panoramic views.",
                        "\u2713 Living Room  \u2713 Jacuzzi  \u2713 Butler Service  \u2713 Panoramic View  \u2713 VIP Amenities" }
        };
        java.awt.Color[] accents = {
                new java.awt.Color(59, 130, 246),
                new java.awt.Color(217, 119, 6),
                new java.awt.Color(139, 92, 246)
        };

        for (int i = 0; i < rooms.length; i++) {
            javax.swing.JPanel card = new javax.swing.JPanel();
            card.setBackground(java.awt.Color.WHITE);
            card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
            card.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 231, 235), 1),
                    javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25)));

            // Color accent bar
            javax.swing.JPanel accent = new javax.swing.JPanel();
            accent.setBackground(accents[i]);
            accent.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 5));
            accent.setPreferredSize(new java.awt.Dimension(0, 5));
            accent.setAlignmentX(0.0f);
            card.add(accent);
            card.add(javax.swing.Box.createVerticalStrut(15));

            javax.swing.JLabel name = new javax.swing.JLabel(rooms[i][0]);
            name.setFont(new java.awt.Font("Segoe UI", 1, 20));
            name.setForeground(new java.awt.Color(31, 41, 55));
            name.setAlignmentX(0.0f);
            card.add(name);
            card.add(javax.swing.Box.createVerticalStrut(5));

            javax.swing.JLabel price = new javax.swing.JLabel(rooms[i][1]);
            price.setFont(new java.awt.Font("Segoe UI", 1, 16));
            price.setForeground(accents[i]);
            price.setAlignmentX(0.0f);
            card.add(price);
            card.add(javax.swing.Box.createVerticalStrut(15));

            javax.swing.JTextArea desc = new javax.swing.JTextArea(rooms[i][2]);
            desc.setFont(new java.awt.Font("Segoe UI", 0, 14));
            desc.setForeground(new java.awt.Color(107, 114, 128));
            desc.setLineWrap(true);
            desc.setWrapStyleWord(true);
            desc.setEditable(false);
            desc.setBackground(java.awt.Color.WHITE);
            desc.setAlignmentX(0.0f);
            card.add(desc);
            card.add(javax.swing.Box.createVerticalStrut(15));

            javax.swing.JLabel amenLabel = new javax.swing.JLabel("Amenities:");
            amenLabel.setFont(new java.awt.Font("Segoe UI", 1, 13));
            amenLabel.setForeground(new java.awt.Color(55, 65, 81));
            amenLabel.setAlignmentX(0.0f);
            card.add(amenLabel);
            card.add(javax.swing.Box.createVerticalStrut(5));

            javax.swing.JTextArea amenText = new javax.swing.JTextArea(rooms[i][3]);
            amenText.setFont(new java.awt.Font("Segoe UI", 0, 12));
            amenText.setForeground(new java.awt.Color(75, 85, 99));
            amenText.setLineWrap(true);
            amenText.setWrapStyleWord(true);
            amenText.setEditable(false);
            amenText.setBackground(java.awt.Color.WHITE);
            amenText.setAlignmentX(0.0f);
            card.add(amenText);
            card.add(javax.swing.Box.createVerticalStrut(20));

            javax.swing.JButton bookBtn = new javax.swing.JButton("Book Now");
            bookBtn.setFont(new java.awt.Font("Segoe UI", 1, 14));
            bookBtn.setForeground(java.awt.Color.WHITE);
            bookBtn.setBackground(accents[i]);
            bookBtn.setFocusPainted(false);
            bookBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            bookBtn.setAlignmentX(0.0f);
            bookBtn.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 40));
            final String roomName = rooms[i][0];
            bookBtn.addActionListener(evt -> {
                tabs.setSelectedComponent(PAYMENT);
                JOptionPane.showMessageDialog(this, "Selected: " + roomName + "\nPlease complete payment details.");
            });
            card.add(bookBtn);

            cards.add(card);
        }

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(cards);
        scroll.setBorder(null);
        panel.add(scroll, java.awt.BorderLayout.CENTER);

        return panel;
    }

    private javax.swing.JPanel createBookingsPanel() {
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout(0, 0));
        panel.setBackground(new java.awt.Color(245, 245, 245));

        // Header
        javax.swing.JPanel header = new javax.swing.JPanel(new java.awt.BorderLayout());
        header.setBackground(new java.awt.Color(0, 108, 228));
        header.setPreferredSize(new java.awt.Dimension(0, 80));
        javax.swing.JLabel title = new javax.swing.JLabel("My Bookings", javax.swing.SwingConstants.CENTER);
        title.setFont(new java.awt.Font("Segoe UI", 1, 28));
        title.setForeground(java.awt.Color.WHITE);
        header.add(title, java.awt.BorderLayout.CENTER);
        panel.add(header, java.awt.BorderLayout.NORTH);

        // Bookings table
        String[] columns = { "Booking ID", "Hotel", "Room Type", "Check-in", "Check-out", "Guests", "Status", "Total" };
        Object[][] data = {
                { "BK-1001", "Sunrise Resort & Spa", "Deluxe Room", "2026-05-15", "2026-05-18", "2 Adults", "Confirmed",
                        "EGP 14,400" },
                { "BK-1002", "Hilton Plaza Hotel", "Standard Room", "2026-04-20", "2026-04-22", "1 Adult", "Completed",
                        "EGP 5,000" },
                { "BK-1003", "Steigenberger Pyramids", "Presidential Suite", "2026-06-01", "2026-06-05",
                        "2 Adults, 1 Child", "Pending", "EGP 48,000" },
        };

        javax.swing.JTable table = new javax.swing.JTable(data, columns);
        table.setFont(new java.awt.Font("Segoe UI", 0, 14));
        table.setRowHeight(40);
        table.setGridColor(new java.awt.Color(229, 231, 235));
        table.setSelectionBackground(new java.awt.Color(219, 234, 254));
        table.setSelectionForeground(new java.awt.Color(31, 41, 55));
        table.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 14));
        table.getTableHeader().setBackground(new java.awt.Color(31, 41, 55));
        table.getTableHeader().setForeground(java.awt.Color.WHITE);
        table.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 45));
        table.setEnabled(false);

        // Status column coloring
        table.getColumnModel().getColumn(6).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                String status = value != null ? value.toString() : "";
                if ("Confirmed".equals(status))
                    setForeground(new java.awt.Color(22, 163, 74));
                else if ("Pending".equals(status))
                    setForeground(new java.awt.Color(217, 119, 6));
                else if ("Completed".equals(status))
                    setForeground(new java.awt.Color(107, 114, 128));
                else
                    setForeground(java.awt.Color.BLACK);
                setFont(new java.awt.Font("Segoe UI", 1, 14));
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                return this;
            }
        });

        javax.swing.JScrollPane tableScroll = new javax.swing.JScrollPane(table);
        tableScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 10, 40));
        panel.add(tableScroll, java.awt.BorderLayout.CENTER);

        // Summary bar
        javax.swing.JPanel summary = new javax.swing.JPanel(
                new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 60, 15));
        summary.setBackground(java.awt.Color.WHITE);
        summary.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(229, 231, 235)));
        String[][] stats = { { "Total Bookings", "3" }, { "Upcoming", "2" }, { "Completed", "1" },
                { "Total Spent", "EGP 67,400" } };
        for (String[] stat : stats) {
            javax.swing.JPanel statCard = new javax.swing.JPanel();
            statCard.setBackground(java.awt.Color.WHITE);
            statCard.setLayout(new javax.swing.BoxLayout(statCard, javax.swing.BoxLayout.Y_AXIS));
            javax.swing.JLabel val = new javax.swing.JLabel(stat[1]);
            val.setFont(new java.awt.Font("Segoe UI", 1, 22));
            val.setForeground(new java.awt.Color(0, 108, 228));
            val.setAlignmentX(0.5f);
            javax.swing.JLabel lbl = new javax.swing.JLabel(stat[0]);
            lbl.setFont(new java.awt.Font("Segoe UI", 0, 13));
            lbl.setForeground(new java.awt.Color(107, 114, 128));
            lbl.setAlignmentX(0.5f);
            statCard.add(val);
            statCard.add(lbl);
            summary.add(statCard);
        }
        panel.add(summary, java.awt.BorderLayout.SOUTH);

        return panel;
    }

    // =========================================================================
    // Hotel catalogue helpers
    // =========================================================================

    /**
     * Synchronises the static hotel-card labels (name + starting price)
     * with the Hotel catalogue objects so every card reflects real data.
     */
    private void updateHotelCardLabels() {
        // --- Hotels section ---
        // Card 1 (jPanel8, leftmost)
        jLabel52.setText(hotelCatalogue[0].getName());
        jLabel54.setText("Starting from EGP " + String.format("%,.0f", hotelCatalogue[0].getStartingPrice()));
        // Card 2 (jPanel7)
        jLabel51.setText(hotelCatalogue[1].getName());
        jLabel50.setText("Starting from EGP " + String.format("%,.0f", hotelCatalogue[1].getStartingPrice()));
        // Card 3 (jPanel10)
        jLabel33.setText(hotelCatalogue[2].getName());
        jLabel32.setText("Starting from EGP " + String.format("%,.0f", hotelCatalogue[2].getStartingPrice()));
        // Card 4 (jPanel16)
        jLabel35.setText(hotelCatalogue[3].getName());
        jLabel49.setText("Starting from EGP " + String.format("%,.0f", hotelCatalogue[3].getStartingPrice()));

        // --- Suites section: fix placeholder button labels ---
        booksuites1.setText("Book");
        booksuites2.setText("Book");
        booksuites3.setText("Book");
        booksuites4.setText("Book");
    }

    /**
     * Attaches ActionListeners to all eight Book buttons.
     * Each button books the hotel/room that is visually shown on its card.
     */
    private void wireBookButtons() {
        // Hotels – each card shows the hotel's featured room
        bookhotel1.addActionListener(e -> handleBooking(
                hotelCatalogue[0], hotelCatalogue[0].getRooms().get(0)));
        bookhotel2.addActionListener(e -> handleBooking(
                hotelCatalogue[1], hotelCatalogue[1].getRooms().get(1)));
        bookhotel3.addActionListener(e -> handleBooking(
                hotelCatalogue[2], hotelCatalogue[2].getRooms().get(0)));
        bookhotel4.addActionListener(e -> handleBooking(
                hotelCatalogue[3], hotelCatalogue[3].getRooms().get(1)));

        // Suites – each suite catalogue entry has exactly one room
        booksuites1.addActionListener(e -> handleBooking(
                suiteCatalogue[0], suiteCatalogue[0].getRooms().get(0)));
        booksuites2.addActionListener(e -> handleBooking(
                suiteCatalogue[1], suiteCatalogue[1].getRooms().get(0)));
        booksuites3.addActionListener(e -> handleBooking(
                suiteCatalogue[2], suiteCatalogue[2].getRooms().get(0)));
        booksuites4.addActionListener(e -> handleBooking(
                suiteCatalogue[3], suiteCatalogue[3].getRooms().get(0)));
    }

    /**
     * Replaces the static My Bookings tab content with a dynamic,
     * scrollable container that booking cards are added to at runtime.
     * It fetches the data from the database.
     */
    private void buildDynamicBookingsPanel() {
        MYBOOKINGS.removeAll();
        MYBOOKINGS.setLayout(new java.awt.BorderLayout());
        MYBOOKINGS.setBackground(new java.awt.Color(245, 247, 250));

        // Header bar
        javax.swing.JPanel header = new javax.swing.JPanel(new java.awt.BorderLayout());
        header.setBackground(new java.awt.Color(0, 51, 102));
        header.setPreferredSize(new java.awt.Dimension(0, 80));
        javax.swing.JLabel title = new javax.swing.JLabel("  My Bookings", javax.swing.SwingConstants.LEFT);
        title.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
        title.setForeground(java.awt.Color.WHITE);
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0));
        header.add(title, java.awt.BorderLayout.CENTER);
        MYBOOKINGS.add(header, java.awt.BorderLayout.NORTH);

        // Scrollable cards container
        bookingsContainer = new javax.swing.JPanel();
        bookingsContainer.setLayout(new java.awt.GridBagLayout());
        bookingsContainer.setBackground(new java.awt.Color(245, 247, 250));
        bookingsContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 20, 30));

        java.awt.GridBagConstraints gc = new java.awt.GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1.0;
        gc.fill = java.awt.GridBagConstraints.NONE;
        gc.anchor = java.awt.GridBagConstraints.NORTH;
        gc.insets = new java.awt.Insets(0, 0, 15, 0);

        boolean hasBookings = false;

        // Fetch bookings from DB
        if (loggedInUser != null) {
            javax.persistence.EntityManager em = null;
            java.util.List<Booking> userBookings = null;
            try {
                em = DatabaseUtil.createEntityManager();
                userBookings = em
                        .createQuery("SELECT b FROM Booking b WHERE b.user.id = :userId ORDER BY b.id DESC",
                                Booking.class)
                        .setParameter("userId", loggedInUser.getId())
                        .getResultList();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (em != null)
                    em.close();
            }

            if (userBookings != null && !userBookings.isEmpty()) {
                hasBookings = true;
                for (Booking b : userBookings) {
                    bookingsContainer.add(createBookingCard(b), gc);
                    gc.gridy++;
                }
            }
        }

        if (!hasBookings) {
            javax.swing.JLabel emptyLbl = new javax.swing.JLabel(
                    "<html><center>No bookings yet.<br>" +
                            "Click <b>Book</b> on a hotel or suite card to get started!</center></html>",
                    javax.swing.SwingConstants.CENTER);
            emptyLbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
            emptyLbl.setForeground(new java.awt.Color(150, 150, 160));
            bookingsContainer.add(emptyLbl, gc);
            gc.gridy++;
        }

        // Wrap inside a BorderLayout panel to allow vertical expansion without limits
        javax.swing.JPanel scrollContent = new javax.swing.JPanel(new java.awt.BorderLayout());
        scrollContent.setBackground(new java.awt.Color(245, 247, 250));
        scrollContent.add(bookingsContainer, java.awt.BorderLayout.NORTH);

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(scrollContent);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        MYBOOKINGS.add(scroll, java.awt.BorderLayout.CENTER);

        MYBOOKINGS.revalidate();
        MYBOOKINGS.repaint();
    }

    /**
     * Reads every form field (country, city, dates, room-type selection,
     * guests), calculates the total price, then creates and prepends a
     * booking card inside the My Bookings pane.
     */
    private void handleBooking(Hotel hotel, HotelRoom room) {
        // --- Read form fields ---
        String country = (String) jComboBox4.getSelectedItem();
        String city = (String) jComboBox5.getSelectedItem();
        String roomSel = (String) jComboBox1.getSelectedItem();
        String guests = (String) jComboBox6.getSelectedItem();

        if (country == null || country.trim().isEmpty() || country.equals("Choose your destination")) {
            JOptionPane.showMessageDialog(this, "Please select a valid destination country.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (city == null || city.trim().isEmpty() || city.equals("Select City")
                || city.equals("Choose your desired city")) {
            JOptionPane.showMessageDialog(this, "Please select a valid city.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (roomSel == null || roomSel.trim().isEmpty() || roomSel.equals("Room selection")) {
            JOptionPane.showMessageDialog(this, "Please select a room type.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (guests == null || guests.trim().isEmpty() || guests.equals("0")) {
            JOptionPane.showMessageDialog(this, "Please select the number of guests.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        java.util.Date checkInDate = (java.util.Date) jSpinner2.getValue();
        java.util.Date checkOutDate = (java.util.Date) jSpinner1.getValue();

        if (checkOutDate.before(checkInDate) || checkOutDate.equals(checkInDate)) {
            JOptionPane.showMessageDialog(this, "Check-out date must be after check-in date.", "Invalid Timing",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MMM yyyy");
        String checkIn = sdf.format(checkInDate);
        String checkOut = sdf.format(checkOutDate);

        long diffMs = checkOutDate.getTime() - checkInDate.getTime();
        int nights = (int) Math.max(1, Math.round((double) diffMs / (1000L * 60 * 60 * 24)));
        double totalPrice = nights * room.getPricePerNight();

        // --- Save to Database ---
        if (loggedInUser != null) {
            Booking booking = new Booking();
            booking.setUser(loggedInUser);
            booking.setHotelName(hotel.getName());
            booking.setRoomTypeSelection(roomSel);
            // Edited by Tester
            booking.setRoomName(roomSel);
            booking.setDestinationCountry(country);
            booking.setCity(city);
            booking.setCheckInDate(checkIn);
            booking.setCheckOutDate(checkOut);
            booking.setGuests(guests);
            booking.setNights(nights);
            booking.setTotalPrice(totalPrice);
            booking.setStatus("Confirmed");
            booking.setBookingDate(java.time.LocalDateTime.now());

            javax.persistence.EntityManager em = null;
            try {
                em = DatabaseUtil.createEntityManager();
                em.getTransaction().begin();

                User managedUser = em.merge(loggedInUser);
                booking.setUser(managedUser);

                em.persist(booking);
                em.getTransaction().commit();
            } catch (Exception ex) {
                if (em != null && em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                JOptionPane.showMessageDialog(this, "Failed to save booking to database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
                return;
            } finally {
                if (em != null)
                    em.close();
            }
        }

        // --- Rebuild the bookings panel to reflect the DB ---
        buildDynamicBookingsPanel();
        tabs.setSelectedComponent(MYBOOKINGS);

        JOptionPane.showMessageDialog(this,
                "Booking confirmed!\n\n" +
                        "Hotel: " + hotel.getName() + "\n" +
                        "Room: " + room.getRoomName() + "\n" +
                        "Location: " + city + ", " + country + "\n" +
                        "Check-in: " + checkIn + "\n" +
                        "Check-out: " + checkOut + "\n" +
                        "Guests: " + guests + "\n" +
                        "Duration: " + nights + " night(s)" + "\n" +
                        "Total: EGP " + String.format("%,.0f", totalPrice),
                "Booking Confirmed",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Builds a premium booking card.
     */
    private javax.swing.JPanel createBookingCard(Booking b) {
        // ── Card outer shell ─────────────────────────────────────────────
        javax.swing.JPanel card = new javax.swing.JPanel(new java.awt.BorderLayout(0, 0));
        card.setBackground(java.awt.Color.WHITE);
        card.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(218, 224, 236), 1),
                javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        card.setMaximumSize(new java.awt.Dimension(750, 180));
        card.setPreferredSize(new java.awt.Dimension(750, 180));

        // Left accent bar (brand blue)
        javax.swing.JPanel accentBar = new javax.swing.JPanel();
        accentBar.setBackground(new java.awt.Color(0, 108, 228));
        accentBar.setPreferredSize(new java.awt.Dimension(7, 0));
        card.add(accentBar, java.awt.BorderLayout.WEST);

        // ── Centre content ───────────────────────────────────────────────
        javax.swing.JPanel content = new javax.swing.JPanel(new java.awt.GridBagLayout());
        content.setBackground(java.awt.Color.WHITE);
        content.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 18, 14, 18));
        java.awt.GridBagConstraints g = new java.awt.GridBagConstraints();
        g.insets = new java.awt.Insets(2, 4, 2, 4);
        g.anchor = java.awt.GridBagConstraints.WEST;

        // Row 0 – hotel name (bold, large)
        javax.swing.JLabel hotelNameLbl = new javax.swing.JLabel(b.getHotelName());
        hotelNameLbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 17));
        hotelNameLbl.setForeground(new java.awt.Color(20, 30, 50));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 4;
        g.weightx = 1.0;
        content.add(hotelNameLbl, g);

        // Row 0 right – CONFIRMED badge
        javax.swing.JLabel badge = new javax.swing.JLabel(" CONFIRMED ");
        badge.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 10));
        badge.setForeground(java.awt.Color.WHITE);
        badge.setBackground(new java.awt.Color(22, 163, 74));
        badge.setOpaque(true);
        badge.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 7, 3, 7));
        g.gridx = 4;
        g.gridy = 0;
        g.gridwidth = 1;
        g.weightx = 0;
        g.anchor = java.awt.GridBagConstraints.EAST;
        content.add(badge, g);
        g.anchor = java.awt.GridBagConstraints.WEST;

        // Row 1 – room name in accent colour
        javax.swing.JLabel roomLbl = new javax.swing.JLabel("Room: " + b.getRoomName());
        roomLbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        roomLbl.setForeground(new java.awt.Color(0, 108, 228));
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 5;
        g.weightx = 1.0;
        content.add(roomLbl, g);

        // Row 2 – info chips
        javax.swing.JPanel chips = new javax.swing.JPanel(new java.awt.FlowLayout(
                java.awt.FlowLayout.LEFT, 6, 0));
        chips.setBackground(java.awt.Color.WHITE);
        String[] chipLabels = {
                b.getDestinationCountry() + ", " + b.getCity(),
                "In: " + b.getCheckInDate(),
                "Out: " + b.getCheckOutDate(),
                b.getGuests() + " guest(s)",
                b.getNights() + " night(s)",
                "Rm type: " + b.getRoomTypeSelection()
        };
        java.awt.Color chipBg = new java.awt.Color(246, 248, 255);
        java.awt.Color chipFg = new java.awt.Color(65, 80, 110);
        java.awt.Color chipBdr = new java.awt.Color(213, 219, 235);
        for (String txt : chipLabels) {
            javax.swing.JLabel chip = new javax.swing.JLabel(txt);
            chip.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
            chip.setForeground(chipFg);
            chip.setBackground(chipBg);
            chip.setOpaque(true);
            chip.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(chipBdr, 1),
                    javax.swing.BorderFactory.createEmptyBorder(3, 7, 3, 7)));
            chips.add(chip);
        }
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 5;
        g.weightx = 1.0;
        content.add(chips, g);

        card.add(content, java.awt.BorderLayout.CENTER);

        // ── Right price panel ────────────────────────────────────────────
        javax.swing.JPanel pricePanel = new javax.swing.JPanel();
        pricePanel.setLayout(new javax.swing.BoxLayout(pricePanel, javax.swing.BoxLayout.Y_AXIS));
        pricePanel.setBackground(new java.awt.Color(246, 248, 255));
        pricePanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(218, 224, 236)),
                javax.swing.BorderFactory.createEmptyBorder(14, 18, 14, 18)));
        pricePanel.setPreferredSize(new java.awt.Dimension(190, 0));

        double ppn = b.getTotalPrice() / Math.max(1, b.getNights());
        javax.swing.JLabel perNightLbl = new javax.swing.JLabel(
                "EGP " + String.format("%,.0f", ppn));
        perNightLbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15));
        perNightLbl.setForeground(new java.awt.Color(0, 108, 228));
        perNightLbl.setAlignmentX(0.5f);

        javax.swing.JLabel perNightTxt = new javax.swing.JLabel("per night");
        perNightTxt.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        perNightTxt.setForeground(new java.awt.Color(130, 140, 160));
        perNightTxt.setAlignmentX(0.5f);

        javax.swing.JSeparator sep = new javax.swing.JSeparator();
        sep.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new java.awt.Color(218, 224, 236));

        javax.swing.JLabel totalTxt = new javax.swing.JLabel("TOTAL");
        totalTxt.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 10));
        totalTxt.setForeground(new java.awt.Color(130, 140, 160));
        totalTxt.setAlignmentX(0.5f);

        javax.swing.JLabel totalLbl = new javax.swing.JLabel(
                "EGP " + String.format("%,.0f", b.getTotalPrice()));
        totalLbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 19));
        totalLbl.setForeground(new java.awt.Color(217, 119, 6));
        totalLbl.setAlignmentX(0.5f);

        // Buttons Panel
        javax.swing.JPanel btnPanel = new javax.swing.JPanel();
        btnPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        btnPanel.setBackground(new java.awt.Color(246, 248, 255));

        javax.swing.JButton payBtn = new javax.swing.JButton();
        if ("Paid".equalsIgnoreCase(b.getStatus())) {
            payBtn.setText("Paid");
            payBtn.setBackground(new java.awt.Color(156, 163, 175)); // Gray
            payBtn.setEnabled(false);
        } else {
            payBtn.setText("Pay");
            payBtn.setBackground(new java.awt.Color(22, 163, 74)); // Green
            payBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            payBtn.addActionListener(e -> {
                // Remember which booking this payment belongs to
                pendingPaymentBookingId = b.getId();
                tabs.setSelectedComponent(PAYMENT);
            });
        }
        payBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        payBtn.setForeground(java.awt.Color.WHITE);
        payBtn.setFocusPainted(false);

        javax.swing.JButton removeBtn = new javax.swing.JButton("Remove");
        removeBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        removeBtn.setForeground(java.awt.Color.WHITE);
        removeBtn.setBackground(new java.awt.Color(220, 38, 38)); // Red
        removeBtn.setFocusPainted(false);
        removeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove this booking?", "Confirm Removal",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                javax.persistence.EntityManager em = null;
                try {
                    em = DatabaseUtil.createEntityManager();
                    em.getTransaction().begin();
                    Booking bToRemove = em.find(Booking.class, b.getId());
                    if (bToRemove != null) {
                        em.remove(bToRemove);
                    }
                    em.getTransaction().commit();
                } catch (Exception ex) {
                    if (em != null && em.getTransaction().isActive())
                        em.getTransaction().rollback();
                    ex.printStackTrace();
                } finally {
                    if (em != null)
                        em.close();
                }
                // Refresh Panel
                buildDynamicBookingsPanel();
            }
        });

        btnPanel.add(payBtn);
        btnPanel.add(removeBtn);

        pricePanel.add(javax.swing.Box.createVerticalGlue());
        pricePanel.add(perNightLbl);
        pricePanel.add(perNightTxt);
        pricePanel.add(javax.swing.Box.createVerticalStrut(8));
        pricePanel.add(sep);
        pricePanel.add(javax.swing.Box.createVerticalStrut(8));
        pricePanel.add(totalTxt);
        pricePanel.add(totalLbl);
        pricePanel.add(javax.swing.Box.createVerticalStrut(10));
        pricePanel.add(btnPanel);
        pricePanel.add(javax.swing.Box.createVerticalGlue());

        card.add(pricePanel, java.awt.BorderLayout.EAST);

        return card;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
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
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Slapping (User) in front of null fixes the static context error instantly!
                Homepage hp = new Homepage((User) null);
                hp.setVisible(true);
                hp.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ABOUTUS;
    private javax.swing.JPanel Feedback1;
    private javax.swing.JPanel Feedback2;
    private javax.swing.JPanel HOME;
    private javax.swing.JPanel MYBOOKINGS;
    private javax.swing.JPanel PAYMENT;
    private javax.swing.JButton bookhotel1;
    private javax.swing.JButton bookhotel2;
    private javax.swing.JButton bookhotel3;
    private javax.swing.JButton bookhotel4;
    private javax.swing.JComboBox<String> bookingProcessComboBox;
    private javax.swing.JButton booksuites1;
    private javax.swing.JButton booksuites2;
    private javax.swing.JButton booksuites3;
    private javax.swing.JButton booksuites4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JTextArea commentTextArea;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton66;
    private javax.swing.JRadioButton jRadioButton67;
    private javax.swing.JRadioButton jRadioButton68;
    private javax.swing.JRadioButton jRadioButton69;
    private javax.swing.JRadioButton jRadioButton70;
    private javax.swing.JRadioButton jRadioButton71;
    private javax.swing.JRadioButton jRadioButton72;
    private javax.swing.JRadioButton jRadioButton73;
    private javax.swing.JRadioButton jRadioButton74;
    private javax.swing.JRadioButton jRadioButton75;
    private javax.swing.JRadioButton jRadioButton76;
    private javax.swing.JRadioButton jRadioButton77;
    private javax.swing.JRadioButton jRadioButton78;
    private javax.swing.JRadioButton jRadioButton79;
    private javax.swing.JRadioButton jRadioButton80;
    private javax.swing.JRadioButton jRadioButton81;
    private javax.swing.JRadioButton jRadioButton82;
    private javax.swing.JRadioButton jRadioButton83;
    private javax.swing.JRadioButton jRadioButton84;
    private javax.swing.JRadioButton jRadioButton85;
    private javax.swing.JRadioButton jRadioButton86;
    private javax.swing.JRadioButton jRadioButton87;
    private javax.swing.JRadioButton jRadioButton88;
    private javax.swing.JRadioButton jRadioButton89;
    private javax.swing.JRadioButton jRadioButton90;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton loginBtn;
    private javax.swing.JComboBox<String> ratingComboBox;
    private javax.swing.JComboBox<String> recommendationComboBox;
    private javax.swing.JButton regBtn;
    private javax.swing.JComboBox<String> roomQualityComboBox;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JButton userprofile;
    private javax.swing.JLabel welcomeUserLbl;
    // End of variables declaration//GEN-END:variables
}