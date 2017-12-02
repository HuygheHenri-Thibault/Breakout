/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Brick;
import domain.Circle;
import domain.Level;
import domain.Pallet;
import domain.Rectangle;
import domain.Sprite;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author micha
 */
public class PowerUpBulletTime extends PowerUpOrDown {
    private Pallet palletOfUser;
    private Timer t;

    public PowerUpBulletTime(Level level, Brick brick) {
        super(level, brick, "Bullet Time", 10, "Makes pallet quicker");
    }
    
//    @Override
//    public void show() {
//        brick.getLevel().getPowerUpsShownOnScreen().add(this);
//    }

    @Override
    public void activate() {
        palletOfUser = level.getUserPallet(ballActivatedPower.getLastUserThatTouchedMe());

//        level.getPowerUpsShownOnScreen().remove(this);
//        level.resetPowerUps();
//        level.setPowerUpActive(this);
        
        setRunning();
        
        palletOfUser.setSpeed(palletOfUser.getSpeed() + 1);
        
        System.out.println("activated");
      
        t = new Timer();
        t.schedule(new TimerTaskPower(this), 0, 1000);
    }
    
    @Override
    public void deActivate() {
        System.out.println("deactivated");
        t.cancel();
        palletOfUser.setSpeed(palletOfUser.getSpeed() - 1);
        level.setPowerUpActive(new NoPower());
    }



    

}
