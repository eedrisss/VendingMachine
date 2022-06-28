/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.dto.SpendingMoney;
import java.util.List;

/**
 *
 * @author Isaac Shadare
 */
public interface VendingServiceLayer {

    List<Product> getStockedProducts() throws VendingPersistenceException;

    Product getProductByIndex(int index) throws VendingPersistenceException;

    void updateProduct(String name, Product product) throws VendingPersistenceException;

    void checkProductStock(Product product) throws VendingNoItemInventoryException;

    void checkCustomerFunds(Product product, SpendingMoney enteredMoney) throws VendingInsufficientFundsException;
}
