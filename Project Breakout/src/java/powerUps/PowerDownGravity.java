/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Brick;
import domain.Level;

/**
 *
 * @author micha
 */
public class PowerDownGravity extends PowerUpOrDown{

    public PowerDownGravity(Level level, Brick theBrick, String name, int duration, String description) {
        super(level, theBrick, name, duration, description);
    }
    
//    @Override
//    public void show() {
//       brick.getLevel().getPowerUpsShownOnScreen().add(this);
//    }

    @Override
    public void activate() {
//        level.getPowerUpsShownOnScreen().remove(this);
//        level.resetPowerUps();
//        level.setPowerUpActive(this);
        
        setRunning();

        ballActivatedPower.setDx(0);
        ballActivatedPower.setDy(ballActivatedPower.getSpeed() / 2);
        

        System.out.println("activated");
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated");
        level.setPowerUpActive(new NoPower());
    }
    
}
