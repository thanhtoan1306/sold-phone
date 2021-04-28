/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.ChiTietPhieuNhap;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ACER
 */
public class ChiTietPhieuNhapDaoTest {
    
    public ChiTietPhieuNhapDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testReadDB() {
        System.out.println("readDB");
        ChiTietPhieuNhapDao instance = new ChiTietPhieuNhapDao();
        ArrayList<ChiTietPhieuNhap> expResult = null;
        ArrayList<ChiTietPhieuNhap> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String columName = "MASP";
        String value = "IP";
        ChiTietPhieuNhapDao instance = new ChiTietPhieuNhapDao();
        ArrayList<ChiTietPhieuNhap> expResult = null;
        ArrayList<ChiTietPhieuNhap> result = instance.search(columName, value);
        assertEquals(2, result.size());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap("PN3", "IP134", 12, 40);
        ChiTietPhieuNhapDao instance = new ChiTietPhieuNhapDao();
        boolean expResult = true;
        boolean result = instance.add(ctpn);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteAll() {
        System.out.println("deleteAll");
        String _mapn = "PN4";
        ChiTietPhieuNhapDao instance = new ChiTietPhieuNhapDao();
        Boolean expResult = true;
        Boolean result = instance.deleteAll(_mapn);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String _mapn = "PN3";
        String _masp = "IP134";
        ChiTietPhieuNhapDao instance = new ChiTietPhieuNhapDao();
        Boolean expResult = true;
        Boolean result = instance.delete(_mapn, _masp);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_ChiTietPhieuNhap() {
        System.out.println("update");
        ChiTietPhieuNhap ct = new ChiTietPhieuNhap("PN3", "IP134", 20, 40);
        ChiTietPhieuNhapDao instance = new ChiTietPhieuNhapDao();
        boolean expResult = true;
        boolean result = instance.update(ct);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_4args() {
        System.out.println("update");
        String maPhieuNhap = "PN3";
        String maSanPham = "IP134";
        int soLuong = 8;
        float donGia = 40.0F;
        ChiTietPhieuNhapDao instance = new ChiTietPhieuNhapDao();
        Boolean expResult = true;
        Boolean result = instance.update(maPhieuNhap, maSanPham, soLuong, donGia);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
