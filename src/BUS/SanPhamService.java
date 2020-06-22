/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;


import DAO.SanPhamDao;
import DTO.Model.SanPham;
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
    
        public String getNextID() {
        return "SP" + String.valueOf(this.dssp.size() + 1);
    }

    public ArrayList<SanPham> search(String value, String type, int soluong1, int soluong2, float gia1, float gia2) {
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
            double gia = sp.getDonGia();
            Boolean soLuongKhongThoa = (soluong1 != -1 && soluong < soluong1) || (soluong2 != -1 && soluong > soluong2);
            Boolean giaKhongThoa = (gia1 != -1 && gia < gia1) || (gia2 != -1 && gia > gia2);

            if (soLuongKhongThoa || giaKhongThoa) {
                result.remove(i);
            }
        }

        return result;
    }
       public Boolean add(SanPham sp) {
        Boolean ok = sanPhamDao.add(sp);

        if (ok) {
            dssp.add(sp);
        }
        return ok;
    }

    public Boolean add(String masp, String malsp, String tensp, float dongia, int soluong, String filename) {
        SanPham sp = new SanPham(masp, malsp, tensp, dongia, soluong, filename);
        return add(sp);
    }

    public Boolean delete(String masp) {
        Boolean ok = sanPhamDao.delete(masp);

        if (ok) {
            for (int i = (dssp.size() - 1); i >= 0; i--) {
                if (dssp.get(i).getMaSP().equals(masp)) {
                    dssp.remove(i);
                }
            }
        }
        return ok;
    }
    
   public Boolean update(String masp, String mahsp, String tensp, float dongia, int soluong, String filename) {
        Boolean ok = sanPhamDao.update(masp, mahsp, tensp, dongia, soluong, filename);

        if (ok) {
            dssp.forEach((sp) -> {
                if (sp.getMaSP().equals(masp)) {
                    sp.setMaHSP(mahsp);
                    sp.setTenSP(tensp);
                    sp.setDonGia(dongia);
                    sp.setSoLuong(soluong);
                    sp.setFileNameHinhAnh(filename);
                   
                }
            });
        }

        return ok;
    }

    public Boolean updateSoLuong(String masp, int soluong) {
        Boolean ok = sanPhamDao.updateSoLuong(masp, soluong);

        if (ok) {
            dssp.forEach((sp) -> {
                if (sp.getMaSP().equals(masp)) {
                    sp.setSoLuong(soluong);
                }
            });
        }

        return ok;
    }


     public ArrayList<SanPham> getDssp() {
        return dssp;
    }
          
}
