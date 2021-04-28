/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
 * @author ACER
 */
public class SanPhamDaoTest {
    
    public SanPhamDaoTest() {
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
        SanPhamDao instance = new SanPhamDao();
        ArrayList<SanPham> expResult = null;
        ArrayList<SanPham> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String columnName = "SL";
        String value = "1";
        SanPhamDao instance = new SanPhamDao();
        ArrayList<SanPham> expResult = null;
        ArrayList<SanPham> result = instance.search(columnName, value);
        assertEquals(4, result.size());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        SanPham sp = new SanPham("OPLOV", "OP", "Oppo 2021", 13.4, 20, null);
        SanPhamDao instance = new SanPhamDao();
        Boolean expResult = true;
        Boolean result = instance.add(sp);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String masp = "OPLOV";
        SanPhamDao instance = new SanPhamDao();
        Boolean expResult = true;
        Boolean result = instance.delete(masp);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String MaSP = "OPLOV";
        String MaHSP = "OP";
        String TenSP = "Oppo Superman";
        float DonGia = 20.2F;
        int SoLuong = 30;
        String filename = null;
        SanPhamDao instance = new SanPhamDao();
        Boolean expResult = true;
        Boolean result = instance.update(MaSP, MaHSP, TenSP, DonGia, SoLuong, filename);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateSoLuong() {
        System.out.println("updateSoLuong");
        String MaSP = "SS97V";
        int SoLuong = 30;
        SanPhamDao instance = new SanPhamDao();
        Boolean expResult = true;
        Boolean result = instance.updateSoLuong(MaSP, SoLuong);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
