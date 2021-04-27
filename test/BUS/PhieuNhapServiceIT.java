/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

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
 * @author HOME
 */
public class PhieuNhapServiceIT {
    
    public PhieuNhapServiceIT() {
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
        PhieuNhapService instance = new PhieuNhapService();
        instance.readDB();
        assertNotNull(instance.dspn);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetNextID() {
        System.out.println("getNextID");
        PhieuNhapService instance = new PhieuNhapService();
        String expResult = "PN4";
        String result = instance.getNextID();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetPhieuNhap() {
        System.out.println("getPhieuNhap");
        String mapn = "PN2";
        PhieuNhapService instance = new PhieuNhapService();
        PhieuNhap expResult = null;
        PhieuNhap result = instance.getPhieuNhap(mapn);
        assertNotNull(result);
       // fail("The test case is a prototype.");
    }

    @Test
    public void testGetDspn() {
        System.out.println("getDspn");
        PhieuNhapService instance = new PhieuNhapService();
        ArrayList<PhieuNhap> expResult = null;
        ArrayList<PhieuNhap> result = instance.getDspn();
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String type = "Tất cả";
        String value = "2021";
        LocalDate _ngay1 = null;
        LocalDate _ngay2 = null;
        int _tong1 = 1;
        int _tong2 = 170;
        PhieuNhapService instance = new PhieuNhapService();
        ArrayList<PhieuNhap> expResult = null;
        ArrayList<PhieuNhap> result = instance.search(type, value, _ngay1, _ngay2, _tong1, _tong2);
        assertEquals(1, result.size());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_PhieuNhap() {
        System.out.println("add");
        PhieuNhap pn = new PhieuNhap("PN8", "NCC4", "E01", LocalDate.of(2021, 05, 10), LocalTime.of(8, 0, 0), 58.0F);
        PhieuNhapService instance = new PhieuNhapService();
        boolean expResult = true;
        boolean result = instance.add(pn);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_6args() {
        System.out.println("update");
        String maPN = "PN1";
        String maNCC = "NCC2";
        String maNV = "E01";
        LocalDate ngayNhap = LocalDate.of(2000, 11, 20);
        LocalTime gioNhap = LocalTime.of(8, 0, 0);
        float tongTien = 48.0F;
        PhieuNhapService instance = new PhieuNhapService();
        Boolean expResult = true;
        Boolean result = instance.update(maPN, maNCC, maNV, ngayNhap, gioNhap, tongTien);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_PhieuNhap() {
        System.out.println("update");
        PhieuNhap pn = new PhieuNhap("PN3", "NCC4", "E02", LocalDate.of(2021, 04, 30), LocalTime.of(10, 30, 0), 50.0F);
        PhieuNhapService instance = new PhieuNhapService();
        Boolean expResult = true;
        Boolean result = instance.update(pn);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateTongTien() {
        System.out.println("updateTongTien");
        String _mapn = "PN06";
        float _tongTien = 90.0F;
        PhieuNhapService instance = new PhieuNhapService();
        Boolean expResult = true;
        Boolean result = instance.updateTongTien(_mapn, _tongTien);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_6args() {
        System.out.println("add");
        String maPN = "PN7";
        String maNCC = "NCC3";
        String maNV = "E02";
        LocalDate ngayNhap = LocalDate.of(2020, 06, 30);
        LocalTime gioNhap = LocalTime.of(11, 0, 0);
        float tongTien = 60.0F;
        PhieuNhapService instance = new PhieuNhapService();
        boolean expResult = true;
        boolean result = instance.add(maPN, maNCC, maNV, ngayNhap, gioNhap, tongTien);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String ma = "PN7";
        PhieuNhapService instance = new PhieuNhapService();
        boolean expResult = true;
        boolean result = instance.delete(ma);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }
    
}
