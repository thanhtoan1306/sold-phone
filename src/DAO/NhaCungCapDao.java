/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.Model.NhaCungCap;
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

    public NhaCungCapDao() {
    }

    public ArrayList<NhaCungCap> readDB() {

        ArrayList<NhaCungCap> dsncc = new ArrayList<>();
        connection = new JDBCConnection();

        try {

            String sql = "SELECT * FROM NHACUNGCAP";
            ResultSet rs = connection.sqlQuery(sql);

            while (rs.next()) {

                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getString("MaNCC"));
                ncc.setTenNCC(rs.getString("TenNCC"));
                ncc.setDiaChi(rs.getString("DiaChi"));
                ncc.setSDT(rs.getString("SDT"));

                dsncc.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnect();
        }
        return dsncc;
    }

    public Boolean add(NhaCungCap ncc) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate(
                
                
                                
                "INSERT INTO `NHACUNGCAP`(`MaNCC`,`TenNCC`,`DiaChi`,`SDT`) VALUES ('"
                + ncc.getMaNCC() + "', '"
                + ncc.getTenNCC() + "', '"
                + ncc.getDiaChi()+ "','"
                + ncc.getSDT() + "');"
                
             
        
        
        
        
        );
        connection.closeConnect();
        return ok;
    }

    public ArrayList<NhaCungCap> search(String columnName, String value) {
        connection = new JDBCConnection();
        ArrayList<NhaCungCap> dsncc = new ArrayList<>();

        try {
            String qry = "SELECT * FROM sanpham WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = connection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String mancc = r.getString(1);
                    String tenncc = r.getString(2);
                    String diachi = r.getString(3);
                    String sdt = r.getString(4);                    
                    dsncc.add(new NhaCungCap(mancc, tenncc, diachi, sdt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng sản phẩm");
        } finally {
            connection.closeConnect();
        }

        return dsncc;
    }

    public Boolean delete(String mancc) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `nhacungcap` WHERE `nhacungcap`.`MaNCC` = '" + mancc + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String ma, String ten, String diachi, String sdt) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update NhaCungCap Set MaNCC='" + ma + "',TenNCC='" + ten + "',DiaChi='" + diachi + "',SDT='" + sdt + "' where MaNCC='" + ma + "'");
        connection.closeConnect();
        return ok;
    }

    public void close() {
        connection.closeConnect();
    }

}
