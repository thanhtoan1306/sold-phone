/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
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
public class JDBCConnectionTest {
    
    public JDBCConnectionTest() {
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
    public void testCheckConnect() {
        System.out.println("checkConnect");
        JDBCConnection instance = new JDBCConnection();
        Boolean expResult = true;
        Boolean result = instance.checkConnect();
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSqlQuery() {
        System.out.println("sqlQuery");
        String qry = "Select * From SanPham";
        JDBCConnection instance = new JDBCConnection();
        ResultSet expResult = null;
        ResultSet result = instance.sqlQuery(qry);
        assertNotEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSqlUpdate() {
        System.out.println("sqlUpdate");
        String qry = "INSERT INTO `HANGSANPHAM` (`MAHSP`, `TenHSP`) VALUES ('NO', 'Nokia');";
        JDBCConnection instance = new JDBCConnection();
        Boolean expResult = true;
        Boolean result = instance.sqlUpdate(qry);
        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testCloseConnect() {
        System.out.println("closeConnect");
        JDBCConnection instance = new JDBCConnection();
        instance.closeConnect();
        try {
            assertTrue(instance.conn.isClosed());
//        fail("The test case is a prototype.");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
