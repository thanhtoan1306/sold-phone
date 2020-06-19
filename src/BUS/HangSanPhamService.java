/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HangSanPhamDao;
import DTO.Model.HangSanPham;
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
    
    public Boolean add(HangSanPham hsp) {
        Boolean ok = hangSanPhamDao.add(hsp);

        if (ok) {
            dshsp.add(hsp);
        }
        return ok;
    }

    public Boolean add(String mahang, String tenhang) {
        HangSanPham hsp = new HangSanPham(mahang, tenhang);
        return add(hsp);
    }
    
     public Boolean delete(String mahsp) {
        Boolean ok = hangSanPhamDao.delete(mahsp);

        if (ok) {
            for (int i = (dshsp.size() - 1); i >= 0; i--) {
                if (dshsp.get(i).getMaHang().equals(mahsp)) {
                    dshsp.remove(i);
                }
            }
        }
        return ok;
    }
    
   public Boolean update(String mahang, String tenhang) {
        Boolean ok = hangSanPhamDao.update(mahang,tenhang);

        if (ok) {
            dshsp.forEach((sp) -> {
                if (sp.getMaHang().equals(mahang)) {
                    sp.setMaHang(mahang);
                    sp.setTenHang(tenhang);
                }
            });
        }

        return ok;
    }

    /*public Boolean updateSoLuong(String mahang) {
        Boolean ok = hangSanPhamDao.updateSoLuong(mahang);

        if (ok) {
            dssp.forEach((sp) -> {
                if (sp.getMaSP().equals(masp)) {
                    sp.setSoLuong(soluong);
                }
            });
        }

        return ok;
    }*/


     public ArrayList<HangSanPham> getDshsp() {
        return dshsp;
    }

}
