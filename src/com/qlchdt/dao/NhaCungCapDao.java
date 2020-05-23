/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.model.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class NhaCungCapDao {

    JDBCConnection connection;

    public ArrayList<NhaCungCap> getAllNhaCungCap() {

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
        }
        return dsncc;
    }

    public Boolean add(NhaCungCap ncc) {
        connection = new JDBCConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO `NHACUNGCAP` (`MaNCC`, `TenNCC`, `SDT`, `DiaChi`) VALUES ('"
                + ncc.getMaNCC() + "', '"
                + ncc.getTenNCC() + "', '"
                + ncc.getSDT() + "', '"
                + ncc.getDiaChi() + "', '"
                + "');");
        //connection.closeConnect();
        return ok;
    }

}
