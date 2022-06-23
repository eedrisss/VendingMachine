/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoInventoryInStock;
import com.sg.vendingmachine.service.Service;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.View;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Isaac Shadare
 */
public class Controller {

    UserIO io;
    View view;
    Service srv;

    public Controller(View view, Service srv) {
        this.srv = srv;
        this.view = view;

    }

    public void run() {
        boolean keepRunning = true;
        try {
            while (keepRunning) {

                String userStartOption;
                listAllItems();

                userStartOption = getStartAction();
                try {
                    switch (userStartOption) {
                        case "1":
                            getDeposit();
                            startVendingProcess();
                            break;

                        case "2":
                            exitMessage();
                            keepRunning = false;
                            break;

                        case "admin":
                            doAdminWork();
                            break;

                        default:
                            exitOnUnknownCommand();
                            keepRunning = false;
                    }
                } catch (InsufficientFundsException | NoInventoryInStock e) {
                    view.errorMessage(e.getMessage());
                }

            }
        } catch (VendingMachineDaoException e) {
            view.errorMessage(e.getMessage());
        }
    }

    private String getStartAction() {
        String userOption = view.getStartingAction();
        return userOption;
    }

    private void getDeposit() {
        String deposit = view.getAmountOfDeposit();
        srv.setDepositAmount(deposit);
    }

    private void listAllItems() throws VendingMachineDaoException {
        ArrayList<Product> currentList = srv.listAllItems();
        view.displayAllItemsBanner();
        view.displayAllItems(currentList);

    }

    private void startVendingProcess()
            throws VendingMachineDaoException,
            InsufficientFundsException,
            NoInventoryInStock {
        int choice = view.selectItem();
        Product item = srv.getSingleItem(choice);
        view.displayVendedItem(item);
        srv.setSelectedItemPrice(choice);
        srv.reduceInventory(choice);
        BigDecimal amtDue = srv.getAmountDue();
        view.displayAmtChangeDue(amtDue);
        srv.makeChange();
        Map coins = srv.getCoinsGiven();
        view.displayChangeGiven(coins);

    }

    private void exitMessage() {
        view.displayExit();
    }

    private void exitOnUnknownCommand() {
        view.displayUnknownCommand();
    }

    
    private void doAdminWork() throws VendingMachineDaoException {
        boolean flag = true;

        while (flag) {

            int adminOperation = getAdminFunction();

            switch (adminOperation) {

                case 1:
                    getNewItemInfo();
                    break;

                case 2:
                    exitMessage();
                    flag = false;
                    break;

                default:
                    exitOnUnknownCommand();
                    flag = false;
                    break;
            }
        }
    }

    private int getAdminFunction() {
        int function = view.chooseAdminFunction();
        return function;
    }

    private void getNewItemInfo() throws VendingMachineDaoException {
        Product item = view.getNewItemInfo();
        srv.addNewInventoryItem(item);
    }
}
