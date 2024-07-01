/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package motorph.payroll_system;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author arc
 */
public class Payroll_System {


    private static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() throws InterruptedException {
      
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        
        // Create an instance of the Login JFrame
        Login loginFrame = new Login();
        
        // Make the Login frame visible
        loginFrame.setVisible(true);
        
        
        
        
        
        try {
            Connection connect = DatabaseConnection.connect();
        } catch (SQLException ex) {
            Logger.getLogger(Payroll_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
}
