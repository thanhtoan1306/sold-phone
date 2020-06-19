/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.Model.NhanVien;
import java.sql.Date;
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
        //System.out.println(connection.checkConnect());
        try {
            String sql = "SELECT * FROM NHANVIEN";
            ResultSet rs = connection.sqlQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String manv = rs.getString("MaNV");
                    String tennv = rs.getString("TenNV");
                    LocalDate ngaysinh = rs.getDate("NgaySinh").toLocalDate();
                    String gioitinh = rs.getString("GioiTinh");
                    String sdt = rs.getString("SDT");
                    String diachi = rs.getString("DiaChi");
                    String hinh = rs.getString("Hinh");
                    dsnv.add(new NhanVien(manv, tennv, ngaysinh, gioitinh, sdt, diachi, hinh));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng nhân viên");
        } finally{
        
        connection.closeConnect();
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
                    String sdt = rs.getString("SDT");
                    String diachi = rs.getString("DiaChi");
                    dsnv.add(new NhanVien(manv, tennv, ngaysinh,gioitinh, sdt, diachi));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng nhân viên");
        } 

        return dsnv;
    }

    public Boolean add(NhanVien nv) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `NHANVIEN` (`MaNV`, `TenNV`, `NgaySinh`, `GioiTinh`, `SDT`, `DiaChi`, `Hinh`) VALUES ('"
                + nv.getMaNV() + "', '"
                + nv.getTenNV() + "', '"
                + Date.valueOf(nv.getNgaySinh()) + "', '"
                + nv.getGioiTinh()+ "', '"
                + nv.getSDT() + "', '"
                + nv.getDiaChi() + "', '"
                + nv.getHinhAnh()+ "');");
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String manv) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `NHANVIEN` WHERE `NHANVIEN`.`MaNV` = '" + manv + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String MaNV, String TenNV, LocalDate NgaySinh,String GioiTinh, String SDT, String DiaChi) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update NHANVIEN Set "
                + "TenNV='" + TenNV
                + "',NgaySinh='" + NgaySinh
                + "',GioiTinh='" + GioiTinh
                + "',SDT='" + SDT
                + "',DiaChi='" + DiaChi
                + "' where MaNV='" + MaNV + "'");
        connection.closeConnect();
        return ok;
    }
    
}
