/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaTesten;

import domain.Ball;
import domain.Brick;
import domain.Game;
import domain.Level;
import domain.Pallet;
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
public class FactoriesTesten {
    
    public FactoriesTesten() {
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
    public void testRowOfBlocksForLevel(){
        Game g = new Game(0, 1000, 1000, 3, 1);
        int som = 0;
        for (int i = 0; i < g.getLevels().get(0).getRowOfBricks().size(); i++) {
            for (int j = 0; j < g.getLevels().get(0).getRowOfBricks().get(i).getBricksOnRow().size() ; j++) {
                som += g.getLevels().get(0).getRowOfBricks().get(i).getBricksOnRow().get(j).getLength();
            }
        }
        assertEquals(500 * 5, som);
    }
    
    @Test
    public void testEenPalletMaken(){
        Game g = new Game(0, 1000, 1000, 3, 1);
        assertTrue(g.getLevels().get(0).getPallets().get(0) instanceof Pallet);
        assertEquals(438, g.getLevels().get(0).getPallets().get(0).getX());
    }
    
    @Test
    public void testMeerderePalletMaken(){
        Game g = new Game(0, 1000, 1000, 3, 2);
        assertTrue(g.getLevels().get(0).getPallets().get(0) instanceof Pallet);
        assertEquals(188, g.getLevels().get(0).getPallets().get(0).getX());
        assertEquals(688, g.getLevels().get(0).getPallets().get(1).getX());
    }
    
    @Test
    public void testBallMaken(){
        Game g = new Game(0, 1000, 1000, 3, 1);
        assertTrue(g.getLevels().get(0).getBalls().get(0) instanceof Ball);
    }
    
    @Test
    public void testLevelsMaken(){
        Game g = new Game(0, 1000, 1000, 3, 1);
        assertTrue(g.getLevels().get(0) instanceof Level);
    }
    
    @Test
    public void testGameMaken(){
        Game g = new Game(0, 1000, 1000, 3, 1);
        assertTrue(g.getLevels().get(0) instanceof Level);
        assertEquals(1, g.getLevels().get(0).getPallets().size());
        assertTrue(g.getLevels().get(0).getPallets().get(0) instanceof Pallet);
        assertEquals(1, g.getLevels().get(0).getBalls().size());
        assertTrue(g.getLevels().get(0).getBalls().get(0) instanceof Ball);
        assertEquals(5, g.getLevels().get(0).getRowOfBricks().size());
        assertTrue(g.getLevels().get(0).getRowOfBricks().get(0).getBricksOnRow().get(0) instanceof Brick);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
