/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietHoaDonDao;
import DTO.Model.ChiTietHoaDon;
import DTO.Model.SanPham;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ChiTietHoaDonService {

    ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();
    private final ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDao();
    private final HoaDonService hoaDonService = new HoaDonService();
    private final SanPhamService sanPhamService = new SanPhamService();

    public ChiTietHoaDonService() {
        dscthd = chiTietHoaDonDao.readDB();
    }

    public ArrayList<ChiTietHoaDon> getDscthd() {
        return this.dscthd;
    }

    public void readDB() {
        dscthd = chiTietHoaDonDao.readDB();
    }

    public ChiTietHoaDon getChiTiet(String mahd, String masp) {
        for (ChiTietHoaDon ct : dscthd) {
            if (ct.getMaHoaDon().equals(mahd) && ct.getMaSanPham().equals(masp)) {
                return ct;
            }
        }
        return null;
    }

    public ArrayList<ChiTietHoaDon> getAllChiTiet(String mahd) {
        ArrayList<ChiTietHoaDon> result = new ArrayList<>();
        dscthd.stream().filter((ct) -> (ct.getMaHoaDon().equals(mahd))).forEachOrdered((ct) -> {
            result.add(ct);
        });
        return result;
    }

    public Boolean add(ChiTietHoaDon ct) {
        int soLuongChiTietMoi = ct.getSoLuong();

        // tìm các chi tiết cùng mã, và tính tổng số lượng
        ArrayList<ChiTietHoaDon> CurArr = new ArrayList<>();

        int tongSoLuong = ct.getSoLuong();

        for (ChiTietHoaDon cthd : dscthd) {
            if (cthd.getMaHoaDon().equals(ct.getMaHoaDon()) && cthd.getMaSanPham().equals(ct.getMaSanPham())) {
                tongSoLuong += cthd.getSoLuong();
                CurArr.add(cthd);
            }
        }
        // xóa chi tiết cũ cùng mã
        dscthd.removeAll(CurArr);
        chiTietHoaDonDao.delete(ct.getMaHoaDon(), ct.getMaSanPham());

        // thêm chi tiết mới có số lượng = tổng số lượng tìm ở trên
        ct.setSoLuong(tongSoLuong);
        Boolean success = chiTietHoaDonDao.add(ct);
        if (success) {
            dscthd.add(ct);
            // update số lượng bên bảng sản phẩm
            updateSoLuongSanPham(ct.getMaSanPham(), -soLuongChiTietMoi);
            // update tổng tiền hóa đơn
            updateTongTien(ct.getMaHoaDon());
            return true;
        }
        return false;
    }

    public Boolean add(String maHoaDon, String maSanPham, int soLuong, float donGia) {
        ChiTietHoaDon hd = new ChiTietHoaDon(maHoaDon, maSanPham, soLuong, donGia);
        return add(hd);
    }

    public Boolean update(String maHoaDon, String maSanPham, int soLuong, float donGia) {
        ChiTietHoaDon hd = new ChiTietHoaDon(maHoaDon, maSanPham, soLuong, donGia);
        return update(hd);
    }

//    public Boolean update(ChiTietHoaDon chitiet) {
//        
//        Boolean success = chiTietHoaDonDao.update(chitiet);
//        
//        if (success) {
//            for (ChiTietHoaDon cthd : dscthd) {
//                if (cthd.getMaHoaDon().equals(chitiet.getMaHoaDon())) {
//                    cthd = chitiet;
//                }
//            }
//            updateSoLuongSanPham(cthd.getMaSanPham(), chitiet.getSoLuong());
//            updateTongTien(chitiet.getMaHoaDon()); // mới sửa
//        }
//
//        return success;
//    }
    public Boolean update(ChiTietHoaDon chitiet) {
        Boolean success = chiTietHoaDonDao.update(chitiet);
        if (success) {
            for (ChiTietHoaDon cthd : dscthd) {
                if (cthd.getMaHoaDon().equals(chitiet.getMaHoaDon()) && cthd.getMaSanPham().equals(chitiet.getMaSanPham())) {
                    ChiTietHoaDon pn = new ChiTietHoaDon(chitiet.getMaHoaDon(),chitiet.getMaSanPham(),chitiet.getSoLuong(),chitiet.getDonGia());
                    updateTongTienSua(pn.getMaHoaDon(),pn.getSoLuong());
                }
            }
        }
        return success;
    }
    
        private Boolean updateTongTienSua(String _mahd,int _sL) {
        float tong = 0;
        for (ChiTietHoaDon ct : dscthd) {
            if (ct.getMaHoaDon().equals(_mahd)) {
                tong += _sL * ct.getDonGia(); // đang fix
            }
        }
        Boolean success = hoaDonService.updateTongTien(_mahd, tong);
        return success;
    }
    

    private Boolean updateTongTien(String _mahd) {
        float tong = 0;
        for (ChiTietHoaDon ct : dscthd) {
            if (ct.getMaHoaDon().equals(_mahd)) {
                tong += ct.getSoLuong() * ct.getDonGia(); // đang fix
            }
        }
        Boolean success = hoaDonService.updateTongTien(_mahd, tong);
        return success;
    }

    private Boolean updateSoLuongSanPham(String _masp, int _soLuongThayDoi) {
        for (SanPham sp : sanPhamService.getDssp()) {
            if (sp.getMaSP().equals(_masp)) {
                return sanPhamService.updateSoLuong(_masp, sp.getSoLuong() + _soLuongThayDoi);
            }
        }
        return false;
    }

    public Boolean delete(String _maHoaDon, String _maSanPham) {
        Boolean success = chiTietHoaDonDao.delete(_maHoaDon, _maSanPham);
        if (success) {
            for (ChiTietHoaDon cthd : dscthd) {
                if (cthd.getMaHoaDon().equals(_maHoaDon) && cthd.getMaSanPham().equals(_maSanPham)) {
                    updateSoLuongSanPham(_maSanPham, cthd.getSoLuong());
                    dscthd.remove(cthd);
                    updateTongTien(_maHoaDon);
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean deleteAll(String _maHoaDon) {
        Boolean success = chiTietHoaDonDao.deleteAll(_maHoaDon);
        if (success) {
            for (ChiTietHoaDon cthd : dscthd) {
                if (cthd.getMaHoaDon().equals(_maHoaDon)) {
                    dscthd.remove(cthd);
                }
            }
            // updateTongTien(_maHoaDon,cthd.getSoLuong());
            return true;
        }
        return false;
    }

    public ArrayList<ChiTietHoaDon> search(String type, String keyword, int soLuong1, int soLuong2, float thanhTien1, float thanhTien2) {
        ArrayList<ChiTietHoaDon> result = new ArrayList<>();

        dscthd.forEach((hd) -> {
            switch (type) {
                case "Tất cả":
                    if (hd.getMaHoaDon().toLowerCase().contains(keyword.toLowerCase())
                            || hd.getMaSanPham().toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(hd.getSoLuong()).toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(hd.getDonGia()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }

                    break;

                case "Mã hóa đơn":
                    if (hd.getMaHoaDon().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;

                case "Mã sản phẩm":
                    if (hd.getMaSanPham().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;

                case "Số lượng":
                    if (String.valueOf(hd.getSoLuong()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;

                case "Đơn giá":
                    if (String.valueOf(hd.getDonGia()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;
            }
        });

        for (int i = result.size() - 1; i >= 0; i--) {
            ChiTietHoaDon ct = result.get(i);
            int sl = ct.getSoLuong();
            double tt = ct.getDonGia() * sl;

            Boolean soLuongKhongThoa = (soLuong1 != -1 && sl < soLuong1) || (soLuong2 != -1 && sl > soLuong2);
            Boolean donGiaKhongThoa = (thanhTien1 != -1 && tt < thanhTien1) || (thanhTien2 != -1 && tt > thanhTien2);

            if (soLuongKhongThoa || donGiaKhongThoa) {
                result.remove(ct);
            }
        }
        return result;
    }

}
