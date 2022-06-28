/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingAuditDao;
import com.sg.vendingmachine.dao.VendingDao;
import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.dto.SpendingMoney;
import java.util.List;

/**
 *
 * @author Isaac Shadare
 */
public class VendingServiceLayerImpl implements VendingServiceLayer {

    VendingDao dao;
    VendingAuditDao auditDao;

    public VendingServiceLayerImpl(VendingDao dao, VendingAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Product> getStockedProducts() throws VendingPersistenceException {
        return dao.getStockedProducts();
    }

    @Override
    public Product getProductByIndex(int index) throws VendingPersistenceException {
        return dao.getProductByIndex(index);
    }

    @Override
    public void updateProduct(String name, Product product) throws VendingPersistenceException {
        dao.updateProduct(name, product);
    }

    @Override
    public void checkProductStock(Product product) throws VendingNoItemInventoryException {
        if (product.getProductQuantity() <= 0) {
            throw new VendingNoItemInventoryException("Sorry - that product is out of stock.");
        }
    }

    @Override
    public void checkCustomerFunds(Product product, SpendingMoney enteredMoney) throws VendingInsufficientFundsException {
        if (product.getProductPrice().compareTo(enteredMoney.getTotal()) > 0) {
            throw new VendingInsufficientFundsException("The product you've selected costs $" + product.getProductPrice() 
                    + " but you've only deposited $" + enteredMoney.getTotal() + ".");
        }
    }
}
