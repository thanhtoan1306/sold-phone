/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Model;


public class HangSanPham {
    String MaHang;
    String TenHang;

    public HangSanPham() {

    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public HangSanPham(String MaHang, String TenHang) {
        this.MaHang = MaHang;
        this.TenHang = TenHang;
    }
    
}
