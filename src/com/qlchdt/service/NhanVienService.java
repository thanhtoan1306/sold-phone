/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.service;

import com.qlchdt.dao.NhanVienDao;
import com.qlchdt.model.NhanVien;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class NhanVienService {

    private ArrayList<NhanVien> dsnv = new ArrayList<>();
    private NhanVienDao nhanVienDao = new NhanVienDao();

    public NhanVienService() {
        nhanVienDao.readDB();
    }

    public void readDB() {
        dsnv = nhanVienDao.readDB();
    }

    public ArrayList<NhanVien> search(String value, String type, LocalDate _ngay1, LocalDate _ngay2) {
        ArrayList<NhanVien> result = new ArrayList<>();

        dsnv.forEach((nv) -> {
            if (type.equals("Tất cả")) {
                if (nv.getMaNV().toLowerCase().contains(value.toLowerCase())
                        || nv.getTenNV().toLowerCase().contains(value.toLowerCase())
                        || nv.getNgaySinh().toString().toLowerCase().contains(value.toLowerCase())
                        || nv.getDiaChi().toLowerCase().contains(value.toLowerCase())
                        || nv.getSDT().toLowerCase().contains(value.toLowerCase())) {
                    result.add(nv);
                }
            } else {
                switch (type) {
                    case "Mã nhân viên":
                        if (nv.getMaNV().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Tên nhân viên":
                        if (nv.getTenNV().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Ngày sinh":
                        if (nv.getNgaySinh().toString().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Giới tính":
                        if (nv.getGioiTinh().toString().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Địa chỉ":
                        if (nv.getDiaChi().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Số điện thoại":
                        if (nv.getSDT().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                }
            }
        });

        //Ngay sinh
        for (int i = result.size() - 1; i >= 0; i--) {
            NhanVien nv = result.get(i);
            LocalDate ngaysinh = nv.getNgaySinh();

            Boolean ngayKhongThoa = (_ngay1 != null && ngaysinh.isBefore(_ngay1)) || (_ngay2 != null && ngaysinh.isAfter(_ngay2));

            if (ngayKhongThoa) {
                result.remove(nv);
            }
        }

        return result;
    }

    public Boolean saveToDatabase(NhanVien nv) {
        Boolean ok = nhanVienDao.add(nv);

        if (ok) {
            dsnv.add(nv);
        }
        return ok;
    }

    public Boolean add(String manv, String tennv, LocalDate ngaysinh,String gioitinh, String sdt, String diachi) {
        NhanVien nv = new NhanVien(manv, tennv, ngaysinh,gioitinh, sdt, diachi);
        return saveToDatabase(nv);
    }

    public Boolean delete(String manv) {
        Boolean ok = nhanVienDao.delete(manv);

        if (ok) {
            for (int i = (dsnv.size() - 1); i >= 0; i--) {
                if (dsnv.get(i).getMaNV().equals(manv)) {
                    dsnv.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String manv, String tennv, LocalDate ngaysinh,String gioitinh, String diachi, String sdt) {
        Boolean ok = nhanVienDao.update(manv, tennv, ngaysinh,gioitinh, diachi, sdt);

        if (ok) {
            dsnv.forEach((nv) -> {
                if (nv.getMaNV().equals(manv)) {
                    nv.setTenNV(tennv);
                    nv.setNgaySinh(ngaysinh);
                    nv.setGioiTinh(gioitinh);
                    nv.setDiaChi(diachi);
                    nv.setSDT(sdt);
                    
                }
            });
        }

        return ok;
    }

    public ArrayList<NhanVien> getDsnv() {
        return dsnv;
    }
}
