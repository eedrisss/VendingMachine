/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author Isaac Shadare
 */
public class VendingInsufficientFundsException extends Exception {

    public VendingInsufficientFundsException(String message) {
        super(message);
    }

    public VendingInsufficientFundsException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
