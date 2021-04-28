/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.Quyen;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HOME
 */
public class QuyenServiceIT {
    
    public QuyenServiceIT() {
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
        QuyenService instance = new QuyenService();
        instance.readDB();
        assertNotNull(instance.getDsq());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetNextID() {
        System.out.println("getNextID");
        QuyenService instance = new QuyenService();
        String expResult = "Q6";
        String result = instance.getNextID();
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetQuyen() {
        System.out.println("getQuyen");
        String maquyen = "Q1";
        QuyenService instance = new QuyenService();
        Quyen expResult = null;
        Quyen result = instance.getQuyen(maquyen);
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String value = "qlBanHang";
        String type = "Tất cả";
        QuyenService instance = new QuyenService();
        ArrayList<Quyen> expResult = null;
        ArrayList<Quyen> result = instance.search(value, type);
        assertEquals(2, result.size());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_Quyen() {
        System.out.println("add");
        Quyen sp = new Quyen("Q6", "Ăn hàng ở không", "");
        QuyenService instance = new QuyenService();
        Boolean expResult = true;
        Boolean result = instance.add(sp);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_3args() {
        System.out.println("add");
        String maquyen = "Q5";
        String tenquyen = "Nhân viên bán hàng (parttime)";
        String chitiet = "xemSanPham xemLoaiSanPham xemHoaDon";
        QuyenService instance = new QuyenService();
        Boolean expResult = true;
        Boolean result = instance.add(maquyen, tenquyen, chitiet);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String maquyen = "Q5";
        QuyenService instance = new QuyenService();
        Boolean expResult = true;
        Boolean result = instance.delete(maquyen);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String maquyen = "Q6";
        String tenquyen = "Ăn không ngồi rồi";
        String chitiet = "xemSanPham";
        QuyenService instance = new QuyenService();
        Boolean expResult = true;
        Boolean result = instance.update(maquyen, tenquyen, chitiet);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetDsq() {
        System.out.println("getDsq");
        QuyenService instance = new QuyenService();
        ArrayList<Quyen> expResult = null;
        ArrayList<Quyen> result = instance.getDsq();
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }
    
}
