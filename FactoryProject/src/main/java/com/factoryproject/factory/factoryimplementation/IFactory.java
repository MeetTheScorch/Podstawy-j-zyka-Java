/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factoryproject.factory.factoryimplementation;

/**
 *
 * @author MeetTheScorch
 */
public interface IFactory {
    public void setName(String name);
    public void setAge(int age);
    public void setCity(String city);
    
    public String getName();
    public int getAge();
    public String getCity();
}
