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

    public PowerUpDoubleTrouble(int id, String name, String type, int duration, String iconPath, String description) {
        super(id, name, type, duration, iconPath, description);
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
    
    @Override
    public String toString() {
        return super.toString() + " double-trouble"; 
    }
}
