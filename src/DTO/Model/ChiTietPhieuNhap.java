/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Model;

public class ChiTietPhieuNhap {
    String maPN;
    String maSP;
    int sLuong;
    double donGia;

    
    public ChiTietPhieuNhap() {
    
    }
    public ChiTietPhieuNhap(String maPN, String maSP, int sLuong, double donGia) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.sLuong = sLuong;
        this.donGia = donGia;
    }
    
    

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getsLuong() {
        return sLuong;
    }

    public void setsLuong(int sLuong) {
        this.sLuong = sLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    
    
}
