/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.NhaCungCap;
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
public class NhaCungCapServiceIT {
    
    public NhaCungCapServiceIT() {
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

//    @Test
//    public void testShow() {
//        System.out.println("show");
//        NhaCungCapService instance = new NhaCungCapService();
//        instance.show();
//        assertNotNull(instance.dsncc);
//        //fail("The test case is a prototype.");
//    }

    @Test
    public void testReadDB() {
        System.out.println("readDB");
        NhaCungCapService instance = new NhaCungCapService();
        instance.readDB();
        assertNotNull(instance.dsncc);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetNextID() {
        System.out.println("getNextID");
        NhaCungCapService instance = new NhaCungCapService();
        String expResult = "NCC9";
        String result = instance.getNextID();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetNhaCungCap() {
        System.out.println("getNhaCungCap");
        String mancc = "NCC6";
        NhaCungCapService instance = new NhaCungCapService();
        NhaCungCap expResult = null;
        NhaCungCap result = instance.getNhaCungCap(mancc);
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String value = "HCM";
        String type = "Tất cả";
        NhaCungCapService instance = new NhaCungCapService();
        ArrayList<NhaCungCap> expResult = null;
        ArrayList<NhaCungCap> result = instance.search(value, type);
        assertEquals(2, result.size());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_NhaCungCap() {
        System.out.println("add");
        NhaCungCap ncc = new NhaCungCap("NCC8", "Cơ sở 8", "Hậu Giang", "01667295478");
        NhaCungCapService instance = new NhaCungCapService();
        Boolean expResult = true;
        Boolean result = instance.add(ncc);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd_4args() {
        System.out.println("add");
        String ma = "NCC9";
        String ten = "Cơ sở 9";
        String diachi = "Tp Hồ Chí Minh";
        String sdt = "0565603762";
        NhaCungCapService instance = new NhaCungCapService();
        Boolean expResult = true;
        Boolean result = instance.add(ma, ten, diachi, sdt);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String mancc = "NCC8";
        NhaCungCapService instance = new NhaCungCapService();
        Boolean expResult = true;
        Boolean result = instance.delete(mancc);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String mancc = "NCC4";
        String tenncc = "Cơ sở 4";
        String diachi = "Hà Nội";
        String sdt = "01667295444";
        NhaCungCapService instance = new NhaCungCapService();
        Boolean expResult = true;
        Boolean result = instance.update(mancc, tenncc, diachi, sdt);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetDsncc() {
        System.out.println("getDsncc");
        NhaCungCapService instance = new NhaCungCapService();
        ArrayList<NhaCungCap> expResult = null;
        ArrayList<NhaCungCap> result = instance.getDsncc();
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }
    
}
