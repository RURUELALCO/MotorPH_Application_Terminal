/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package motorph.payroll_system;



import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class EmployeeProfile extends javax.swing.JPanel {

    private final String employeenumber;

    
    public EmployeeProfile() {
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
            employeename.setText(rs.getString("firstname") + ", " + rs.getString("lastname"));
            input_employeeNumber.setText(rs.getString("EmployeeNo"));
            dob1.setText(rs.getString("birthday"));
            address.setText(rs.getString("address"));
            contact_number.setText(rs.getString("phone_number"));
            position.setText(rs.getString("current_position"));
            work_status.setText(rs.getString("status"));
            supervisor.setText(rs.getString("immediate_supervisor"));
            
            // Populate government identifiers
            tax_no.setText(rs.getString("tin_number"));
            hdmf_no.setText(rs.getString("pag_ibig_number"));
            philhealth_no.setText(rs.getString("philhealth_number"));
            sss_no.setText(rs.getString("sss_number"));
            
            // Populate the salary profile fields
            basicpay.setText(rs.getString("basic_salary"));
            clo_allowance.setText(rs.getString("clothing_allowance"));
            rice_subsidy1.setText(rs.getString("rice_subsidy"));
            ph_allowance.setText(rs.getString("phone_allowance"));
            grossMonthly.setText(rs.getString("grosssemimonthlyrate"));
            rateperhour .setText(rs.getString("HourlyRate"));       
            
        } else {
            JOptionPane.showMessageDialog(null, "Employee not found.");
        }
        
        backButton.addMouseListener(new MouseAdapter() {
            
            public void mouseEntered(MouseEvent e) {
                backButton.setForeground(Color.white);    
            }
            
            public void mouseClicked(MouseEvent e) {
                backButton.setForeground(Color.white);
               
            }
        });
    }
} catch (SQLException ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error accessing database: " + ex.getMessage());
}
    
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ViewRecordsPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        input_employeeNumber = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        employeename = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        sex1 = new javax.swing.JTextField();
        dob1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        marital_status1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        philhealth_no = new javax.swing.JTextField();
        hdmf_no = new javax.swing.JTextField();
        sss_no = new javax.swing.JTextField();
        tax_no = new javax.swing.JTextField();
        contact_number = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        supervisor = new javax.swing.JTextField();
        start_date = new javax.swing.JTextField();
        work_status = new javax.swing.JTextField();
        position = new javax.swing.JTextField();
        ph_allowance = new javax.swing.JTextField();
        clo_allowance = new javax.swing.JTextField();
        rateperhour = new javax.swing.JTextField();
        basicpay = new javax.swing.JTextField();
        rice_subsidy1 = new javax.swing.JTextField();
        grossMonthly = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        panelmove = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1363, 1262));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ViewRecordsPanel.setBackground(new java.awt.Color(255, 255, 255));
        ViewRecordsPanel.setMaximumSize(new java.awt.Dimension(730, 694));
        ViewRecordsPanel.setMinimumSize(new java.awt.Dimension(730, 694));
        ViewRecordsPanel.setPreferredSize(new java.awt.Dimension(730, 694));
        ViewRecordsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setText("Employee Number: ");
        ViewRecordsPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 30));

        input_employeeNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_employeeNumberActionPerformed(evt);
            }
        });
        input_employeeNumber.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                input_employeeNumberPropertyChange(evt);
            }
        });
        ViewRecordsPanel.add(input_employeeNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 750, -1));

        jLabel27.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel27.setText("Employee Name:");
        ViewRecordsPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 140, 20));

        employeename.setEditable(false);
        employeename.setFocusable(false);
        employeename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeenameActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(employeename, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 410, -1));

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel12.setText("Birthdate:");
        ViewRecordsPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 80, -1));

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel11.setText("Sex:");
        ViewRecordsPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, -1, 30));

        sex1.setEditable(false);
        sex1.setFocusable(false);
        sex1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sex1ActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(sex1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, 210, -1));

        dob1.setEditable(false);
        dob1.setFocusable(false);
        dob1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dob1ActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(dob1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 410, -1));

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel10.setText("Marital Status:");
        ViewRecordsPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, -1, 30));

        jLabel17.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel17.setText("Address:");
        ViewRecordsPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 70, -1));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        address.setEditable(false);
        address.setColumns(20);
        address.setLineWrap(true);
        address.setRows(5);
        address.setWrapStyleWord(true);
        address.setAutoscrolls(false);
        address.setFocusable(false);
        jScrollPane2.setViewportView(address);

        ViewRecordsPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 760, 60));

        marital_status1.setEditable(false);
        marital_status1.setFocusable(false);
        marital_status1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marital_status1ActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(marital_status1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 210, -1));

        jLabel18.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel18.setText("Contact Number:");
        ViewRecordsPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel23.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel23.setText("TIN No.");
        ViewRecordsPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, -1, -1));

        jLabel24.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel24.setText("SSS No.");
        ViewRecordsPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 520, 60, -1));

        jLabel25.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel25.setText("HDMF");
        ViewRecordsPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 50, -1));

        jLabel26.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel26.setText("PhilHealth");
        ViewRecordsPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, -1, -1));

        philhealth_no.setEditable(false);
        philhealth_no.setFocusable(false);
        ViewRecordsPanel.add(philhealth_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 600, 200, -1));

        hdmf_no.setEditable(false);
        hdmf_no.setFocusable(false);
        ViewRecordsPanel.add(hdmf_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 560, 200, -1));

        sss_no.setEditable(false);
        sss_no.setFocusable(false);
        ViewRecordsPanel.add(sss_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, 200, -1));

        tax_no.setEditable(false);
        tax_no.setFocusable(false);
        ViewRecordsPanel.add(tax_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, 200, -1));

        contact_number.setEditable(false);
        contact_number.setFocusable(false);
        contact_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contact_numberActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(contact_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 200, -1));

        jLabel28.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel28.setText("Basic Pay:");
        ViewRecordsPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 500, -1, 30));

        jLabel29.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel29.setText("Rate per hour:");
        ViewRecordsPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 560, -1, 30));

        jLabel30.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel30.setText("CLO Allowance:");
        ViewRecordsPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 520, -1, 40));

        jLabel31.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel31.setText("PH Allowance:");
        ViewRecordsPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, -1, 40));

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel13.setText("Position");
        ViewRecordsPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 340, -1, 30));

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel14.setText("Status");
        ViewRecordsPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 400, 60, 30));

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel15.setText("Start Date");
        ViewRecordsPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 460, -1, 30));

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel16.setText("Supervisor");
        ViewRecordsPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 520, -1, 30));

        supervisor.setEditable(false);
        supervisor.setFocusable(false);
        supervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supervisorActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(supervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 500, 220, -1));

        start_date.setEditable(false);
        start_date.setFocusable(false);
        start_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_dateActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(start_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 440, 220, -1));

        work_status.setEditable(false);
        work_status.setFocusable(false);
        work_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                work_statusActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(work_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 380, 220, -1));

        position.setEditable(false);
        position.setFocusable(false);
        position.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(position, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 320, 220, -1));

        ph_allowance.setEditable(false);
        ph_allowance.setFocusable(false);
        ViewRecordsPanel.add(ph_allowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 560, 120, -1));

        clo_allowance.setEditable(false);
        clo_allowance.setFocusable(false);
        ViewRecordsPanel.add(clo_allowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 530, 120, -1));

        rateperhour.setEditable(false);
        rateperhour.setFocusable(false);
        ViewRecordsPanel.add(rateperhour, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, 120, -1));

        basicpay.setEditable(false);
        basicpay.setFocusable(false);
        basicpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basicpayActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(basicpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 500, 120, -1));

        rice_subsidy1.setEditable(false);
        rice_subsidy1.setFocusable(false);
        ViewRecordsPanel.add(rice_subsidy1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 120, -1));

        grossMonthly.setEditable(false);
        grossMonthly.setFocusable(false);
        ViewRecordsPanel.add(grossMonthly, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 530, 120, -1));

        jLabel32.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel32.setText("Rice Subsidy:");
        ViewRecordsPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, -1, 40));

        jLabel33.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel33.setText("Gross Semi-monthly Rate:");
        ViewRecordsPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 530, -1, 30));

        backButton.setBackground(new java.awt.Color(255, 51, 51));
        backButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        ViewRecordsPanel.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 650, 230, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\T480\\Downloads\\dp (1).png")); // NOI18N
        jLabel1.setText(" ");
        jLabel1.setMaximumSize(new java.awt.Dimension(93, 96));
        jLabel1.setMinimumSize(new java.awt.Dimension(93, 96));
        jLabel1.setName(""); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(90, 90));
        ViewRecordsPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 790, 320));

        panelmove.setOpaque(false);

        javax.swing.GroupLayout panelmoveLayout = new javax.swing.GroupLayout(panelmove);
        panelmove.setLayout(panelmoveLayout);
        panelmoveLayout.setHorizontalGroup(
            panelmoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );
        panelmoveLayout.setVerticalGroup(
            panelmoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );

        ViewRecordsPanel.add(panelmove, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MotorPH");
        ViewRecordsPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 620, -1, -1));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\T480\\Downloads\\MotorPH LOGO.png")); // NOI18N
        jLabel3.setOpaque(true);
        ViewRecordsPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-180, -210, 1700, 1360));

        add(ViewRecordsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1363, 899));
    }// </editor-fold>//GEN-END:initComponents

    private void input_employeeNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_employeeNumberActionPerformed
        
    }//GEN-LAST:event_input_employeeNumberActionPerformed

    private void input_employeeNumberPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_input_employeeNumberPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_input_employeeNumberPropertyChange

    private void employeenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeenameActionPerformed

    private void sex1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sex1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sex1ActionPerformed

    private void dob1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dob1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dob1ActionPerformed

    private void marital_status1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marital_status1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marital_status1ActionPerformed

    private void contact_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contact_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contact_numberActionPerformed

    private void supervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supervisorActionPerformed

    private void start_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_start_dateActionPerformed

    private void work_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_work_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_work_statusActionPerformed

    private void positionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_positionActionPerformed

    private void basicpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basicpayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_basicpayActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
        this.hide();
        MainPageEMployee frm = new MainPageEMployee();
        setVisible(true);
        MainPageEMployee.getFrames();
        
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ViewRecordsPanel;
    private javax.swing.JTextArea address;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField basicpay;
    private javax.swing.JTextField clo_allowance;
    private javax.swing.JTextField contact_number;
    private javax.swing.JTextField dob1;
    private javax.swing.JTextField employeename;
    private javax.swing.JTextField grossMonthly;
    private javax.swing.JTextField hdmf_no;
    private javax.swing.JTextField input_employeeNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField marital_status1;
    private javax.swing.JPanel panelmove;
    private javax.swing.JTextField ph_allowance;
    private javax.swing.JTextField philhealth_no;
    private javax.swing.JTextField position;
    private javax.swing.JTextField rateperhour;
    private javax.swing.JTextField rice_subsidy1;
    private javax.swing.JTextField sex1;
    private javax.swing.JTextField sss_no;
    private javax.swing.JTextField start_date;
    private javax.swing.JTextField supervisor;
    private javax.swing.JTextField tax_no;
    private javax.swing.JTextField work_status;
    // End of variables declaration//GEN-END:variables

   
}
