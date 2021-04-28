/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.TaiKhoan;
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
public class TaiKhoanDaoTest {
    
    public TaiKhoanDaoTest() {
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
        TaiKhoanDao instance = new TaiKhoanDao();
        ArrayList<TaiKhoan> expResult = null;
        ArrayList<TaiKhoan> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String columnName = "TenTK";
        String value = "admin";
        TaiKhoanDao instance = new TaiKhoanDao();
        ArrayList<TaiKhoan> expResult = null;
        ArrayList<TaiKhoan> result = instance.search(columnName, value);
        assertEquals(1, result.size());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        TaiKhoan tk = new TaiKhoan("tienTien", "87654321", "E05", "Q2");
        TaiKhoanDao instance = new TaiKhoanDao();
        Boolean expResult = true;
        Boolean result = instance.add(tk);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String manv = "E05";
        TaiKhoanDao instance = new TaiKhoanDao();
        Boolean expResult = true;
        Boolean result = instance.delete(manv);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String tentk = "tienTien";
        String matkhau = "123456789";
        String manv = "E05";
        String maquyen = "Q1";
        TaiKhoanDao instance = new TaiKhoanDao();
        Boolean expResult = true;
        Boolean result = instance.update(tentk, matkhau, manv, maquyen);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
