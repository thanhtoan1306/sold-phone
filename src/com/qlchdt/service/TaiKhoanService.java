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

     private ArrayList<TaiKhoan> dstk = new ArrayList<>();
    TaiKhoanDao taiKhoanDao = new TaiKhoanDao();

    

    public TaiKhoanService() {
         dstk = taiKhoanDao.readDB();

    }
    
        public void readDB() {
        dstk = taiKhoanDao.readDB();
    }

    public TaiKhoan getTaiKhoan(String tentk) {
        for (TaiKhoan tk : dstk) {
            if (tk.getUserName().equals(tentk)) {
                return tk;
            }
        }
        return null;
    }

    public ArrayList<TaiKhoan> search(String value, String type) {
        ArrayList<TaiKhoan> result = new ArrayList<>();

        dstk.forEach((tk) -> {
            if (type.equals("Tất cả")) {
                if (tk.getUserName().toLowerCase().contains(value.toLowerCase())
                        || tk.getPassWord().toLowerCase().contains(value.toLowerCase())
                        || tk.getMaNV().toLowerCase().contains(value.toLowerCase())
                        || tk.getMaQuyen().toLowerCase().contains(value.toLowerCase())) {
                    result.add(tk);
                }
            } else {
                switch (type) {
                    case "Tên tài khoản":
                        if (tk.getUserName().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mật khẩu":
                        if (tk.getPassWord().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mã nhân viên":
                        if (tk.getMaNV().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mã quyền":
                        if (tk.getMaQuyen().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                }
            }

        });

        return result;
    }

    public Boolean add(TaiKhoan tk) {
        Boolean ok = taiKhoanDao.add(tk);

        if (ok) {
            dstk.add(tk);
        }
        return ok;
    }

    public Boolean add(String username, String pass, String maNV, String maQuyen) {
        TaiKhoan tk = new TaiKhoan(username, pass, maNV, maQuyen);
        return add(tk);
    }

    public Boolean delete(String username) {
        Boolean ok = taiKhoanDao.delete(username);

        if (ok) {
            for (int i = (dstk.size() - 1); i >= 0; i--) {
                if (dstk.get(i).getUserName().equals(username)) {
                    dstk.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String username, String pass, String maNV, String maQuyen) {
        Boolean ok = taiKhoanDao.update(username, pass, maNV, maQuyen);

        if (ok) {
            dstk.forEach((tk) -> {
                if (tk.getUserName().equals(username)) {
                    tk.setPassWord(pass);
                    tk.setMaNV(maNV);
                    tk.setMaQuyen(maQuyen);
                }
            });
        }

        return ok;
    }

    public ArrayList<TaiKhoan> getDstk() {
        return dstk;
    }
//
//    public void showlistTK() {
//        taikhoan.forEach((tk)
//                -> {
//            System.out.print(tk.getTentk() + " ");
//            System.out.print(tk.getMk() + " ");
//        });
//    }
//
//    public void readDB() {
//        taikhoan = TaiKhoanDao.readDB();
//    }

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
//    public int kiemtraLogin(String user, String pass) {
//        for (int i = 0; i < taikhoan.size(); ++i) {
//            if (taikhoan.get(i).getTentk().equals(user) && taikhoan.get(i).getMk().equals(pass)) {
//                return i;
//            }
//        }
//        return -1;
//    }
}
