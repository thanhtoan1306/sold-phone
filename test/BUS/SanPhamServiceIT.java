/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.SanPham;
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
public class SanPhamServiceIT {
    
    public SanPhamServiceIT() {
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
        SanPhamService instance = new SanPhamService();
        instance.readDB();
        assertNotNull(instance.getDssp());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetSanPham() {
        System.out.println("getSanPham");
        String masp = "IP09K";
        SanPhamService instance = new SanPhamService();
        SanPham expResult = null;
        SanPham result = instance.getSanPham(masp);
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }

   /* @Test
    public void testGetNextID() {
        System.out.println("getNextID");
        SanPhamService instance = new SanPhamService();
        String expResult = "";
        String result = instance.getNextID();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }*/

    @Test
    public void testSearch() {
        System.out.println("search");
        String value = "iPhone";
        String type = "Tất cả";
        int soluong1 = -1;
        int soluong2 = -1;
        float gia1 = -1;
        float gia2 = -1;
        SanPhamService instance = new SanPhamService();
        ArrayList<SanPham> expResult = null;
        ArrayList<SanPham> result = instance.search(value, type, soluong1, soluong2, gia1, gia2);
        assertEquals(5, result.size());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_SanPham() {
        System.out.println("add");
        SanPham sp = new SanPham("IP12P", "IP", "iPhone 12 Pro", 30.0F, 5, "");
        SanPhamService instance = new SanPhamService();
        Boolean expResult = true;
        Boolean result = instance.add(sp);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_6args() {
        System.out.println("add");
        String masp = "IP09T";
        String malsp = "IP";
        String tensp = "iPhone 12";
        float dongia = 20.0F;
        int soluong = 10;
        String filename = "";
        SanPhamService instance = new SanPhamService();
        Boolean expResult = true;
        Boolean result = instance.add(masp, malsp, tensp, dongia, soluong, filename);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String masp = "IP12P";
        SanPhamService instance = new SanPhamService();
        Boolean expResult = true;
        Boolean result = instance.delete(masp);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String masp = "IP12P";
        String mahsp = "IP";
        String tensp = "iPhone 12 ProVIp";
        float dongia = 27.0F;
        int soluong = 20;
        String filename = "";
        SanPhamService instance = new SanPhamService();
        Boolean expResult = true;
        Boolean result = instance.update(masp, mahsp, tensp, dongia, soluong, filename);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateSoLuong() {
        System.out.println("updateSoLuong");
        String masp = "IP09T";
        int soluong = 30;
        SanPhamService instance = new SanPhamService();
        Boolean expResult = true;
        Boolean result = instance.updateSoLuong(masp, soluong);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetDssp() {
        System.out.println("getDssp");
        SanPhamService instance = new SanPhamService();
        ArrayList<SanPham> expResult = null;
        ArrayList<SanPham> result = instance.getDssp();
        assertNotNull(result);
        //ail("The test case is a prototype.");
    }
    
}
