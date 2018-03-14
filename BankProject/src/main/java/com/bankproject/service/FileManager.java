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

    private FileManager() {
    }
        
    public void saveToFile(String path, String name, List history) throws IOException{
        PrintWriter printWriter = null;
        try {
            File directory = new File(path);
            if(!directory.exists()){
                directory.mkdir();
            }
            printWriter = new PrintWriter(new FileWriter(path+name, true));
            for(Object line : history){
                printWriter.write(line + "\r\n");
            }
        } 
        catch (IOException ex) {
            throw ex;
        }
        finally{
            if(printWriter != null){
                printWriter.close();
            }
        }
    }
    
    public List<String> loadFromFile(String path, String name) throws IOException{
        List<String> history = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path+name));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                history.add(line);
            }
            bufferedReader.close();
        } 
        catch (IOException ex) {
            throw ex;
        }
        finally{
            if(bufferedReader != null){
                bufferedReader.close();
            }
        }
        return history;
    }
}
