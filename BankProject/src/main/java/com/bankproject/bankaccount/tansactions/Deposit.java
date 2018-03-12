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
public class Deposit implements Transaction {
    private final String description = "Deposit money on account.\t";
    private final BankAccount account;
    private final Double amount;
    private final String title;

    public Deposit(BankAccount account, Double amount, String title) {
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
        return account.deposit(amount);
    }
    
    @Override
    public String toString(){
        return description + 
                " Bank account: " + account.getAccountNumber() + 
                " Amount: " + amount +
                " Title: " + title;
    }
    
}
