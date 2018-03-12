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
public class TransferInternationalOutgoing implements Transaction {
    private final String description = "International transfer outgoing.";
    private final BankAccount accountFrom;
    private final String accountTo;
    private final String swift;
    private final Double amount;
    private final String title;
    private final String bankName;

    public TransferInternationalOutgoing(BankAccount accountFrom, String accountTo, String swift, Double amount, String title, String bankName) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.swift = swift;
        this.amount = amount;
        this.title = title;
        this.bankName = bankName;
    }    
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean action() {
        if(accountFrom.withdraw(amount)){
            //Some kind of connection with other bank
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return description + 
                " Bank account from: " + accountFrom.getAccountNumber() + 
                " Bank account to: " + accountTo + 
                " SWIFT: " + swift + 
                " Amount: " + amount +
                " Title: " + title +
                " Bank name: " + bankName;
    }

    
    
}
