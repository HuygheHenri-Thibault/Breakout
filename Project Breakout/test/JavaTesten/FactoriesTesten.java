/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaTesten;

import data.MySQLBrickRepository;
import domain.Ball;
import domain.Brick;
import domain.BrickData;
import domain.BrickRow;
import domain.Game;
import domain.Level;
import domain.MultiPlayerGame;
import domain.Pallet;
import domain.SinglePlayerGame;
import domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    User me = new User(1, "henri", "wachtwoord", "eenemail@email.com", 1, "een mooie bio");
    User otherMe = new User(2, "brecht", "wachtwoord2", "eeneanderemail@email.com", 1, "een lelijke bio");
    User anotherMe = new User(3, "frederik", "wachtwoord3", "eenkleineemail@email.com", 1, "een prachtige bio");
    
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
    public void testNieuwLevelWithNoGame(){
        try {
             Level level = new Level(null, 1, 0);
        }
        catch(NullPointerException ex){
           
        }
    }
    
    @Test
    public void testRowOfBlocksForLevel(){
        Game g = new SinglePlayerGame(me, 1000, 1000);
        Level level = g.getLevels().get(0);
        int som = 0;
        for (BrickRow br : level.getRowsOfBricks()) {
            for (Brick b : br.getBricksOnRow()) {
                som += b.getLength();
            }
        }
        assertEquals(2500, som);
    }
    
    @Test
    public void testEenPalletMaken(){
        Game g = new SinglePlayerGame(me, 1000, 1000);
        Level level = g.getLevels().get(0);
        Pallet p = level.getPallets().get(0);
        assertTrue(p instanceof Pallet);
        assertEquals(438, p.getX());
    }
    
    @Test
    public void testMeerderePalletMaken(){
        List<User> players = new ArrayList<>(Arrays.asList(me, otherMe));
        Game g = new MultiPlayerGame(players, 1000, 1000, 2);
        Level level = g.getLevels().get(0);
        Pallet p1 = level.getPallets().get(0);
        Pallet p2 = level.getPallets().get(1);
        
        assertTrue(level.getPallets().get(0) instanceof Pallet);
        assertEquals(188, p1.getX());
        assertEquals(688, p2.getX());
    }
    
    @Test
    public void testBallMaken(){
        Game g = new SinglePlayerGame(me, 1000, 1000);
        Ball b = g.getLevels().get(0).getBalls().get(0);
        assertTrue(b instanceof Ball);
    }
    
    @Test 
    public void meerdereBallen(){
        List<User> players = new ArrayList<>(Arrays.asList(me, otherMe, anotherMe));
        Game g = new MultiPlayerGame(players, 1000, 1000, 3);
        Ball ball1 = g.getLevels().get(0).getBalls().get(0);
        Ball ball2 = g.getLevels().get(0).getBalls().get(1);
        assertEquals(250, ball1.getX());
        assertEquals(750, ball2.getX());
        assertEquals(800, ball1.getY());
    }
    
    @Test
    public void testLevelsMaken(){
        Game g = new SinglePlayerGame(me, 1000, 1000);
        Level level = g.getLevels().get(0);
        assertTrue(level instanceof Level);
    }
    
    @Test
    public void testGameMaken(){
        Game g = new SinglePlayerGame(me, 1000, 1000);
        Level level = g.getLevels().get(0);
        Pallet p = level.getPallets().get(0);
        Ball b = level.getBalls().get(0);
        BrickRow br = level.getRowsOfBricks().get(0);
        Brick brick = br.getBricksOnRow().get(0);
        
        assertTrue(level instanceof Level);
        assertEquals(1, level.getPallets().size());
        assertTrue(p instanceof Pallet);
        assertEquals(1, level.getBalls().size());
        assertTrue(b instanceof Ball);
        assertEquals(5, level.getRowsOfBricks().size());
        assertTrue(brick instanceof Brick);
    }
    
    @Test
    public void createNextLevel(){
        Game g = new SinglePlayerGame(me, 1000, 1000);
        g.createNewLevel();
        Pallet palletLevel2 = g.getLevels().get(1).getPallets().get(0);
        g.createNewLevel();
        g.createNewLevel();
        g.createNewLevel();
        g.createNewLevel();
        Pallet palletLevel6 = g.getLevels().get(5).getPallets().get(0);
        Ball ballLevel3 = g.getLevels().get(2).getBalls().get(0);
        Brick b = g.getLevels().get(1).getRowsOfBricks().get(4).getBricksOnRow().get(0);
        Brick b1 = g.getLevels().get(2).getRowsOfBricks().get(4).getBricksOnRow().get(0);
        assertEquals(20, b.getAchievedScore());
        assertEquals(30, b1.getAchievedScore());
        assertEquals(124, palletLevel2.getLength());
        assertEquals(12, ballLevel3.getSpeed());
        assertEquals(123, palletLevel6.getLength());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
