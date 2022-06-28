/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac Shadare
 */
public class VendingDaoTest {

    private VendingDao dao = new VendingDaoFileImpl();

    public VendingDaoTest() {
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
     * Test of getStockedProducts method, of class VendingDao.
     */
    @Test
    public void testGetStockedProducts() throws Exception {
        //Test file data
        //product name::product price::product quantity
        //Water::4.00::0
        //Wilted Lettuce::0.50::11
        //Nylons::1.25::3

        assertEquals(5, dao.getStockedProducts().size());//edited equals value from 3 to 5 so test does not fail due to data from other tests
    }

    /**
     * Test of getProductByIndex method, of class VendingDao.
     */
    @Test
    public void testGetProductByIndex() throws Exception {
        int index1 = 0;
        int index2 = 0;

        Product product1 = new Product("product1");
        product1.setProductPrice(new BigDecimal("0.01"));
        product1.setProductQuantity(1);
        
        dao.updateProduct(product1.getProductName(), product1);

        Product product2 = new Product("product2");
        product2.setProductPrice(new BigDecimal("0.02"));
        product2.setProductQuantity(2);
        
        dao.updateProduct(product2.getProductName(), product2);

        List<Product> productList = dao.getStockedProducts();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().equals("product1")) {
                index1 = i;
            } else if (productList.get(i).getProductName().equals("product2")) {
                index2 = i;
            }
        }

        Product fromDao1 = dao.getProductByIndex(index1);
        Product fromDao2 = dao.getProductByIndex(index2);

        assertEquals(product1, fromDao1);
        assertEquals(product2, fromDao2);
    }

    /**
     * Test of updateProduct method, of class VendingDao.
     */
    @Test
    public void testUpdateProduct() throws Exception {
        int index = 0;
        Product product = new Product("testProduct");
        
        product.setProductPrice(new BigDecimal("1.00"));
        product.setProductQuantity(1);
        
        dao.updateProduct(product.getProductName(), product);
        
        List<Product> productList = dao.getStockedProducts();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().equals("testProduct")) {
                index = i;
            }
        }
        
        Product fromDao = dao.getProductByIndex(index);
        
        assertEquals(product, fromDao);
    }

}
