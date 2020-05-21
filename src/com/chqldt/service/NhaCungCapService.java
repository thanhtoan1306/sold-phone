/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chqldt.service;



import com.qlchdt.dao.NhaCungCapDao;
import com.qlchdt.model.NhaCungCap;
import java.util.List;

/**
 *
 * @author User
 */
public class NhaCungCapService {
    
      private NhaCungCapDao nhacungcapDao;

    public NhaCungCapService() {
        nhacungcapDao = new NhaCungCapDao();
    }

    public List<NhaCungCap> getAllNhaCungCap() {
        return nhacungcapDao.getAllNhaCungCap();
    }
    
    public void addNcc(NhaCungCap ncc) {
    nhacungcapDao.addNcc(ncc);
    }
    
    
}
