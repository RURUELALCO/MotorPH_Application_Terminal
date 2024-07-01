package motorph.payroll_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Class representing an employee record and methods to manage it.
 */
public class CreateEmployeeRecord {
    
    private int employeenumber;
    private String lastname;
    private String firstname;
    private Date birthday;
    private String address;
    private String phoneNumber;
    private String currentPosition;
    private String status;
    private String tinNumber;
    private String pagIbigNumber;
    private String philhealthNumber;
    private String sssNumber;
    private String immediateSupervisor;
    private Double basicSalary;
    private Double clothingAllowance;
    private Double riceSubsidy;
    private Double phoneAllowance;
     
    private Double GrossSemimonthlyRate;
    private Double HourlyRate;

    // Constructor
   public CreateEmployeeRecord(int employeenumber, String lastname, String firstname, Date birthday, String address, String phoneNumber, String currentPosition, String status, String tinNumber, String pagIbigNumber, String philhealthNumber, String sssNumber, String immediateSupervisor, Double basicSalary, Double clothingAllowance, Double riceSubsidy, Double phoneAllowance, Double GrossSemimonthlyRate, Double HourlyRate) {
        
        this.employeenumber = employeenumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.currentPosition = currentPosition;
        this.status = status;
        this.tinNumber = tinNumber;
        this.pagIbigNumber = pagIbigNumber;
        this.philhealthNumber = philhealthNumber;
        this.sssNumber = sssNumber;
        this.immediateSupervisor = immediateSupervisor;
        this.basicSalary = basicSalary;
        this.clothingAllowance = clothingAllowance;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
      

        this.GrossSemimonthlyRate = GrossSemimonthlyRate;
        this.HourlyRate = HourlyRate;
    }

    // Getters
    public int getEmployeeNumber() { return employeenumber; }
    public String getLastname() { return lastname; }
    public String getFirstname() { return firstname; }
    public Date getBirthday() { return birthday; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getCurrentPosition() { return currentPosition; }
    public String getStatus() { return status; }
    public String getTinNumber() { return tinNumber; }
    public String getPagIbigNumber() { return pagIbigNumber; }
    public String getPhilhealthNumber() { return philhealthNumber; }
    public String getSssNumber() { return sssNumber; }
    public String getImmediateSupervisor() { return immediateSupervisor; }
    public Double getBasicSalary() { return basicSalary; }
    public Double getClothingAllowance() { return clothingAllowance; }
    public Double getRiceSubsidy() { return riceSubsidy; }
    public Double getPhoneAllowance() { return phoneAllowance; }
     
    public Double getGrossSemimonthlyRate() { return GrossSemimonthlyRate; }
    public Double getHourlyRate() { return HourlyRate; }

// Setters
    public void setEmployeeNumber(int employeenumber) { this.employeenumber = employeenumber; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public void setAddress(String address) { this.address = address; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setCurrentPosition(String currentPosition) { this.currentPosition = currentPosition; }
    public void setStatus(String status) { this.status = status; }
    public void setTinNumber(String tinNumber) { this.tinNumber = tinNumber; }
    public void setPagIbigNumber(String pagIbigNumber) { this.pagIbigNumber = pagIbigNumber; }
    public void setPhilhealthNumber(String philhealthNumber) { this.philhealthNumber = philhealthNumber; }
    public void setSssNumber(String sssNumber) { this.sssNumber = sssNumber; }
    public void setImmediateSupervisor(String immediateSupervisor) { this.immediateSupervisor = immediateSupervisor; }
    public void setBasicSalary(Double basicSalary) { this.basicSalary = basicSalary; }
    public void setClothingAllowance(Double clothingAllowance) { this.clothingAllowance = clothingAllowance; }
    public void setRiceSubsidy(Double riceSubsidy) { this.riceSubsidy = riceSubsidy; }
    public void setPhoneAllowance(Double phoneAllowance) { this.phoneAllowance = phoneAllowance; }
   
    public void setGrossSemimonthlyRate(Double GrossSemimonthlyRate) { this.GrossSemimonthlyRate = GrossSemimonthlyRate; }
    public void setHourlyRate(Double HourlyRate) { this.HourlyRate = HourlyRate; }
    
    // Method to retrieve the maximum existing employee number from the database
   
    // Method to add employee details to the database
    public void addEmployeeDetails(CreateEmployeeRecord employee) {
        
       
      // Show a confirmation dialog
    int response = JOptionPane.showConfirmDialog(null, "Do you want to save this data?", "Confirm Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

    if (response == JOptionPane.YES_OPTION) {
        String sql = "INSERT INTO login.\"EmployeeDetails\"(\n" +
                     "    \"EmployeeNo\", lastname, firstname, birthday, address, phone_number, sss_number, philhealth_number, tin_number, pag_ibig_number, status, current_position, immediate_supervisor, basic_salary, rice_subsidy, phone_allowance, clothing_allowance, \"GrossSemimonthlyRate\", \"HourlyRate\")\n" +
                     "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, employee.getEmployeeNumber());
            pstmt.setString(2, employee.getLastname());
            pstmt.setString(3, employee.getFirstname());
            pstmt.setDate(4, new java.sql.Date(employee.getBirthday().getTime()));
            pstmt.setString(5, employee.getAddress());
            pstmt.setString(6, employee.getPhoneNumber());
            pstmt.setString(7, employee.getSssNumber());
            pstmt.setString(8, employee.getPhilhealthNumber());
            pstmt.setString(9, employee.getTinNumber());
            pstmt.setString(10, employee.getPagIbigNumber());
            pstmt.setString(11, employee.getStatus());
            pstmt.setString(12, employee.getCurrentPosition());
            pstmt.setString(13, employee.getImmediateSupervisor());
            pstmt.setDouble(14, employee.getBasicSalary());
            pstmt.setDouble(15, employee.getRiceSubsidy());
            pstmt.setDouble(16, employee.getPhoneAllowance());
            pstmt.setDouble(17, employee.getClothingAllowance());
            pstmt.setDouble(18, employee.getGrossSemimonthlyRate());
            pstmt.setDouble(19, employee.getHourlyRate());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Employee added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Employee added successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No employee was added", "Failure", JOptionPane.ERROR_MESSAGE);
                System.out.println("No employee was added");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
        }
    } else {
        System.out.println("Save cancelled");
    }
}
    
    
    public void EditEmployeeDetails(CreateEmployeeRecord employee) {
    
    
    
    
    
    }

    void deleteEmployeesignin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}














   /*
    // Method to add employee details to the database
    public void addEmployeeDetails(CreateEmployeeRecord employee) {
        String sql = "INSERT INTO employeedata (employee_number, lastname, firstname, birthday, address, phone_number, "
                   + "current_position, status, tin_number, pag_ibig_number, philhealth_number, "
                   + "sss_number, immediate_supervisor, basic_salary, clothing_allowance, rice_subsidy, "
                   + "phone_allowance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, employee.getEmployeeNumber());
            pstmt.setString(2, employee.getLastname());
            pstmt.setString(3, employee.getFirstname());
            pstmt.setDate(4, new java.sql.Date(employee.getBirthday().getTime()));
            pstmt.setString(5, employee.getAddress());
            pstmt.setString(6, employee.getPhoneNumber());
            pstmt.setString(7, employee.getCurrentPosition());
            pstmt.setString(8, employee.getStatus());
            pstmt.setString(9, employee.getTinNumber());
            pstmt.setString(10, employee.getPagIbigNumber());
            pstmt.setString(11, employee.getPhilhealthNumber());
            pstmt.setString(12, employee.getSssNumber());
            pstmt.setString(13, employee.getImmediateSupervisor());
            pstmt.setDouble(14, employee.getBasicSalary());
            pstmt.setDouble(15, employee.getClothingAllowance());
            pstmt.setDouble(16, employee.getRiceSubsidy());
            pstmt.setDouble(17, employee.getPhoneAllowance());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Employee added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Employee added successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No employee was added", "Failure", JOptionPane.ERROR_MESSAGE);
                System.out.println("No employee was added");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    */








