/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.HoaDon;
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
 * @author W7x64Pro
 */
public class HoaDonServiceTest {
    
    public HoaDonServiceTest() {
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
    public void testGetDshd() {
        System.out.println("getDshd");
        HoaDonService instance = new HoaDonService();
        ArrayList<HoaDon> expResult = null;
        ArrayList<HoaDon> result = instance.getDshd();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testReadDB() {
        System.out.println("readDB");
        HoaDonService instance = new HoaDonService();
        instance.readDB();
        assertNotNull(instance.dshd);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetNextID() {
        System.out.println("getNextID");
        HoaDonService instance = new HoaDonService();
        String expResult = "HD" + (instance.getDshd().size()+1);
        String result = instance.getNextID();
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetHoaDon() {
        System.out.println("getHoaDon");
        String mahd = "HD1";
        HoaDonService instance = new HoaDonService();
        HoaDon expResult = null;
        HoaDon result = instance.getHoaDon(mahd);
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

//    @Test
//    public void testAdd_HoaDon() {
//        System.out.println("add");
//        HoaDon hd = new HoaDon("HD5", "E03", "KH3", "KM1", LocalDate.now(), LocalTime.now(), 0);
//        HoaDonService instance = new HoaDonService();
//        Boolean expResult = true;
//        Boolean result = instance.add(hd);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testAdd_7args() {
        System.out.println("add");
        String maHoaDon = "HD5";
        String maNhanVien = "E04";
        String maKhachHang = "KH4";
        String maKhuyenMai = "KM1";
        LocalDate ngayNhap = LocalDate.now();
        LocalTime gioNhap = LocalTime.now();
        float tongTien = 0.0F;
        HoaDonService instance = new HoaDonService();
        Boolean expResult = true;
        Boolean result = instance.add(maHoaDon, maNhanVien, maKhachHang, maKhuyenMai, ngayNhap, gioNhap, tongTien);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_7args() {
        System.out.println("update");
        String maHoaDon = "HD5";
        String maNhanVien = "E02";
        String maKhachHang = "KH2";
        String maKhuyenMai = "KM1";
        LocalDate ngayNhap = LocalDate.now();
        LocalTime gioNhap = LocalTime.now();
        float tongTien = 0.0F;
        HoaDonService instance = new HoaDonService();
        Boolean expResult = true;
        Boolean result = instance.update(maHoaDon, maNhanVien, maKhachHang, maKhuyenMai, ngayNhap, gioNhap, tongTien);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

//    @Test
//    public void testUpdate_HoaDon() {
//        System.out.println("update");
//        HoaDon hd = null;
//        HoaDonService instance = new HoaDonService();
//        Boolean expResult = null;
//        Boolean result = instance.update(hd);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testUpdateTongTien() {
        System.out.println("updateTongTien");
        String _mahd = "HD5";
        float _tongTien = 10.0F;
        HoaDonService instance = new HoaDonService();
        Boolean expResult = true;
        Boolean result = instance.updateTongTien(_mahd, _tongTien);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String maHoaDon = "HD5";
        HoaDonService instance = new HoaDonService();
        instance.readDB();
        Boolean expResult = true;
        Boolean result = instance.delete(maHoaDon);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String type = "Tất cả";
        String keyword = "HD1";
        LocalDate _ngay1 = LocalDate.of(2019, 1, 1);
        LocalDate _ngay2 = LocalDate.of(2022, 1, 1);
        double _tong1 = 0.0F;
        double _tong2 = 1000000000.0F;
        HoaDonService instance = new HoaDonService();
        instance.readDB();
        ArrayList<HoaDon> expResult = null;
        ArrayList<HoaDon> result = instance.search(type, keyword, _ngay1, _ngay2, _tong1, _tong2);
        assertEquals(1, result.size());
//        fail("The test case is a prototype.");
    }
    
}
