/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.model.SanPham;
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
public class SanPhamDao {

    JDBCConnection connection;

    public SanPhamDao() {
        
    }

    public ArrayList<SanPham> readDB() {
        connection = new JDBCConnection();

        ArrayList<SanPham> dssp = new ArrayList<>();

        try {

            String sql = "SELECT * FROM SANPHAM";
          
            ResultSet rs = connection.sqlQuery(sql);

            while (rs.next()) {

                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setMaHSP(rs.getString("MaHSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDonGia(rs.getFloat("DonGia"));
                sp.setSoLuong(rs.getInt("SL"));
                sp.setFileNameHinhAnh(rs.getString("HinhAnh"));

                dssp.add(sp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng sản phẩm");
        }
        return dssp;
    }

    public ArrayList<SanPham> search(String columnName, String value) {

        connection = new JDBCConnection();
        ArrayList<SanPham> dssp = new ArrayList<>();
        
        try {
             String sql = "SELECT * FROM SANPHAM WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet rs = connection.sqlQuery(sql);                       
            if (rs != null) {
                while (rs.next()) {
                    String masp = rs.getString("MaSP");
                    String hangsp = rs.getString("MaHSP");
                    String tensp = rs.getString("TenSP");
                    float dongia = rs.getFloat("DonGia");
                    int soluong = rs.getInt("SL");
                    String url = rs.getString("HinhAnh");
                    dssp.add(new SanPham(masp, hangsp, tensp, dongia, soluong, url));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng sản phẩm");
        }

        return dssp;
    }
    
        public Boolean add(SanPham sp) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `SANPHAM` (`MaSP`, `MaHSP`, `TenSP`, `DonGia`, `SL`, `HinhAnh`) VALUES ('"
                + sp.getMaSP() + "', '"
                + sp.getMaHSP() + "', '"
                + sp.getTenSP() + "', '"
                + sp.getDonGia() + "', '"
                + sp.getSoLuong() + "', '"
                + sp.getFileNameHinhAnh() +"');");
        //connection.closeConnect();
        return ok;
    }

    public Boolean delete(String masp) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `SANPHAM` WHERE `SANPHAM`.`MaSP` = '" + masp + "'");
        //connection.closeConnect();
        return ok;
    }

    public Boolean update(String MaSP, String MaLSP, String TenSP, float DonGia, int SoLuong, String filename) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update SANPHAM Set "
                + "MaLSP='" + MaLSP
                + "',TenSP='" + TenSP
                + "',DonGia='" + DonGia
                + "',SL='" + SoLuong
                + "',HinhAnh='" + filename                
                + "' where MaSP='" + MaSP + "'");
//        connection.closeConnect();
        return ok;
    }

    public Boolean updateSoLuong(String MaSP, int SoLuong) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update SANPHAM Set "
                + "SL='" + SoLuong
                + "' where MaSP='" + MaSP + "'");
        //connection.closeConnect();
        return ok;
    }
    



}
