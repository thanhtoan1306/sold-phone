/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhanhHangDao;
import DTO.Model.KhachHang;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class KhachHangService {

    private ArrayList<KhachHang> dskh = new ArrayList<>();
    private KhanhHangDao khachHangDao = new KhanhHangDao();

    public KhachHangService() {
         dskh = khachHangDao.readDB();
    }

    public ArrayList<KhachHang> getDskh() {
        return dskh;
    }

        public void showConsole() {
        dskh.forEach((kh) -> {
            System.out.print(kh.getMaKH() + " ");
            System.out.println(kh.getTenKH() + " ");
            System.out.println(kh.getDiaChi() + " ");
            System.out.println(kh.getSDT() + " ");           
        });
    }

        
    public void readDB() {
        dskh = khachHangDao.readDB();
    }
       public KhachHang getKhachHang(String makh) {
        for (KhachHang kh : dskh) {
            if (kh.getMaKH().equals(makh)) {
                return kh;
            }
        }
        return null;
    }
    
    public ArrayList<KhachHang> search(String value, String type) {

        ArrayList<KhachHang> result = new ArrayList<>();

        dskh.forEach((kh) -> {
            if (type.equals("Tất cả")) {
                if (kh.getMaKH().toLowerCase().contains(value.toLowerCase())
                        || kh.getTenKH().toLowerCase().contains(value.toLowerCase())
                        || kh.getDiaChi().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(kh.getSDT()).toLowerCase().contains(value.toLowerCase())                        ) {
                    result.add(kh);
                }
            } else {
                switch (type) {
                    case "Mã KH":
                        if (kh.getMaKH().toLowerCase().contains(value.toLowerCase())) {
                            result.add(kh);
                        }
                        break;
                    case "Tên KH":
                        if (kh.getTenKH().toLowerCase().contains(value.toLowerCase())) {
                            result.add(kh);
                        }
                        break;
                    case "Địa chỉ":
                        if (kh.getDiaChi().toLowerCase().contains(value.toLowerCase())) {
                            result.add(kh);
                        }
                        break;
                    case "Số điện thoại":
                        if (kh.getSDT().toLowerCase().contains(value.toLowerCase())) {
                            result.add(kh);
                        }
                        break;                  
                }
            }

        });

        return result;
    }

    public Boolean add(KhachHang kh) {
        Boolean ok = khachHangDao.add(kh);

        if (ok) {
            dskh.add(kh);            
        }
        return ok;
    }

    public Boolean add(String makh, String tenkh, String diachi, String sdt) {
        KhachHang kh = new KhachHang(makh, tenkh, diachi, sdt);
        return add(kh);
    }
    
    
    
    
    public String getNextID() {
        return "KH" + String.valueOf(this.dskh.size() + 1);
    }
    public Boolean delete(String makh) {
        Boolean ok = khachHangDao.delete(makh);

        if (ok) {
            for (int i = (dskh.size() - 1); i >= 0; i--) {
                if (dskh.get(i).getMaKH().equals(makh)) {
                    dskh.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String makh, String tenkh, String diachi, String sdt) {
        Boolean ok = khachHangDao.update(makh, tenkh, diachi, sdt);

        if (ok) {
            dskh.forEach((kh) -> {
                if (kh.getMaKH().equals(makh)) {
                    kh.setTenKH(tenkh);
                    kh.setDiaChi(diachi);
                    kh.setSDT(sdt);
                }
            });
        }

        return ok;
    }
}
