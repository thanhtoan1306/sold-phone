/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

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
 * @author HOME
 */
public class ChiTietPhieuNhapServiceIT {
    
    public ChiTietPhieuNhapServiceIT() {
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
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        instance.readDB();
        assertNotNull(instance.dsctpn);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String type = "Tất cả";
        String value = "PN2";
        int soLuong1 = 1;
        int soLuong2 = 60;
        float thanhTien1 = 1.0F;
        float thanhTien2 = 800.0F;
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        ArrayList<ChiTietPhieuNhap> expResult = null;
        ArrayList<ChiTietPhieuNhap> result = instance.search(type, value, soLuong1, soLuong2, thanhTien1, thanhTien2);
        assertEquals(1, result.size());
        //fail("The test case is a prototype.");
    }

   /* @Test
    public void testDeleteAll() {
        System.out.println("deleteAll");
        String _maPhieuNhap = "PN6";
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        Boolean expResult = true;
        Boolean result = instance.deleteAll(_maPhieuNhap);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test*/
    public void testGetChiTiet() {
        System.out.println("getChiTiet");
        String mapn = "PN2";
        String masp = "IP134";
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        ChiTietPhieuNhap expResult = null;
        ChiTietPhieuNhap result = instance.getChiTiet(mapn, masp);
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllChiTiet() {
        System.out.println("getAllChiTiet");
        String mapn = "PN2";
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        ArrayList<ChiTietPhieuNhap> expResult = null;
        ArrayList<ChiTietPhieuNhap> result = instance.getAllChiTiet(mapn);
        assertEquals(1, result.size());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testDelete_String_String() {
        System.out.println("delete");
        String _maPhieuNhap = "PN6";
        String _maSanPham = "IPFAK";
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        Boolean expResult = true;
        Boolean result = instance.delete(_maPhieuNhap, _maSanPham);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_ChiTietPhieuNhap() {
        System.out.println("add");
        ChiTietPhieuNhap ct = new ChiTietPhieuNhap("PN6", "OP1J3", 10, 7.0f);
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        Boolean expResult = true;
        Boolean result = instance.add(ct);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_4args() {
        System.out.println("add");
        String ma = "PN6";
        String masp = "IPFAK";
        Integer soluong = 6;
        Float dongia = 15.0F;
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        boolean expResult = true;
        boolean result = instance.add(ma, masp, soluong, dongia);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /*@Test
    public void testDelete_String() {
        System.out.println("delete");
        String ma = "";
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        boolean expResult = false;
        boolean result = instance.delete(ma);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }*/

    @Test
    public void testUpdate_4args() {
        System.out.println("update");
        String maPhieuNhap = "PN4";
        String maSanPham = "IPFAK";
        int soLuong = 15;
        float donGia = 15.0F;
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        Boolean expResult = true;
        Boolean result = instance.update(maPhieuNhap, maSanPham, soLuong, donGia);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_ChiTietPhieuNhap() {
        System.out.println("update");
        ChiTietPhieuNhap chitiet = new ChiTietPhieuNhap("PN2", "IPFAK", 20, 15.0f);
        ChiTietPhieuNhapService instance = new ChiTietPhieuNhapService();
        Boolean expResult = true;
        Boolean result = instance.update(chitiet);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }
    
}
