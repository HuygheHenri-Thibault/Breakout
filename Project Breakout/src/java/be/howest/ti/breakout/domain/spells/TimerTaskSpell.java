/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.spells;

import java.util.TimerTask;

/**
 *
 * @author micha
 */
public class TimerTaskSpell extends TimerTask {

    private final Spell spell;

    public TimerTaskSpell(Spell spell) {
        this.spell = spell;
        // 5 veranderen naar spell.getCoolDown
    }

    @Override
    public void run() {
        spell.setCoolDown(spell.getCooldown() - 1);
        if (spell.getCooldown() < 0) {
            spell.setReady();
            spell.setCoolDown(spell.getOriginalCoolDown());
            cancel();
        }
    }

}
