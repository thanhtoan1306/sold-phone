/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.model.PhieuNhap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class PhieuNhapDao {

    JDBCConnection connection;

    public ArrayList<PhieuNhap> readDB() {
        connection = new JDBCConnection();
        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PHIEUNHAP";
            ResultSet rs = connection.sqlQuery(sql);
            if (rs != null) {

                while (rs.next()) {
                    PhieuNhap pn = new PhieuNhap();

                    pn.setMaPN(rs.getString(1));
                    pn.setMaNCC(rs.getString(2));
                    pn.setMaNV(rs.getString(3));
                    pn.setNgayNhap(rs.getDate(4).toLocalDate());
                    pn.setTongTien(rs.getFloat(5));
                    dspn.add(pn);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phiếu nhập");
        }
        return dspn;
    }

    public Boolean add(PhieuNhap pn) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO phieunhap(MaPN,MaNCC,MaNV,NgayNhap,GioNhap,TongTien) VALUES ('"
                + pn.getMaPN() + "','"
                + pn.getMaNCC() + "','"
                + pn.getMaNV() + "','"
                + pn.getNgayNhap() + "','"
                + pn.getTongTien() + "');");
        //connection.closeConnect();
        return ok;
    }

}
