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
public class TaiKhoan {
    
    private String tentk;
    private String mk;
    private String manv;
    private String maquyen;

    public TaiKhoan() {
    }

    public TaiKhoan(String tentk, String mk, String manv, String maquyen) {
        this.tentk = tentk;
        this.mk = mk;
        this.manv = manv;
        this.maquyen = maquyen;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMaquyen() {
        return maquyen;
    }

    public void setMaquyen(String maquyen) {
        this.maquyen = maquyen;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }
}
