/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaTesten;

import domain.Ball;
import domain.Brick;
import domain.BrickRow;
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
    
//    @Test
//    public void testRowOfBlocksForLevel(){
//        Game g = new Game(0, 1000, 1000, 3, 1);
//        Level level = g.getLevels().get(0);
//        int som = 0;
//        for (BrickRow br : level.getRowsOfBricks()) {
//            for (Brick b : br.getBricksOnRow()) {
//                som += b.getLength();
//            }
//        }
//        assertEquals(2500, som);
//    }
//    
//    @Test
//    public void testEenPalletMaken(){
//        Game g = new Game(0, 1000, 1000, 3, 1);
//        Level level = g.getLevels().get(0);
//        Pallet p = level.getPallets().get(0);
//        assertTrue(p instanceof Pallet);
//        assertEquals(438, p.getX());
//    }
//    
//    @Test
//    public void testMeerderePalletMaken(){
//        Game g = new Game(0, 1000, 1000, 3, 2);
//        Level level = g.getLevels().get(0);
//        Pallet p1 = level.getPallets().get(0);
//        Pallet p2 = level.getPallets().get(1);
//        
//        assertTrue(level.getPallets().get(0) instanceof Pallet);
//        assertEquals(188, p1.getX());
//        assertEquals(688, p2.getX());
//    }
//    
//    @Test
//    public void testBallMaken(){
//        Game g = new Game(0, 1000, 1000, 3, 1);
//        Ball b = g.getLevels().get(0).getBalls().get(0);
//        assertTrue(b instanceof Ball);
//    }
//    
//    @Test
//    public void testLevelsMaken(){
//        Game g = new Game(0, 1000, 1000, 3, 1);
//        Level level = g.getLevels().get(0);
//        assertTrue(level instanceof Level);
//    }
//    
//    @Test
//    public void testGameMaken(){
//        Game g = new Game(0, 1000, 1000, 3, 1);
//        Level level = g.getLevels().get(0);
//        Pallet p = level.getPallets().get(0);
//        Ball b = level.getBalls().get(0);
//        BrickRow br = level.getRowsOfBricks().get(0);
//        Brick brick = br.getBricksOnRow().get(0);
//        
//        assertTrue(level instanceof Level);
//        assertEquals(1, level.getPallets().size());
//        assertTrue(p instanceof Pallet);
//        assertEquals(1, level.getBalls().size());
//        assertTrue(b instanceof Ball);
//        assertEquals(5, level.getRowsOfBricks().size());
//        assertTrue(brick instanceof Brick);
//    }
//    
    @Test
    public void createNextLevel(){
        Game g = new Game(0, 1000, 1000, 3, 1);
        g.createNewLevel();
        g.createNewLevel();
        Brick b = g.getLevels().get(1).getRowsOfBricks().get(4).getBricksOnRow().get(0);
        Brick b1 = g.getLevels().get(2).getRowsOfBricks().get(4).getBricksOnRow().get(0);
        assertEquals(20, b.getAchievedScore());
        assertEquals(30, b1.getAchievedScore());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
