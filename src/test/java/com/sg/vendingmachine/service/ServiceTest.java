/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachine.dto.Change;
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
public class ServiceTest {
    
    Service srv;
    Change change = new Change();
    
    
    public ServiceTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        srv = new Service(dao);
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
     * Test of setDepositAnount method, of class Service.
     */
    @Test
    public void testSetDepositAmount() {
        
        String amount = "2.119";
        BigDecimal testNum = new BigDecimal("2.12");
        srv.setDepositAmount(amount);
        BigDecimal frmSrv =srv.getDepositAmount();
        
        assertEquals(testNum, frmSrv);      
        
    }
    
    /**
     * Test of setDepositAnount method, of class Service.
     */
    @Test
    public void testSetDepositAmount2() {
        
        String amount = "2.111";
        BigDecimal testNum = new BigDecimal("2.11");
        srv.setDepositAmount(amount);
        BigDecimal frmSrv =srv.getDepositAmount();
        
        assertEquals(testNum, frmSrv);      
        
    }
    
    
    /**
     * Test of setDepositAnount method, of class Service.
     */
    @Test
    public void testSetDepositAmount3() {
        
        String amount = "2.115";
        BigDecimal testNum = new BigDecimal("2.12");
        srv.setDepositAmount(amount);
        BigDecimal frmSrv =srv.getDepositAmount();
        
        assertEquals(testNum, frmSrv);      
        
    }
//    
//    /**
//     * Test of setDepositAnount method, of class Service.
//     */
    @Test
    public void testSetDepositAmount4() {
        
        String amount = "2.999";
        BigDecimal testNum = new BigDecimal("3.00");
        srv.setDepositAmount(amount);
        BigDecimal frmSrv =srv.getDepositAmount();
        
        assertEquals(testNum, frmSrv);      
        
    }

    

//    /**
//     * Test of getDepositAmount method, of class Service.
//     */
    @Test
    public void testGetDepositAmount() {
    }

//    /**
//     * Test of listAllItems method, of class Service.
//     */
    @Test
    public void testAddItemsAndListAllItems()
    throws VendingMachineDaoException{
        Product item1 = new Product("chips");
        item1.setProductPrice(BigDecimal.valueOf(2.00));
        item1.setProductInventoryCount(5);
        srv.addNewInventoryItem(item1);

        Product item2 = new Product("soda");
        item2.setProductPrice(BigDecimal.valueOf(2.50));
        item2.setProductInventoryCount(3);
        srv.addNewInventoryItem(item2);

        assertEquals(3, srv.listAllItems().size());
        
    }
    
    @Test
    public void testgetSingleItem()
    throws VendingMachineDaoException,
     InsufficientFundsException,
     NoInventoryInStock{
        srv.setDepositAmount("15.00");
        Product wantedItem = srv.getSingleItem(0);

        assertEquals("Bunnies", wantedItem.getProductName());
    }

    
    /**
     * Test of setSelectedItemPrice method, of class Service.
     * @throws com.sg.vendingmachine.dao.VendingMachineDaoException
     * @throws com.sg.vendingmachine.service.InsufficientFundsException
     * @throws com.sg.vendingmachine.service.NoInventoryInStock
     */
    @Test
    public void testSetSelectedItemPrice()
    throws VendingMachineDaoException,
            InsufficientFundsException,
     NoInventoryInStock{
        
        srv.setDepositAmount("20.00");
                
        Product wantedItem = srv.getSingleItem(0);
        srv.setSelectedItemPrice(0);
        
        assertEquals(wantedItem.getProductPrice(), srv.change.getPrice());
        
    }

    /**
     * Test of reduceInventory method, of class Service.
     * @throws com.sg.vendingmachine.dao.VendingMachineDaoException
     * @throws com.sg.vendingmachine.service.InsufficientFundsException
     * @throws com.sg.vendingmachine.service.NoInventoryInStock
     */
    @Test
    public void testReduceInventory()
    throws VendingMachineDaoException,
     InsufficientFundsException,
     NoInventoryInStock{
        
        srv.setDepositAmount("20.00");
        Product item1 = new Product("chips");
        item1.setProductPrice(BigDecimal.valueOf(2.00));
        item1.setProductInventoryCount(5);
        srv.addNewInventoryItem(item1);

        Product item2 = new Product("soda");
        item2.setProductPrice(BigDecimal.valueOf(2.50));
        item2.setProductInventoryCount(3);
        srv.addNewInventoryItem(item2);
        
        srv.reduceInventory(1);
        
        assertEquals(4, srv.getSingleItem(1).getProductInventoryCount());
    }

    

    /**
     * Test of getAmountDue method, of class Service.
     */
    @Test
    public void testGetAmountDue() {
    }

    /**
     * Test of makeChange method, of class Service.
     * @throws com.sg.vendingmachine.dao.VendingMachineDaoException
     */
    @Test
    public void testMakeChange1()
    throws VendingMachineDaoException{
        srv.setDepositAmount("3.25");
        srv.setSelectedItemPrice(0);
        
        srv.makeChange();
        
        assertEquals(new BigDecimal("4"), srv.change.getQuartersGiven());
    }

    
    
}
