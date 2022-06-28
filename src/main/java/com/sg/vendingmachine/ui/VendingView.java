/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Money;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.dto.SpendingMoney;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Isaac Shadare
 */
public class VendingView {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    private UserIO io;

    public VendingView(UserIO io) {
        this.io = io;
    }

    public void displayOutOfOrder() {
        io.printLine("\n" + ANSI_YELLOW + "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
        io.printLine(ANSI_YELLOW + "*                                        *");
        io.printLine(ANSI_YELLOW + "=         " + ANSI_RED + "Sorry --- Out Of Order" + ANSI_YELLOW + "         =");
        io.printLine(ANSI_YELLOW + "*                                        *");
        io.printLine(ANSI_YELLOW + "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
    }

    public void displayStockedInventory(List<Product> productList) {
        int longestSelector = 6;
        int longestName = 0;
        int longestPrice = 0;
        String itemHeading = "Item #";
        String productHeading = "Product";
        String priceHeading = "Price";
        String preText = "| ";
        String midText1 = " | ";
        String midText2 = " | $";
        String postText = " |";
        int totalLength;

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().length() > longestName) {
                longestName = productList.get(i).getProductName().length();
            }

            if (productList.get(i).getProductPrice().toPlainString().length() > longestPrice) {
                longestPrice = productList.get(i).getProductPrice().toPlainString().length();
            }
        }

        if (longestName < productHeading.length()) {
            longestName = productHeading.length();
        }

        if (longestPrice < priceHeading.length()) {
            longestPrice = priceHeading.length();
        }

        totalLength = longestSelector + longestName + longestPrice + preText.length()
                + midText1.length() + midText2.length() + postText.length();

        for (int i = 1; i <= totalLength; i++) {
            io.print("=");
        }

        io.printLine("");

        io.print(preText + itemHeading + midText1 + productHeading);

        for (int i = 0; i < longestName - productHeading.length(); i++) {
            io.print(" ");
        }

        io.print(midText1 + priceHeading);

        for (int i = 0; i <= longestPrice - priceHeading.length(); i++) {
            io.print(" ");
        }

        io.printLine(postText);

        for (int i = 1; i <= totalLength; i++) {
            io.print("=");
        }
        io.printLine("");

        for (int i = 0; i < productList.size(); i++) {
            io.print(preText);
            for (int j = 1; j <= longestSelector - Integer.toString(i + 1).length(); j++) {
                io.print(" ");
            }
            io.print((i + 1) + midText1 + productList.get(i).getProductName());
            for (int j = 1; j <= longestName - productList.get(i).getProductName().length(); j++) {
                io.print(" ");
            }
            io.print(midText2);
            for (int j = 1; j <= longestPrice - productList.get(i).getProductPrice().toPlainString().length(); j++) {
                io.print(" ");
            }
            io.printLine(productList.get(i).getProductPrice() + postText);
        }

        for (int i = 1; i <= totalLength; i++) {
            io.print("=");
        }
        io.printLine("");
    }

    public String printContinueMakePurchase() {
        return io.readString("\nWould you like to make a purchase? (y/n)   ", "yn", false);
    }

    public void displayEnterMoneyBanner() {
        io.printLine("\n==================================");
        io.printLine("=      Please deposit money      =");
        io.printLine("==================================");
    }

    public void getEnteredMoney(SpendingMoney enteredMoney) throws UserIOException {
        int quantity = 0;
        boolean hasErrors = false;

        for (Money monies : Money.values()) {
            do {
                try {
                    quantity = io.readInt("How many " + monies.getPrint() + "?   ", false);
                    hasErrors = false;
                } catch (UserIOException e) {
                    hasErrors = true;
                    displayErrorMessage(e.getMessage());
                }

            } while (hasErrors);

            enteredMoney.addMoney(monies, quantity);
        }
    }

    public void displayNoMoneyEntered() {
        io.printLine("\nYou must deposit money to make a purchase.");
        io.readString("Press enter to continue.", true);
    }

    public void displayMoneyEntered(BigDecimal totalMoney) {
        io.printLine("\nYou've deposited $" + totalMoney + ".\n");
        io.readString("Press enter to continue.", true);
    }

    public int getProductSelection(List<Product> productList) throws UserIOException {
        int selection = 0;
        boolean hasErrors = false;

        do {
            try {
                selection = io.readInt("\nPlease select the product to purchase (1 - " + productList.size() + "):   ", 1, productList.size(), false) - 1;
                hasErrors = false;
            } catch (UserIOException e) {
                hasErrors = true;
                displayErrorMessage(e.getMessage());
            }

        } while (hasErrors);

        return selection;
    }

    public void displayNotEnoughMoney(BigDecimal totalMoney, BigDecimal productPrice) {
        io.printLine("\nThe product you've selected costs $" + productPrice + " but you've only deposited $" + totalMoney + ".");
    }

    public boolean getEnterMoreMoney() {
        boolean enterMore = false;

        String yesNo = io.readString("Would you like to enter more money? (y/n)   ", "yn", false);

        if (yesNo.equalsIgnoreCase("y")) {
            enterMore = true;
        }

        return enterMore;
    }

    public void displayProdutOutOfStock() {
        io.printLine("Sorry - that product is out of stock.");
    }

    public String displaySelectAnotherProduct() {
        return io.readString("\nWould you like to select a different product? (y/n)   ", "yn", false);
    }

    public void displayThankYouBanner() {
        io.printLine("\n==================================");
        io.printLine("=    Thank you for shopping!!    =");
        io.printLine("==================================");
    }

    public void displayPurchasedProduct(String productName) {
        io.printLine("We hope you enjoy your " + productName + "!");
        io.readString("\nPress enter to continue.", true);
    }

    public void displayChangeReturnedBanner() {
        io.printLine("\n==================================");
        io.printLine("=   Please collect your change   =");
        io.printLine("==================================");
    }

    public void displayChangeReturned(Change change) {
        for (int i = 0; i < change.getCoins().length; i++) {
            if (change.getCoinQuantities()[i] > 0) {
                io.printLine(change.getCoinQuantities()[i] + " " + change.getCoins()[i]);
            }
        }
        io.readString("\nPress enter to continue.", true);
    }

    public void displayErrorMessage(String errorMsg) {
        io.printLine("\n=== ERROR ===");
        io.printLine(errorMsg);
    }
}
