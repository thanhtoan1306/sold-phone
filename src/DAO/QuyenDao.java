/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.Model.Quyen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class QuyenDao {
        JDBCConnection connection;

    public QuyenDao() {

    }

    public ArrayList<Quyen> readDB() {
        connection = new JDBCConnection();
        ArrayList<Quyen> dsq = new ArrayList<>();
        try {
            String qry = "SELECT * FROM phanquyen";
            ResultSet r = connection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String maq = r.getString("MaQuyen");
                    String tenq = r.getString("TenQuyen");
                    String chitietq = r.getString("ChiTietQuyen");
                    
                    dsq.add(new Quyen(maq, tenq, chitietq));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng phân quyền");
        } finally {
            connection.closeConnect();
        }
        return dsq;
    }

    public ArrayList<Quyen> search(String columnName, String value) {
        connection = new JDBCConnection();
        ArrayList<Quyen> dsq = new ArrayList<>();

        try {
            String qry = "SELECT * FROM phanquyen WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = connection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String maq = r.getString("MaQuyen");
                    String tenq = r.getString("TenQuyen");
                    String chitietq = r.getString("ChiTietQuyen");
                    
                    dsq.add(new Quyen(maq, tenq, chitietq));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng phân quyền");
        } finally {
            connection.closeConnect();
        }

        return dsq;
    }

    public Boolean add(Quyen q) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `phanquyen` (`MaQuyen`, `TenQuyen`, `ChiTietQuyen`) VALUES ('"
                + q.getMaQuyen()+ "', '" 
                + q.getTenQuyen()+ "', '" 
                + q.getChiTietQuyen()+ "');");
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String maq) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `phanquyen` WHERE `phanquyen`.`MaQuyen` = '" + maq + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String maq, String tenquyen, String chitietquyen) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update phanquyen Set "
                + "TenQuyen='" + tenquyen 
                + "',ChiTietQuyen='" + chitietquyen 
                + "' where MaQuyen='" + maq + "';");
        connection.closeConnect();
        return ok;
    }

    public void close() {
        connection.closeConnect();
    }
}
