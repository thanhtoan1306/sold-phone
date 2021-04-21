/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.HangSanPham;
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
public class HangSanPhamServiceTest {
    
    public HangSanPhamServiceTest() {
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
        HangSanPhamService instance = new HangSanPhamService();
        instance.readDB();
        assertNotNull(instance.getDshsp());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetHangSanPham() {
        System.out.println("getHangSanPham");
        String mahang = "IP";
        HangSanPhamService instance = new HangSanPhamService();
        HangSanPham expResult = null;
        HangSanPham result = instance.getHangSanPham(mahang);
        assertNotNull(result);
//        fail("The test case is a prototype.");
    }

//    @Test
//    public void testAdd_HangSanPham() {
//        System.out.println("add");
//        HangSanPham hsp = null;
//        HangSanPhamService instance = new HangSanPhamService();
//        Boolean expResult = null;
//        Boolean result = instance.add(hsp);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testAdd_String_String() {
        System.out.println("add");
        String mahang = "NO";
        String tenhang = "NOKIA";
        HangSanPhamService instance = new HangSanPhamService();
        Boolean expResult = true;
        Boolean result = instance.add(mahang, tenhang);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String mahsp = "NO";
        HangSanPhamService instance = new HangSanPhamService();
        Boolean expResult = true;
        Boolean result = instance.delete(mahsp);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String mahang = "NO";
        String tenhang = "Nokia";
        HangSanPhamService instance = new HangSanPhamService();
        Boolean expResult = true;
        Boolean result = instance.update(mahang, tenhang);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDshsp() {
        System.out.println("getDshsp");
        HangSanPhamService instance = new HangSanPhamService();
        int expResult = 0;
        ArrayList<HangSanPham> result = instance.getDshsp();
        assertNotEquals(expResult, result.size());
//        fail("The test case is a prototype.");
    }
    
}
