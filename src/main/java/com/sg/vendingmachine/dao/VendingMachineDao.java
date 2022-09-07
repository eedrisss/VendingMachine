/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eddie
 */
public interface VendingMachineDao {

    //display all of the items and their respective prices
    public ArrayList<Product> getAllInventoryItems() throws VendingMachineDaoException;

    public Product getSingleInventoryItem(int index)throws VendingMachineDaoException;

    public Product editInventoryItemQuanity(int itemIndex)throws VendingMachineDaoException;

    //Add item to inventory
    public Product addInventoryItem(Product itemName) throws VendingMachineDaoException;

    //loadDatafromFile
    //public void loadInventory() throws VendingMachineDaoException;

    //WriteDatatoFile
    //public void writeInventory() throws VendingMachineDaoException;

}
