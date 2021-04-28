/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.PhieuNhap;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class PhieuNhapDaoTest {
    
    public PhieuNhapDaoTest() {
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
        PhieuNhapDao instance = new PhieuNhapDao();
        ArrayList<PhieuNhap> expResult = null;
        ArrayList<PhieuNhap> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        PhieuNhap pn = new PhieuNhap("PN5", "NCC5", "E05", LocalDate.of(2021, 4, 25), LocalTime.of(15, 15, 15), 0);
        PhieuNhapDao instance = new PhieuNhapDao();
        Boolean expResult = true;
        Boolean result = instance.add(pn);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String mapn = "PN5";
        PhieuNhapDao instance = new PhieuNhapDao();
        Boolean expResult = true;
        Boolean result = instance.delete(mapn);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_PhieuNhap() {
        System.out.println("update");
        PhieuNhap pn = new PhieuNhap("PN5", "NCC2", "E02", LocalDate.of(2021, 4, 25), LocalTime.of(15, 15, 15), 0);
        PhieuNhapDao instance = new PhieuNhapDao();
        Boolean expResult = true;
        Boolean result = instance.update(pn);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateTongTien() {
        System.out.println("updateTongTien");
        String _mapn = "PN5";
        float _tongTien = 10.0F;
        PhieuNhapDao instance = new PhieuNhapDao();
        Boolean expResult = true;
        Boolean result = instance.updateTongTien(_mapn, _tongTien);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_6args() {
        System.out.println("update");
        String maPN = "PN5";
        String maNCC = "NCC4";
        String maNV = "E04";
        LocalDate ngayNhap = LocalDate.of(2021, 4, 26);
        LocalTime gioNhap = LocalTime.of(16, 10, 15);
        float tongTien = 0.0F;
        PhieuNhapDao instance = new PhieuNhapDao();
        Boolean expResult = true;
        Boolean result = instance.update(maPN, maNCC, maNV, ngayNhap, gioNhap, tongTien);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
