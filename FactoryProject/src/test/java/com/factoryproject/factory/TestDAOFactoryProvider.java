/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factoryproject.factory;

import com.factoryproject.factory.factoryimplementation.CSVFactory;
import com.factoryproject.factory.factoryimplementation.DBFactory;
import com.factoryproject.factory.factoryimplementation.WSFactory;
import com.factoryproject.factory.factoryimplementation.XMLFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author MeetTheScorch
 */
public class TestDAOFactoryProvider {
    CSVFactory csvFactory;
    DBFactory dbFactory;
    WSFactory wsFactory;
    XMLFactory xmlFactory;
    DAOFactoryProvider provider;
    
    @Before
    public void setUp(){
        csvFactory = mock(CSVFactory.class);
        dbFactory = mock(DBFactory.class);
        wsFactory = mock(WSFactory.class);
        xmlFactory = mock(XMLFactory.class);

        provider = DAOFactoryProvider.getInstance();
    }
    
    @Test
    public void testCSVFactory(){
        when(csvFactory.getName()).thenReturn("CSV mock name");
        when(csvFactory.getAge()).thenReturn(1);
        when(csvFactory.getCity()).thenReturn("CSV mock city");
        
        provider.setSourceOfData(FactoryEnum.CSV);
        
        assertEquals("CSV mock name",csvFactory.getName());
        assertEquals(1,csvFactory.getAge());
        assertEquals("CSV mock city",csvFactory.getCity());
    }
    
    @Test
    public void testDBFactory(){
        when(dbFactory.getName()).thenReturn("DB mock name");
        when(dbFactory.getAge()).thenReturn(2);
        when(dbFactory.getCity()).thenReturn("DB mock city");
        
        provider.setSourceOfData(FactoryEnum.DB);
        
        assertEquals("DB mock name",dbFactory.getName());
        assertEquals(2,dbFactory.getAge());
        assertEquals("DB mock city",dbFactory.getCity());
    }
    
    @Test
    public void testWSFactory(){
        when(wsFactory.getName()).thenReturn("WS mock name");
        when(wsFactory.getAge()).thenReturn(3);
        when(wsFactory.getCity()).thenReturn("WS mock city");
        
        provider.setSourceOfData(FactoryEnum.WS);
        
        assertEquals("WS mock name",wsFactory.getName());
        assertEquals(3,wsFactory.getAge());
        assertEquals("WS mock city",wsFactory.getCity());
    }
    
    @Test
    public void testXMLFactory(){
        when(xmlFactory.getName()).thenReturn("XML mock name");
        when(xmlFactory.getAge()).thenReturn(2);
        when(xmlFactory.getCity()).thenReturn("XML mock city");
        
        provider.setSourceOfData(FactoryEnum.XML);
        
        assertEquals("XML mock name",xmlFactory.getName());
        assertEquals(2,xmlFactory.getAge());
        assertEquals("XML mock city",xmlFactory.getCity());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
