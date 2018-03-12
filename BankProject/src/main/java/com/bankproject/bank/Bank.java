/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bankproject.bank;

import com.bankproject.service.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MeetTheScorch
 */
public class Bank {
    private final String name;
    private String swiftCode;
    private List<String> bankOperationHistory = new ArrayList<>();
    private final String path;

    public Bank(String name, String swiftCode) {
        this.path = "history\\banks\\";
        //this.path = "history\\";
        this.name = name;
        this.swiftCode = swiftCode;
    }
    
    public void addOperationHistory(String record){
        bankOperationHistory.add(record);
    }
    
    public void saveOperationHistory(){
        FileManager fileManager = FileManager.getInstance();
        try{
            fileManager.saveToFile(path,"Bank-"+name+".txt", bankOperationHistory);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public void loadOperationHistory(){
        FileManager fileManager = FileManager.getInstance();
        try {
            bankOperationHistory = fileManager.loadFromFile(path,"Bank-"+name+".txt");
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public void printOperationHistory(){
        bankOperationHistory.forEach((line) -> {
            System.out.println(line);
        });
    }
}
