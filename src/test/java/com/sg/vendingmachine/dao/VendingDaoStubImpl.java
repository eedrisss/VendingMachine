/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Isaac Shadare
 */
public class VendingDaoStubImpl implements VendingDao {

    Product product;
    Map<String, Product> inventory = new HashMap<>();
    List<Product> prodList = new ArrayList<>(inventory.values());

    public VendingDaoStubImpl() {
        product = new Product("dust bunny");
        product.setProductPrice(new BigDecimal("1.00"));
        product.setProductQuantity(1);

        prodList.add(product);
    }

    @Override
    public List<Product> getStockedProducts() throws VendingPersistenceException {
        return prodList;
    }

    @Override
    public Product getProductByIndex(int index) throws VendingPersistenceException {
        return prodList.get(index);
    }

    @Override
    public void updateProduct(String name, Product product) throws VendingPersistenceException {
        inventory.put(name, product);
        prodList = new ArrayList<>(inventory.values());
    }

}
