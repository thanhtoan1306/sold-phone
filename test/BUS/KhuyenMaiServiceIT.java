/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.KhuyenMai;
import java.time.LocalDate;
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
public class KhuyenMaiServiceIT {
    
    public KhuyenMaiServiceIT() {
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
        KhuyenMaiService instance = new KhuyenMaiService();
        instance.readDB();
        assertNotNull(instance.getDskm());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetNextID() {
        System.out.println("getNextID");
        KhuyenMaiService instance = new KhuyenMaiService();
        String expResult ="KM6";
        String result = instance.getNextID();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetKhuyenMai() {
        System.out.println("getKhuyenMai");
        String makm = "KM2";
        KhuyenMaiService instance = new KhuyenMaiService();
        KhuyenMai expResult = null;
        KhuyenMai result = instance.getKhuyenMai(makm);
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String value = "2021";
        String type = "Tất cả";
        int dk1 = -1;
        int dk2 = -1;
        float phantram1 = -1;
        float phantram2 = -1;
        LocalDate ngay1 = null;
        LocalDate ngay2 = null;
        KhuyenMaiService instance = new KhuyenMaiService();
        ArrayList<KhuyenMai> expResult = null;
        ArrayList<KhuyenMai> result = instance.search(value, type, dk1, dk2, phantram1, phantram2, ngay1, ngay2);
        assertEquals(2, result.size());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_KhuyenMai() {
        System.out.println("add");
        KhuyenMai km = new KhuyenMai("KM8", "Chạy đồ án", 3.0F, 25.0F, LocalDate.of(2021, 5, 3), LocalDate.of(2021, 6, 1));
        KhuyenMaiService instance = new KhuyenMaiService();
        Boolean expResult = true;
        Boolean result = instance.add(km);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_6args() {
        System.out.println("add");
        String makm = "KM7";
        String tenkm = "Lễ 01-05";
        float dkkm = 2.0F;
        float phantramkm = 10.0F;
        LocalDate ngaybd = LocalDate.of(2021, 4, 27);
        LocalDate ngaykt = LocalDate.of(2021, 5, 2);
        KhuyenMaiService instance = new KhuyenMaiService();
        Boolean expResult = true;
        Boolean result = instance.add(makm, tenkm, dkkm, phantramkm, ngaybd, ngaykt);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String makm = "KM8";
        KhuyenMaiService instance = new KhuyenMaiService();
        Boolean expResult = true;
        Boolean result = instance.delete(makm);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String makm = "KM3";
        String tenkm = "Ngày sinh nhật của Sếp Tùng";
        float dkkm = 2.0F;
        float phantramkm = 60.0F;
        LocalDate ngaybd = LocalDate.of(2020, 06, 13);
        LocalDate ngaykt = LocalDate.of(2020, 06, 27);
        KhuyenMaiService instance = new KhuyenMaiService();
        Boolean expResult = true;
        Boolean result = instance.update(makm, tenkm, dkkm, phantramkm, ngaybd, ngaykt);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetDskm() {
        System.out.println("getDskm");
        KhuyenMaiService instance = new KhuyenMaiService();
        ArrayList<KhuyenMai> expResult = null;
        ArrayList<KhuyenMai> result = instance.getDskm();
        assertNotNull(result);
       // fail("The test case is a prototype.");
    }
    
}
