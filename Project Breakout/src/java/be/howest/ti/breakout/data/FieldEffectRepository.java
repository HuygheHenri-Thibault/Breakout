/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.domain.fieldeffects.FieldEffect;
import java.util.List;

/**
 *
 * @author micha
 */
public interface FieldEffectRepository {
    public List<FieldEffect> getAllFieldEffects();
}
