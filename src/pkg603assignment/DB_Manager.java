/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg603assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sandra
 */
public class DB_Manager {
    private static final String USER_NAME = "abc";
    private static final String PASSWORD = "abc";
    public static Connection conn;
    
    
     private static final String URL = "jdbc:derby://localhost:1527/Test1;create=true";
     
     public void establishConnection()
     {
         try{
            conn=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println(URL+" connected...");
            }
            catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            }   
     }
      public DB_Manager() {
        establishConnection();
    }

    public static void main(String[] args) {
        DB_Manager dbManager = new DB_Manager();

        System.out.println(dbManager.getConnection());

    }

    public Connection getConnection() {
        return this.conn;
    }
    
  


    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                
            }
        }
    }

    public ResultSet myQuery(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void myUpdate(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
