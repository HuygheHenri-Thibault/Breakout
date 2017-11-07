/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaTesten;

import domain.Game;
import java.awt.event.KeyEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author micha
 */
public class MoveAndCollissionTesten {
    
    public MoveAndCollissionTesten() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void movePallet(){
        Game g = new Game(0, 1);
        g.getPallets().get(0).move();
        // niet echt te testen
    }
    
    @Test
    public void moveBall(){
        Game g = new Game(0, 1);
        g.getBalls().get(0).move();
        assertEquals(g.getMAX_GAME_BORDER_X() / 2 + 5, g.getBalls().get(0).getX());
        assertEquals(((g.getMAX_GAME_BORDER_Y()/ 10) * 8) - 5, g.getBalls().get(0).getY());
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
