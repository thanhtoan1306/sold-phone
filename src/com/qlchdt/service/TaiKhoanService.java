/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.service;

import com.qlchdt.dao.TaiKhoanDao;
import com.qlchdt.model.TaiKhoan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class TaiKhoanService {

    private ArrayList<TaiKhoan> taikhoan = new ArrayList<>();

    TaiKhoanDao TaiKhoanDao = new TaiKhoanDao();

    public TaiKhoanService() {
        taikhoan = TaiKhoanDao.readDB();

    }

    public void showlistTK() {
        taikhoan.forEach((tk)
                -> {
            System.out.print(tk.getTentk() + " ");
            System.out.print(tk.getMk() + " ");
        });
    }

    public void readDB() {
        taikhoan = TaiKhoanDao.readDB();
    }

//    public static void main(String arg[]) {
//        TaiKhoanService test = new TaiKhoanService();
//        test.showlistTK();
//    }

//    public TaiKhoan getTaiKhoan(String user, String pass) {
//        for (TaiKhoan tk : taikhoan) {
//            if (tk.getTentk().equals(user) && tk.getMk().equals(pass)) {
//                JOptionPane.showMessageDialog(null, "Đăng nhập đúng!!!");
//                return tk;
//            }
//            else{
//                 JOptionPane.showMessageDialog(null, "Đăng nhập sai!!!");
////                 return null;
//                    continue;
//            }
//        }
//        return null;
//    }
//        for (int i = 0; i < taikhoan.size(); ++i) {
//            if (taikhoan.get(i).getTentk().equals(user) && taikhoan.get(i).getMk().equals(pass)) {
//                JOptionPane.showMessageDialog(null, "Đăng nhập đúng!!!");
//            }
//        }
//        return getTaiKhoan(user, pass);
//    }
    public int kiemtraLogin(String user, String pass) {
        for (int i = 0; i < taikhoan.size(); ++i) {
            if (taikhoan.get(i).getTentk().equals(user) && taikhoan.get(i).getMk().equals(pass)) {
                return i;
            }
        }
        return -1;
    }
}
