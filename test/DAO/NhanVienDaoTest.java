/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
 * @author ACER
 */
public class NhanVienDaoTest {
    
    public NhanVienDaoTest() {
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
        NhanVienDao instance = new NhanVienDao();
        ArrayList<NhanVien> expResult = null;
        ArrayList<NhanVien> result = instance.readDB();
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String columnName = "GioiTinh";
        String value = "Nữ";
        NhanVienDao instance = new NhanVienDao();
        ArrayList<NhanVien> expResult = null;
        ArrayList<NhanVien> result = instance.search(columnName, value);
        assertEquals(1, result.size());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        NhanVien nv = new NhanVien("E10", "Vũ Thị Đào", LocalDate.of(1996, 6, 25), "Nữ", "0865488732", "Nhà Bè", null);
        NhanVienDao instance = new NhanVienDao();
        Boolean expResult = true;
        Boolean result = instance.add(nv);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String manv = "E10";
        NhanVienDao instance = new NhanVienDao();
        Boolean expResult = true;
        Boolean result = instance.delete(manv);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        String MaNV = "E10";
        String TenNV = "Vũ Thị Tơ";
        LocalDate NgaySinh = LocalDate.of(1995, 10, 10);
        String GioiTinh = "Nữ";
        String SDT = "0756123515";
        String DiaChi = "Củ Chi";
        NhanVienDao instance = new NhanVienDao();
        Boolean expResult = true;
        Boolean result = instance.update(MaNV, TenNV, NgaySinh, GioiTinh, SDT, DiaChi);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }
    
}
