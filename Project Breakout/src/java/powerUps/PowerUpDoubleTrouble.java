/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Brick;
import domain.Level;
import java.util.Timer;

/**
 *
 * @author micha
 */
public class PowerUpDoubleTrouble extends PowerUpOrDown{

    public PowerUpDoubleTrouble(Level level, Brick theBrick, String name, int duration, String description) {
        super(level, theBrick, name, duration, description);
    } 
    
//    @Override
//    public void show() {
//        brick.getLevel().getPowerUpsShownOnScreen().add(this);
//    }

    @Override
    public void activate() {
//        level.getPowerUpsShownOnScreen().remove(this);
//        level.resetPowerUps();
//        level.setPowerUpActive(this);
//        level.pause();
        setRunning();
        level.createExtraBall();
//        level.unpause();

        System.out.println("activated");
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated");
        level.getBalls().remove(level.getBalls().size() - 1);
        level.decrementLife();
        //level.decrementBallsOnScreen();
        level.setPowerUpActive(new NoPower());
    }
    
}
