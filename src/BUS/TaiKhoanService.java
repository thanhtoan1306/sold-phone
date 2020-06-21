/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhanVienDao;
import DAO.TaiKhoanDao;
import DTO.Model.TaiKhoan;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class TaiKhoanService {

    private ArrayList<TaiKhoan> dstk = new ArrayList<>();
    private TaiKhoanDao taikhoanDao = new TaiKhoanDao();

    public TaiKhoanService() {
        dstk = taikhoanDao.readDB();
    }

    public void readDB() {
        dstk = taikhoanDao.readDB();
    }

    public TaiKhoan getTaiKhoan(String tentk) {
        for (TaiKhoan tk : dstk) {
            if (tk.getTentk().equals(tentk)) {
                return tk;
            }
        }
        return null;
    }

    public ArrayList<TaiKhoan> search(String value, String type, LocalDate _ngay1, LocalDate _ngay2) {
        ArrayList<TaiKhoan> result = new ArrayList<>();

        dstk.forEach((tk) -> {
            if (type.equals("Tất cả")) {
                if (tk.getManv().toLowerCase().contains(value.toLowerCase())
                        || tk.getTentk().toLowerCase().contains(value.toLowerCase())
                        || tk.getMk().toString().toLowerCase().contains(value.toLowerCase())
                        || tk.getManv().toLowerCase().contains(value.toLowerCase())
                        || tk.getMaquyen().toLowerCase().contains(value.toLowerCase())) {
                    result.add(tk);
                }
            } else {
                switch (type) {
                    case "Tên tài khoản":
                        if (tk.getTentk().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mật khẩu":
                        if (tk.getMk().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mã nhân viên":
                        if (tk.getManv().toString().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mã quyền":
                        if (tk.getMaquyen().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
            
                }
            }
        });
        return result;
        //Ngay sinh
    }


    public Boolean saveToDatabase(TaiKhoan tk) {
        Boolean ok = taikhoanDao.add(tk);
        return ok;
    }

    public Boolean add(String tentk, String mk, String manv, String maquyen) {
        TaiKhoan tk = new TaiKhoan(tentk, mk, manv, maquyen);
        return dstk.add(tk);
    }

    public Boolean delete(String manv) {
        Boolean ok = taikhoanDao.delete(manv);

        if (ok) {
            for (int i = (dstk.size() - 1); i >= 0; i--) {
                if (dstk.get(i).getManv().equals(manv)) {
                    dstk.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String tentk, String mk, String manv, String maquyen) {
        Boolean ok = taikhoanDao.update(tentk, mk, manv, maquyen);

        if (ok) {
            dstk.forEach((tk) -> {
                if (tk.getManv().equals(manv)) {
                    tk.setTentk(tentk);
                    tk.setMk(mk);
                    tk.setManv(manv);
                    tk.setMaquyen(maquyen);
                    
                }
            });
        }

        return ok;
    }
    public int kiemtraLogin(String user, String pass){
        for (int i=0; i<dstk.size(); ++i){
            if(dstk.get(i).getTentk().equals(user) && dstk.get(i).getMk().equals(pass)){
                return i;
            }
        }
        return -1;
        
    }
    
    public ArrayList<TaiKhoan> getDstk() {
        return dstk;
    }
}
