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
public class WSFactory implements IFactory{

    @Override
    public void setName(String name) {
        
    }

    @Override
    public void setAge(int age) {
        
    }

    @Override
    public void setCity(String city) {
        
    }

    @Override
    public String getName() {
         return "WS";
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public String getCity() {
        return null;
    }
}
