/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Eddie
 */
public class Product {
    
    private String productName;
    private BigDecimal productPrice;
    private int productInventoryCount;
    
    public Product(String itemName){
        this.productName = itemName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductInventoryCount() {
        return productInventoryCount;
    }

    public void setProductInventoryCount(int productInventoryCount) {
        this.productInventoryCount = productInventoryCount;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.productName);
        hash = 71 * hash + Objects.hashCode(this.productPrice);
        hash = 71 * hash + this.productInventoryCount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.productInventoryCount != other.productInventoryCount) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.productPrice, other.productPrice)) {
            return false;
        }
        return true;
    }
    
    
    
}
