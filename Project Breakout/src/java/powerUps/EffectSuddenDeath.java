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
public class EffectSuddenDeath extends Effect{

    public EffectSuddenDeath(int duration) {
        super(duration);
    }
    
    @Override
    public void activate() {
        System.out.println("activated sudden death");
        setRunning();
        getThisLevel().getGame().setLives(1);
        setT(new Timer());
        getT().schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated sudden death");
        getT().cancel();
        getThisLevel().getGame().setLives(getThisLevel().getGame().getStartLives());
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " sudden-death"; 
    }
}
