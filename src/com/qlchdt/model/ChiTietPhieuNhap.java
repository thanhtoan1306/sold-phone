/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.model;

/**
 *
 * @author User
 */
public class ChiTietPhieuNhap {
    String maPN;
    String maSP;
    int sLuong;
    float donGia;

    
    public ChiTietPhieuNhap() {
    
    }
    public ChiTietPhieuNhap(String maPN, String maSP, int sLuong, float donGia) {
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

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }
    
    
    
}
