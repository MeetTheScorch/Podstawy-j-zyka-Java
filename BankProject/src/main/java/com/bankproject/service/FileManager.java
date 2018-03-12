/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bankproject.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MeetTheScorch
 */
public class FileManager {
    private static FileManager instance;
    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }
    
    public void saveToFile(String path, String name, List history) throws IOException{
        try {
            File directory = new File(path);
            if(!directory.exists()){
                directory.mkdir();
            }
            FileWriter fileWriter = new FileWriter(path+name, true);
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                history.forEach((line) -> {
                    printWriter.write(line + "\r\n");
                });
            }
        } 
        catch (IOException ex) {
            throw ex;
        }
    }
    
    public List<String> loadFromFile(String path, String name) throws IOException{
        List<String> history = new ArrayList<>();
        try {
            FileReader reader = new FileReader(path+name);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                history.add(line);
            }
            bufferedReader.close();
        } 
        catch (IOException ex) {
            throw ex;
        }
        return history;
    }
}
