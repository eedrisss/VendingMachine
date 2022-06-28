/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.util.List;

/**
 *
 * @author Isaac Shadare
 */
public interface VendingDao {

    List<Product> getStockedProducts() throws VendingPersistenceException;

    Product getProductByIndex(int index) throws VendingPersistenceException;

    void updateProduct(String name, Product product) throws VendingPersistenceException;
}
