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
import domain.GameDifficulty;
import domain.Level;
import domain.MultiPlayerGame;
import domain.Pallet;
import domain.Shape;
import domain.SinglePlayerGame;
import domain.User;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import powerUps.PowerUpOrDown;

/**
 *
 * @author micha
 */
public class MoveAndCollissionTesten {
    
    User me = new User(1, "henri", "wachtwoord", "eenemail@email.com", 1, "een mooie bio");
    User otherMe = new User(2, "brecht", "wachtwoord2", "eeneanderemail@email.com", 1, "een lelijke bio");
    User anotherMe = new User(3, "frederik", "wachtwoord3", "eenkleineemail@email.com", 1, "een prachtige bio");
    GameDifficulty easy = new GameDifficulty("easy", 0.2f);
    
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
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        Pallet p = g.getLevels().get(0).getPallets().get(0);
        p.setDx(1);
        p.moveRight();
        assertEquals(439, p.getX());
    }
    
    @Test
    public void moveBall(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        Ball b = g.getLevels().get(0).getBalls().get(0);
        b.move();
        assertEquals(1000 / 2 + 5, g.getLevels().get(0).getBalls().get(0).getX());
        assertEquals(795, g.getLevels().get(0).getBalls().get(0).getY());
    }
    
    @Test
    public void CollissionDetectionPallet(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        Pallet p = g.getLevels().get(0).getPallets().get(0);
        p.setSpeed(1);
        p.setDx(1); // normaal via key event
        p.setX(874);
        assertEquals(874, p.getX());
        p.moveRight();
        p.moveRight();
        assertEquals(875, p.getX());
        p.setX(0);
        p.setDx(-1); //normaal via key event
        p.moveLeft();
        assertEquals(0, p.getX());
    }
    
    @Test
    public void CollissionDetectionMeerderePallet(){
        List<User> players = new ArrayList<>(Arrays.asList(me, otherMe, anotherMe));
        Game g = new MultiPlayerGame(players, 1000, 1000, 2, easy);
        Pallet p1 = g.getLevels().get(0).getPallets().get(0);
        Pallet p2 = g.getLevels().get(0).getPallets().get(1);
        
        p1.setSpeed(1);
        p1.setDx(1); // normaal via key event
        
        p2.setSpeed(1);
        p2.setDx(1); // normaal via key event
        
        p1.setX(563);
        p1.moveRight();
        assertEquals(563, p1.getX());
        
        p2.setX(688);
        p2.setDx(-1); // normaal via key event
        p2.moveLeft();
        assertEquals(688, p2.getX());
    }
    
    @Test
    public void testBallCollissionWithPalletInMiddle(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        Ball b = g.getLevels().get(0).getBalls().get(0);
        b.setY(885);
        b.setSpeed(1);
        b.setDy(1);
        b.setDx(1);
        b.move();
        b.move();
        assertEquals(885, b.getY()); 
        assertEquals(1, b.getLastUserThatTouchedMe());
    }
    
    @Test
    public void testBallCollissionWithPalletInOnLeftSide(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        Ball b = g.getLevels().get(0).getBalls().get(0);
        b.setX(464);
        b.setY(885);
        b.setSpeed(5);
        b.setDx(5);
        b.setDy(5);
        b.move();
        b.move();
        assertEquals(883, b.getY());
        assertEquals(472, b.getX());
    }
    
    @Test
    public void testBallCollissionWithPalletInOnLeftBorder(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        Ball b = g.getLevels().get(0).getBalls().get(0);
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
        Ball b = g.getLevels().get(0).getBalls().get(0);
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
        Level l = g.getLevels().get(0);
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
        Level l = g.getLevels().get(0);
        Ball b = l.getBalls().get(0);
        BrickRow br = g.getLevels().get(0).getRowsOfBricks().get(4);
        Brick brick = br.getBricksOnRow().get(0);
        b.setX(250);
        b.setY(515);
        b.setSpeed(2);
        b.setDy(-2); 
        b.setDx(2);
        b.move(); // x 252 y 513
        b.move(); // x 253 y 516
        assertEquals(516, b.getY());
        assertEquals(253, b.getX());
        assertFalse(br.getBricksOnRow().contains(brick));
        assertEquals(10, l.getScore());
    }
    
    @Test
    public void testBallCollidingWithTopBoundary(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        Level l = g.getLevels().get(0);
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
        Level l = g.getLevels().get(0);
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
        Level l = g.getLevels().get(0);
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
        Level l = g.getLevels().get(0);
        Ball b = l.getBalls().get(0);
        b.setX(15);
        b.setY(985);
        b.setSpeed(2);
        b.setDy(2); 
        b.setDx(2);
        b.move(); // x 13 y 798 -> x 500 y 800
        b.move(); // x 502 y 798
        assertEquals(798, b.getY());
        assertEquals(502, b.getX());
        assertEquals(2, g.getLives());
    }
    
    @Test
    public void testBallTouchedPowerUp(){
        Game g = new SinglePlayerGame(me, 1000, 1000, easy);
        Level l = g.getLevels().get(0);
        Ball b = l.getBalls().get(0);
        BrickRow lastRow = l.getRowsOfBricks().get(l.getRowsOfBricks().size() - 1);
        Brick brick = lastRow.getBricksOnRow().get(lastRow.getBricksOnRow().size() - 1);
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
