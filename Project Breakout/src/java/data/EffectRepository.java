/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import powerUps.Effect;

/**
 *
 * @author micha
 */
public interface EffectRepository {
    public List<Effect> getAllEffects();
    public Effect getEffect(int effectID);
    public List<Effect> getEffectOfPowerUp(int powerUpID);
}
