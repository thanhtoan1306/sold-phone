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
public class TaiKhoan {
    
    private String userName;
    private String passWord;
    private String maNV;
    private String maQuyen;

    public TaiKhoan(String userName, String passWord, String maNV, String maQuyen) {
        this.userName = userName;
        this.passWord = passWord;
        this.maNV = maNV;
        this.maQuyen = maQuyen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }


    
    
}
