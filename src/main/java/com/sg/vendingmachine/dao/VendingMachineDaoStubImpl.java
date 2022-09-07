/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Eddie
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    
    
    
    Product onlyItem;
    ArrayList<Product> testList = new ArrayList<>();
    
    public VendingMachineDaoStubImpl(){
        Product onlyItem = new Product("Bunnies");
        onlyItem.setProductPrice(new BigDecimal("2.25"));
        onlyItem.setProductInventoryCount(3);
        
        testList.add(onlyItem);
    }    

    @Override
    public ArrayList<Product> getAllInventoryItems() throws VendingMachineDaoException {
        return testList;
    }

    @Override
    public Product getSingleInventoryItem(int index) throws VendingMachineDaoException {
        return testList.get(index);
    }

    @Override
    public Product editInventoryItemQuanity(int itemIndex) throws VendingMachineDaoException {
        Product testItem = testList.get(itemIndex);
        int count = testItem.getProductInventoryCount();
        count --;
        testItem.setProductInventoryCount(count);
        return testItem;
    }

    @Override
    public Product addInventoryItem(Product itemName) throws VendingMachineDaoException {
           Product newItem = itemName;
            testList.add(newItem);
            return newItem;
        
    }

   
    
    
}
