package motorph.payroll_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//handles all database-related connections.
public class DatabaseConnection {

    // Database connection URL
    private static final String URL = "jdbc:postgresql://localhost:5432/Database";  
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private static Connection connection; // Declare connection as a static field

   // Method to establish a connection to the database
    public static Connection connect() throws SQLException {
      
        try {
            // Attempt to establish a connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful."); // Log successful connection
            return connection;
          
        } catch (SQLException e) {
            // Log error message and exception stack trace
            System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace(); // Log exception stack trace
            throw new SQLException("Connection unsuccessful: " + e.getMessage(), e);
        }
    }

    // Method to close the database connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the database connection: " + e.getMessage());
            e.printStackTrace(); // Log exception stack trace
        }
    }
    
    
}
