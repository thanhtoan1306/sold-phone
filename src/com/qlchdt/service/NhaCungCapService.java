/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.service;

import com.qlchdt.dao.NhaCungCapDao;
import com.qlchdt.model.NhaCungCap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class NhaCungCapService {

    private ArrayList<NhaCungCap> dsncc = new ArrayList<>();
    private NhaCungCapDao nhacungcapDao;

    public NhaCungCapService() {
        dsncc = nhacungcapDao.readDB();
    }

    public ArrayList<NhaCungCap> getDsNcc() {
        return dsncc;
    }

    public void readDB() {
        dsncc = nhacungcapDao.readDB();
    }

    public Boolean add(NhaCungCap ncc) {
        Boolean ok = nhacungcapDao.add(ncc);

        if (ok) {
            dsncc.add(ncc);
        }
        return ok;
    }

    public Boolean add(String mancc, String tenncc, String diachi, String sdt) {
        NhaCungCap ncc = new NhaCungCap(mancc, tenncc, diachi, sdt);
        return add(ncc);
    }

}
