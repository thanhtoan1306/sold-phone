/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Model.NhanVien;
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
public class NhanVienServiceIT {
    
    public NhanVienServiceIT() {
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
        NhanVienService instance = new NhanVienService();
        instance.readDB();
        assertNotNull(instance.getDsnv());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetNhanVien() {
        System.out.println("getNhanVien");
        String manv = "E01";
        NhanVienService instance = new NhanVienService();
        NhanVien expResult = null;
        NhanVien result = instance.getNhanVien(manv);
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String value = "1997";
        String type = "Tất cả";
        NhanVienService instance = new NhanVienService();
        ArrayList<NhanVien> expResult = null;
        ArrayList<NhanVien> result = instance.search(value, type);
        assertEquals(2, result.size());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testSaveToDatabase() {
        System.out.println("saveToDatabase");
        NhanVien nv = new NhanVien("E08", "Trần Thuận Tiến", LocalDate.of(2000, 06, 20), "Bê Đê", "0123456789", "Hồ Chí Minh");
        NhanVienService instance = new NhanVienService();
        Boolean expResult = true;
        Boolean result = instance.saveToDatabase(nv);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        String manv = "E07";
        String tennv = "Lê Thanh Toàn";
        LocalDate ngaysinh = LocalDate.of(2000, 06, 13);
        String gioitinh = "Nữ";
        String sdt = "0565603761";
        String diachi = "Củ Chi";
        String hinh = "E03";
        NhanVienService instance = new NhanVienService();
        Boolean expResult = true;
        Boolean result = instance.add(manv, tennv, ngaysinh, gioitinh, sdt, diachi, hinh);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String manv = "E07";
        NhanVienService instance = new NhanVienService();
        Boolean expResult = true;
        Boolean result = instance.delete(manv);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String manv = "E03";
        String tennv = "Trần Quốc Toàn";
        LocalDate ngaysinh = LocalDate.of(2000, 02, 20);
        String gioitinh = "Nam";
        String diachi = "Hồ Chí Minh";
        String sdt = "0565603762";
        String hinh = "E02";
        NhanVienService instance = new NhanVienService();
        Boolean expResult = true;
        Boolean result = instance.update(manv, tennv, ngaysinh, gioitinh, diachi, sdt, hinh);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetDsnv() {
        System.out.println("getDsnv");
        NhanVienService instance = new NhanVienService();
        ArrayList<NhanVien> expResult = null;
        ArrayList<NhanVien> result = instance.getDsnv();
        assertNotNull(result);
        //fail("The test case is a prototype.");
    }
    
}
