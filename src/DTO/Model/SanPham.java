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
public class SanPham {
    String MaSP, MaHSP, TenSP;
    double DonGia;
    int SoLuong;
    String  fileNameHinhAnh;

    public SanPham() {
    }

    public SanPham(String MaSP, String MaHSP, String TenSP, double DonGia, int SoLuong, String fileNameHinhAnh) {
        this.MaSP = MaSP;
        this.MaHSP = MaHSP;
        this.TenSP = TenSP;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        this.fileNameHinhAnh = fileNameHinhAnh;
    }

    
    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaHSP() {
        return MaHSP;
    }

    public void setMaHSP(String MaHSP) {
        this.MaHSP = MaHSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getFileNameHinhAnh() {
        return fileNameHinhAnh;
    }

    public void setFileNameHinhAnh(String fileNameHinhAnh) {
        this.fileNameHinhAnh = fileNameHinhAnh;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    
    
}
