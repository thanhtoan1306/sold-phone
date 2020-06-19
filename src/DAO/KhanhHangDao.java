/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.Model.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class KhanhHangDao {

    JDBCConnection connection;

    public KhanhHangDao() {
    }

    public ArrayList<KhachHang> readDB() {
        connection = new JDBCConnection();
        ArrayList<KhachHang> dskh = new ArrayList<>();
        try {
            String sql = "SELECT * FROM KHACHHANG";
            ResultSet rs = connection.sqlQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String makh = rs.getString("MaKH");
                    String tenkh = rs.getString("TenKH");
                    String diachi = rs.getString("DiaChi");
                    String sdt = rs.getString("SDT");

                    dskh.add(new KhachHang(makh, tenkh, diachi, sdt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng khách hàng");
        }finally{
        
        connection.closeConnect();
                }
        return dskh;
    }

    public ArrayList<KhachHang> search(String columnName, String value) {
        connection = new JDBCConnection();
        ArrayList<KhachHang> dskh = new ArrayList<>();

        try {
            String sql = "SELECT * FROM KHACHHANG WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet rs = connection.sqlQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String makh = rs.getString("MaKH");
                    String tenkh = rs.getString("TenKH");
                    String diachi = rs.getString("DiaChi");
                    String sdt = rs.getString("SDT");
                    dskh.add(new KhachHang(makh, tenkh, diachi, sdt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng khách hàng");
        }finally{
        
        connection.closeConnect();
        }

        return dskh;
    }

    public Boolean add(KhachHang kh) {
        connection = new JDBCConnection();

        Boolean ok = connection.sqlUpdate(
                
                
                
                
                "INSERT INTO `khachhang`(`MaKH`,`TenKH`,`DiaChi`,`SDT`) VALUES ('"
                + kh.getMaKH() + "', '"
                + kh.getTenKH() + "', '"
                + kh.getDiaChi() + "','"
                + kh.getSDT() + "');"
        
        
        
        
        
        );
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String makh) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `khachhang` WHERE `khachhang`.`MaKH` = '" + makh + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String MaKH, String TenKH, String DiaChi, String SDT) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update KHACHHANG Set "
                + "TenKH='" + TenKH
                + "', DiaChi='" + DiaChi
                + "', SDT='" + SDT
                + "' where MaKH='" + MaKH + "'");
        connection.closeConnect();
        return ok;
    }
    
    
    
       public void close() {
        connection.closeConnect();
    }
    

}
