/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author Eddie
 */
public class NoInventoryInStock extends Exception{
    
    public NoInventoryInStock(String message) {
        super(message);
    }
    
    public NoInventoryInStock(String message, Throwable cause) {
        super(message, cause);
    }
    
}
