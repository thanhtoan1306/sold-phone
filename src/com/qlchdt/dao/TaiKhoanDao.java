/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.dao.JDBCConnection;
import com.qlchdt.model.TaiKhoan;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class TaiKhoanDao {

    JDBCConnection connection;

      

    public TaiKhoanDao() {

    }

    public ArrayList<TaiKhoan> readDB() {
        connection = new JDBCConnection();
        ArrayList<TaiKhoan> dstk = new ArrayList<>();
        try {
            String qry = "SELECT * FROM taikhoan";
            ResultSet r = connection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String ten = r.getString("TenTK");
                    String pass = r.getString("MK");
                    String manv = r.getString("MaNV");
                    String maquyen = r.getString("MaQuyen");
                    
                    dstk.add(new TaiKhoan(ten, pass, manv, maquyen));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng tài khoản");
        } finally {
            connection.closeConnect();
        }
        return dstk;
    }

    public Boolean add(TaiKhoan tk) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `taikhoan` (`TenTK`, `MK`, `MaNV`, `MAQuyen`) VALUES ('"
                + tk.getUserName()+ "', '" + tk.getPassWord()+ "', '" + tk.getMaNV() + "', '" + tk.getMaQuyen() + "');");
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String username) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `taikhoan` WHERE `taikhoan`.`TenTK` = '" + username + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String username, String pass, String maNV, String maQuyen) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update taikhoan Set MK='" + pass + "',MaNV='" + maNV
                + "',MaQuyen='" + maQuyen + "' where TenTK='" + username + "'");
        connection.closeConnect();
        return ok;
    }

    public void close() {
        connection.closeConnect();
    }
}
