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
public class PowerDownSuddenDeath extends PowerUpOrDown{
    private Timer t;

    public PowerDownSuddenDeath(Level level, Brick theBrick, String name, int duration, String description) {
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

        level.getGame().setLives(1);
        
        t = new Timer();
        t.schedule(new TimerTaskPower(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated");
        t.cancel();
        level.getGame().setLives(level.getGame().getStartLives());
        level.setPowerUpActive(new NoPower());
    }
    
}
