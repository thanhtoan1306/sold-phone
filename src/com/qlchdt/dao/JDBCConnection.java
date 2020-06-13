package com.qlchdt.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class JDBCConnection {

    static int countConection = 0;
    static int countQuery = 0;
    static int countUpdate = 0;

    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    String DB_Name = null;
    String user_Name = null;
    String pass = null;

    String ipAddress = "localhost:3306";

    public JDBCConnection() {
        checkDriver();
        DB_Name = "qlchdt";
        user_Name = "root";
        pass = "admin1234";
        setupConnect();
    }

    private void setupConnect() {
        try {
            String url = "jdbc:mysql://" + ipAddress + "/" + DB_Name + "?useUnicode=true&characterEncoding=UTF-8";
            conn = DriverManager.getConnection(url, user_Name, pass);
            stmt = conn.createStatement();
            countConection++;
            System.out.println("**\n" + countConection + ": Success! Đã kết nối tới '" + DB_Name + "'");

        } catch (SQLException e) {
            System.err.println("-- ERROR! Không thể kết nối tới '" + DB_Name + "'");
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể kết nối tới '" + DB_Name + "'");
        }
    }

    // check logIn, connect
    public Boolean checkConnect() {
        if (conn == null || stmt == null) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Chưa thiết lập kết nối tới '" + DB_Name + "'. Vui lòng đăng nhập để thiết lập kết nối!");
            return false;
        }
        return true;
    }

    // check driver
    private void checkDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không tìm thấy Driver mySql");
        }
    }
    
        // lấy data theo câu query
    public ResultSet sqlQuery(String qry) {
        if (checkConnect()) {
            try {
                rset = stmt.executeQuery(qry);
                countQuery++;
                System.out.println(countQuery + ": Success Query! " + qry);
                return rset;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "-- ERROR! Không thể lấy dữ liệu từ " + DB_Name + "\n" + ex.getLocalizedMessage());
                return null;
            }
        }
        return null;
    }

    // ghi data theo câu update
    public Boolean sqlUpdate(String qry) {
        if (checkConnect()) {
            try {
                stmt.executeUpdate(qry);
                countUpdate++;
                System.out.println(countUpdate + ": Success Update! " + qry);
                return true;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "-- ERROR! Không thể ghi dữ liệu xuống " + DB_Name + "\n" + ex.getLocalizedMessage());
                //System.err.println(ex.getLocalizedMessage());
                return false;
            }
        }
        return false;
    }

    // đóng connection
    public void closeConnect() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            System.out.println("Success! Đóng kết nối tới '" + DB_Name + "' thành công.\n**");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể đóng kết nối tới " + DB_Name + "\n" + ex.getLocalizedMessage());
        }
    }


}
