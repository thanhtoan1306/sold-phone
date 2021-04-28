/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
 * @author ACER
 */
public class HangSanPhamDaoTest {
    
    public HangSanPhamDaoTest() {
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
        HangSanPhamDao instance = new HangSanPhamDao();
        ArrayList<HangSanPham> expResult = null;
        ArrayList<HangSanPham> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String columnName = "TenHSP";
        String value = "one";
        HangSanPhamDao instance = new HangSanPhamDao();
        int expResult = 1;
        ArrayList<HangSanPham> result = instance.search(columnName, value);
        assertEquals(expResult, result.size());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        HangSanPham hsp = new HangSanPham("NO", "Nokia");
        HangSanPhamDao instance = new HangSanPhamDao();
        Boolean expResult = true;
        Boolean result = instance.add(hsp);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String mahsp = "NO";
        HangSanPhamDao instance = new HangSanPhamDao();
        Boolean expResult = true;
        Boolean result = instance.delete(mahsp);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String MaHSP = "NO";
        String TenHSP = "TestNokia2";
        HangSanPhamDao instance = new HangSanPhamDao();
        Boolean expResult = true;
        Boolean result = instance.update(MaHSP, TenHSP);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
