/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.ChiTietHoaDon;
import java.util.ArrayList;
import java.util.Iterator;
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
public class ChiTietHoaDonServiceTest {
    
    public ChiTietHoaDonServiceTest() {
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
    public void testGetDscthd() {
        System.out.println("getDscthd");
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        ArrayList<ChiTietHoaDon> expResult = null;
        ArrayList<ChiTietHoaDon> result = instance.getDscthd();
        assertNotNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testReadDB() {
        System.out.println("readDB");
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        instance.readDB();
        assertNotNull(instance.dscthd);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetChiTiet() {
        System.out.println("getChiTiet");
        String mahd = "HD1";
        String masp = "IP09K";
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        ChiTietHoaDon expResult = new ChiTietHoaDon("HD1", "IP09K", 3, 21.9);
        ChiTietHoaDon result = instance.getChiTiet(mahd, masp);
        
        assertTrue(expResult.getMaHoaDon().equals(result.getMaHoaDon()));
        assertTrue(expResult.getMaSanPham().equals(result.getMaSanPham()));
        assertTrue(expResult.getSoLuong() == (result.getSoLuong()));
        assertTrue(expResult.getDonGia() == (result.getDonGia()));
        
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllChiTiet() {
        System.out.println("getAllChiTiet");
        String mahd = "HD1";
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        int expResult = 0;
        ArrayList<ChiTietHoaDon> result = instance.getAllChiTiet(mahd);
        assertNotEquals(expResult, result.size());
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_ChiTietHoaDon() {
        System.out.println("add");
        ChiTietHoaDon ct = new ChiTietHoaDon("HD4", "IPJC7", 3, 43.9);
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        
        Boolean result = instance.add(ct);
        Boolean expResult = instance.getDscthd().contains(ct);
        
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_4args() {
        System.out.println("add");
        String maHoaDon = "HD4";
        String maSanPham = "SS776";
        int soLuong = 5;
        float donGia = 9.9F;
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        Boolean expResult = null;
        Boolean result = instance.add(maHoaDon, maSanPham, soLuong, donGia);
        assertTrue(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_4args() {
        System.out.println("update");
        String maHoaDon = "HD4";
        String maSanPham = "SS776";
        int soLuong = 10;
        float donGia = 9.9F;
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        Boolean expResult = null;
        Boolean result = instance.update(maHoaDon, maSanPham, soLuong, donGia);
        assertTrue(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_ChiTietHoaDon() {
        System.out.println("update");
        ChiTietHoaDon chitiet = new ChiTietHoaDon("HD4", "IPJC7", 1, 43.9);
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        
        Boolean result = instance.update(chitiet);
        instance.readDB();
        ArrayList<ChiTietHoaDon>  expResult = instance.getDscthd();
        
        for (Iterator<ChiTietHoaDon> cthd = expResult.iterator(); cthd.hasNext();) {
            ChiTietHoaDon next = cthd.next();
            if (next.getMaHoaDon().equals(chitiet.getMaHoaDon()))
            {
//                assertTrue(next.getMaHoaDon().equals(chitiet.getMaHoaDon()));
                assertTrue(expResult.toString(),next.getMaSanPham().equals(chitiet.getMaSanPham()));
                assertTrue(expResult.toString(),next.getSoLuong() == (chitiet.getSoLuong()));
                assertTrue(expResult.toString(),next.getDonGia() == (chitiet.getDonGia()));
                
            }
            
        }
        
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String _maHoaDon = "HD4";
        String _maSanPham = "SS776";
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        Boolean expResult = true;
        Boolean result = instance.delete(_maHoaDon, _maSanPham);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteAll() {
        System.out.println("deleteAll");
        String _maHoaDon = "HD4";
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        Boolean expResult = true;
        Boolean result = instance.deleteAll(_maHoaDon);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String type = "Tất cả";
        String keyword = "HD1";
        int soLuong1 = 0;
        int soLuong2 = 1;
        float thanhTien1 = 0.0F;
        float thanhTien2 = 100.0F;
        ChiTietHoaDonService instance = new ChiTietHoaDonService();
        int expResult = 0;
        ArrayList<ChiTietHoaDon> result = instance.search(type, keyword, soLuong1, soLuong2, thanhTien1, thanhTien2);
        assertEquals(expResult, result.size());
//        fail("The test case is a prototype.");
    }
    
}
