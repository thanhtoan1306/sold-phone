/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.KhuyenMai;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class KhuyenMaiDaoTest {
    
    public KhuyenMaiDaoTest() {
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
        KhuyenMaiDao instance = new KhuyenMaiDao();
        ArrayList<KhuyenMai> expResult = null;
        ArrayList<KhuyenMai> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String columnName = "NgayBD";
        String value = "2020";
        KhuyenMaiDao instance = new KhuyenMaiDao();
        ArrayList<KhuyenMai> expResult = null;
        ArrayList<KhuyenMai> result = instance.search(columnName, value);
        assertEquals(3, result.size());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        KhuyenMai km = new KhuyenMai("KM5", "Lễ tháng 5", 1, 10, LocalDate.of(2021, 5, 1), LocalDate.of(2021,5,31));
        KhuyenMaiDao instance = new KhuyenMaiDao();
        Boolean expResult = true;
        Boolean result = instance.add(km);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");    
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String makm = "KM5";
        KhuyenMaiDao instance = new KhuyenMaiDao();
        Boolean expResult = true;
        Boolean result = instance.delete(makm);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String makm = "KM5";
        String tenkm = "Lễ tháng 5 updated";
        float dkkm = 5.0F;
        float phantramkm = 20.0F;
        LocalDate ngaybd = LocalDate.of(2021, 5, 1);
        LocalDate ngaykt = LocalDate.of(2021, 6, 1);
        KhuyenMaiDao instance = new KhuyenMaiDao();
        Boolean expResult = true;
        Boolean result = instance.update(makm, tenkm, dkkm, phantramkm, ngaybd, ngaykt);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testClose() {
        System.out.println("close");
        KhuyenMaiDao instance = new KhuyenMaiDao();
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
