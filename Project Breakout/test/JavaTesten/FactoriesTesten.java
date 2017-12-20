/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaTesten;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.game.Game;
import be.howest.ti.breakout.domain.game.GameDifficulty;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.game.MultiPlayerGame;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.game.SinglePlayerGame;
import be.howest.ti.breakout.domain.game.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import be.howest.ti.breakout.domain.spells.Spell;

/**
 *
 * @author micha
 */
public class FactoriesTesten {

    User me = new User(1, "henri", "wachtwoord", "eenemail@email.com", 1, "een mooie bio", 0);
    User otherMe = new User(2, "brecht", "wachtwoord2", "eeneanderemail@email.com", 1, "een lelijke bio", 0);
    User anotherMe = new User(3, "frederik", "wachtwoord3", "eenkleineemail@email.com", 1, "een prachtige bio", 0);
    List<User> players2 = new ArrayList<>(Arrays.asList(me, otherMe));
    List<User> players3 = new ArrayList<>(Arrays.asList(me, otherMe, anotherMe));
    GameDifficulty easy = new GameDifficulty("easy", 0.2f, 1);
    Game singlePlayerGame = new SinglePlayerGame(me, 1000, 1000, easy);
    Game multiPlayerGame2P = new MultiPlayerGame(players2, 1000, 1000, easy);
    Game multiPlayerGame3P = new MultiPlayerGame(players3, 1000, 1000, easy);


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
    public void testNieuwLevelWithNoGame() {
        try {
            Level level = new Level(null, 0);
        } catch (NullPointerException ex) {

        }
    }

    @Test
    public void testRowOfBlocksForLevel() {
        Level level = singlePlayerGame.getLevelPlayedRightNow();
        int som = 0;
        for (Brick b : level.getBricks()) {
            som += b.getLength();
        }
        assertEquals(5000, som);
    }

    @Test
    public void testEenPalletMaken() {
        Level level = singlePlayerGame.getLevelPlayedRightNow();
        Pallet p = level.getUserPallet(me.getUserId());
        assertTrue(p instanceof Pallet);
        assertEquals(425, p.getX());
    }

    @Test
    public void testMeerderePalletMaken() {
        Level level = multiPlayerGame2P.getLevelPlayedRightNow();
        Pallet p1 = level.getUserPallet(me.getUserId());
        Pallet p2 = level.getUserPallet(otherMe.getUserId());

        assertTrue(level.getUserPallet(me.getUserId()) instanceof Pallet);
        assertEquals(175, p1.getX());
        assertEquals(675, p2.getX());
    }

    @Test
    public void testBallMaken() {
        Ball b = singlePlayerGame.getLevelPlayedRightNow().getBalls().get(0);
        assertTrue(b instanceof Ball);
    }

    @Test
    public void meerdereBallen() {
        Ball ball1 = multiPlayerGame3P.getLevelPlayedRightNow().getBalls().get(0);
        Ball ball2 = multiPlayerGame3P.getLevelPlayedRightNow().getBalls().get(1);
        assertEquals(250, ball1.getX());
        assertEquals(750, ball2.getX());
        assertEquals(600, ball1.getY());
    }

    @Test
    public void testLevelsMaken() {
        Level level = singlePlayerGame.getLevelPlayedRightNow();
        assertTrue(level instanceof Level);
    }

    @Test
    public void testGameMaken() {
        Level level = singlePlayerGame.getLevelPlayedRightNow();
        Pallet p = level.getUserPallet(me.getUserId());
        Ball b = level.getBalls().get(0);
        Brick brick  = level.getBricks().get(0);

        assertTrue(level instanceof Level);
        assertEquals(1, level.getPallets().size());
        assertTrue(p instanceof Pallet);
        assertEquals(1, level.getBalls().size());
        assertTrue(b instanceof Ball);
        assertTrue(brick instanceof Brick);
    }

    @Test
    public void createNextLevel() {
        singlePlayerGame.createNewLevel();
        Pallet palletLevel2 = singlePlayerGame.getLevels().get(1).getPallets().get(0);
        singlePlayerGame.createNewLevel();
        singlePlayerGame.createNewLevel();
        singlePlayerGame.createNewLevel();
        singlePlayerGame.createNewLevel();
        Pallet palletLevel6 = singlePlayerGame.getLevels().get(5).getPallets().get(0);
        Ball ballLevel3 = singlePlayerGame.getLevels().get(2).getBalls().get(0);
        Brick b = singlePlayerGame.getLevels().get(1).getBricks().get(0);
        assertEquals(20, b.getAchievedScore());
        assertEquals(149, palletLevel2.getLength());
        assertEquals(6, ballLevel3.getSpeed());
        assertEquals(148, palletLevel6.getLength());
    }

    @Test
    public void angle() {
        int targetY = 800;
        int targetX = 250;
        int x = 500;
        int y = 0;
        double angle;
        angle = (double) round(Math.toDegrees(Math.atan2(targetY - y, targetX - x)), 1);
        
//        System.out.println(angle);
//        System.out.println(Math.cos(angle * Math.PI / 180));
//        System.out.println(Math.sin(angle * Math.PI / 180));
        
        if (angle < 0) {
            angle += 360;
        }
        
        double dx = 4 * Math.cos(angle * Math.PI / 180);
        double dy = 4 * Math.sin(angle * Math.PI / 180);
        
//        System.out.println(dx);
//        System.out.println(dy);
        
        x = x + (int) dx;
        y = y + (int) dy;
        
        //System.out.println(angle);
//        System.out.println(dx);
//        System.out.println(dy);
//        System.out.println(x);
//        System.out.println(y);
    }
    
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
    
    @Test
    public void testSpell(){
        Level level = new Level(singlePlayerGame, 1);
        //Spell spell = me.getSpell();
        //System.out.println(spell.getName());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
