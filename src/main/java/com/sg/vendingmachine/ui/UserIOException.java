/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

/**
 *
 * @author Isaac Shadare
 */
public class UserIOException extends Exception {

    public UserIOException(String message) {
        super(message);
    }

    public UserIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
