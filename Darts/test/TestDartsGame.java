/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MeetTheScorch
 */
public class TestDartsGame {
        
    DartsGame darts = new DartsGame();
    
    @Test
    public void testGetPlayerPoints(){
        assertEquals(301,darts.getPlayerPoints(1));
        assertEquals(301,darts.getPlayerPoints(2));
    }
    
    @Test
    public void testTurn(){
        darts.turn(1,20,1);
        darts.turn(1,20,2);
        darts.turn(1,20,3);
        
        darts.turn(2,20,1);
        darts.turn(2,20,2);
        darts.turn(2,20,3);
        
        darts.turn(1,20,3);
        darts.turn(1,20,3);
        darts.turn(1,20,3);
 
        darts.turn(2,20,3);
        darts.turn(2,20,3);
        darts.turn(2,20,3);

        darts.turn(1,20,3);
        darts.turn(1,20,3);
        darts.turn(1,20,3);
 
        darts.turn(2,1,1);
    }
}
