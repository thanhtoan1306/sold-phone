/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.dao;

import com.qlchdt.model.NhaCungCap;
import com.qlchdt.model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

/**
 *
 * @author User
 */
public class NhaCungCapDao {

    public List<NhaCungCap> getAllNhaCungCap() {

        List<NhaCungCap> nhacungcap = new ArrayList<NhaCungCap>();

        try {
            Connection connection = JDBCConnection.getJDBCConnection();

            Statement preparedStatement = connection.createStatement();

            String sql = "SELECT * FROM NHACUNGCAP";
            ResultSet rs = preparedStatement.executeQuery(sql);

            while (rs.next()) {

                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getString("MaNCC"));
                ncc.setTenNCC(rs.getString("TenNCC"));
                ncc.setDiaChi(rs.getString("DiaChi"));
                ncc.setSDT(rs.getString("SDT"));

                nhacungcap.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhacungcap;
    }

    public void addNcc(NhaCungCap ncc) {
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO NHACUNGCAP(MaNCC, TenNCC, DiaChi, SDT) VALUE(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ncc.getMaNCC());
            preparedStatement.setString(2, ncc.getTenNCC());
            preparedStatement.setString(3, ncc.getDiaChi());
            preparedStatement.setString(4, ncc.getSDT());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
            
        } catch (SQLException e)  {
            
            e.printStackTrace();
        }
        }

    }
