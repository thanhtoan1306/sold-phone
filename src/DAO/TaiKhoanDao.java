/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.TaiKhoan;
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
        ArrayList<TaiKhoan> dstk = new ArrayList<>(); // khởi tạo 1 array
        connection = new JDBCConnection();            // khởi tạo đối tượng connect

        try {
            String sql = "SELECT * FROM TAIKHOAN";
            ResultSet rs = connection.sqlQuery(sql);    //lấy data thông qua câu sql
            if (rs != null) {
                while (rs.next()) {
                    String tentk = rs.getString("TenTK");   //key phải trùng với key ở db
                    String matkhau = rs.getString("MK");
                    String manv = rs.getString("MaNV");
                    String maquyen = rs.getString("MaQuyen");
                    dstk.add(new TaiKhoan(tentk, matkhau, manv, maquyen));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng nhân viên");
        } finally {

            connection.closeConnect();
        }
        return dstk;
    }

    public ArrayList<TaiKhoan> search(String columnName, String value) {
        connection = new JDBCConnection();
        ArrayList<TaiKhoan> dstk = new ArrayList<>();

        try {
            String sql = "SELECT * FROM TAIKHOAN WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet rs = connection.sqlQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String tentk = rs.getString("TenTK");
                    String matkhau = rs.getString("MK");
                    String manv = rs.getString("MaNV");
                    String maquyen = rs.getString("MaQuyen");
                    dstk.add(new TaiKhoan(tentk, matkhau, manv, maquyen));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng tài khoản");
        }

        return dstk;
    }

    public Boolean add(TaiKhoan tk) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `TAIKHOAN` (`TenTK`, `MK`, `MaNV`, `MaQuyen`) VALUES ('"
                + tk.getTentk() + "', '"
                + tk.getMk() + "', '"
                + tk.getManv() + "', '"
                + tk.getMaquyen() + "');");
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String manv) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `TAIKHOAN` WHERE `TAIKHOAN`.`MaNV` = '" + manv + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String tentk, String matkhau, String manv, String maquyen) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update TAIKHOAN Set "
                + "TenTK='" + tentk
                + "',MK='" + matkhau
                + "',MaQuyen='" + maquyen
                + "' where MaNV='" + manv + "'");
        connection.closeConnect();
        return ok;
    }
}
