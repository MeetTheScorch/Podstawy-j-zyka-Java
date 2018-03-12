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
public class Withdraw implements Transaction {
    private final String description = "Withdraw money from account.";
    private final BankAccount account;
    private final Double amount;
    private final String title;

    public Withdraw(BankAccount account, Double amount, String title) {
        this.account = account;
        this.amount = amount;
        this.title = title;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean action() {
        return account.withdraw(amount);
    }
    
    @Override
    public String toString(){
        return description + 
                " Bank account: " + account.getAccountNumber() + 
                " Amount: " + amount +
                " Title: " + title;
    }
    
}
