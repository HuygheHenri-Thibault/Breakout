/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Effect;
import java.util.List;

/**
 *
 * @author Henri
 */
public interface EffectRepository {
    public List<Effect> getAllEffects();
    public Effect getEffectWithName(String name);
    public Effect getEffectWithId(int id);
    public void addEffect(Effect e);
    public void deleteEffect(Effect e);
}
