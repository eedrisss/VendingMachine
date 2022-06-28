/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingAuditDao;
import com.sg.vendingmachine.dao.VendingPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Isaac Shadare
 */
public class LoggingAdvice {
    
        VendingAuditDao auditDao;
 
    public LoggingAdvice(VendingAuditDao auditDao) {
        this.auditDao = auditDao;
    }
 
    public void createAuditEntry(JoinPoint jp, Exception ex) {
        String auditEntry = ex.getClass().getSimpleName() + " | ";
        
        Object[] args = jp.getArgs();
        for (Object currentArg : args) {
            if (currentArg.getClass().getSimpleName().equalsIgnoreCase("Product")) {
                auditEntry += currentArg;
            }
        }

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingPersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
