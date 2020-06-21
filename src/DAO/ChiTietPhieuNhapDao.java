/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.Model.ChiTietPhieuNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ChiTietPhieuNhapDao {

    JDBCConnection connection;

    public ArrayList<ChiTietPhieuNhap> readDB() {
        ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();
        connection = new JDBCConnection();
        try {

            String query = "SELECT * FROM CTPN";
            ResultSet r = connection.sqlQuery(query);
            if (r != null) {
                while (r.next()) {

                    ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(r.getString("MaPN"),
                            r.getString("MaSP"),
                            r.getInt("SoLuong"),
                            r.getFloat("DonGia"));

                    dsctpn.add(ctpn);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không thấy data cần tìm trong ResultSet");

        } finally {
            connection.closeConnect();
        }
        return dsctpn;

    }

    public ArrayList<ChiTietPhieuNhap> search(String columName, String value) {
        ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();
        connection = new JDBCConnection();
        try {

            String query = "SELECT * FROM CTPN WHERE" + columName + "LIKE '%" + value + "%'";
            ResultSet r = connection.sqlQuery(query);
            if (r != null) {
                while (r.next()) {
                    String ma = r.getString(1);
                    String maSP = r.getString(2);
                    Integer soLuong = r.getInt(3);
                    Float donGia = r.getFloat(4);

                    ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(ma, maSP, soLuong, donGia);
                    dsctpn.add(ctpn);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không thấy data cần tìm trong ResultSet");

        } finally {
            connection.closeConnect();
        }
        return dsctpn;

    }

    public boolean add(ChiTietPhieuNhap ctpn) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `CTPN`(`MaPN`,`MaSP`,`SoLuong`,`DonGia`) VALUE('"
                + ctpn.getMaPN()+ "', '" + ctpn.getMaSP() + "','" + ctpn.getsLuong()+ "','" + ctpn.getDonGia() + "')");
        connection.closeConnect();
        return ok;

    }

    public Boolean deleteAll(String _mapn) {
        connection = new JDBCConnection();
        Boolean success = connection.sqlUpdate("DELETE FROM CTPN WHERE MaPN='" + _mapn + "';");
        connection.closeConnect();
        return success;
    }

    public Boolean delete(String _mapn, String _masp) {
        connection = new JDBCConnection();
        Boolean success = connection.sqlUpdate("DELETE FROM CTPN WHERE MaPN='" + _mapn + "' AND MaSP='" + _masp + "';");
        connection.closeConnect();
        return success;
    }

    public boolean update(ChiTietPhieuNhap ct) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("UPDATE `CTPN` SET "
                + "SoLuong='" + ct.getsLuong()
                + "',DonGia='" + ct.getDonGia()
                + "' WHERE MaPN='" + ct.getMaPN() + "' AND MaSP='" + ct.getMaSP() + "';");
        connection.closeConnect();
        return ok;
    }
     public Boolean update(String maPhieuNhap, String maSanPham, int soLuong, float donGia) {
        ChiTietPhieuNhap pn = new ChiTietPhieuNhap(maPhieuNhap, maSanPham, soLuong, donGia);
        return update(pn);
    }
}
