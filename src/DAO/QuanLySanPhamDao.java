/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.SanPham;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class QuanLySanPhamDao {
    List<SanPham> sanpham = new ArrayList<>();
    public int add(SanPham sp){
        sanpham.add(sp);
        return 1;
    }
    JDBCConnection connection;
    
    public ArrayList<SanPham> addsp() {
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
                    float dongia = r.getFloat("DonGia");
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
    
    public List <SanPham> getAllSanPham(){
        return sanpham;
    }

    public int delSanPhamByID(String ma){
        for (SanPham sp : sanpham){
            if(sp.getMaSP().equalsIgnoreCase(ma)){
                sanpham.remove(sp);
                return 1;
            }
        }
        return -1;
        
    }
    
    public SanPham getSanPhamByID(String id){
        for (SanPham sp : sanpham){
            if(sp.getMaSP().equalsIgnoreCase(id)){
                return sp;
            }
        }
        return null;
    }
   
    public int updateSanPhamByID(SanPham spNew){
        for(SanPham sp : sanpham){
            if(sp.getMaSP().equalsIgnoreCase(spNew.getMaSP())){
                sp.setMaSP(spNew.getMaSP());
                sp.setMaHSP(spNew.getMaHSP());
                sp.setTenSP(spNew.getTenSP());
                sp.setSoLuong(spNew.getSoLuong());
                sp.setDonGia(spNew.getDonGia());
                sp.setFileNameHinhAnh(spNew.getFileNameHinhAnh());
                return 1;
                
            }
        }
        return -1;
    }
}

