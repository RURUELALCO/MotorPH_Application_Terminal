/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package motorph.payroll_system;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;



public class Login extends javax.swing.JFrame {
    
    public static String employeenumber;
    private String access;
    private Connection conn;
   
    
    
    public Login() {
        initComponents();
        
        setBackground(new Color(0,0,0,0));
        menu2.initMoving(Login.this);
        
        
         ((AbstractDocument) EmployeeNo.getDocument()).setDocumentFilter(new NumberOnlyFilter());
         
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Database", "postgres", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
        
        //key listeners
        EmployeeNo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Password.requestFocus();
                }
            }
        });

        Password.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });
        
        
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(Color.RED);
         
            }
            
            

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(Color.LIGHT_GRAY);
                
            }
        });
        
        
        forgotPassword.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseEntered(MouseEvent e){
                forgotPassword.setForeground(Color.red);
                
            }
            @Override
            
            public void mouseExited(MouseEvent e){
                forgotPassword.setForeground(Color.LIGHT_GRAY);
            }
        });
          
        
    }
    
    
    

    public class GlobalVariables {
        public static String employeenumber;
        public static String firstname;
        private String access;
            }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        panelBorder1 = new com.raven.swing.PanelBorder();
        menu2 = new com.raven.component.menu();
        jPanel2 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        employeenoLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        EmployeeNo = new javax.swing.JTextField();
        rememberMe = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        forgotPassword = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        closeButton = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("closeButtonName"); // NOI18N
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        menu2.setMaximumSize(new java.awt.Dimension(200, 600));
        menu2.setMinimumSize(new java.awt.Dimension(200, 600));
        menu2.setPreferredSize(new java.awt.Dimension(200, 600));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        loginButton.setBackground(new java.awt.Color(255, 51, 51));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        employeenoLabel.setForeground(new java.awt.Color(255, 0, 0));
        employeenoLabel.setText("Employee No:");

        passLabel.setForeground(new java.awt.Color(255, 51, 51));
        passLabel.setText("Password:");

        EmployeeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmployeeNoActionPerformed(evt);
            }
        });

        rememberMe.setForeground(new java.awt.Color(255, 51, 51));
        rememberMe.setText("Remember me");
        rememberMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rememberMeActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("___________________________________________________");

        forgotPassword.setText("Forgotten Password?");
        forgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgotPasswordMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Login");

        closeButton.setBackground(new java.awt.Color(153, 153, 153));
        closeButton.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        closeButton.setForeground(new java.awt.Color(0, 0, 0));
        closeButton.setText("X");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeButtonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(employeenoLabel)
                                            .addComponent(passLabel))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(EmployeeNo, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                            .addComponent(rememberMe)
                                            .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(forgotPassword))
                                            .addComponent(Password)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)))
                        .addGap(0, 98, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employeenoLabel)
                            .addComponent(EmployeeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(passLabel))
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rememberMe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(forgotPassword)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        getContentPane().add(panelBorder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 290));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed

      String employeenoStr, password;
    int employeeno;

    employeenoStr = EmployeeNo.getText();
    password = Password.getText();

    try {
        employeeno = Integer.parseInt(employeenoStr); // Parse employeeno to integer

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Database", "postgres", "1234");

        // Check if account is locked
        String checkLockQuery = "SELECT attempts, lock_time FROM login_attempts WHERE employeeno = ?";
        PreparedStatement checkLockPst = conn.prepareStatement(checkLockQuery);
        checkLockPst.setInt(1, employeeno);
        ResultSet checkLockRs = checkLockPst.executeQuery();

        if (checkLockRs.next()) {
            int attempts = checkLockRs.getInt("attempts");
            Timestamp lockTime = checkLockRs.getTimestamp("lock_time");

            if (attempts >= 3) {
                long lockDuration = System.currentTimeMillis() - lockTime.getTime();
                if (lockDuration < 15 * 60 * 1000) { // 15 minutes
                    JOptionPane.showMessageDialog(null, "Your account has been locked." + "\nMaximum number of login attempts has been reached." + "\n \n *** \n \n \n Try again after 15 minutes or contact technical support.");
                    return;
                } else {
                    // Reset attempts after 15 minutes10001
                    String resetAttemptsQuery = "UPDATE login_attempts SET attempts = 0, lock_time = NULL WHERE employeeno = ?";
                    PreparedStatement resetAttemptsPst = conn.prepareStatement(resetAttemptsQuery);
                    resetAttemptsPst.setInt(1, employeeno);
                    resetAttemptsPst.executeUpdate();
                }
            }
        }

        // Proceed with login
        String sqlquery = "SELECT * FROM login_credentials WHERE employeeno=? AND password=?";
        PreparedStatement pst = conn.prepareStatement(sqlquery);
        pst.setInt(1, employeeno);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            // Invalid login
            JOptionPane.showMessageDialog(null, "Oops! You seem to have entered invalid login details." + "\n Please try again." + "\n \n \n (MAX of 3 attempts)"
                    , " ERROR MESSAGE ", JOptionPane.WARNING_MESSAGE);
            
            EmployeeNo.setText("");
            Password.setText("");

            // Increment login attempts
            String updateAttemptsQuery = "INSERT INTO login_attempts (employeeno, attempts, lock_time) VALUES (?, 1, NULL) ON CONFLICT (employeeno) DO UPDATE SET attempts = login_attempts.attempts + 1, lock_time = CASE WHEN login_attempts.attempts >= 2 THEN CURRENT_TIMESTAMP ELSE login_attempts.lock_time END";
            PreparedStatement updateAttemptsPst = conn.prepareStatement(updateAttemptsQuery);
            updateAttemptsPst.setInt(1, employeeno);
            updateAttemptsPst.executeUpdate();
        } else {
            // Successful login
            JOptionPane.showMessageDialog(null, "Logged in successfully!");
            employeenumber = employeenoStr;
            access = getAccessLevel(employeeno);
            GlobalVariables.employeenumber = employeenumber;
            this.dispose();
            //new MainPage().show();
            
             
    
            if (access != null) {
                 if (access.equals("admin")) {
                    new MainPage().show();
            } else if (access.equals("employee")) {
                    new MainPageEMployee().show();
            } else {
                    JOptionPane.showMessageDialog(null, "Unknown access level: " + access);
            }
            } else {
                    JOptionPane.showMessageDialog(null, "Access level not found for employee number: " + employeeno);
            }


            // Reset login attempts on successful login
            String resetAttemptsQuery = "UPDATE login_attempts SET attempts = 0, lock_time = NULL WHERE employeeno = ?";
            PreparedStatement resetAttemptsPst = conn.prepareStatement(resetAttemptsQuery);
            resetAttemptsPst.setInt(1, employeeno);
            resetAttemptsPst.executeUpdate();

            // Check the access column
            String accessQuery = "SELECT access FROM login_credentials WHERE employeeno = ?";
            PreparedStatement accessPst = conn.prepareStatement(accessQuery);
            accessPst.setInt(1, employeeno);
            ResultSet accessRs = accessPst.executeQuery();

            
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,  "Oops! You entered an Invalid Employee Number." + "\n Please check if you entered the right number.");
        EmployeeNo.setText("");
        Password.setText("");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        EmployeeNo.setText("");
        Password.setText("");
    }
    }//GEN-LAST:event_loginButtonActionPerformed

    
    private void EmployeeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmployeeNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmployeeNoActionPerformed

    private void rememberMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rememberMeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rememberMeActionPerformed

    private void forgotPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotPasswordMouseClicked
        // TODO add your handling code here:
        
        closeButton.setOpaque(true);
        JOptionPane.showMessageDialog(null, "You forgot it, better remember it." + "\n Kidding, please contact technical support.");
        //FORGOT PASSWORD PORTION EDIT TO DO
    }//GEN-LAST:event_forgotPasswordMouseClicked

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        //[X] BUTTON
            
        closeButton.setOpaque(true);
            
                dispose();
            
            
            
        
        
    }//GEN-LAST:event_closeButtonMouseClicked

    private void closeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_closeButtonMousePressed
        
// JULY 04 2024 SUGGESTED MOUSE ADAPTER 5AM
    
//    public void initMoving(JFrame frame){
//        panelmove.addMouseListener(new MouseAdapter(){
//        @Override
//        public void mousePressed(MouseEvent me){
//          x = me.getX();
//          y = me.getY();
//        }
//    });
//        panelmove.addMouseMotionListener(new MouseMotionAdapter() {
//        @Override
//        public void mouseDragged(MouseEvent me){
//            fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
//        }
//        });
//    }
//    
    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
    private String getAccessLevel(int employeeno) throws SQLException {
    String accessQuery = "SELECT access FROM login_credentials WHERE employeeno = ?";
    try (PreparedStatement accessPst = conn.prepareStatement(accessQuery)) {
        accessPst.setInt(1, employeeno);
        try (ResultSet accessRs = accessPst.executeQuery()) {
            if (accessRs.next()) {
                String access = accessRs.getString("access");
                System.out.println("Access level retrieved: " + access); // Print access level
                return access;
            } else {
                System.out.println("No access level found for employeeno: " + employeeno);
                return null; // No access level found
            }
        }
    }
}
    
  

    void clearFields() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    static class NumberOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null && text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }
    
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EmployeeNo;
    private javax.swing.JPasswordField Password;
    private javax.swing.JLabel closeButton;
    private javax.swing.JLabel employeenoLabel;
    private javax.swing.JLabel forgotPassword;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginButton;
    private com.raven.component.menu menu2;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JLabel passLabel;
    private javax.swing.JCheckBox rememberMe;
    // End of variables declaration//GEN-END:variables
}
