/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.effects.EffectQuickerPallet;
import be.howest.ti.breakout.domain.effects.EffectExtraBall;
import be.howest.ti.breakout.domain.effects.EffectStraightDownBall;
import be.howest.ti.breakout.domain.effects.EffectBiggerPallet;
import be.howest.ti.breakout.domain.effects.EffectDragonFireBall;
import be.howest.ti.breakout.domain.effects.EffectExtraLifePoint;
import be.howest.ti.breakout.domain.effects.EffectSmallerPallet;
import be.howest.ti.breakout.domain.effects.EffectSlowerPallet;
import be.howest.ti.breakout.domain.effects.EffectOneLifeLeft;
import be.howest.ti.breakout.domain.effects.EffectShadow;
import be.howest.ti.breakout.domain.effects.EffectWebs;
import be.howest.ti.breakout.util.BreakoutException;

/**
 *
 * @author micha
 */
public class MySQLEffectRepository implements EffectRepository{
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DURATION = "duration";
    
    private static final String GET_ALL_EFFECTS = "SELECT * FROM breakout.effect";
    private static final String GET_EFFECT_BY_ID = "SELECT * FROM breakout.effect where id = ?";
    private static final String GET_EFFECT_OF_POWERUP = "SELECT e.* FROM breakout.effect e join powereffect pe on e.id = pe.effectid  join powerupsdowns p on pe.powerid = p.id where p.id =  ?";

    @Override
    public List<Effect> getAllEffects() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_EFFECTS)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<Effect> allEffect = new ArrayList<>(); 
                while(rs.next()) {
                     Effect effect = createEffect(rs);
                     allEffect.add(effect);
                }
                return allEffect;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get all powerUps", ex);
        }
    }
    
    private Effect createEffect(ResultSet rs) throws SQLException{
        String name = rs.getString(FIELD_NAME);
        int duration = rs.getInt(FIELD_DURATION);
        return createEffectClassBasedOnName(name, duration);
    }
    
    private Effect createEffectClassBasedOnName(String name, int duration){
        Effect effect = null;
        switch(name){
            case"quickerPallet":
                effect = new EffectQuickerPallet(name, duration);
                break;
            case"biggerPallet":
                effect = new EffectBiggerPallet(name, duration);
                break;
            case"extraBall":
                effect = new EffectExtraBall(name, duration);
                break;
            case"straightDownBall":
                effect = new EffectStraightDownBall(name, duration);
                break;
            case"slowerPallet":
                effect = new EffectSlowerPallet(name, duration);
                break;
            case"littlePallet":
                effect = new EffectSmallerPallet(name, duration);
                break;
            case"oneLifeLeft":
                effect = new EffectOneLifeLeft(name, duration);
                break;
            case"extraLife":
                effect = new EffectExtraLifePoint(name, duration);
                break;
            case"fireBall":
                effect = new EffectDragonFireBall(name, duration);
                break;
            case"webs":
                effect = new EffectWebs(name, duration);
                break;
            case"shadow":
                effect = new EffectShadow(name, duration);
                break;
            default:
                break;
        }
        return effect;
    }

    @Override
    public Effect getEffect(int effectID) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_EFFECT_BY_ID)) {
            
            stmt.setInt(1, effectID);
            
            try(ResultSet rs = stmt.executeQuery()) {
                Effect e = null;
                while(rs.next()) {
                    e = createEffect(rs);
                }
                return e;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get effect with name", ex);
        }
    }

    @Override
    public List<Effect> getEffectOfPowerUp(int powerUpID) {
    try(Connection con = MySQLConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(GET_EFFECT_OF_POWERUP)) {
            
            stmt.setInt(1, powerUpID);
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<Effect> allEffectsOfPowerUp = new ArrayList<>();
                while(rs.next()) {
                    Effect effect = createEffect(rs);
                    allEffectsOfPowerUp.add(effect);
                }
                return allEffectsOfPowerUp;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get effect with name", ex);
        }
    }
    
}
