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
public class SpendingMoney {

    private BigDecimal total;

    public SpendingMoney() {
        this.total = new BigDecimal("0");
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void addMoney(Money money, int quantity) {
        this.total = this.total.add(money.getCurrencyValue().multiply(new BigDecimal(quantity)));
    }

    public void subtractMoney(BigDecimal debit) {
        this.total = this.total.subtract(debit);
    }
}
