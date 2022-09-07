/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eddie
 */
public class VendingMachineDaoImplTest {

    VendingMachineDao dao = new VendingMachineDaoImpl();

    public VendingMachineDaoImplTest() {
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

    /**
     * Test of getAllInventoryItems method, of class VendingMachineDaoImpl.
     * @throws com.sg.vendingmachine.dao.VendingMachineDaoException
     */
    @Test
    public void testGetAllInventoryItems() 
    throws VendingMachineDaoException{
        Product item1 = new Product("chips");
        item1.setProductPrice(BigDecimal.valueOf(2.00));
        item1.setProductInventoryCount(5);
        dao.addInventoryItem(item1);

        Product item2 = new Product("soda");
        item2.setProductPrice(BigDecimal.valueOf(2.50));
        item2.setProductInventoryCount(3);
        dao.addInventoryItem(item2);

        assertEquals(2, dao.getAllInventoryItems().size());
    }

    /**
     * Test of editInventoryItemQuanity method, of class VendingMachineDaoImpl.
     * @throws com.sg.vendingmachine.dao.VendingMachineDaoException
     */
//    @Test
    public void testEditInventoryItemQuanity()
    throws VendingMachineDaoException{
        Product newItem = new Product("cups");
        newItem.setProductPrice(BigDecimal.valueOf(2.35));
        newItem.setProductInventoryCount(3);
        dao.addInventoryItem(newItem);
        
        dao.editInventoryItemQuanity(50);
        
        assertEquals(50, dao.getSingleInventoryItem(0).getProductInventoryCount());
        
    }

    /**
     * Test of addInventoryItem method, of class VendingMachineDaoImpl.
     * @throws com.sg.vendingmachine.dao.VendingMachineDaoException
     */
    @Test
    public void testAddGetInventoryItem()
    throws VendingMachineDaoException{
        Product newItem = new Product("Fishes");
        newItem.setProductPrice(BigDecimal.valueOf(1.75));
        newItem.setProductInventoryCount(10);
        dao.addInventoryItem(newItem);
        Product fromDao = dao.getSingleInventoryItem(0);
        assertEquals(newItem, fromDao);

    }
    
    //@Test
    public void testAddGetInventoryItem2()
    throws VendingMachineDaoException{
        Product item1 = new Product("chips");
        item1.setProductPrice(BigDecimal.valueOf(2.00));
        item1.setProductInventoryCount(5);
        dao.addInventoryItem(item1);

        Product item2 = new Product("soda");
        item2.setProductPrice(BigDecimal.valueOf(2.50));
        item2.setProductInventoryCount(3);
        dao.addInventoryItem(item2);

        assertEquals("soda", dao.getSingleInventoryItem(1).getProductName());
        
    }

    /**
     * Test of loadInventory method, of class VendingMachineDaoImpl.
     */
    @Test
    public void testLoadInventory() {
    }

    /**
     * Test of writeInventory method, of class VendingMachineDaoImpl.
     */
    @Test
    public void testWriteInventory() {
    }

}
