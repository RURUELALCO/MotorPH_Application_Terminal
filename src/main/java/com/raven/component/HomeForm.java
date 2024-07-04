/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.component;

import java.sql.Connection;
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
    String sql = "SELECT * FROM employeedetails WHERE employeeno = ?";
    
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        long employeeNumber = Long.parseLong(employeenumber);
        
        pstmt.setLong(1, employeeNumber);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            // Populate the fields with the employee details
            name.setText(rs.getString("firstname") + ", " + rs.getString("lastname"));
                   
            
        } else {
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

        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setText("Welcome");

        name.setFont(new java.awt.Font("Segoe UI Emoji", 1, 50)); // NOI18N
        name.setForeground(new java.awt.Color(255, 51, 51));
        name.setText("To MotorPH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(name)
                .addContainerGap(364, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables
}
