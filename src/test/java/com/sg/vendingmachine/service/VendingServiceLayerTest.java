/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Money;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.dto.SpendingMoney;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Isaac Shadare
 */
public class VendingServiceLayerTest {

    private VendingServiceLayer service;

    public VendingServiceLayerTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        service
                = ctx.getBean("serviceLayer", VendingServiceLayer.class);
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
     * Test of checkProductStock method, of class VendingServiceLayer.
     */
    @Test
    public void testCheckProductStock() throws Exception {
        Product product = service.getProductByIndex(0);

        try {
            service.checkProductStock(product);
        } catch (VendingNoItemInventoryException e) {
            fail("Expected VendingNoItemInventoryException exception.");
        }
    }

    @Test
    public void testCheckProductStockOutOfStock() throws Exception {
        Product product = service.getProductByIndex(0);
        product.setProductQuantity(0);

        try {
            service.checkProductStock(product);
            fail("Expected VendingNoItemInventoryException exception.");
        } catch (VendingNoItemInventoryException e) {
            return;
        }
    }

    /**
     * Test of checkCustomerFunds method, of class VendingServiceLayer.
     */
    @Test
    public void testCheckCustomerFunds() throws Exception {
        Product product = service.getProductByIndex(0);
        SpendingMoney enteredMoney = new SpendingMoney();

        enteredMoney.addMoney(Money.DOLLAR, 1);
        enteredMoney.addMoney(Money.PENNY, 1);

        try {
            service.checkCustomerFunds(product, enteredMoney);
        } catch (VendingInsufficientFundsException e) {
            fail("Expected VendingInsufficientFundsException exception.");
        }
    }

    @Test
    public void testCheckCustomerFundsEqual() throws Exception {
        Product product = service.getProductByIndex(0);
        SpendingMoney enteredMoney = new SpendingMoney();

        enteredMoney.addMoney(Money.DOLLAR, 1);

        try {
            service.checkCustomerFunds(product, enteredMoney);
        } catch (VendingInsufficientFundsException e) {
            fail("Expected VendingInsufficientFundsException exception.");
        }
    }

    @Test
    public void testCheckCustomerFundsTooLittleFunds() throws Exception {
        Product product = service.getProductByIndex(0);
        SpendingMoney enteredMoney = new SpendingMoney();

        enteredMoney.addMoney(Money.PENNY, 99);

        try {
            service.checkCustomerFunds(product, enteredMoney);
            fail("Expected VendingInsufficientFundsException exception.");
        } catch (VendingInsufficientFundsException e) {
            return;
        }
    }
}
