/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class NhanVienDao {

    JDBCConnection connection;

    public NhanVienDao() {
    }

    public ArrayList<NhanVien> readDB() {
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        connection = new JDBCConnection();
        try {
            String sql = "SELECT * FROM NHANVIEN";
            ResultSet rs = connection.sqlQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String manv = rs.getString("MaNV");
                    String tennv = rs.getString("TenNV");
                    LocalDate ngaysinh = rs.getDate("NgaySinh").toLocalDate();
                    String gioitinh = rs.getString("GioiTinh");
                    String diachi = rs.getString("DiaChi");
                    String sdt = rs.getString("SDT");
                    String hinhanh = rs.getString("Hinh");

                    dsnv.add(new NhanVien(manv, tennv, ngaysinh, gioitinh, diachi, sdt, hinhanh));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng nhân viên");
        } 
        return dsnv;
    }

    public ArrayList<NhanVien> search(String columnName, String value) {
        connection = new JDBCConnection();
        ArrayList<NhanVien> dsnv = new ArrayList<>();

        try {
            String sql = "SELECT * FROM NHANVIEN WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet rs = connection.sqlQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String manv = rs.getString("MaNV");
                    String tennv = rs.getString("TenNV");
                    LocalDate ngaysinh = rs.getDate("NgaySinh").toLocalDate();
                    String gioitinh = rs.getString("GioiTinh");
                    String diachi = rs.getString("DiaChi");
                    String sdt = rs.getString("SDT");                    
                    dsnv.add(new NhanVien(manv, tennv, ngaysinh,gioitinh, diachi, sdt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng nhân viên");
        } 

        return dsnv;
    }

    public Boolean add(NhanVien nv) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `NHANVIEN` (`MaNV`, `TenNV`, `NgaySinh`,`GioiTinh`, `DiaChi`, `SDT`, `HinhAnh`) VALUES ('"
                + nv.getMaNV() + "', '"
                + nv.getTenNV() + "', '"
                + nv.getNgaySinh() + "', '"
                + nv.getDiaChi() + "', '"
                + nv.getSDT() + "', '"
                + nv.getHinhAnh()+ "');");
        //connection.closeConnect();
        return ok;
    }

    public Boolean delete(String manv) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `NHANVIEN` WHERE `NHANVIEN`.`MaNV` = '" + manv + "'");
        //connection.closeConnect();
        return ok;
    }

    public Boolean update(String MaNV, String TenNV, LocalDate NgaySinh,String GioiTinh, String DiaChi, String SDT) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update NHANVIEN Set "
                + "TenNV='" + TenNV
                + "',NgaySinh='" + NgaySinh
                + "',GioiTinh='" + GioiTinh
                + "',DiaChi='" + DiaChi
                + "',SDT='" + SDT               
                + "' where MaNV='" + MaNV + "'");
        //connection.closeConnect();
        return ok;
    }

}
