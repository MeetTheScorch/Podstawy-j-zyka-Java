/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bankproject.service;

import com.bankproject.bank.Bank;
import com.bankproject.bank.SWIFT;
import com.bankproject.bankaccount.BankAccount;
import com.bankproject.bankaccount.tansactions.Deposit;
import com.bankproject.bankaccount.tansactions.Transfer;
import com.bankproject.bankaccount.tansactions.TransferInternationalIncoming;
import com.bankproject.bankaccount.tansactions.TransferInternationalOutgoing;
import com.bankproject.bankaccount.tansactions.Withdraw;
import com.bankproject.person.Person;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author MeetTheScorch
 */
public class BankService {
    private final Bank bank = new Bank("POKA BP","POKAPLP0");
    private final List<Person> personList = new ArrayList<>();
    private final List<BankAccount> bankAccountList = new ArrayList<>();
    private final Map<BankAccount, Person> accountPersonMap = new HashMap<>();
    private final GregorianCalendar date = new GregorianCalendar();
    private final List<SWIFT> swiftCodes = new ArrayList<>();

    public BankService() {
        swiftCodes.add(new SWIFT("POKAPLP0","POKA BP"));
    }
    
    //
    
    public String dateToString(){
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        fmt.setCalendar(date);
        String dateFormatted = fmt.format(date.getTime());
        return dateFormatted;
    }

    // Person methods //
    
    public void addPerson(String firstName, String lastName, GregorianCalendar birthDate){
        if(firstName.length() <= 0){
            throw new IllegalArgumentException("Invalid first name.");
        }
        if(lastName.length() <= 0){
            throw new IllegalArgumentException("Invalid last name.");
        }
        Person person = new Person(firstName,lastName,birthDate);
        if(personList.contains(person)){
            throw new IllegalArgumentException("Person already exists.");
        }
        
        personList.add(person);
        
        bank.addOperationHistory(dateToString()+" Added person.\t\t "+person.toString());
        person.addOperationHistory(dateToString()+" Added person.\t\t "+person.toString());
    }
    
    public Person getPerson(String firstName, String lastName, GregorianCalendar birthDate){
        if(!personList.contains(new Person(firstName,lastName,birthDate))){
            throw new IllegalArgumentException("Person does not exist. ");
        }
        return personList.get(personList.indexOf(new Person(firstName,lastName,birthDate)));
    }
    
    public void removePerson(String firstName, String lastName, GregorianCalendar birthDate){
        Person person = getPerson(firstName,lastName,birthDate);
        if(accountPersonMap.containsValue(person)){
            List<BankAccount> temp = new ArrayList<>();
                accountPersonMap.forEach((BankAccount key,Person value) -> {
                if(value.equals(person)){
                    temp.add(key);
                }
            });
            for(BankAccount account : temp){
                removeAccount(account.getAccountNumber());
                accountPersonMap.remove(account);
            }
        }
        bank.addOperationHistory(dateToString()+" Removed person.\t\t "+person.toString());
        person.addOperationHistory(dateToString()+" Removed person.\t\t "+person.toString());
        person.saveOperationHistory();
        personList.remove(person);
    }
    
    // Bank account methods //

    public void addAccount(String firstName, String lastName, GregorianCalendar birthDate, String accountNumber, double balance) {
        Person person = getPerson(firstName,lastName,birthDate);
        if(accountNumber.length()!=18){
            throw new IllegalArgumentException("Inappropriate account length.");
        }
        for(char c : accountNumber.toCharArray()){
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("Inappropriate account number.");
            }
        }
        if(balance < 0){
            throw new IllegalArgumentException("Invalid balance.");
        }
        BankAccount account = new BankAccount(accountNumber,balance);
        if(bankAccountList.contains(account)){
            throw new IllegalArgumentException("Bank account already exists.");
        }
        
        bankAccountList.add(account);
        accountPersonMap.put(account,person);
        
        bank.addOperationHistory(dateToString()+" Added account.\t\t "+account.toString()+"\t"+person.toString());
        person.addOperationHistory(dateToString()+" Added account.\t\t "+account.toString()+"\t"+person.toString());
        account.addOperationHistory(dateToString()+" Added account.\t\t "+account.toString()+"\t"+person.toString());
    }
    
    public BankAccount getAccount(String accountNumber){
        if(!bankAccountList.contains(new BankAccount(accountNumber,0))){
            throw new IllegalArgumentException("Bank account does not exist.");
        }
        return bankAccountList.get(bankAccountList.indexOf(new BankAccount(accountNumber,0)));
    }
    
    public void removeAccount(String accountNumber){
        BankAccount account = getAccount(accountNumber);
        Person person = accountPersonMap.get(account);
        bank.addOperationHistory(dateToString()+" Removed account.\t\t "+account.toString()+"\t"+person.toString());
        person.addOperationHistory(dateToString()+" Removed account.\t\t "+account.toString()+"\t"+person.toString());
        account.addOperationHistory(dateToString()+" Removed account.\t\t "+account.toString()+"\t"+person.toString());
        account.saveOperationHistory();
        bankAccountList.remove(account);
        accountPersonMap.remove(account);
    }
    
    // Transactions methods //
    
    public void deposit(String accountNumber, double value, String title){
        BankAccount account = getAccount(accountNumber);
        Deposit deposit = new Deposit(account,value,title);
        if(deposit.action()){
            bank.addOperationHistory(dateToString()+" "+deposit.toString());
            accountPersonMap.get(account).addOperationHistory(dateToString()+" "+deposit.toString());
            account.addOperationHistory(dateToString()+" "+deposit.toString());
        }
        else{
           throw new IllegalArgumentException("Can not perform action."); 
        }
    }
    
    public void withdraw(String accountNumber, double value, String title){
        BankAccount account = getAccount(accountNumber);
        Withdraw withdraw = new Withdraw(account,value,title);
        if(withdraw.action()){
            bank.addOperationHistory(dateToString()+" "+withdraw.toString());
            accountPersonMap.get(account).addOperationHistory(dateToString()+" "+withdraw.toString());
            account.addOperationHistory(dateToString()+" "+withdraw.toString());
        }
        else{
           throw new IllegalArgumentException("Can not perform action."); 
        }
    }
    
    public void transfer(String accountNumberFrom, String accountNumberTo, double value, String title){
        BankAccount accountFrom = getAccount(accountNumberFrom);
        BankAccount accountTo = getAccount(accountNumberTo);
        Transfer transfer = new Transfer(accountFrom, accountTo, value, title);
        if(transfer.action()){
            bank.addOperationHistory(dateToString()+" "+transfer.toString());
            accountPersonMap.get(accountFrom).addOperationHistory(dateToString()+" "+transfer.toString());
            accountPersonMap.get(accountTo).addOperationHistory(dateToString()+" "+transfer.toString());
            accountFrom.addOperationHistory(dateToString()+" "+transfer.toString());
            accountTo.addOperationHistory(dateToString()+" "+transfer.toString());
        }
        else{
           throw new IllegalArgumentException("Can not perform action."); 
        }
    }
    
    public void transferInternationalOutgoing(String accountNumberFrom, String accountNumberTo, String swift, Double amount, String title, String bankName){
        if(accountNumberTo.length()!=18){
            throw new IllegalArgumentException("Inappropriate account length.");
        }
        for(char c : accountNumberTo.toCharArray()){
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("Inappropriate account number.");
            }
        }
        if(!swiftCodes.contains(new SWIFT(swift,bankName))){
            throw new IllegalArgumentException("Inappropriate SWIFT.");
        }
        
        BankAccount accountFrom = getAccount(accountNumberTo);
        TransferInternationalOutgoing transfer = new TransferInternationalOutgoing(accountFrom, accountNumberTo, swift, amount, title, bankName);
        if(transfer.action()){
            bank.addOperationHistory(dateToString()+" "+transfer.toString());
            accountPersonMap.get(accountFrom).addOperationHistory(dateToString()+" "+transfer.toString());
            accountFrom.addOperationHistory(dateToString()+" "+transfer.toString());
        }
        else{
           throw new IllegalArgumentException("Can not perform action."); 
        }
    }
    
    public void transferInternationalIncoming(String accountNumberFrom, String accountNumberTo, String swift, Double amount, String title, String bankName){
        if(accountNumberFrom.length()!=18){
            throw new IllegalArgumentException("Inappropriate account length.");
        }
        for(char c : accountNumberFrom.toCharArray()){
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("Inappropriate account number.");
            }
        }
        if(!swiftCodes.contains(new SWIFT(swift,bankName))){
            throw new IllegalArgumentException("Inappropriate SWIFT.");
        }
        
        BankAccount accountTo = getAccount(accountNumberTo);
        TransferInternationalIncoming transfer = new TransferInternationalIncoming(accountNumberFrom, accountTo, swift, amount, title, bankName);
        if(transfer.action()){
            bank.addOperationHistory(dateToString()+" "+transfer.toString());
            accountPersonMap.get(accountTo).addOperationHistory(dateToString()+" "+transfer.toString());
            accountTo.addOperationHistory(dateToString()+" "+transfer.toString());
        }
        else{
           throw new IllegalArgumentException("Can not perform action."); 
        }
    }
    
    // 
    
    public void saveHistory(){
        bank.saveOperationHistory();
        personList.forEach((person) -> {
            person.saveOperationHistory();
        });
        bankAccountList.forEach((account) -> {
            account.saveOperationHistory();
        });
    }
}
