/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import motorph.payroll_system.DatabaseConnection;
import motorph.payroll_system.Login;

public class HomeForm extends javax.swing.JPanel {

    private final String employeenumber;    
    public HomeForm() {
        initComponents();
            this.employeenumber = Login.GlobalVariables.employeenumber;
            System.out.println("this is: " + this.employeenumber);

                try (Connection connection = DatabaseConnection.connect()) {

                    //date 07272024 23:13 \"___"\ is not necessary. (But it does add readability)
                String sql = "SELECT * FROM \"employeedetails\" WHERE \"employeeno\" = ?";

                    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                        long employeeNumber = Long.parseLong(employeenumber);

                        pstmt.setLong(1, employeeNumber);
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            // Populate the fields with the employee details
                            employeename.setText(rs.getString("firstname").toUpperCase() + "!");                
                        }
                            else {
                                    JOptionPane.showMessageDialog(null, "Employee not found.");
                                }
                    }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error accessing database: " + ex.getMessage());
        }
               
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboardWelcome = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        employeename = new javax.swing.JLabel();
        background1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboardWelcome.setBackground(new java.awt.Color(255, 255, 255));
        dashboardWelcome.setFont(new java.awt.Font("Segoe UI", 1, 100)); // NOI18N
        dashboardWelcome.setText("Welcome to the Dashboard,");
        add(dashboardWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1670, 125));

        background.setFont(new java.awt.Font("Segoe UI Emoji", 1, 50)); // NOI18N
        background.setForeground(new java.awt.Color(255, 51, 51));
        background.setIcon(new javax.swing.ImageIcon("C:\\Users\\T480\\Downloads\\MotorPH LOGO.png")); // NOI18N
        background.setText("To MotorPH");
        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-120, -140, 1730, -1));

        employeename.setFont(new java.awt.Font("Segoe UI Black", 1, 60)); // NOI18N
        employeename.setForeground(new java.awt.Color(51, 0, 0));
        employeename.setText("MOTOR PH");
        add(employeename, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 420, 110));

        background1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 50)); // NOI18N
        background1.setForeground(new java.awt.Color(255, 51, 51));
        background1.setIcon(new javax.swing.ImageIcon("C:\\Users\\T480\\Downloads\\Elements blob (3).png")); // NOI18N
        add(background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1730, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel background1;
    private javax.swing.JLabel dashboardWelcome;
    private javax.swing.JLabel employeename;
    // End of variables declaration//GEN-END:variables
}

   

