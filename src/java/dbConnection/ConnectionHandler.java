/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A
 */
public class ConnectionHandler {
    
        public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (SQLException ex) {
                Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

      
        return con;
    }
        
        public static void main(String[] args) {
            ConnectionHandler connectionHandler = new ConnectionHandler();
            Connection connection = ConnectionHandler.getConnection();
            System.out.println("connection = " + connection);
    }
}
