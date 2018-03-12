/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bankproject.bankaccount;

import com.bankproject.service.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MeetTheScorch
 */
public class BankAccount {
    private final String accountNumber;
    private double balance;
    private List<String> bankAccountOperationHistory = new ArrayList<>();
    private final String path;

    public BankAccount(String accountNumber, double balance) {
        this.path = "history\\bank accounts\\";
        //this.path = "history\\";
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    // get-set //

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    //
    
    public boolean deposit(double amount){
        if(amount > 0){
            this.balance += amount;
            return true;
        }
        return false;
    }
    
    public boolean withdraw(double amount){
        if(this.balance - amount >= 0 && amount > 0){
            this.balance -= amount;
            return true;
        } 
        return false;
    }
    
    public void addOperationHistory(String record){
        bankAccountOperationHistory.add(record);
    }
    
    public void saveOperationHistory(){
        FileManager fileManager = FileManager.getInstance();
        try{
            fileManager.saveToFile(path,"BankAccount-"+accountNumber+".txt", bankAccountOperationHistory);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public void loadOperationHistory(){
        FileManager fileManager = FileManager.getInstance();
        try{
            bankAccountOperationHistory = fileManager.loadFromFile(path,"BankAccount-"+accountNumber+".txt");
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public void printOperationHistory(){
        bankAccountOperationHistory.forEach((line) -> {
            System.out.println(line);
        });
    }
    
    @Override
    public String toString(){
        return "Account number: " + accountNumber +
                ", Balance: " + balance;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.accountNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BankAccount other = (BankAccount) obj;
        if (!Objects.equals(this.accountNumber, other.accountNumber)) {
            return false;
        }
        return true;
    }


}
