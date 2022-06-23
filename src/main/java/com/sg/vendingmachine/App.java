/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.Controller;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.service.Service;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOImpl;
import com.sg.vendingmachine.ui.View;

/**
 *
 * @author Isaac Shadare
 */
public class App {

    public static void main(String[] args) throws VendingMachineDaoException {
        UserIO myIo = new UserIOImpl();
        View myView = new View(myIo);
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        Service mySrv = new Service(myDao);
        Controller driver = new Controller(myView, mySrv);
        driver.run();
    }

}
