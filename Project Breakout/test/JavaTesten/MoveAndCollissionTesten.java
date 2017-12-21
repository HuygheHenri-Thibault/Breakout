/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaTesten;

import be.howest.ti.breakout.data.Repositories;
import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.game.Game;
import be.howest.ti.breakout.domain.game.GameDifficulty;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.Shape;
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
    Game singlePlayerGame = new Game(1000, 1000, 1, easy);
    
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
         singlePlayerGame.replaceGuestByUser(1, me);
         singlePlayerGame.createNewLevel();
         
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testMovePallet(){
        Pallet p = singlePlayerGame.getLevelPlayedRightNow().getPlayerPallet(me);
        p.setDx(1);
        p.moveRight();
        assertEquals(429, p.getX());
    }
    
    @Test
    public void testMoveBall(){
        Ball b = singlePlayerGame.getLevelPlayedRightNow().getBalls().get(0);
        b.move();
        assertEquals(500, singlePlayerGame.getLevelPlayedRightNow().getBalls().get(0).getX());
        assertEquals(603, singlePlayerGame.getLevelPlayedRightNow().getBalls().get(0).getY());
    }
    
    @Test
    public void testCollissionDetectionPallet(){
        Pallet p = singlePlayerGame.getLevelPlayedRightNow().getPlayerPallet(me);
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
        Game multiplayerGame3P = new Game(1000, 1000, 3, easy);
        multiplayerGame3P.replaceGuestByUser(1, me);
        multiplayerGame3P.replaceGuestByUser(2, otherMe);
        multiplayerGame3P.replaceGuestByUser(3, anotherMe);
        multiplayerGame3P.createNewLevel();
        Pallet p1 = multiplayerGame3P.getLevelPlayedRightNow().getPlayerPallet(me);
        Pallet p2 = multiplayerGame3P.getLevelPlayedRightNow().getPlayerPallet(otherMe);
        
        p1.setSpeed(1);
        p1.moveRight(); 
        
        p1.setX(307);
        p1.move();
        assertEquals(307, p1.getX());
        
        p2.setSpeed(1);
        p2.moveLeft(); 
         
        p2.move();
        assertEquals(435, p2.getX());
    }
    
    @Test
    public void testBallCollissionWithPalletInMiddle(){
        Ball b = singlePlayerGame.getLevelPlayedRightNow().getBalls().get(0);
        b.setY(890);
        b.setSpeed(1);
        b.setDy(1);
        b.setDx(1);
        b.move();
        b.move();
        assertEquals(890, b.getY()); 
        assertEquals(me, b.getLastPlayerThatTouchedMe());
    }
    
    @Test
    public void testBallCollissionWithPalletInOnLeftBorder(){
        Ball b = singlePlayerGame.getLevelPlayedRightNow().getBalls().get(0);
        b.setX(434);
        b.setY(890);
        b.setSpeed(3);
        b.setDy(2);
        b.setDx(-2);
        b.move();
        b.move();
        assertEquals(887, b.getY());
        assertEquals(431, b.getX());
    }
    
    @Test
    public void testBallCollissionWithPalletOnLeftSide(){
        Ball b = singlePlayerGame.getLevelPlayedRightNow().getBalls().get(0);
        b.setX(480);
        b.setY(890);
        b.setSpeed(3);
        b.setDy(2);
        b.setDx(-2);
        b.move();
        b.move();
        System.out.println(b.getDx());
        System.out.println(b.getDy());
        assertEquals(888, b.getY());
        assertEquals(476, b.getX());
    }
    
    @Test
    public void testBallCollissionWithPalletOnSide(){
        Ball b = singlePlayerGame.getLevelPlayedRightNow().getBalls().get(0);
        b.setX(419);
        b.setY(905);
        b.setSpeed(1);
        b.setDy(1);
        b.setDx(1);
        b.move();
        b.move();
        assertEquals(907, b.getY());
        assertEquals(419, b.getX());
    }
    
    @Test
    public void testBallCollissionWithBall(){
        Game multiplayerGame3P = new Game(1000, 1000, 3, easy);
        multiplayerGame3P.replaceGuestByUser(1, me);
        multiplayerGame3P.replaceGuestByUser(2, otherMe);
        multiplayerGame3P.replaceGuestByUser(3, anotherMe);
        multiplayerGame3P.createNewLevel();
        Level l = multiplayerGame3P.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        Ball b2 = l.getBalls().get(1);

        b.setX(730);
        b.setY(595);
        b.setSpeed(1);
        b.setDy(1); 
        b.setDx(1);
        b.move();
        b.move();
        assertEquals(595, b.getY());
        assertEquals(729, b.getX());
    }
    
    @Test
    public void testBallCollidingWithBrick(){
        Game singlePlayerGameTest = new Game(1000, 1000, 1, easy);
        singlePlayerGameTest.replaceGuestByUser(1, me);
        singlePlayerGameTest.createNewLevel();
        Level l = singlePlayerGame.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        Brick brick = singlePlayerGame.getLevelPlayedRightNow().getBricks().get(0);
        b.setDamage(3);
        b.setX(20);
        b.setY(445);
        b.setSpeed(2);
        b.setDy(-2); 
        b.setDx(2);
        b.move(); 
        b.move(); 
        assertEquals(445, b.getY());
        assertEquals(24, b.getX());
        assertFalse(singlePlayerGame.getLevelPlayedRightNow().getBricks().contains(brick));
        assertEquals(10, l.getCollectiveScore());
    }
    
    @Test
    public void testBallCollidingWithTopBoundary(){
        Level l = singlePlayerGame.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        b.setX(50);
        b.setY(10);
        b.setSpeed(2);
        b.setDy(-2); 
        b.setDx(2);
        b.move(); 
        b.move();
        assertEquals(10, b.getY());
        assertEquals(54, b.getX());
    }
    
    @Test
    public void testBallCollidingWithLeftBoundary(){
        Level l = singlePlayerGame.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        b.setX(10);
        b.setY(800);
        b.setSpeed(2);
        b.setDy(-2); 
        b.setDx(-2);
        b.move(); 
        b.move(); 
        assertEquals(796, b.getY());
        assertEquals(10, b.getX());
    }
    
    @Test
    public void testBallCollidingWithRightBoundary(){
        Level l = singlePlayerGame.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        b.setX(990);
        b.setY(800);
        b.setSpeed(2);
        b.setDy(2); 
        b.setDx(2);
        b.move(); 
        b.move(); 
        assertEquals(804, b.getY());
        assertEquals(990, b.getX());
    }
    
    @Test
    public void testBallCollidingWithBottomBoundary(){
        Level l = singlePlayerGame.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        b.setX(100);
        b.setY(990);
        b.setSpeed(2);
        b.setDx(-2);
        b.setDy(2);
        b.move();
        if(!b.isOnScreen()){
            l.getBalls().remove(b);
        }
        if(l.getBalls().isEmpty()){
            l.resetStates();
        }
        b = l.getBalls().get(0);
        assertEquals(600, b.getY());
        assertEquals(500, b.getX());
        assertEquals(2, singlePlayerGame.getLives());
    }
    
    @Test
    public void testBallTouchedPowerUp(){
        Level l = singlePlayerGame.getLevelPlayedRightNow();
        Ball b = l.getBalls().get(0);
        Brick brick = l.getBricks().get(l.getBricks().size() - 1);
        PowerUpOrDown power = Repositories.getPowerUpDownRepository().getPowerUpOrDownWithName("Scaffolds");
        l.deleteBrick(brick, b.getLastPlayerThatTouchedMe());
        PowerUpOrDown powerup = brick.getPowerUP();
        b.setX(powerup.getBoundaries().getX() - 10);
        b.setY(powerup.getBoundaries().getY() + 5);
        b.setDx(1);
        b.setDy(-1);
        b.move();
        assertTrue(l.getAllActivePowerUps().contains(power));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
