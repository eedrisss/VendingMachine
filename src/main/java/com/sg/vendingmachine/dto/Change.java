/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Isaac Shadare
 */
public class Change {

   public enum d {
        PENNY  (new BigDecimal("0.01")),
        NICKEL (new BigDecimal("0.05")),
        DIME   (new BigDecimal("0.10")),
        Quarter(new BigDecimal("0.25")),
        Dollar (new BigDecimal("1.00"));

        private final BigDecimal val;

         d(BigDecimal v) {
            this.val = v;

        }

        public BigDecimal getVal() {
            return val;
        }
    }
   
   
    private BigDecimal deposit=  new BigDecimal("0");
    private BigDecimal price = new BigDecimal("0");
    private BigDecimal quartersGiven = new BigDecimal("0");
    private BigDecimal dimesGiven = new BigDecimal("0");
    private BigDecimal nickelsGiven = new BigDecimal("0");
    private BigDecimal penniesGiven = new BigDecimal("0");
    
    
    

    
    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuartersGiven() {
        return quartersGiven;
    }

    public void setQuartersGiven(BigDecimal quartersGiven) {
        this.quartersGiven = quartersGiven;
    }

    public BigDecimal getDimesGiven() {
        return dimesGiven;
    }

    public void setDimesGiven(BigDecimal dimesGiven) {
        this.dimesGiven = dimesGiven;
    }

    public BigDecimal getNickelsGiven() {
        return nickelsGiven;
    }

    public void setNickelsGiven(BigDecimal nickelsGiven) {
        this.nickelsGiven = nickelsGiven;
    }

    public BigDecimal getPenniesGiven() {
        return penniesGiven;
    }

    public void setPenniesGiven(BigDecimal penniesGiven) {
        this.penniesGiven = penniesGiven;
    }
}
