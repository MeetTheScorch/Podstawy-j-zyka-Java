/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bankproject;

import com.bankproject.service.BankService;
import java.util.GregorianCalendar;

/**
 *
 * @author MeetTheScorch
 */
public class BankProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BankService service = new BankService("POKA BP","POKAPLP0");
        
        try{
            service.addPerson("Michał","Miłek",new GregorianCalendar(1992,3,29));
            System.out.println(service.getPerson("Michał","Miłek",new GregorianCalendar(1992,3,29)).toString());
            System.out.println();

            service.addAccount("Michał","Miłek",new GregorianCalendar(1992,3,29), "123456789012345678", 5000);
            System.out.println(service.getAccount("123456789012345678").toString());
            System.out.println();

            service.deposit("123456789012345678", 1000, "Wpłata");
            System.out.println(service.getAccount("123456789012345678").toString());
            System.out.println();

            service.withdraw("123456789012345678", 123.59, "Wypłata");
            System.out.println(service.getAccount("123456789012345678").toString());
            System.out.println();

            service.addAccount("Michał","Miłek",new GregorianCalendar(1992,3,29), "111111111111111111", 5000);
            service.removeAccount("111111111111111111");

            service.addPerson("a","b",new GregorianCalendar(1992,3,29));
            service.addAccount("a","b",new GregorianCalendar(1992,3,29), "999999999999999999", 5);
            service.removePerson("a","b", new GregorianCalendar(1992,3,29));

            service.addAccount("Michał","Miłek",new GregorianCalendar(1992,3,29), "666666666666666666", 100);
            System.out.println(service.getAccount("666666666666666666").toString());
            System.out.println(service.getAccount("123456789012345678").toString());
            System.out.println();

            service.transfer("666666666666666666","123456789012345678",100,"Przelew");
            System.out.println(service.getAccount("666666666666666666").toString());
            System.out.println(service.getAccount("123456789012345678").toString());
            System.out.println();


            service.getAccount("123456789012345678").printOperationHistory();
            System.out.println();

            service.getAccount("666666666666666666").loadOperationHistory();
            service.getAccount("666666666666666666").printOperationHistory();
            System.out.println();
            
            System.out.println(service.getAccount("123456789012345678").toString());
            service.transferInternationalOutgoing("123456789012345678", "123456789012345678", "POKAPLP0", 50.0, "Przelew międzynarodowy wychodzący", "POKA BP");
            System.out.println(service.getAccount("123456789012345678").toString());
            System.out.println();
            
            System.out.println(service.getAccount("123456789012345678").toString());
            service.transferInternationalIncoming("123456789012345678", "123456789012345678", "POKAPLP0", 50.0, "Przelew międzynarodowy przychodzący", "POKA BP");
            System.out.println(service.getAccount("123456789012345678").toString());
            System.out.println();

            service.saveHistory();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    
}
