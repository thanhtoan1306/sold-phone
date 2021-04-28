/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.TaiKhoan;
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
public class TaiKhoanServiceIT {
    
    public TaiKhoanServiceIT() {
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
        TaiKhoanService instance = new TaiKhoanService();
        instance.readDB();
        assertNotNull(instance.getDstk());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetTaiKhoan() {
        System.out.println("getTaiKhoan");
        String tentk = "banhang";
        TaiKhoanService instance = new TaiKhoanService();
        TaiKhoan expResult = null;
        TaiKhoan result = instance.getTaiKhoan(tentk);
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String value = "123";
        String type = "Tất cả";
        LocalDate _ngay1 = null;
        LocalDate _ngay2 = null;
        TaiKhoanService instance = new TaiKhoanService();
        ArrayList<TaiKhoan> expResult = null;
        ArrayList<TaiKhoan> result = instance.search(value, type, _ngay1, _ngay2);
        assertEquals(4, result.size());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSaveToDatabase() {
        System.out.println("saveToDatabase");
        TaiKhoan tk = new TaiKhoan("annhau", "124654", "E05", "Q4");
        TaiKhoanService instance = new TaiKhoanService();
        Boolean expResult = true;
        Boolean result = instance.saveToDatabase(tk);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        String tentk = "anchoi";
        String mk = "156352";
        String manv = "E07";
        String maquyen = "Q3";
        TaiKhoanService instance = new TaiKhoanService();
        Boolean expResult = true;
        Boolean result = instance.add(tentk, mk, manv, maquyen);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String manv = "E07";
        TaiKhoanService instance = new TaiKhoanService();
        Boolean expResult = true;
        Boolean result = instance.delete(manv);
        assertEquals(expResult, result);
       // fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String tentk = "quanly";
        String mk = "123456test";
        String manv = "E06";
        String maquyen = "Q2";
        TaiKhoanService instance = new TaiKhoanService();
        Boolean expResult = true;
        Boolean result = instance.update(tentk, mk, manv, maquyen);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testKiemtraLogin() {
        System.out.println("kiemtraLogin");
        String user = "banhang";
        String pass = "123456";
        TaiKhoanService instance = new TaiKhoanService();
        int expResult = 2;
        int result = instance.kiemtraLogin(user, pass);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetDstk() {
        System.out.println("getDstk");
        TaiKhoanService instance = new TaiKhoanService();
        ArrayList<TaiKhoan> expResult = null;
        ArrayList<TaiKhoan> result = instance.getDstk();
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }
    
}
