/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bankproject.bankaccount.tansactions;

import com.bankproject.bankaccount.BankAccount;

/**
 *
 * @author MeetTheScorch
 */
public class Transfer implements Transaction {
    private final String description = "Transfer from account to account.";
    private final BankAccount accountFrom;
    private final BankAccount accountTo;
    private final Double amount;
    private final String title;

    public Transfer(BankAccount accountFrom, BankAccount accountTo, Double amount, String title) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.title = title;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean action() {
        if(accountFrom.withdraw(amount)){
            return accountTo.deposit(amount);
        }
        return false;
    }
    
    @Override
    public String toString(){
        return description + 
                " Bank account from: " + accountFrom.getAccountNumber() + 
                " Bank account to: " + accountTo.getAccountNumber() + 
                " Amount: " + amount +
                " Title: " + title;
    }

    
    
}
