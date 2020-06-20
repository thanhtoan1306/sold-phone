/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Model;


import java.time.LocalDate;

/**
 *
 * @author User
 */
public class KhuyenMai {

    String MaKM, TenKM;
    float DieuKienKM, PhanTramKM;
    LocalDate NgayBD, NgayKT;

    public KhuyenMai () {}
    public KhuyenMai(String MaKM, String TenKM, float DieuKienKM, float PhanTramKM, LocalDate NgayBD, LocalDate NgayKT) {
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.DieuKienKM = DieuKienKM;
        this.PhanTramKM = PhanTramKM;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
    }
    
        public String getTrangThai() {
        LocalDate now = LocalDate.now();
        if (now.isBefore(this.NgayBD)) {
            return "Chưa bắt đầu";
        } else if (now.isAfter(this.NgayKT)) {
            return "Đã kết thúc";
        } else {
            return "Đang diễn ra";
        }
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public float getDieuKienKM() {
        return DieuKienKM;
    }

    public void setDieuKienKM(float DieuKienKM) {
        this.DieuKienKM = DieuKienKM;
    }

    public float getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(float PhanTramKM) {
        this.PhanTramKM = PhanTramKM;
    }

    public LocalDate getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(LocalDate NgayBD) {
        this.NgayBD = NgayBD;
    }

    public LocalDate getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(LocalDate NgayKT) {
        this.NgayKT = NgayKT;
    }
    
    

}
