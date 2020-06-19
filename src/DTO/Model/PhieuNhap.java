/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Model;


import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author User
 */
public class PhieuNhap {

    String maPN;
    String maNCC;
    String maNV;
    LocalDate ngayNhap;
    LocalTime gioNhap;
    float tongTien = 0;

    public PhieuNhap() {
        this.ngayNhap = LocalDate.now();

    }

    public PhieuNhap(String maPN, String maNCC, String maNV, LocalDate ngayNhap, LocalTime gioNhap,float tongTien) {
        this.maPN = maPN;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.ngayNhap = ngayNhap;
        this.gioNhap = gioNhap;
        this.tongTien = tongTien;
    }

 

    public LocalTime getGioNhap() {
        return gioNhap;
    }

    public void setGioNhap(LocalTime gioNhap) {
        this.gioNhap = gioNhap;
    }


    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
    

}
