/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.model.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class NhaCungCapDao {

    JDBCConnection connection;

    public ArrayList<NhaCungCap> readDB() {
        ArrayList<NhaCungCap> dsncc = new ArrayList<>();
        connection = new JDBCConnection();
        try {
            String qry = "SELECT * FROM nhacungcap";
            ResultSet r = connection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String ma = r.getString(1);
                    String ten = r.getString(2);
                    String diachi = r.getString(3);
                    String sdt = r.getString(4);

                    dsncc.add(new NhaCungCap(ma, ten, diachi, sdt));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không thấy data cần tìm trong ResultSet");
        }finally{
        
        connection.closeConnect();
                }
        return dsncc;
    }

    public ArrayList<NhaCungCap> search(String columnName, String value) {

        connection = new JDBCConnection();
        ArrayList<NhaCungCap> dsncc = new ArrayList<>();

        try {
            String sql = "SELECT * FROM NHACUNGCAP WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet rs = connection.sqlQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String mancc = rs.getString("MaSP");
                    String tenncc = rs.getString("MaHSP");
                    String diachi = rs.getString("TenSP");
                    String sdt = rs.getString("DonGia");

                    dsncc.add(new NhaCungCap(mancc, tenncc, diachi, sdt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng sản phẩm");
        }

        return dsncc;
    }

    public Boolean delete(String mancc) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `NHACUNGCAP` WHERE `NHACUNGCAP`.`MaNCC` = '" + mancc + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String MaNCC, String TenNCC, String DiaChi, String SDT) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update NHACUNGCAP Set "
                + "MaLSP='" + MaNCC
                + "',TenSP='" + TenNCC
                + "',DonGia='" + DiaChi
                + "',SL='" + SDT
                + "'");
      connection.closeConnect();
        return ok;
    }

    public Boolean add(NhaCungCap ncc) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `DiaChi`,`SDT`) VALUES ('" + ncc.getMaNCC() + "', '"
                + ncc.getTenNCC() + "', '"
                + ncc.getDiaChi() + "','"
                + ncc.getSDT()
                + "');");

        connection.closeConnect();
        return ok;
    }

}
