/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.KhachHang;
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
public class KhachHangServiceTest {
    
    public KhachHangServiceTest() {
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
    public void testGetDskh() {
        System.out.println("getDskh");
        KhachHangService instance = new KhachHangService();
        int expResult = 0;
        ArrayList<KhachHang> result = instance.getDskh();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testShowConsole() {
        System.out.println("showConsole");
        KhachHangService instance = new KhachHangService();
        instance.showConsole();
//        fail("The test case is a prototype.");
    }

    @Test
    public void testReadDB() {
        System.out.println("readDB");
        KhachHangService instance = new KhachHangService();
        instance.readDB();
        assertNotNull(instance.getDskh());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetKhachHang() {
        System.out.println("getKhachHang");
        String makh = "KH2";
        KhachHangService instance = new KhachHangService();
        KhachHang expResult = null;
        KhachHang result = instance.getKhachHang(makh);
        assertNotNull(result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String value = "Tất cả";
        String type = "KH4";
        KhachHangService instance = new KhachHangService();
        ArrayList<KhachHang> expResult = null;
        ArrayList<KhachHang> result = instance.search(value, type);
        assertEquals(0, result.size());
//        fail("The test case is a prototype.");
    }

//    @Test
//    public void testAdd_KhachHang() {
//        System.out.println("add");
//        KhachHang kh = null;
//        KhachHangService instance = new KhachHangService();
//        Boolean expResult = null;
//        Boolean result = instance.add(kh);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testAdd_4args() {
        System.out.println("add");
        String makh = "KH8";
        String tenkh = "Tài Tâm Đức";
        String diachi = "15 Núi Bạch Đằng";
        String sdt = "0925860920";
        KhachHangService instance = new KhachHangService();
        Boolean expResult = true;
        Boolean result = instance.add(makh, tenkh, diachi, sdt);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetNextID() {
        System.out.println("getNextID");
        KhachHangService instance = new KhachHangService();
        String expResult = "KH8";
        String result = instance.getNextID();
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String makh = "KH8";
        KhachHangService instance = new KhachHangService();
        Boolean expResult = true;
        Boolean result = instance.delete(makh);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String makh = "KH8";
        String tenkh = "Tài Ác Lương";
        String diachi = "28/15 Địa ngục";
        String sdt = "0777777777";
        KhachHangService instance = new KhachHangService();
        Boolean expResult = true;
        Boolean result = instance.update(makh, tenkh, diachi, sdt);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
