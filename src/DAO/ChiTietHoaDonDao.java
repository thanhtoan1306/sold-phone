/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.Model.ChiTietHoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ChiTietHoaDonDao {

    JDBCConnection connection;

    public ChiTietHoaDonDao() {
    }

    public ArrayList readDB() {
        connection = new JDBCConnection();
        ArrayList<ChiTietHoaDon> dshd = new ArrayList<>();
        try {
            String qry = "SELECT * FROM CTHD";
            ResultSet rs = connection.sqlQuery(qry);
            if (rs != null) {
                while (rs.next()) {
                    ChiTietHoaDon hd = new ChiTietHoaDon(rs.getString("MaHD"),
                            rs.getString("MaSP"), 
                            rs.getInt("SoLuong"),
                            rs.getDouble("DonGia"));
                    dshd.add(hd);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu !!");
        } finally {

            connection.closeConnect();
        }
        return dshd;
    }

    public Boolean add(ChiTietHoaDon hd) {
        connection = new JDBCConnection();
        Boolean success = connection.sqlUpdate("INSERT INTO cthd(`MaHD`,`MaSP`,`SoLuong`,`DonGia`) VALUES ('"
                + hd.getMaHoaDon() + "','"
                + hd.getMaSanPham() + "','"
                + hd.getSoLuong() + "','"
                + hd.getDonGia() + "');");
        connection.closeConnect();
        return success;
    }

    public Boolean delete(String _mahd, String _masp) {
        connection = new JDBCConnection();
        Boolean success = connection.sqlUpdate("DELETE FROM cthd WHERE "
                + "MaHD='" + _mahd
                + "' AND MaSP='" + _masp + "';");
        connection.closeConnect();
        return success;
    }

    public Boolean deleteAll(String _mahd) {
        connection = new JDBCConnection();
        Boolean success = connection.sqlUpdate("DELETE FROM cthd WHERE MaHD='" + _mahd + "';");
        connection.closeConnect();
        return success;
    }

    public Boolean update(ChiTietHoaDon ct) {
        connection = new JDBCConnection();
        Boolean success = connection.sqlUpdate("UPDATE cthd set "
                + "SoLuong='" + ct.getSoLuong()
                + "', DonGia='" + ct.getDonGia()
                + "' WHERE MaHD='" + ct.getMaHoaDon() + "' AND MaSP='" + ct.getMaSanPham() + "';");
        connection.closeConnect();
        return success;
    }

    public Boolean update(String maHoaDon, String maSanPham, int soLuong, float donGia) {
        ChiTietHoaDon hd = new ChiTietHoaDon(maHoaDon, maSanPham, soLuong, donGia);
        return update(hd);
    }

    public void closeConnection() {
        connection.closeConnect();
    }
}
