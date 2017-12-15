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
    private int originalLivesLeft;
    
    public EffectSuddenDeath(String name, int duration) {
        super(name, duration);
    }
    
    @Override
    public void activate() {
        System.out.println("activated sudden death");
        setRunning();
        originalLivesLeft = getThisLevel().getGame().getLivesLeftOriginally();
        getThisLevel().getGame().setLives(1);
        setT(new Timer());
        getT().schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated sudden death");
        getT().cancel();
        getThisLevel().getGame().setLives(originalLivesLeft);
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " sudden-death"; 
    }
}
