/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package motorph.payroll_system;

import com.raven.component.DefaultForm;
import com.raven.component.HomeForm;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import motorph.payroll_system.Login.GlobalVariables;

/**
 *
 * @author Ruel Rey
 */
public class MainPage extends javax.swing.JFrame {
    
    private String employeenumber;
    private static String actionMode;
    
    
    
    
    
    public MainPage() {
        initComponents();
        
        menu1.setEvent(new MenuEvent(){
            
            @Override
               public void selected(int index, int subIndex) {
                if (index == 0) {
                    showForm(new HomeForm());
                    
                } else if (index == 4 && subIndex == 0) {
                    dispose(); // Close the MainPage
                    Login loginForm = new Login();
                    loginForm.setVisible(true);
                    
                 } else if (index == 3 && subIndex == 1) {    
                    
                    SharedState.actionMode = "save"; // Set the action mode to "save"
                    System.out.println("Action mode in MainPage: " + SharedState.actionMode); // Debugging
                    showForm(new CreateEmployee(actionMode));
                 
                 } else if (index == 3 && subIndex == 2) {    
                    
                    SharedState.actionMode = "edit"; // Set the action mode to "edit"
                    System.out.println("Action mode in MainPage: " + SharedState.actionMode); // Debugging
                    showForm(new CreateEmployee(actionMode)); 
                 } else if (index == 3 && subIndex == 3) {    
                    
                    SharedState.actionMode = "delete"; // Set the action mode to "delete"
                    System.out.println("Action mode in MainPage: " + SharedState.actionMode); // Debugging
                    showForm(new CreateEmployee(actionMode));
                    
                 }else if (index == 1 && subIndex == 1) {
                     showForm(new EmployeeProfile());
                     
                     
                } else {
                    showForm(new DefaultForm("Form :" + index + " " + subIndex));
                }
            }
        });
        this.employeenumber = GlobalVariables.employeenumber;
        System.out.println("employeenumber in MainPage constructor: " + this.employeenumber);
        
        menu1.getEvent().selected(0, 0);
     this.employeenumber = GlobalVariables.employeenumber;
    System.out.println("employeenumber in MainPage constructor: " + this.employeenumber);
    checkEmployeeAccess(this.employeenumber);
        
    }
    
    
    public class SharedState {
    public static String actionMode = "";
    }    
    
    private void showForm(Component com){
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    
    }
    
     class Database {
    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/Userlogin"; // Change to your database URL
        String user = "admin"; // Change to your database username
        String password = "123456789"; // Change to your database password

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
     private void checkEmployeeAccess(String employeenumber) {
        String sql = "SELECT access FROM login.\"login_credentials\" WHERE \"employeeno\" = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            // Convert employeenumber to integer before setting it in the prepared statement
            int employeeNumberInt = Integer.parseInt(employeenumber);
            pstmt.setInt(1, employeeNumberInt);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String access = rs.getString("access");
                    System.out.println("Access for employeenumber " + employeenumber + ": " + access);
                } else {
                    System.out.println("No employee found with employeenumber " + employeenumber);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid employeenumber format: " + employeenumber);
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        polygonCorner1 = new scrollpane.PolygonCorner();
        panelBorder1 = new motorph.payroll_system.PanelBorder();
        scrollPaneWin111 = new scrollpane.ScrollPaneWin11();
        menu1 = new motorph.payroll_system.menu();
        body = new javax.swing.JPanel();
        header1 = new com.raven.component.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setForeground(new java.awt.Color(243, 249, 249));

        scrollPaneWin111.setBorder(null);
        scrollPaneWin111.setViewportView(menu1);

        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, 1616, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private com.raven.component.Header header1;
    private motorph.payroll_system.menu menu1;
    private motorph.payroll_system.PanelBorder panelBorder1;
    private scrollpane.PolygonCorner polygonCorner1;
    private scrollpane.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}
