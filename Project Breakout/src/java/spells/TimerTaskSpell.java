/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spells;

import java.util.TimerTask;

/**
 *
 * @author micha
 */
public class TimerTaskSpell extends TimerTask{
    
    private final Spell spell;

    private final long start;
    private final long end;

    public TimerTaskSpell(Spell spell) {
       this.spell = spell;
        this.start = System.currentTimeMillis();
        this.end = start + (5 * 1000);
        // 5 veranderen naar spell.getCoolDown
    }

    @Override
    public void run() {
         if (System.currentTimeMillis() > end) {
            spell.setReady();
            cancel();
        }
    }
    
}
