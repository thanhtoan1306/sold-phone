/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.HangSanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class HangSanPhamDao {

    JDBCConnection connection;

    public HangSanPhamDao() {

    }

    public ArrayList<HangSanPham> readDB() {
        connection = new JDBCConnection();

        ArrayList<HangSanPham> dshsp = new ArrayList<>();

        try {

            String sql = "SELECT * FROM HANGSANPHAM";

            ResultSet rs = connection.sqlQuery(sql);

            while (rs.next()) {

                HangSanPham hsp = new HangSanPham();
                hsp.setMaHang(rs.getString("MaHSP"));
                hsp.setTenHang(rs.getString("TenHSP"));

                dshsp.add(hsp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng sản phẩm");
        } finally {
            connection.closeConnect();
        } 
        return dshsp;
    }

    public ArrayList<HangSanPham> search(String columnName, String value) {
        connection = new JDBCConnection();
        ArrayList<HangSanPham> dslsp = new ArrayList<>();

        try {
            String qry = "SELECT * FROM HANGSANPHAM WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet rs = connection.sqlQuery(qry);
            if (rs != null) {
                while (rs.next()) {
                    String malsp = rs.getString(1);
                    String tenlsp = rs.getString(2);
                    dslsp.add(new HangSanPham(malsp, tenlsp));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng hãng sản phẩm");
        }finally{
        connection.closeConnect();}

        return dslsp;
    }

    public Boolean add(HangSanPham hsp) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `HANGSANPHAM` (`MAHSP`, `TenHSP`) VALUES ('" + hsp.getMaHang() + "', '" + hsp.getTenHang() + "');");
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String mahsp) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("DELETE FROM `HANGSANPHAM` WHERE `HANGSANPHAM`.`mahsp` = '" + mahsp + "'");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String MaHSP, String TenHSP) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("Update HANGSANPHAM Set TenLSP='" + TenHSP + "' where MASHP='" + MaHSP + "'");
        connection.closeConnect();
        return ok;
    }

}
