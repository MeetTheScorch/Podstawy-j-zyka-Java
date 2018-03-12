/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bankproject.bankaccount.tansactions;

/**
 *
 * @author MeetTheScorch
 */
public interface Transaction {  
    public String getDescription();
    public boolean action();
    
    @Override
    public String toString();
}
