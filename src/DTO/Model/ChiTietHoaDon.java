/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Model;



/**
 *
 * @author User
 */
public class ChiTietHoaDon {
   private String maHoaDon;
    private String maSanPham;
    private int soLuong;
    private double donGia;

    public ChiTietHoaDon() {
    
    }
    public ChiTietHoaDon(String mahd, String masp, int soluong, double dongia) {
        this.maHoaDon = mahd;
        this.maSanPham = masp;
        this.soLuong = soluong;
        this.donGia = dongia;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

}
