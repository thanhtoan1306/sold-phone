/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.Model.SanPham;
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
            String qry = "SELECT * FROM SANPHAM";
            ResultSet r = connection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String masp = r.getString("MaSP");
                    String loaisp = r.getString("MaHSP");
                    String tensp = r.getString("TenSP");
                    double dongia = r.getDouble("DonGia");
                    int soluong = r.getInt("SL");
                    String url = r.getString("HinhAnh");
                    
                    dssp.add(new SanPham(masp, loaisp, tensp, dongia, soluong, url));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng sản phẩm"); 
        } finally {
            connection.closeConnect();
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
        } finally {

            connection.closeConnect();
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
                + sp.getFileNameHinhAnh() + "');");
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String masp) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `SANPHAM` WHERE `SANPHAM`.`MaSP` = '" + masp + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String MaSP, String MaHSP, String TenSP, float DonGia, int SoLuong, String filename) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update SANPHAM Set "
                + "MaHSP='" + MaHSP
                + "',TenSP='" + TenSP
                + "',DonGia='" + DonGia
                + "',SL='" + SoLuong
                + "',HinhAnh='" + filename
                + "' where MaSP='" + MaSP + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean updateSoLuong(String MaSP, int SoLuong) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update SANPHAM Set "
                + "SL='" + SoLuong
                + "' where MaSP='" + MaSP + "'");
        connection.closeConnect();
        return ok;
    }

}
