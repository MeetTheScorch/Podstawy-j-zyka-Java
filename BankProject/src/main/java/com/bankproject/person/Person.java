/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bankproject.person;

import com.bankproject.service.FileManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MeetTheScorch
 */
public class Person {
    private String firstName;
    private String lastName;
    private GregorianCalendar birthDate;
    private List<String> personOperationHistory = new ArrayList<>();
    private final String path;

    public Person(String firstName, String lastName, GregorianCalendar birthDate){
        this.path = "history\\persons\\";
        //this.path = "history\\";
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // get-set //
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public GregorianCalendar getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(GregorianCalendar birthDate) {
        this.birthDate = birthDate;
    }
    
    //
    
    public String birthDateToString(){
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        fmt.setCalendar(birthDate);
        String dateFormatted = fmt.format(birthDate.getTime());
        return dateFormatted;
    }
    
    public void addOperationHistory(String record){
        personOperationHistory.add(record);
    }
    
    public void saveOperationHistory(){
        FileManager fileManager = FileManager.getInstance();
        try{
            fileManager.saveToFile(path,"Person-"+firstName+"-"+lastName+".txt",
                    personOperationHistory);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public void loadOperationHistory(){
        FileManager fileManager = FileManager.getInstance();
        try{
            personOperationHistory = fileManager.loadFromFile(path,"Person-"+firstName+"-"+lastName+".txt");
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public void printOperationHistory(){
        personOperationHistory.forEach((line) -> {
            System.out.println(line);
        });
    }
    
    @Override
    public String toString(){
        return "First name: " + firstName + 
                ", Last name: " + lastName +
                ", Birth date: " + birthDateToString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.firstName);
        hash = 37 * hash + Objects.hashCode(this.lastName);
        hash = 37 * hash + Objects.hashCode(this.birthDate);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.birthDate, other.birthDate)) {
            return false;
        }
        return true;
    }
}
