/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.fieldeffects.FieldEffect;
import be.howest.ti.breakout.util.BreakoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class MySQLFieldEffectRepository implements FieldEffectRepository{

    private final String GET_ALL_FIELDEFFECT = "SELECT * FROM breakout.fieldeffect";
    
    @Override
    public List<FieldEffect> getAllFieldEffects() {
         try(Connection con = MySQLConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(GET_ALL_FIELDEFFECT);
            ResultSet rs = prep.executeQuery();){
            
                List<FieldEffect> fieldEffects = new ArrayList<>();
                while(rs.next()){
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int interval = rs.getInt("interval");
                    int effectID = rs.getInt("effectid");
                    Effect effect = Repositories.getEffect_Repository().getEffect(effectID);
                    FieldEffect fieldEffect = new FieldEffect(name, description, effect, interval);
                    fieldEffects.add(fieldEffect);
                }
                return fieldEffects;
               
        } catch (SQLException ex) {
            throw new BreakoutException("couldn't find all fieldeffects", ex);
        }
    }
    
}
