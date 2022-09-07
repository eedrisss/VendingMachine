/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author Eddie
 */
public class View {
    
    UserIO io;
    
    public View(UserIO io){
        this.io = io;
    }
    
    
    public String getAmountOfDeposit() {
        
        String deposit = io.readString("Enter the amount of your deposit."); 
        return deposit;
    }
    
    public String getStartingAction() {
        
        io.print("CHOOSE YOUR OPTION.");
        String option = io.readString("Enter 1 to deposit coins or Enter 2 to leave Vending Machine.");
        return option;
    }
    
    
    
    public void displayAllItemsBanner() {
        io.print("Welcome To Vending Machine");
        System.out.println();
        System.out.println("ITEMS AVAILABLE TODAY");
        System.out.println();
        
    }
    
    public void displayAllItems(ArrayList<Product> availableItems) {
                for(int i = 0; i <= availableItems.size() -1; i++) {
                    Product item = availableItems.get(i);
                    System.out.println((i +"  " + item.getProductName() + "  ") 
                            + item.getProductPrice());
        }
    }
    
    public int selectItem(){
        int selectedItem = io.readInt("Select an item from the above menu.");
        return selectedItem;
    }
    
    public void displayVendedItem(Product item){
        io.print("Here is your " + item.getProductName());
        
    }
    public void displayAmtChangeDue(BigDecimal chngDue){
        io.print("Your change is " + chngDue);
    }
    
    public void displayChangeGiven(Map incoming) {
        incoming.forEach((k,v)->System.out.println(k + ": " + v));
        
    }
    
    public void displayExit() {
        io.print("EXIT PROGRAM");
    }
    
    public void displayUnknownCommand(){
        io.print("Unknown command good bye");
    }
    
    public void errorMessage(String promot) {
        io.print(promot);
    }
    
    //The following code is for the admin panel.
    
    public int chooseAdminFunction() {
        io.print("=== ADMINISTRATION MENU ===");
        io.print("");
        io.print("1. Add New Item");
        io.print("2. EXIT");
        int choice = io.readInt("Enter selection from above menu.", 1, 2);
        return choice;
    }
    
    public Product getNewItemInfo(){
        String name = io.readString("Enter the product's name.");
        String sPrice = io.readString("Enter the new product's price");
        int quanity = io.readInt("Enter the quanity in inventory.");
        Product newItem = new Product(name);
        newItem.setProductInventoryCount(quanity);
        newItem.setProductPrice(new BigDecimal(sPrice));
        return newItem;
        
    }
    
        
    
}
