/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.HoaDon;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class HoaDonDaoTest {
    
    public HoaDonDaoTest() {
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
        HoaDonDao instance = new HoaDonDao();
        ArrayList<HoaDon> expResult = null;
        ArrayList<HoaDon> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        HoaDon hd = new HoaDon("HD7", "E02", "KH3", "KM1", LocalDate.of(2021, 4, 25), LocalTime.of(14, 14, 45), 0);
        HoaDonDao instance = new HoaDonDao();
        Boolean expResult = true;
        Boolean result = instance.add(hd);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String mahd = "HD7";
        HoaDonDao instance = new HoaDonDao();
        Boolean expResult = true;
        Boolean result = instance.delete(mahd);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_HoaDon() {
        System.out.println("update");
        HoaDon hd = new HoaDon("HD7", "E01", "KH1", "KM1", LocalDate.of(2021, 4, 26), LocalTime.of(12, 25, 45), 0);
        
        HoaDonDao instance = new HoaDonDao();
        Boolean expResult = true;
        Boolean result = instance.update(hd);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateTongTien() {
        System.out.println("updateTongTien");
        String _mahd = "HD7";
        float _tongTien = 1.0F;
        HoaDonDao instance = new HoaDonDao();
        Boolean expResult = true;
        Boolean result = instance.updateTongTien(_mahd, _tongTien);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate_7args() {
        System.out.println("update 7args");
        String maHoaDon = "HD7";
        String maNhanVien = "E02";
        String maKhachHang = "KH5";
        String maKhuyenMai = "KM1";
        LocalDate ngayLap = LocalDate.of(2021, 4, 27);
        LocalTime gioLap = LocalTime.of(18, 21, 45);
        float tongTien = 0.0F;
        HoaDonDao instance = new HoaDonDao();
        Boolean expResult = true;
        Boolean result = instance.update(maHoaDon, maNhanVien, maKhachHang, maKhuyenMai, ngayLap, gioLap, tongTien);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testClose() {
        System.out.println("close");
        HoaDonDao instance = new HoaDonDao();
        instance.readDB();
        instance.close();
        try {
            boolean result = instance.connection.conn.isClosed();
            assertEquals(true, result);
//        fail("The test case is a prototype.");
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
