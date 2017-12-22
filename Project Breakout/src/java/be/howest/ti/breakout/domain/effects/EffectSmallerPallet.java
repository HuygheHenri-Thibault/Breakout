/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import java.util.Timer;

/**
 *
 * @author micha
 */
public final class EffectSmallerPallet extends Effect{

    public EffectSmallerPallet(String name, String description, int duration) {
        super(name, description, duration);
    }

    @Override
    public void activate() {
        setRunning();
        
        playerPallet.setLength((int) (playerPallet.getLength() - (playerPallet.getOriginalLenght()* 0.2)));
        System.out.println("activated shrunk");
        
        TimerEffect = new Timer();
        TimerEffect.schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated shrunk");
        TimerEffect.cancel();
        playerPallet.setLength((int) (playerPallet.getLength() + (playerPallet.getOriginalLenght()* 0.2)));
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " shrunk"; 
    }
}
