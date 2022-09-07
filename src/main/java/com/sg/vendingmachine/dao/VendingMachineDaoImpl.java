/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eddie
 */
public class VendingMachineDaoImpl implements VendingMachineDao{
    
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    
    ArrayList<Product> itemList = new ArrayList<>();

    @Override //done
    public ArrayList<Product> getAllInventoryItems()throws VendingMachineDaoException {
       itemList.clear();
        loadInventory();
        return itemList;
    }

    @Override
    public Product getSingleInventoryItem(int index)throws VendingMachineDaoException {
               
       Product item = itemList.get(index);
       return item;
        
    }

    @Override
    public Product editInventoryItemQuanity(int itemIndex)throws VendingMachineDaoException {
        
        Product wantedItem = itemList.get(itemIndex);
        int count = wantedItem.getProductInventoryCount();
        count --;
        wantedItem.setProductInventoryCount(count);
        writeInventory();
        return wantedItem;
        
    }

    @Override //Must get all item info from ui
    public Product addInventoryItem(Product newItem)
    throws VendingMachineDaoException{
        itemList.add(newItem);
       writeInventory();
       return newItem;
        
    }

    
    private void loadInventory() throws VendingMachineDaoException {
        
        Scanner scanner;
        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException(
                    "-_- Could not load inventory data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product currentItem = new Product(currentTokens[0]);

            currentItem.setProductPrice(new BigDecimal(currentTokens[1]));
            currentItem.setProductInventoryCount(Integer.parseInt(currentTokens[2]));
            itemList.add(currentItem);
        }

        scanner.close();
    }
        
    
    //@Override
    private void writeInventory() throws VendingMachineDaoException {
        
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoException(
                    "Could not save inventory data.", e);
        }

        ArrayList<Product> items = this.writerGetAllInventoryItems();
        for (Product currentItem : items) {

            out.println(currentItem.getProductName() + DELIMITER
                    + currentItem.getProductPrice()+ DELIMITER
                    + currentItem.getProductInventoryCount());
            
                    

            out.flush();
        }
        out.close();
        

    }  
    
     private ArrayList<Product> writerGetAllInventoryItems()throws VendingMachineDaoException {
        loadInventory();
        return itemList;
    }

    
  
    
}
