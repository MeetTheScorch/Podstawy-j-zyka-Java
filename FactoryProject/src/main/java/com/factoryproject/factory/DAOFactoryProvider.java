/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factoryproject.factory;

import com.factoryproject.factory.factoryimplementation.IFactory;
import com.factoryproject.factory.factoryimplementation.CSVFactory;
import com.factoryproject.factory.factoryimplementation.DBFactory;
import com.factoryproject.factory.factoryimplementation.WSFactory;
import com.factoryproject.factory.factoryimplementation.XMLFactory;

import java.util.HashMap;

/**
 *
 * @author MeetTheScorch
 */
public class DAOFactoryProvider {
    private static DAOFactoryProvider instance;
    public synchronized static final DAOFactoryProvider getInstance(){
        if(instance == null){
            instance = new DAOFactoryProvider();
        }
        return instance;
    };
    
    private IFactory factory = null;
    private static final HashMap<FactoryEnum, IFactory> factoryMap = new HashMap<FactoryEnum, IFactory>();
    static{
        factoryMap.put( FactoryEnum.CSV, new CSVFactory() );
        factoryMap.put( FactoryEnum.DB, new DBFactory() );
        factoryMap.put( FactoryEnum.WS, new WSFactory() );
        factoryMap.put( FactoryEnum.XML, new XMLFactory() );
    }

    public void setSourceOfData(FactoryEnum Source){
        factory = factoryMap.get(Source);
     }
    
    public IFactory getFactory(){
        return factory;
    }
}
