/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Model.Quyen;
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
public class QuyenDaoTest {
    
    public QuyenDaoTest() {
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
        QuyenDao instance = new QuyenDao();
        ArrayList<Quyen> expResult = null;
        ArrayList<Quyen> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String columnName = "TenQuyen";
        String value = "Nhân viên";
        QuyenDao instance = new QuyenDao();
        ArrayList<Quyen> expResult = null;
        ArrayList<Quyen> result = instance.search(columnName, value);
        assertEquals(2, result.size());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        Quyen q = new Quyen("Q10", "Phụ việc", "xemLoaiSanPham xemSanPham");
        QuyenDao instance = new QuyenDao();
        Boolean expResult = true;
        Boolean result = instance.add(q);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String maq = "Q10";
        QuyenDao instance = new QuyenDao();
        Boolean expResult = true;
        Boolean result = instance.delete(maq);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String maq = "Q10";
        String tenquyen = "Gián điệp";
        String chitietquyen = "qlNhanVien qlSanPham qlKhachHang";
        QuyenDao instance = new QuyenDao();
        Boolean expResult = true;
        Boolean result = instance.update(maq, tenquyen, chitietquyen);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testClose() {
        System.out.println("close");
        QuyenDao instance = new QuyenDao();
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
