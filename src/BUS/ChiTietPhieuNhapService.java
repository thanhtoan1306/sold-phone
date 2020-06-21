/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietPhieuNhapDao;
import DTO.Model.ChiTietPhieuNhap;
import DTO.Model.SanPham;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ChiTietPhieuNhapService {

    ChiTietPhieuNhapDao qlctpnDAO = new ChiTietPhieuNhapDao();
    PhieuNhapService qlpnBUS = new PhieuNhapService();
    SanPhamService qlspBUS = new SanPhamService();
    ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();

    public ChiTietPhieuNhapService() {
        dsctpn = qlctpnDAO.readDB();
    }

    public void readDB() {
        dsctpn = qlctpnDAO.readDB();
    }

    public ArrayList<ChiTietPhieuNhap> search(String type, String value, int soLuong1, int soLuong2, float thanhTien1, float thanhTien2) {

        ArrayList<ChiTietPhieuNhap> result = new ArrayList<>();
        dsctpn.forEach((ctpn) -> {
            if (type.equals("Tất cả")) {
                if (ctpn.getMaPN().toLowerCase().contains(value.toLowerCase())
                        || ctpn.getMaSP().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(ctpn.getDonGia()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(ctpn.getsLuong()).toLowerCase().contains(value.toLowerCase())) {
                    result.add(ctpn);
                }
            } else {
                switch (type) {
                    case "Mã phiếu nhập":
                        if (ctpn.getMaPN().toLowerCase().contains(value.toLowerCase())) {
                            result.add(ctpn);
                        }
                        break;
                    case "Mã sản phẩm":
                        if (ctpn.getMaSP().toLowerCase().contains(value.toLowerCase())) {
                            result.add(ctpn);
                        }
                        break;
                    case "Đơn giá":
                        if (String.valueOf(ctpn.getDonGia()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(ctpn);
                        }
                        break;
                    case "Số lượng":
                        if (String.valueOf(ctpn.getsLuong()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(ctpn);
                        }
                        break;
                }
            }
        });
        
        for (int i = result.size() - 1; i >= 0; i--) {
            ChiTietPhieuNhap ct = result.get(i);
            int sl = ct.getsLuong();
            double tt = ct.getDonGia()* sl;

            Boolean soLuongKhongThoa = (soLuong1 != -1 && sl < soLuong1) || (soLuong2 != -1 && sl > soLuong2);
            Boolean donGiaKhongThoa = (thanhTien1 != -1 && tt < thanhTien1) || (thanhTien2 != -1 && tt > thanhTien2);

            if (soLuongKhongThoa || donGiaKhongThoa) {
                result.remove(ct);
            }
        }

        return result;
    }

    public Boolean deleteAll(String _maPhieuNhap) {
        Boolean success = qlctpnDAO.deleteAll(_maPhieuNhap);
        if (success) {
            for (ChiTietPhieuNhap cthd : dsctpn) {
                if (cthd.getMaPN().equals(_maPhieuNhap)) {
                    dsctpn.remove(cthd);
                }
            }
            updateTongTien(_maPhieuNhap);
            return true;
        }
        return false;
    }

    public ChiTietPhieuNhap getChiTiet(String mapn, String masp) {
        for (ChiTietPhieuNhap ct : dsctpn) {
            if (ct.getMaSP().equals(masp) && ct.getMaPN().equals(mapn)) {
                return ct;
            }
        }
        return null;
    }

    public ArrayList<ChiTietPhieuNhap> getAllChiTiet(String mapn) {
        ArrayList<ChiTietPhieuNhap> result = new ArrayList<>();
        for (ChiTietPhieuNhap ctpn : dsctpn) {
            if (ctpn.getMaPN().equals(mapn)) {
                result.add(ctpn);
            }
        }
        return result;
    }

    public Boolean delete(String _maPhieuNhap, String _maSanPham) {
        Boolean success = qlctpnDAO.delete(_maPhieuNhap, _maSanPham);
        if (success) {
            for (ChiTietPhieuNhap ctpn : dsctpn) {
                if (ctpn.getMaPN().equals(_maPhieuNhap) && ctpn.getMaSP().equals(_maSanPham)) {
                    updateSoLuongSanPham(_maSanPham, ctpn.getsLuong());
                    dsctpn.remove(ctpn);
                    updateTongTien(_maPhieuNhap);
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean add(ChiTietPhieuNhap ct) {
        int soLuongChiTietMoi = ct.getsLuong();

        // tìm các chi tiết cùng mã, và tính tổng số lượng
        ArrayList<ChiTietPhieuNhap> toRemove = new ArrayList<>();
        int tongSoLuong = ct.getsLuong();

        for (ChiTietPhieuNhap ctpn : dsctpn) {
            if (ctpn.getMaPN().equals(ct.getMaPN()) && ctpn.getMaSP().equals(ct.getMaSP())) {
                tongSoLuong += ctpn.getsLuong();
                toRemove.add(ctpn);
            }
        }
        // xóa chi tiết cũ cùng mã
        dsctpn.removeAll(toRemove);
        qlctpnDAO.delete(ct.getMaPN(), ct.getMaSP());

        // thêm chi tiết mới có số lượng = tổng số lượng tìm ở trên
        ct.setsLuong(tongSoLuong);
        Boolean success = qlctpnDAO.add(ct);
        if (success) {
            dsctpn.add(ct);
            updateSoLuongSanPham(ct.getMaSP(), soLuongChiTietMoi);
            updateTongTien(ct.getMaPN());
            return true;
        }
        return false;
    }

    private Boolean updateSoLuongSanPham(String _masp, int _soLuongThayDoi) {
        Boolean success = false;
        for (SanPham sp : qlspBUS.getDssp()) {
            if (sp.getMaSP().equals(_masp)) {
                success = qlspBUS.updateSoLuong(_masp, sp.getSoLuong() + _soLuongThayDoi);
            }
        }
        return success;
    }

    public boolean add(String ma, String masp, Integer soluong, Float dongia) {
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(ma, masp, soluong, dongia);
        return add(ctpn);
    }

    public boolean delete(String ma) {
        Boolean ok = qlctpnDAO.deleteAll(ma);
        if (ok) {
            for (int i = (dsctpn.size() - 1); i >= 0; i--) {
                if (dsctpn.get(i).getMaPN().equals(ma)) {
                    dsctpn.remove(i);
                }
            }

        }
        return ok;
    }

   /* public Boolean update(String mapn, String masp, int soluong, float dongia) {
        Boolean ok = qlctpnDAO.update(mapn, masp, soluong, dongia);

        if (ok) {
            dsctpn.forEach((ctpn) -> {
                if (ctpn.getMaPN().equals(mapn) && ctpn.getMaSP().equals(masp)) {
                    ChiTietPhieuNhap pn = new ChiTietPhieuNhap(mapn, masp, soluong, dongia);
                    dsctpn.add(pn);
                }
            });
        }

        return ok;
    }*/
    
    public Boolean update(String maPhieuNhap, String maSanPham, int soLuong, float donGia) {
        ChiTietPhieuNhap hd = new ChiTietPhieuNhap(maPhieuNhap, maSanPham, soLuong, donGia);
        return update(hd);
    }
    
    public Boolean update(ChiTietPhieuNhap chitiet) {
        Boolean success = qlctpnDAO.update(chitiet);
        if (success) {
            for (ChiTietPhieuNhap cthd : dsctpn) {
                if (cthd.getMaPN().equals(chitiet.getMaPN()) && cthd.getMaSP().equals(chitiet.getMaSP())) {
                    ChiTietPhieuNhap pn = new ChiTietPhieuNhap(chitiet.getMaPN(),chitiet.getMaSP(),chitiet.getsLuong(),chitiet.getDonGia());
                    updateTongTienSua(pn.getMaPN(),pn.getsLuong());
                }
            }
        }
        return success;
    }
    
    private Boolean updateTongTienSua(String _mahd,int _sL) {
        float tong = 0;
        for (ChiTietPhieuNhap ct : dsctpn) {
            if (ct.getMaPN().equals(_mahd)) {
                tong += _sL * ct.getDonGia(); // đang fix
            }
        }
        Boolean success = qlpnBUS.updateTongTien(_mahd, tong);
        return success;
    }

    private Boolean updateTongTien(String _mapn) {
        float tong = 0;
        for (ChiTietPhieuNhap ct : dsctpn) {
            if (ct.getMaPN().equals(_mapn)) {
                tong += ct.getsLuong() * ct.getDonGia();
            }
        }
        Boolean success = qlpnBUS.updateTongTien(_mapn, tong);

        return success;
    }
}
