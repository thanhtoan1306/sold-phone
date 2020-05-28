/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.service;

import com.qlchdt.dao.HangSanPhamDao;
import com.qlchdt.model.HangSanPham;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class HangSanPhamService {

    private ArrayList<HangSanPham> dshsp = new ArrayList<>();
    private HangSanPhamDao hangSanPhamDao = new HangSanPhamDao();

    public HangSanPhamService() {
        dshsp = hangSanPhamDao.readDB();
    }

    public void readDB() {
        dshsp = hangSanPhamDao.readDB();
    }

    public HangSanPham getHangSanPham(String mahang) {
        for (HangSanPham hsp : dshsp) {
            if (hsp.getMaHang().equals(mahang)) {
                return hsp;
            }
        }
        return null;
    }

}
