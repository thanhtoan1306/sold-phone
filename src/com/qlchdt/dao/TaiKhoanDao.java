/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.dao.JDBCConnection;
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

JDBCConnection connection;
    public  TaiKhoanDao()  
    {        
    }
    public ArrayList<TaiKhoan> readDB()
    {
        connection = new JDBCConnection();
        ArrayList<TaiKhoan> taikhoan = new ArrayList<TaiKhoan>();
        try {
            String sql = "SELECT * FROM taikhoan";
            ResultSet rs = connection.sqlQuery(sql);
            while (rs.next()) 
            {
                TaiKhoan tk = new TaiKhoan();
                tk.setTentk(rs.getString("TenTK"));
                tk.setMk(rs.getString("MK"));
                taikhoan.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return taikhoan;
    }
}
