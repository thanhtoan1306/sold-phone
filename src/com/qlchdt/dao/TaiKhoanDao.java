/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.model.TaiKhoan;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class TaiKhoanDao {

    public  List<TaiKhoan> getAllTaiKhoan() {

       List<TaiKhoan> taikhoan = new ArrayList<TaiKhoan>();

        try {
            Connection connection = JDBCConnection.getJDBCConnection();

            Statement preparedStatement = connection.createStatement();

            String sql = "SELECT * FROM TAIKHOAN";
            ResultSet rs = preparedStatement.executeQuery(sql);

            while (rs.next()) {

                TaiKhoan tk = new TaiKhoan();
                tk.setTentk(rs.getString("TenTK"));
                tk.setMk(rs.getString("MK"));
                tk.setLoaitk(rs.getString("LoaiTK"));
                tk.setTrangthai(rs.getInt("TrangThai"));

                taikhoan.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return taikhoan;
    }
}
