package com.qlchdt.dao;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCConnection {
    
         
    public static Connection getJDBCConnection(){
        final String url = "jdbc:mysql://localhost:3306/qlchdt";
        final String user = "root";
        final String password = "admin1234";                          
      try {                          
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
             ex.printStackTrace();
             
        } catch (SQLException ex) {
             ex.printStackTrace();
        
        }
    return null;    
     
}  
    public static void main(String[] args) {
        Connection connection = getJDBCConnection();
        if(connection != null) {
            System.out.println("Thanh cong");
        }else {
                System.out.println("That bai");
        }
    }
}
