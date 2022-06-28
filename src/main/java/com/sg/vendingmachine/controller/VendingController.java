/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.dto.SpendingMoney;
import com.sg.vendingmachine.service.VendingInsufficientFundsException;
import com.sg.vendingmachine.service.VendingNoItemInventoryException;
import com.sg.vendingmachine.service.VendingServiceLayer;
import com.sg.vendingmachine.ui.UserIOException;
import com.sg.vendingmachine.ui.VendingView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Isaac Shadare
 */
public class VendingController {

    private VendingView view;
    private VendingServiceLayer service;

    public VendingController(VendingServiceLayer service, VendingView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        String continueMakePurchase;
        boolean requestMoney;
        boolean transactionComplete;

        try {

            do {
                boolean stocked = checkStockedInventory();
                requestMoney = false;

                if (!stocked) {
                    notifyOutOfOrder();
                    continueMakePurchase = "n";
                } else {
                    listStockedInventory();
                    continueMakePurchase = getContinueMakePurchase();
                    SpendingMoney customerMoney = new SpendingMoney();
                    transactionComplete = false;

                    while (continueMakePurchase.equalsIgnoreCase("y") && !transactionComplete) {
                        if (customerMoney.getTotal().compareTo(new BigDecimal("0")) == 0 || requestMoney) {
                            getCustomerMoney(customerMoney);
                            requestMoney = false;
                        }

                        Product selectedProduct = getProductSelection();

                        try {
                            service.checkProductStock(selectedProduct);
                            service.checkCustomerFunds(selectedProduct, customerMoney);
                            processTransaction(selectedProduct, customerMoney);
                            transactionComplete = true;
                        } catch (VendingNoItemInventoryException e) {
                            notifyOfError(e.getMessage());
                            continueMakePurchase = requestSelectAnotherProduct();
                        } catch (VendingInsufficientFundsException e) {
                            notifyOfError(e.getMessage());
                            requestMoney = requestMoreMoney();
                            if (!requestMoney) {
                                continueMakePurchase = "n";
                            }
                        }
                    }

                    if (customerMoney.getTotal().compareTo(new BigDecimal("0")) > 0) {
                        dispenseChange(customerMoney);
                    }
                }

            } while (continueMakePurchase.equalsIgnoreCase("y"));
            exitingMessage();

        } catch (VendingPersistenceException | UserIOException e) {
            notifyOfError(e.getMessage());
        }
    }

    private void notifyOutOfOrder() {
        view.displayOutOfOrder();
    }

    private boolean checkStockedInventory() throws VendingPersistenceException {
        List<Product> productList = service.getStockedProducts();
        return !productList.isEmpty();
    }

    private void listStockedInventory() throws VendingPersistenceException {
        List<Product> productList = service.getStockedProducts();
        view.displayStockedInventory(productList);
    }

    private String getContinueMakePurchase() {
        return view.printContinueMakePurchase();
    }

    private void getCustomerMoney(SpendingMoney enteredMoney) throws UserIOException {
        view.displayEnterMoneyBanner();

        do {
            view.getEnteredMoney(enteredMoney);
            if (enteredMoney.getTotal().compareTo(new BigDecimal("0")) == 0) {
                view.displayNoMoneyEntered();
            }

        } while (enteredMoney.getTotal().compareTo(new BigDecimal("0")) == 0);
        view.displayMoneyEntered(enteredMoney.getTotal());
    }

    private Product getProductSelection() throws UserIOException, VendingPersistenceException {
        List<Product> productList = service.getStockedProducts();
        view.displayStockedInventory(productList);
        int index = view.getProductSelection(productList);
        Product selectedProduct = service.getProductByIndex(index);

        return selectedProduct;
    }

    private boolean requestMoreMoney() {
        return view.getEnterMoreMoney();
    }

    private void processTransaction(Product purchasedProduct, SpendingMoney enteredMoney) throws VendingPersistenceException {
        enteredMoney.subtractMoney(purchasedProduct.getProductPrice());
        purchasedProduct.decrementProductQuantity();
        service.updateProduct(purchasedProduct.getProductName(), purchasedProduct);
        view.displayThankYouBanner();
        view.displayPurchasedProduct(purchasedProduct.getProductName());
    }

    private String requestSelectAnotherProduct() {
        return view.displaySelectAnotherProduct();
    }

    private void dispenseChange(SpendingMoney enteredMoney) {
        Change customerChange = new Change(enteredMoney.getTotal());
        view.displayChangeReturnedBanner();
        view.displayChangeReturned(customerChange);
        enteredMoney.subtractMoney(enteredMoney.getTotal());
    }

    private void notifyOfError(String errorMessage) {
        view.displayErrorMessage(errorMessage);
    }

    private void exitingMessage() {
        view.displayThankYouBanner();
    }
}
