/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.service;

import com.qlchdt.dao.HangSanPhamDao;
import com.qlchdt.dao.SanPhamDao;
import com.qlchdt.model.HangSanPham;
import com.qlchdt.model.SanPham;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class SanPhamService {

    private ArrayList<SanPham> dssp = new ArrayList<>();
    SanPhamDao sanPhamDao = new SanPhamDao();

    public SanPhamService() {
        dssp = sanPhamDao.readDB();
    }

    public void showConsole() {
        dssp.forEach((sp) -> {
            System.out.print(sp.getMaSP() + " ");
            System.out.print(sp.getMaHSP() + " ");
            System.out.println(sp.getTenSP() + " ");
            System.out.println(sp.getDonGia() + " ");
            System.out.println(sp.getSoLuong() + " ");

        });
    }

    public void readDB() {
        dssp = sanPhamDao.readDB();
    }

    public SanPham getSanPham(String masp) {
        for (SanPham sp : dssp) {
            if (sp.getMaSP().equals(masp)) {
                return sp;
            }
        }
        return null;
    }

    public ArrayList<SanPham> search(String value, String type, int soluong1, int soluong2, float gia1, float gia2, int trangthai) {
        ArrayList<SanPham> result = new ArrayList<>();

        dssp.forEach((sp) -> {
            if (type.equals("Tất cả")) {
                if (sp.getMaSP().toLowerCase().contains(value.toLowerCase())
                        || sp.getMaHSP().toLowerCase().contains(value.toLowerCase())
                        || sp.getTenSP().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(sp.getDonGia()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(sp.getSoLuong()).toLowerCase().contains(value.toLowerCase())) {
                    result.add(sp);
                }
            } else {
                switch (type) {
                    case "Mã sản phẩm":
                        if (sp.getMaSP().toLowerCase().contains(value.toLowerCase())) {
                            result.add(sp);
                        }
                        break;
                    case "Mã loại":
                        if (sp.getMaHSP().toLowerCase().contains(value.toLowerCase())) {
                            result.add(sp);
                        }
                        break;
                    case "Tên":
                        if (sp.getTenSP().toLowerCase().contains(value.toLowerCase())) {
                            result.add(sp);
                        }
                        break;
                    case "Đơn giá":
                        if (String.valueOf(sp.getDonGia()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(sp);
                        }
                        break;
                    case "Số lượng":
                        if (String.valueOf(sp.getSoLuong()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(sp);
                        }
                        break;
                }
            }
        });

        for (int i = result.size() - 1; i >= 0; i--) {
            SanPham sp = result.get(i);
            int soluong = sp.getSoLuong();
            float gia = sp.getDonGia();
            Boolean soLuongKhongThoa = (soluong1 != -1 && soluong < soluong1) || (soluong2 != -1 && soluong > soluong2);
            Boolean giaKhongThoa = (gia1 != -1 && gia < gia1) || (gia2 != -1 && gia > gia2);

            if (soLuongKhongThoa || giaKhongThoa) {
                result.remove(i);
            }
        }

        return result;
    }

    public ArrayList<SanPham> getDssp() {
        return dssp;
    }
}
