/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.KhachHang;
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
public class KhachHangDaoTest {
    
    public KhachHangDaoTest() {
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
        KhachHangDao instance = new KhachHangDao();
        ArrayList<KhachHang> expResult = null;
        ArrayList<KhachHang> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String columnName = "TenKH";
        String value = "Tiến";
        KhachHangDao instance = new KhachHangDao();
        ArrayList<KhachHang> expResult = null;
        ArrayList<KhachHang> result = instance.search(columnName, value);
        assertEquals(1, result.size());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        KhachHang kh = new KhachHang("KH8", "Lương Như Thủy", "Sông Bạch Đằng Việt Nam", "0856931248");
        KhachHangDao instance = new KhachHangDao();
        Boolean expResult = true;
        Boolean result = instance.add(kh);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String makh = "KH8";
        KhachHangDao instance = new KhachHangDao();
        Boolean expResult = true;
        Boolean result = instance.delete(makh);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String MaKH = "KH8";
        String TenKH = "Lương Thủy Cạn";
        String DiaChi = "Sa mạc Đại dương";
        String SDT = "0855693111";
        KhachHangDao instance = new KhachHangDao();
        Boolean expResult = true;
        Boolean result = instance.update(MaKH, TenKH, DiaChi, SDT);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testClose() {
        System.out.println("close");
        KhachHangDao instance = new KhachHangDao();
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
