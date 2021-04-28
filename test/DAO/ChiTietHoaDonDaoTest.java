/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.ChiTietHoaDon;
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
public class ChiTietHoaDonDaoTest {
    
    public ChiTietHoaDonDaoTest() {
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
        ChiTietHoaDonDao instance = new ChiTietHoaDonDao();
        ArrayList expResult = null;
        ArrayList result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        ChiTietHoaDon hd = new ChiTietHoaDon("HD4", "IPFAK", 4, 15.0);
        ChiTietHoaDonDao instance = new ChiTietHoaDonDao();
        Boolean expResult = true;
        Boolean result = instance.add(hd);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String _mahd = "HD4";
        String _masp = "IPFAK";
        ChiTietHoaDonDao instance = new ChiTietHoaDonDao();
        Boolean expResult = true;
        Boolean result = instance.delete(_mahd, _masp);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteAll() {
        System.out.println("deleteAll");
        String _mahd = "HD4";
        ChiTietHoaDon hd = new ChiTietHoaDon("HD4", "SS65F", 3, 23.0);
        
        ChiTietHoaDonDao instance = new ChiTietHoaDonDao();
        instance.add(hd);
        Boolean expResult = true;
        Boolean result = instance.deleteAll(_mahd);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_ChiTietHoaDon() {
        System.out.println("update");
        ChiTietHoaDon ct = new ChiTietHoaDon("HD4", "IPFAK", 10, 15.0);
        ChiTietHoaDonDao instance = new ChiTietHoaDonDao();
        Boolean expResult = true;
        Boolean result = instance.update(ct);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_4args() {
        System.out.println("update");
        String maHoaDon = "HD1";
        String maSanPham = "IP09K";
        int soLuong = 1;
        float donGia = 21.9F;
        ChiTietHoaDonDao instance = new ChiTietHoaDonDao();
        Boolean expResult = true;
        Boolean result = instance.update(maHoaDon, maSanPham, soLuong, donGia);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        ChiTietHoaDonDao instance = new ChiTietHoaDonDao();
        instance.readDB();
        instance.closeConnection();
        try {
            boolean result = instance.connection.conn.isClosed();
            assertEquals(true, result);
//        fail("The test case is a prototype.");
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
