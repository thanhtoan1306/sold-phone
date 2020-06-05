/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.model.KhuyenMai;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class KhuyenMaiDao {
    JDBCConnection connection;

    public KhuyenMaiDao() {
    }
        public ArrayList<KhuyenMai> readDB() {
        connection = new JDBCConnection();
        ArrayList<KhuyenMai> dssp = new ArrayList<>();
        try {
            String qry = "SELECT * FROM khuyenmai";
            ResultSet r = connection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String makm = r.getString("MaKM");
                    String tenkm = r.getString("TenKM");
                    float dkkm = r.getFloat("DieuKienKM");
                    float phantramkm = r.getFloat("PhanTramKM");
                    LocalDate ngaybd = r.getDate("NgayBD").toLocalDate();
                    LocalDate ngaykt = r.getDate("NgayKT").toLocalDate();
                    dssp.add(new KhuyenMai(makm, tenkm, dkkm, phantramkm, ngaybd, ngaykt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng khuyến mãi");
        } finally {
            connection.closeConnect();
        }
        return dssp;
    }

    public ArrayList<KhuyenMai> search(String columnName, String value) {
        connection = new JDBCConnection();
        ArrayList<KhuyenMai> dssp = new ArrayList<>();

        try {
            String qry = "SELECT * FROM khuyenmai WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = connection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String makm = r.getString("MaKM");
                    String tenkm = r.getString("TenKM");
                    float dkkm = r.getFloat("DieuKienKM");
                    float phantramkm = r.getFloat("PhanTramKM");
                    LocalDate ngaybd = r.getDate("NgayBD").toLocalDate();
                    LocalDate ngaykt = r.getDate("NgayKT").toLocalDate();
                    dssp.add(new KhuyenMai(makm, tenkm, dkkm, phantramkm, ngaybd, ngaykt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng khuyến mãi");
        } finally {
            connection.closeConnect();
        }

        return dssp;
    }

    public Boolean add(KhuyenMai km) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `DieuKienKM`, `PhanTramKM`, `NgayBD`, `NgayKT`) VALUES ('"
                + km.getMaKM()+ "', '"
                + km.getTenKM() + "', '"
                + km.getDieuKienKM() + "', '"
                + km.getPhanTramKM() + "', '"
                + km.getNgayBD() + "', '"
                + km.getNgayKT() + "');");
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String makm) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `khuyenmai` WHERE `khuyenmai`.`MaKM` = '" + makm + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String makm, String tenkm, float dkkm, float phantramkm, LocalDate ngaybd, LocalDate ngaykt) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update KhuyenMai Set "
                + "TenKM='" + tenkm
                + "', DieuKienKM='" + dkkm
                + "', PhanTramKM='" + phantramkm
                + "', NgayBD='" + ngaybd
                + "', NgayKT='" + ngaykt
                + "' where MaKM='" + makm + "'");
        connection.closeConnect();
        return ok;
    }

    public void close() {
        connection.closeConnect();
    }
    
}