/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Change.d;
import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



/**
 *
 * @author Isaac Shadare
 */
public class Service {
    
    Change change = new Change();
    VendingMachineDao dao;

    public Service(VendingMachineDao dao) {
        this.dao = dao;
    }
    
    Map<String, Integer> giveCoins = new HashMap<>();
    
       
    public void setDepositAmount(String deposit) {
        BigDecimal a = new BigDecimal(deposit);
        BigDecimal b = a.setScale(2, RoundingMode.HALF_UP);
        change.setDeposit(b);
        
    }
    
    public BigDecimal getDepositAmount(){
        return change.getDeposit();
    }
    
    public void addNewInventoryItem(Product newItem)throws VendingMachineDaoException{
        dao.addInventoryItem(newItem);        
        
    }
    
    public ArrayList<Product> listAllItems() throws VendingMachineDaoException{        
        ArrayList currentList = dao.getAllInventoryItems();
        return currentList;
    }
    
    public Product getSingleItem(int index)throws VendingMachineDaoException,
            InsufficientFundsException,
            NoInventoryInStock{
        Product wantedItem = dao.getSingleInventoryItem(index);
        if(wantedItem.getProductPrice().compareTo(change.getDeposit())> 0){
            throw new InsufficientFundsException(
                "ERROR: Insufficient Funds.  Deposited Funds:"
                + change.getDeposit());
        }
        if(wantedItem.getProductInventoryCount() < 1){
            throw new NoInventoryInStock(
                "Sorry this item is out of Stock");
                
        }
        return wantedItem;
    }
    
    public void setSelectedItemPrice(int index)throws VendingMachineDaoException {
        Product wantedItem = dao.getSingleInventoryItem(index);
        change.setPrice(wantedItem.getProductPrice());
    }
    
    public void reduceInventory(int index) throws VendingMachineDaoException{
        dao.editInventoryItemQuanity(index);
        
        
    }
    
    public BigDecimal getAmountDue() {
        BigDecimal amtDue = change.getDeposit().subtract(change.getPrice());
        return amtDue;
    }
    
    public void makeChange(){
       BigDecimal changeToBeGiven = change.getDeposit().subtract(change.getPrice());
       
       //gets quarters to be given and resets total changeToBeGiven to value less quarters
       change.setQuartersGiven(changeToBeGiven.divide(d.Quarter.getVal()).setScale(0, RoundingMode.FLOOR));
       changeToBeGiven = changeToBeGiven.subtract(change.getQuartersGiven().multiply(d.Quarter.getVal()));
              
       //gets Dimes to be given and resets total changeToBeGiven to value less Dimes
       change.setDimesGiven(changeToBeGiven.divide(d.DIME.getVal()).setScale(0, RoundingMode.FLOOR));
       changeToBeGiven = changeToBeGiven.subtract(change.getDimesGiven().multiply(d.DIME.getVal()));
       
       //gets nickles to be given and resets total changeToBeGiven to value less nickles
       change.setNickelsGiven(changeToBeGiven.divide(d.NICKEL.getVal()).setScale(0, RoundingMode.FLOOR));
       changeToBeGiven = changeToBeGiven.subtract(change.getNickelsGiven().multiply(d.NICKEL.getVal()));
       
       //gets pennies to be given and resets total changeToBeGiven to value less pennies
       change.setPenniesGiven(changeToBeGiven.divide(d.PENNY.getVal()).setScale(0, RoundingMode.FLOOR));
               
}
    
    public Map<String, Integer> getCoinsGiven() {
        
        createChangeObj();        
       Map<String, Integer> result = giveCoins.entrySet()
               .stream()
               .filter(map -> map.getValue() > 0)
               .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
       
       return result;              
              
        
    }
    
    private void createChangeObj() {        
                
        giveCoins.put("Quarters", change.getQuartersGiven().intValueExact());
        giveCoins.put("Dimes", change.getDimesGiven().intValueExact());
        giveCoins.put("Nickels", change.getNickelsGiven().intValueExact());
        giveCoins.put("Pennies", change.getPenniesGiven().intValueExact());
        
    }
    
}
