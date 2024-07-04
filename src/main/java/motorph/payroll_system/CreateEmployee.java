/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */


package motorph.payroll_system;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import motorph.payroll_system.MainPage.SharedState;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CreateEmployee extends javax.swing.JPanel {

    
     private String actionMode; // Field to store the action mode
    
    public CreateEmployee(String actionMode1) {
        this.actionMode = actionMode;
        initComponents();
        loadEmployeeData();
        addDocumentListenerToShowEmployeeNumber();
        checkActionMode();
        menu.add(panel);
       
       addbasicsalary.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        updateGrossMonthly();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateGrossMonthly();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // Plain text components don't fire these events
    }

    private void updateGrossMonthly() {
        String text = addbasicsalary.getText();
        if (!text.isEmpty()) {
            try {
                double basicSalary = Double.parseDouble(text);
                double grossmonthly = basicSalary / 2;
                double hourlyRate = basicSalary / (21.0 * 8.0);
                hourlyRate = Math.round(hourlyRate * 100.0) / 100.0;

                
                grossMonthly.setText(String.valueOf(grossmonthly));
                rateperhour.setText(String.valueOf(hourlyRate));
                grossMonthly.setEditable(false);
                rateperhour.setEditable(false);
            } catch (NumberFormatException ex) {

                System.err.println("Invalid input for basic salary: " + ex.getMessage());
            }
        } else {
            grossMonthly.setText("");
        }
    }
});
       
       SwingUtilities.invokeLater(new Runnable() {
    public void run() {
        // GUI-related code here
    }
});
       
      addbirthday.getDateEditor().addPropertyChangeListener(e -> {
    if ("date".equals(e.getPropertyName())) {
        Date selectedDate = addbirthday.getDate();
        if (selectedDate != null) {
            int age = calculateAge(selectedDate);
            if (age < 18) {
                JOptionPane.showMessageDialog(null, "You're too young for the position.");
                addbirthday.setDate(null); // Clear the selected date
            } else if (age > 70) {
                JOptionPane.showMessageDialog(null, "You're a bit old for the position.");
                addbirthday.setDate(null); // Clear the selected date
            }
        }
    }
});
        
        
        //((AbstractDocument) phonenumber.getDocument()).setDocumentFilter(new Login.NumberOnlyFilter());
        ((AbstractDocument) addbasicsalary.getDocument()).setDocumentFilter(new Login.NumberOnlyFilter());
        //((AbstractDocument) rateperhour.getDocument()).setDocumentFilter(new Login.NumberOnlyFilter());
        ((AbstractDocument) addphone1.getDocument()).setDocumentFilter(new Login.NumberOnlyFilter());
        //((AbstractDocument) jTextField2.getDocument()).setDocumentFilter(new Login.NumberOnlyFilter());
        ((AbstractDocument) addrice.getDocument()).setDocumentFilter(new Login.NumberOnlyFilter());
        ((AbstractDocument) addcloth.getDocument()).setDocumentFilter(new Login.NumberOnlyFilter());
        
        // Add selection listener to employeeTable
        employeeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
                    Object EmployeeNo = model.getValueAt(selectedRow, 0);
                    ShowEmployeeNumber.setText(EmployeeNo.toString());
                    try {
                        // Get the employee number as a String from the text field
                     String empNumberStr = ShowEmployeeNumber.getText();

                     // Convert the employee number String to an int
                    int empNumber = Integer.parseInt(empNumberStr);

                    // Pass the employee number (now an int) to the showEmployeeDetails method
                     showEmployeeDetails(empNumber);
                    } catch (NumberFormatException ex) {
                       // Handle the case where the input is not a valid integer
                JOptionPane.showMessageDialog(null, "Oops! You entered an Invalid Employee Number." + "\n Please check if you entered the right number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateEmployee.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    
                }
            }
        });
    }
    
    private static int calculateAge(Date birthDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthDate);
        int birthYear = cal.get(Calendar.YEAR);
        int birthMonth = cal.get(Calendar.MONTH);
        int birthDay = cal.get(Calendar.DAY_OF_MONTH);
        
        LocalDate currentDate = LocalDate.now();
        LocalDate birthLocalDate = LocalDate.of(birthYear, birthMonth + 1, birthDay);
        
        return Period.between(birthLocalDate, currentDate).getYears();
    }


   
    
   private void checkActionMode() {
        System.out.println("Checking Action Mode: " + SharedState.actionMode); // Debugging
        if ("edit".equals(SharedState.actionMode)) {
            edit1.setVisible(true);
            delete.setVisible(false); // Make deleteButton invisible
            save.setVisible(false); // Make saveButton invisible
            addfirstname1.setEditable(true);
            addlastname.setEditable(true);
            ShowEmployeeNumber.setBackground(Color.YELLOW);
            
            } else if ("save".equals(SharedState.actionMode)){
                loadLastEmployeeNumber();
            edit1.setVisible(false);
            delete.setVisible(false); 
            save.setVisible(true); 
            ShowEmployeeNumber.setEditable(true);
            employeeTable.setEnabled(false);
            
             } else if ("delete".equals(SharedState.actionMode)){
            edit1.setVisible(false);
            delete.setVisible(true); 
            save.setVisible(false); 
            addfirstname1.setEditable(false);
            addlastname.setEditable(false);
            addbirthday.setEnabled(false);
            addmaritalstatus.setEditable(false);
            addaddress.setEditable(false);
            phonenumber.setEditable(false);
            addtin.setEditable(false);
            addsss.setEditable(false);
            addpagibig.setEditable(false);
            addphilhealth.setEditable(false);
            addposition.setEditable(false);
            addstatus.setEditable(false);
            addsupervisor.setEditable(false);
            addbasicsalary.setEditable(false);
            addphone1.setEditable(false);
            rateperhour.setEditable(false);
            grossMonthly.setEditable(false);
             addrice.setEditable(false);
              addcloth.setEditable(false);
            
        } else {
            delete.setVisible(true);
            save.setVisible(true);
        }
    }


    public void loadLastEmployeeNumber() {
        String sql = "SELECT \"employeeno\" FROM \"employeedetails\" ORDER BY \"employeeno\" DESC LIMIT 1";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                int employeeNumber = rs.getInt("EmployeeNo" );

                // Show the employee number in the text field
                ShowEmployeeNumber.setText(String.valueOf(employeeNumber + 1));
                employeeTable.setEnabled(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading last employee number: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
public void loadEmployeeData() {
    DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
    model.setRowCount(0); // Clear existing rows

    String sql = "SELECT * FROM \"employeedetails\""; 

    try (Connection connection = DatabaseConnection.connect();
         PreparedStatement pstmt = connection.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            
            int employeeNumber = rs.getInt("EmployeeNo");
            String lastName = rs.getString("lastname");
            String firstName = rs.getString("firstname");
            Date birthday = rs.getDate("birthday");
            String address = rs.getString("address");
            String phoneNumber = rs.getString("phone_number");
            String currentPosition = rs.getString("current_position");
            String status = rs.getString("status");
            String tinNumber = rs.getString("tin_number");
            String pagIbigNumber = rs.getString("pag_ibig_number");
            String philhealthNumber = rs.getString("philhealth_number");
            String sssNumber = rs.getString("sss_number");
            String immediateSupervisor = rs.getString("immediate_supervisor");
            Double basicSalary = rs.getDouble("basic_salary");
            Double clothingAllowance = rs.getDouble("clothing_allowance");
            Double riceSubsidy = rs.getDouble("rice_subsidy");
            Double phoneAllowance = rs.getDouble("phone_allowance");
            Double grosssemimonthlyrate = rs.getDouble("grosssemimonthlyrate");
            Double HourlyRate = rs.getDouble("HourlyRate");
           

            model.addRow(new Object[]{
                employeeNumber, firstName, lastName, birthday, address, phoneNumber, tinNumber, sssNumber, pagIbigNumber, philhealthNumber, 
                 currentPosition, status, immediateSupervisor, 
                basicSalary, clothingAllowance, riceSubsidy, phoneAllowance, grosssemimonthlyrate, HourlyRate
            });
        }

   } catch (SQLException e) {
    e.printStackTrace(); // Print the exception trace to see what's happening
    JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
}
    private void addDocumentListenerToShowEmployeeNumber() {
        ShowEmployeeNumber.getDocument().addDocumentListener(new DocumentListener() {
            
            public void insertUpdate(DocumentEvent e) {
                checkEmployeeNumber();
            }

            public void removeUpdate(DocumentEvent e) {
                checkEmployeeNumber();
            }

            
            public void changedUpdate(DocumentEvent e) {
                checkEmployeeNumber();
            }
        });
    }

    private void checkEmployeeNumber() {
        String enteredNumber = ShowEmployeeNumber.getText().trim();
        if (enteredNumber.isEmpty()) {
            setFieldsEditable(false);
            return;
        }

        String sql = "SELECT * FROM \"employeedetails\" WHERE \"employeeno\" = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(enteredNumber));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    setFieldsEditable(false);
                } else {
                    setFieldsEditable(true);
                }
            }

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error checking employee number: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFieldsEditable(boolean editable) {
       // addfirstname1.setEditable(editable);
        //addlastname.setEditable(editable);
        // Set other text fields as editable or non-editable
    }
    
    private void showEmployeeDetails(int empNumber) throws SQLException {
        try (Connection connection = DatabaseConnection.connect()) {
           String sql = "SELECT * FROM \"employeedetails\" WHERE \"employeeno\" = ?";
            
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, empNumber);
                
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    // Populate the fields Employee Personal Profile with the employee details
                    
                    
                    addfirstname1.setText(rs.getString("firstname"));
                    addlastname.setText(rs.getString("lastname"));
                    addbirthday.setDate(rs.getDate("birthday"));
                    addaddress.setText(rs.getString("address"));
                    phonenumber.setText(rs.getString("phone_number"));
                    addposition.setText(rs.getString("current_position"));
                    addstatus.setSelectedItem(rs.getString("status"));                           
                    addsupervisor.setText(rs.getString("immediate_supervisor"));
                    
                    // Populate government identifiers
                    addtin.setText(rs.getString("tin_number"));
                    addpagibig.setText(rs.getString("pag_ibig_number"));
                    addphilhealth.setText(rs.getString("philhealth_number"));
                    addsss.setText(rs.getString("sss_number"));  
                    
                    // Populate the fields Employee Salary Profile with the employee details
                    
                    addbasicsalary.setText(rs.getString("basic_salary"));
                    addcloth.setText(rs.getString("clothing_allowance"));
                    addrice.setText(rs.getString("rice_subsidy"));
                    addphone1.setText(rs.getString("phone_allowance"));   
                    grossMonthly.setText(rs.getString("grosssemimonthlyrate"));
                    rateperhour.setText(rs.getString("HourlyRate"));
       
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Employee not found.");
                }
            }
        }
    } 
      
      // Method to generate a random password
    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        
        return sb.toString();
    }
    
    public void addEmployeesignin(CreateEmployeeRecord employee) {
    // Generate a random password
    String randomPassword = generateRandomPassword(10); // You can adjust the length of the password
    
    String sql = "INSERT INTO \"login_credentials\"(\n" +
                 "    \"employeeno\", password, access)\n" +
                 "    VALUES (?, ?, ?);";

    try (Connection connection = DatabaseConnection.connect();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {

        // Parse the ShowEmployeeNumber.getText() to an integer
        int employeeNumber = Integer.parseInt(ShowEmployeeNumber.getText());
        String getaccess = "employee";
        
        pstmt.setInt(1, employeeNumber);
        pstmt.setString(2, randomPassword); // Set the random password
        pstmt.setString(3, "employee");

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            //System.out.println("Employee login credentials added successfully");
        } else {
            //System.out.println("No employee login credentials were added");
        }

    } catch (NumberFormatException e) {
        // Handle the case where the text cannot be parsed to an integer
        JOptionPane.showMessageDialog(null,  "Oops! You entered an Invalid Employee Number." + "\n Please check if you entered the right number.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        Logger.getLogger(CreateEmployeeRecord.class.getName()).log(Level.SEVERE, null, ex);
    }
}
      
    public void deleteEmployeesignin() {
    String employeeNumberInput = ShowEmployeeNumber.getText().trim();

    try {
        // Convert the employee number input to an integer
        int employeeNumber = Integer.parseInt(employeeNumberInput);

        // Create a SQL DELETE statement to remove the record from the database
        String sql = "DELETE FROM \"login_credentials\" WHERE \"employeeno\" = ?";

        // Connect to the database and execute the DELETE statement
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set the employee number as a parameter in the SQL statement
            pstmt.setInt(1, employeeNumber);

            // Execute the DELETE statement
            int rowsAffected = pstmt.executeUpdate();

            // Check if any rows were affected (i.e., if the record was deleted successfully)
            if (rowsAffected > 0) {
                //JOptionPane.showMessageDialog(this, "Record with employee number " + employeeNumber + " deleted successfully.", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);

            } else {
                //JOptionPane.showMessageDialog(this, "No record found with employee number " + employeeNumber + ".", "Record Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,  "Oops! You entered an Invalid Employee Number." + "\n Please check if you entered the right number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "An error occurred while deleting the record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
     
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        menu = new javax.swing.JPopupMenu();
        CreateEmployeePanel = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        grossMonthly = new javax.swing.JTextField();
        addaddress = new javax.swing.JTextField();
        middleinitial = new javax.swing.JTextField();
        addlastname = new javax.swing.JTextField();
        ShowEmployeeNumber = new javax.swing.JTextField();
        addstartdate = new com.toedter.calendar.JDateChooser();
        Female = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        Male1 = new javax.swing.JRadioButton();
        addstatus = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        addtin = new javax.swing.JTextField();
        addsss = new javax.swing.JTextField();
        addpagibig = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        addphilhealth = new javax.swing.JTextField();
        addposition = new javax.swing.JTextField();
        addbirthday = new com.toedter.calendar.JDateChooser();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        addsupervisor = new javax.swing.JTextField();
        addbasicsalary = new javax.swing.JTextField();
        addrice = new javax.swing.JTextField();
        addcloth = new javax.swing.JTextField();
        addmaritalstatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        addphone1 = new javax.swing.JTextField();
        rateperhour = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        edit1 = new javax.swing.JButton();
        addfirstname1 = new javax.swing.JTextField();
        phonenumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        jScrollPane2.setViewportView(list);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );

        menu.setFocusable(false);

        CreateEmployeePanel.setBackground(new java.awt.Color(255, 255, 255));
        CreateEmployeePanel.setForeground(new java.awt.Color(255, 255, 255));
        CreateEmployeePanel.setMaximumSize(new java.awt.Dimension(740, 694));
        CreateEmployeePanel.setMinimumSize(new java.awt.Dimension(740, 694));
        CreateEmployeePanel.setPreferredSize(new java.awt.Dimension(740, 694));
        CreateEmployeePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel32.setText("Employee ID");
        CreateEmployeePanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 290, -1));

        jLabel33.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel33.setText("First Name");
        CreateEmployeePanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        jLabel34.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel34.setText("Last Name");
        CreateEmployeePanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabel35.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel35.setText("Middle Initial");
        CreateEmployeePanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, 20));

        jLabel36.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel36.setText("Date of Birth:");
        CreateEmployeePanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, -1, -1));

        jLabel37.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel37.setText("Sex:");
        CreateEmployeePanel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, -1, -1));

        jLabel38.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel38.setText("Marital Status");
        CreateEmployeePanel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, -1, -1));

        jLabel39.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel39.setText("Address Line");
        CreateEmployeePanel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 40));

        grossMonthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grossMonthlyActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(grossMonthly, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, 160, -1));

        addaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addaddressActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 480, -1));

        middleinitial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middleinitialActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(middleinitial, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 160, -1));

        addlastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addlastnameActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addlastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 160, -1));

        ShowEmployeeNumber.setFocusable(false);
        ShowEmployeeNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowEmployeeNumberActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(ShowEmployeeNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 200, 310, -1));
        CreateEmployeePanel.add(addstartdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 650, 160, -1));

        Female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FemaleActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(Female, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, -1, -1));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel1.setText("Female");
        CreateEmployeePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, -1, 20));

        jLabel41.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel41.setText("Male");
        CreateEmployeePanel.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, -1, 20));

        Male1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Male1ActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(Male1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        addstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Probationary" }));
        CreateEmployeePanel.add(addstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 620, 160, 30));

        jLabel42.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel42.setText("Contact Details (Phone Number)");
        CreateEmployeePanel.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, -1, 40));

        jLabel43.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel43.setText("TIN Number");
        CreateEmployeePanel.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 560, -1, 40));

        jLabel44.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel44.setText("SSS Number");
        CreateEmployeePanel.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 590, -1, 40));

        jLabel45.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel45.setText("Pag-Ibig Number");
        CreateEmployeePanel.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 620, -1, 40));

        jLabel46.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel46.setText("PhilHealth Number");
        CreateEmployeePanel.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 660, -1, 30));

        addtin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtinActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addtin, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 570, 160, -1));

        addsss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addsssActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addsss, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 600, 160, -1));

        addpagibig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpagibigActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addpagibig, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 630, 160, -1));

        jLabel40.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel40.setText("Position:");
        CreateEmployeePanel.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 570, -1, 20));

        jLabel47.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel47.setText("Status:");
        CreateEmployeePanel.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 610, -1, 40));

        jLabel48.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel48.setText("Start Date:");
        CreateEmployeePanel.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 660, -1, -1));

        addphilhealth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addphilhealthActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addphilhealth, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 660, 160, -1));

        addposition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpositionActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addposition, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 570, 160, -1));
        CreateEmployeePanel.add(addbirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 160, 20));

        jLabel49.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel49.setText("Allowance:");
        CreateEmployeePanel.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 590, -1, 30));

        jLabel50.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel50.setText("Supervisor");
        CreateEmployeePanel.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 590, 80, 30));

        jLabel51.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel51.setText("Personal Information");
        CreateEmployeePanel.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, 30));

        jLabel52.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel52.setText("Rice Allowance");
        CreateEmployeePanel.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, 130, 30));

        jLabel53.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel53.setText("CLO Allowance");
        CreateEmployeePanel.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 660, 120, 30));

        addsupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addsupervisorActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addsupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 596, 160, -1));

        addbasicsalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbasicsalaryActionPerformed(evt);
            }
        });
        addbasicsalary.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                addbasicsalaryPropertyChange(evt);
            }
        });
        CreateEmployeePanel.add(addbasicsalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 480, 160, -1));

        addrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addriceActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 630, 160, -1));

        addcloth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addclothActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addcloth, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 660, 160, -1));

        addmaritalstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married" }));
        CreateEmployeePanel.add(addmaritalstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 160, -1));

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Emp. ID", "First Name", "Last Name", "Date of Birth", "Address", "Phone #", "TIN #", "SSS #", "Pag-ibig #", "PhilHealth #", "Position", "Status", "Supervisor", "Basic Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance", "Gross Semi Monthly Rate", "Rate Per Hour"
            }
        ));
        employeeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        employeeTable.setFillsViewportHeight(true);
        employeeTable.setGridColor(new java.awt.Color(153, 0, 0));
        employeeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(employeeTable);

        CreateEmployeePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, 310, 270));

        jLabel54.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel54.setText("Basic Pay:");
        CreateEmployeePanel.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, 30));

        jLabel55.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel55.setText("Financial Compensation Details");
        CreateEmployeePanel.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, -1, 30));

        jLabel56.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel56.setText("Job Status:");
        CreateEmployeePanel.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 330, -1, 30));

        jLabel57.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel57.setText("Government Information");
        CreateEmployeePanel.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, -1, 30));

        jLabel58.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel58.setText("Gross Semi-monthly Rate");
        CreateEmployeePanel.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 560, -1, 30));

        jLabel29.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel29.setText("Rate per hour:");
        CreateEmployeePanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, -1, 30));

        addphone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addphone1ActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addphone1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 600, 160, -1));

        rateperhour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateperhourActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(rateperhour, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 160, -1));

        save.setBackground(new java.awt.Color(255, 51, 51));
        save.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 640, 120, -1));

        delete.setBackground(new java.awt.Color(255, 51, 51));
        delete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 640, 120, -1));

        edit1.setBackground(new java.awt.Color(255, 51, 51));
        edit1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit1.setForeground(new java.awt.Color(255, 255, 255));
        edit1.setText("Edit");
        edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit1ActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 640, 120, -1));

        addfirstname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addfirstname1ActionPerformed(evt);
            }
        });
        CreateEmployeePanel.add(addfirstname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 160, -1));
        CreateEmployeePanel.add(phonenumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 200, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 60)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\T480\\Downloads\\smollogo.png")); // NOI18N
        jLabel2.setText("New Employee Form");
        CreateEmployeePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 880, 100));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 230, 230));
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\T480\\Downloads\\Elements blob (Facebook Cover) (2).png")); // NOI18N
        CreateEmployeePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -250, 1080, 570));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\T480\\Downloads\\Elements blob (Facebook Cover) (2).png")); // NOI18N
        CreateEmployeePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, 490));

        jPanel1.setBackground(new java.awt.Color(246, 232, 232));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );

        CreateEmployeePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, -120, 220, 950));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(CreateEmployeePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(CreateEmployeePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addaddressActionPerformed

    private void middleinitialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middleinitialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_middleinitialActionPerformed

    private void addlastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addlastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addlastnameActionPerformed

    private void ShowEmployeeNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowEmployeeNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShowEmployeeNumberActionPerformed

    private void FemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FemaleActionPerformed

    private void Male1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Male1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Male1ActionPerformed

    private void addtinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addtinActionPerformed

    private void addsssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addsssActionPerformed
        // TODO add your handling code here:
         String strTemp;
        if (addsss.getText().length() == 10) {
        strTemp = addsss.getText();
        strTemp = strTemp.substring(0, 2) + "-" + strTemp.substring(2, 9) + "-" + strTemp.substring(9, 10);
        addsss.setText(strTemp);
        }
        
    }//GEN-LAST:event_addsssActionPerformed

    private void addpagibigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpagibigActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addpagibigActionPerformed

    private void addphilhealthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addphilhealthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addphilhealthActionPerformed

    private void addpositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addpositionActionPerformed

    private void addsupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addsupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addsupervisorActionPerformed

    private void addbasicsalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbasicsalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addbasicsalaryActionPerformed

    private void addriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addriceActionPerformed

    private void addclothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addclothActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addclothActionPerformed

    private void addphone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addphone1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addphone1ActionPerformed

    private void rateperhourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateperhourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rateperhourActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        
        try {
        int employeenumber = Integer.parseInt(ShowEmployeeNumber.getText().trim());
        String firstName = addfirstname1.getText().trim();
        String lastName = addlastname.getText().trim();
        Date birthday = addbirthday.getDate();
        String address = addaddress.getText().trim();
        String phoneNumber = phonenumber.getText().trim();
        String currentPosition = addposition.getText().trim();
        String status = (String) addstatus.getSelectedItem();
        String tinNumber = addtin.getText().trim();
        String pagIbigNumber = addpagibig.getText().trim();
        String philhealthNumber = addphilhealth.getText().trim();
        String sssNumber = addsss.getText().trim();
        String immediateSupervisor = addsupervisor.getText().trim();

        // Check for empty or null values
        if (firstName.isEmpty() || lastName.isEmpty() || birthday == null || address.isEmpty() ||
            phoneNumber.isEmpty() || currentPosition.isEmpty() || status.isEmpty() || tinNumber.isEmpty() ||
            pagIbigNumber.isEmpty() || philhealthNumber.isEmpty() || sssNumber.isEmpty() || immediateSupervisor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Missing Information", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert string values to Double
        Double basicSalary = Double.valueOf(addbasicsalary.getText().trim());
        Double clothingAllowance = Double.valueOf(addcloth.getText().trim());
        Double riceSubsidy = Double.valueOf(addrice.getText().trim());
        Double phoneAllowance = Double.valueOf(phonenumber.getText().trim());
        
        Double grosssemimonthlyrate = Double.valueOf(grossMonthly.getText().trim());
        Double HourlyRate = Double.valueOf(rateperhour.getText().trim());

        // Create an instance of CreateEmployeeRecord with the retrieved information
        CreateEmployeeRecord employeeRecord = new CreateEmployeeRecord(
                
            employeenumber, lastName, firstName, birthday, address, phoneNumber, currentPosition, status, 
            tinNumber, pagIbigNumber, philhealthNumber, sssNumber, immediateSupervisor, 
            basicSalary, clothingAllowance, riceSubsidy, phoneAllowance, grosssemimonthlyrate, HourlyRate
        );

        // Add the employee details to the database
        employeeRecord.addEmployeeDetails(employeeRecord);
        addEmployeesignin(employeeRecord);
        //Set Add Mode background color
         Color neonSkyBlue = new Color(0, 255, 255);
  

        // Set the background color of the Text Field
        ShowEmployeeNumber.setBackground(neonSkyBlue);

       
        // Load the updated employee data into the JTable
         loadEmployeeData();
         
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for salary and allowances.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
             clear_employee_EntryActionPerformed(evt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
         // Get the employee number input from the text field
    String employeeNumberInput = ShowEmployeeNumber.getText().trim();

    // Check if the employee number input is empty
    if (employeeNumberInput.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter the employee number.", "Missing Information", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Convert the employee number input to an integer
        int employeeNumber = Integer.parseInt(employeeNumberInput);

        // Show a confirmation dialog
        int response = JOptionPane.showConfirmDialog(this, "Do you want to delete this data?", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            // Create a SQL DELETE statement to remove the record from the database
            String sql = "DELETE FROM employeedetails WHERE \"employeeno\" = ?";

            // Connect to the database and execute the DELETE statement
            try (Connection connection = DatabaseConnection.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {

                // Set the employee number as a parameter in the SQL statement
                pstmt.setInt(1, employeeNumber);

                // Execute the DELETE statement
                int rowsAffected = pstmt.executeUpdate();

                // Check if any rows were affected (i.e., if the record was deleted successfully)
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Record with employee number " + employeeNumber + " deleted successfully.", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
                    deleteEmployeesignin();
                    // Clear the employee details from the view
                    clear_employee_EntryActionPerformed(evt); // Call the clear button action
                    loadEmployeeData();
                } else {
                    JOptionPane.showMessageDialog(this, "No record found with employee number " + employeeNumber + ".", "Record Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            System.out.println("Deletion cancelled");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid employee number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "An error occurred while deleting the record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

        
    }//GEN-LAST:event_deleteActionPerformed

    private void edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit1ActionPerformed
      // TODO add your handling code here:
    try {
        // Retrieve input values
        int employeenumber = Integer.parseInt(ShowEmployeeNumber.getText().trim());
        String firstName = addfirstname1.getText().trim();
        String lastName = addlastname.getText().trim();
        java.util.Date utilDate = addbirthday.getDate();
        java.sql.Date birthday = new java.sql.Date(utilDate.getTime());
        String address = addaddress.getText().trim();
        String phoneNumber = phonenumber.getText().trim();
        String currentPosition = addposition.getText().trim();
        String status = (String) addstatus.getSelectedItem();
        String tinNumber = addtin.getText().trim();
        String pagIbigNumber = addpagibig.getText().trim();
        String philhealthNumber = addphilhealth.getText().trim();
        String sssNumber = addsss.getText().trim();
        String immediateSupervisor = addsupervisor.getText().trim();

        // Check for empty or null values
        if (firstName.isEmpty() || lastName.isEmpty() || birthday == null || address.isEmpty() ||
            phoneNumber.isEmpty() || currentPosition.isEmpty() || status.isEmpty() || tinNumber.isEmpty() ||
            pagIbigNumber.isEmpty() || philhealthNumber.isEmpty() || sssNumber.isEmpty() || immediateSupervisor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Missing Information", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert string values to Double
        Double basicSalary = Double.valueOf(addbasicsalary.getText().trim());
        Double clothingAllowance = Double.valueOf(addcloth.getText().trim());
        Double riceSubsidy = Double.valueOf(addrice.getText().trim());
        Double phoneAllowance = Double.valueOf(phonenumber.getText().trim());
        Double grosssemimonthlyrate = Double.valueOf(grossMonthly.getText().trim());
        Double HourlyRate = Double.valueOf(rateperhour.getText().trim());

        // Show a confirmation dialog
        int response = JOptionPane.showConfirmDialog(this, "Do you really want to edit this data?", "Confirm Edit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            // SQL Update query
            String sql = "UPDATE \"login\".\"EmployeeDetails\" SET " +
                         "\"firstname\" = ?, \"lastname\" = ?, \"birthday\" = ?, \"address\" = ?, \"phone_number\" = ?, " +
                         "\"current_position\" = ?, \"status\" = ?, \"tin_number\" = ?, \"pag_ibig_number\" = ?, " +
                         "\"philhealth_number\" = ?, \"sss_number\" = ?, \"immediate_supervisor\" = ?, " +
                         "\"basic_salary\" = ?, \"clothing_allowance\" = ?, \"rice_subsidy\" = ?, \"phone_allowance\" = ?, " +
                         "\"grosssemimonthlyrate\" = ?, \"HourlyRate\" = ? WHERE \"employeeno\" = ?";

            try (Connection connection = DatabaseConnection.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                // Set parameters
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setDate(3, birthday);
                statement.setString(4, address);
                statement.setString(5, phoneNumber);
                statement.setString(6, currentPosition);
                statement.setString(7, status);
                statement.setString(8, tinNumber);
                statement.setString(9, pagIbigNumber);
                statement.setString(10, philhealthNumber);
                statement.setString(11, sssNumber);
                statement.setString(12, immediateSupervisor);
                statement.setDouble(13, basicSalary);
                statement.setDouble(14, clothingAllowance);
                statement.setDouble(15, riceSubsidy);
                statement.setDouble(16, phoneAllowance);
                statement.setDouble(17, grosssemimonthlyrate);
                statement.setDouble(18, HourlyRate);
                statement.setInt(19, employeenumber);

                // Execute the update
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Employee details updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
                    clear_employee_EntryActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(this, "No employee found with the given employee number.", "Update Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            // Load the updated employee data into the JTable
            loadEmployeeData();
        } else {
            System.out.println("Edit cancelled");
        }

        // Set Add Mode background color
        Color neonSkyBlue = new Color(0, 255, 255);
        ShowEmployeeNumber.setBackground(neonSkyBlue);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

     
        
    }//GEN-LAST:event_edit1ActionPerformed

    private void addfirstname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addfirstname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addfirstname1ActionPerformed

    private void addbasicsalaryPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_addbasicsalaryPropertyChange
   
    }//GEN-LAST:event_addbasicsalaryPropertyChange

    private void grossMonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grossMonthlyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grossMonthlyActionPerformed

 private void clear_employee_EntryActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here: 
                
            addaddress.setText("");

            for (Component C : CreateEmployeePanel.getComponents()) {   
                if (C instanceof JTextField) {
                    JTextField jtf = (JTextField) C;
                    jtf.setText("");
                }
                // for clearing JTextArea components
                else if (C instanceof JTextArea) {
                    JTextArea jta = (JTextArea) C;
                    jta.setText("");
                }
                else if (C instanceof JDateChooser) {
                JDateChooser jdc = (JDateChooser) C;
                jdc.setDate(null);
                }

            }
            
            ShowEmployeeNumber.setBackground(Color.WHITE); // Set to default background color
            
        // Clear the JTable (All rows)
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        model.setRowCount(0); 
        
    }   
 
 
    static class NumberOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null && text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }
 
 

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CreateEmployeePanel;
    private javax.swing.JRadioButton Female;
    private javax.swing.JRadioButton Male1;
    public javax.swing.JTextField ShowEmployeeNumber;
    private javax.swing.JTextField addaddress;
    private javax.swing.JTextField addbasicsalary;
    private com.toedter.calendar.JDateChooser addbirthday;
    private javax.swing.JTextField addcloth;
    private javax.swing.JTextField addfirstname1;
    private javax.swing.JTextField addlastname;
    private javax.swing.JComboBox<String> addmaritalstatus;
    private javax.swing.JTextField addpagibig;
    private javax.swing.JTextField addphilhealth;
    private javax.swing.JTextField addphone1;
    private javax.swing.JTextField addposition;
    private javax.swing.JTextField addrice;
    private javax.swing.JTextField addsss;
    private com.toedter.calendar.JDateChooser addstartdate;
    private javax.swing.JComboBox<String> addstatus;
    private javax.swing.JTextField addsupervisor;
    private javax.swing.JTextField addtin;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit1;
    private javax.swing.JTable employeeTable;
    private javax.swing.JTextField grossMonthly;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> list;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JTextField middleinitial;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField phonenumber;
    private javax.swing.JTextField rateperhour;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables

   

    private void setTextFieldBackground(Color WHITE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
