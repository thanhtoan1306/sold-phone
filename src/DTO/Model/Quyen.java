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
public class Quyen {
    String MaQuyen;
    String TenQuyen;
    String ChiTietQuyen;

    public Quyen(String MaQuyen, String TenQuyen, String ChiTietQuyen) {
        this.MaQuyen = MaQuyen;
        this.TenQuyen = TenQuyen;
        this.ChiTietQuyen = ChiTietQuyen;
    }

    public String getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(String MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public String getTenQuyen() {
        return TenQuyen;
    }

    public void setTenQuyen(String TenQuyen) {
        this.TenQuyen = TenQuyen;
    }

    public String getChiTietQuyen() {
        return ChiTietQuyen;
    }

    public void setChiTietQuyen(String ChiTietQuyen) {
        this.ChiTietQuyen = ChiTietQuyen;
    }
    
}
