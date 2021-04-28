/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.NhaCungCap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class NhaCungCapDaoTest {
    
    public NhaCungCapDaoTest() {
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
        NhaCungCapDao instance = new NhaCungCapDao();
        ArrayList<NhaCungCap> expResult = null;
        ArrayList<NhaCungCap> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        NhaCungCap ncc = new NhaCungCap("NCC10", "Sunshine Factory", "USA", "0635782156");
        NhaCungCapDao instance = new NhaCungCapDao();
        Boolean expResult = true;
        Boolean result = instance.add(ncc);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String columnName = "DiaChi";
        String value = "HCM";
        NhaCungCapDao instance = new NhaCungCapDao();
        ArrayList<NhaCungCap> expResult = null;
        ArrayList<NhaCungCap> result = instance.search(columnName, value);
        assertEquals(2, result.size());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String mancc = "NCC10";
        NhaCungCapDao instance = new NhaCungCapDao();
        Boolean expResult = true;
        Boolean result = instance.delete(mancc);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String ma = "NCC10";
        String ten = "Công ty Bình Dương";
        String diachi = "Bình Thuận";
        String sdt = "0861549862";
        NhaCungCapDao instance = new NhaCungCapDao();
        Boolean expResult = true;
        Boolean result = instance.update(ma, ten, diachi, sdt);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testClose() {
        System.out.println("close");
        NhaCungCapDao instance = new NhaCungCapDao();
        instance.readDB();
        instance.close();
        try {
            assertTrue(instance.connection.conn.isClosed());
//        fail("The test case is a prototype.");
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
