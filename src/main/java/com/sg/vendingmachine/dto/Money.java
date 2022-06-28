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
public enum Money {
    DOLLAR20(new BigDecimal("20.00"), "twenty dollar bills"),
    DOLLAR10(new BigDecimal("10.00"), "ten dollar bills"),
    DOLLAR5(new BigDecimal("5.00"), "five dollar bills"),
    DOLLAR(new BigDecimal("1.00"), "one dollar bills"),
    QUARTER(new BigDecimal("0.25"), "quarters"),
    DIME(new BigDecimal("0.10"), "dimes"),
    NICKEL(new BigDecimal("0.05"), "nickels"),
    PENNY(new BigDecimal("0.01"), "pennies");

    private BigDecimal valueCurrency;
    private String valuePrint;

    Money(BigDecimal value, String print) {
        this.valueCurrency = value;
        this.valuePrint = print;
    }

    public BigDecimal getCurrencyValue() {
        return valueCurrency;
    }

    public String getPrint() {
        return valuePrint;
    }
}
