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
import be.howest.ti.breakout.domain.Shape;
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
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;

/**
 *
 * @author micha
 */
public class MoveAndCollissionTesten {
    
    User me = new User(1, "henri", "wachtwoord", "eenemail@email.com", 1, "een mooie bio", 0);
    User otherMe = new User(2, "brecht", "wachtwoord2", "eeneanderemail@email.com", 1, "een lelijke bio", 0);
    User anotherMe = new User(3, "frederik", "wachtwoord3", "eenkleineemail@email.com", 1, "een prachtige bio", 0);
    GameDifficulty easy = new GameDifficulty("easy", 0.2f, 1);
    
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
    public void testMovePallet(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Pallet p = g.getLevelPlayedRightNow().getUserPallet(me);
        p.setDx(1);
        p.moveRight();
        assertEquals(429, p.getX());
    }
    
    @Test
    public void testMoveBall(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Ball b = g.getLevelPlayedRightNow().getBalls().get(0);
        b.move();
        assertEquals(500, g.getLevelPlayedRightNow().getBalls().get(0).getX());
        assertEquals(603, g.getLevelPlayedRightNow().getBalls().get(0).getY());
    }
    
    @Test
    public void testCollissionDetectionPallet(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Pallet p = g.getLevelPlayedRightNow().getUserPallet(me);
        p.setSpeed(1);
        p.moveRight(); // normaal via key event
        p.setX(857);
        p.move();
        assertEquals(857, p.getX());
        p.setX(0);
        p.moveLeft(); //normaal via key event
        p.move();
        assertEquals(0, p.getX());
    }
    
    @Test
    public void testCollissionDetectionMeerderePallet(){
        List<User> players = new ArrayList<>(Arrays.asList(me, otherMe, anotherMe));
        Game g = new MultiPlayerGame(players, 1000, 1000, easy);
        g.createNewLevel();
        Pallet p1 = g.getLevelPlayedRightNow().getUserPallet(me);
        Pallet p2 = g.getLevelPlayedRightNow().getUserPallet(otherMe);
        
        p1.setSpeed(1);
        p1.moveRight(); // normaal via key event
        
        p2.setSpeed(1);
        p2.moveLeft(); // normaal via key event
        
        p1.setX(307);
        p1.move();
        assertEquals(307, p1.getX());
        
        p2.setX(230); // normaal via key event
        p2.move();
        assertEquals(230, p2.getX());
    }
    
    @Test
    public void testBallCollissionWithPalletInMiddle(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Ball b = g.getLevelPlayedRightNow().getBalls().get(0);
        b.setY(890);
        b.setSpeed(1);
        b.setDy(1);
        b.setDx(1);
        b.move();
        b.move();
        assertEquals(890, b.getY()); 
        assertEquals(me, b.getLastUserThatTouchedMe());
    }
    
    @Test
    public void testBallCollissionWithPalletInOnLeftSide(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Ball b = g.getLevelPlayedRightNow().getBalls().get(0);
        b.setX(464);
        b.setY(890);
        b.setSpeed(3);
        b.setDy(3);
        b.setDx(-3);
        b.move();
        assertEquals(893, b.getY());
        assertEquals(461, b.getX());
        b.move();
        assertEquals(889, b.getY());
        assertEquals(459, b.getX());
        assertEquals(me, b.getLastUserThatTouchedMe());
    }
    
    @Test
    public void testBallCollissionWithPalletInOnLeftBorder(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Ball b = g.getLevelPlayedRightNow().getBalls().get(0);
        b.setX(438);
        b.setY(885);
        b.setSpeed(2);
        b.setDy(2);
        b.setDx(2);
        b.move();
        b.move();
        assertEquals(884, b.getY());
        assertEquals(441, b.getX());
    }
    
    @Test
    public void testBallCollissionWithPalletOnSide(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Ball b = g.getLevelPlayedRightNow().getBalls().get(0);
        b.setX(564);
        b.setY(925);
        b.setSpeed(1);
        b.setDy(1);
        b.setDx(-1);
        b.move();
        b.move();
        assertEquals(927, b.getY());
        assertEquals(564, b.getX());
    }
    
    @Test
    public void testBallCollissionWithBall(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Level l = g.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        Ball b2 = new Ball(l, 15, 2, "red", 600, 500);
        l.getBalls().add(b2);
        b.setX(585);
        b.setY(500);
        b.setSpeed(1);
        b.setDy(1); 
        b.setDx(1);
        b.move(); // x 586 y 501
        b.move(); // x 585 y 500
        assertEquals(500, b.getY());
        assertEquals(585, b.getX());
    }
    
    @Test
    public void testBallCollidingWithBrick(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Level l = g.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        Brick brick = g.getLevelPlayedRightNow().getBricks().get(0);
        b.setX(250);
        b.setY(515);
        b.setSpeed(2);
        b.setDy(-2); 
        b.setDx(2);
        b.move(); // x 252 y 513
        b.move(); // x 253 y 516
        assertEquals(516, b.getY());
        assertEquals(253, b.getX());
        assertFalse(g.getLevelPlayedRightNow().getBricks().contains(brick));
        assertEquals(10, l.getCollectiveScore());
    }
    
    @Test
    public void testBallCollidingWithTopBoundary(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Level l = g.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        b.setX(50);
        b.setY(15);
        b.setSpeed(2);
        b.setDy(-2); 
        b.setDx(2);
        b.move(); // x 52 y 16
        b.move(); // x 54 y 15
        assertEquals(15, b.getY());
        assertEquals(54, b.getX());
    }
    
    @Test
    public void testBallCollidingWithLeftBoundary(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Level l = g.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        b.setX(15);
        b.setY(800);
        b.setSpeed(2);
        b.setDy(-2); 
        b.setDx(-2);
        b.move(); // x 13 y 798
        b.move(); // x 15 y 796
        assertEquals(796, b.getY());
        assertEquals(15, b.getX());
    }
    
    @Test
    public void testBallCollidingWithRightBoundary(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Level l = g.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        b.setX(985);
        b.setY(800);
        b.setSpeed(2);
        b.setDy(2); 
        b.setDx(2);
        b.move(); // x 987 y 802
        b.move(); // x 985 y 804
        assertEquals(804, b.getY());
        assertEquals(985, b.getX());
    }
    
    @Test
    public void testBallCollidingWithBottomBoundary(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Level l = g.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        b.setX(15);
        b.setY(990);
        b.setSpeed(2);
        b.setDx(-2);
        b.setDy(2);
        b.move(); // x 13 y 798 -> x 500 y 800
        b.move(); // x 502 y 798
        assertEquals(992, b.getY());
        assertEquals(5, b.getX());
        assertEquals(2, g.getLives());
    }
    
    @Test
    public void testBallTouchedPowerUp(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        g.createNewLevel();
        Level l = g.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        Brick brick = l.getBricks().get(l.getBricks().size() - 1);
        l.deleteBrick(brick, b.getLastUserThatTouchedMe());
        PowerUpOrDown powerup = brick.getPowerUP();
        List<Shape> allEntities = l.getAllEntities();
        b.setX(powerup.getBoundaries().getX() - 15);
        b.setY(powerup.getBoundaries().getY() + 5);
        b.setDx(1);
        b.setDy(-1);
        b.move();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
