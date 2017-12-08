/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import data.util.MySQLConnection;
import domain.Effect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.BreakoutException;

/**
 *
 * @author Henri
 */
public class MySQLEffectRepository implements EffectRepository {
    private static final String FIELD_ID = "id";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DURATION = "duration";
    private static final String FIELD_ICON = "icon";
    private static final String FIELD_DESCRIPTION = "description";
    
    private static final String GET_ALL_EFFECTS = "SELECT * FROM breakout.effects";
    private static final String GET_EFFECT_WITH_NAME = "SELECT * FROM breakout.effects WHERE name is like ?";
    private static final String GET_EFFECT_WITH_ID = "SELECT * FROM breakout.effects WHERE id = ?";
    private static final String ADD_EFFECT = "INSERT INTO breakout.effects(type, name, duration, icon, description) VALUES(?, ?, ?, ?, ?)";
    private static final String DELETE_EFFECT = "DELETE FROM breakout.effect WHERE id = ?";
    
    @Override
    public List<Effect> getAllEffects() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_EFFECTS)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<Effect> effects = new ArrayList<>();
                while(rs.next()) {
                    int id = rs.getInt(FIELD_ID);
                    String type = rs.getString(FIELD_TYPE);
                    String name = rs.getString(FIELD_NAME);
                    int duration = rs.getInt(FIELD_DURATION);
                    String iconPath = rs.getString(FIELD_ICON);
                    String description = rs.getString(FIELD_DESCRIPTION);
                    effects.add(new Effect(id, type, name, duration, iconPath, description));
                }
                return effects;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get all effects", ex);
        }
    }

    @Override
    public Effect getEffectWithName(String name) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_EFFECT_WITH_NAME)) {
            
            stmt.setString(1, name);
            
            try(ResultSet rs = stmt.executeQuery()) {
                Effect e = null;
                while(rs.next()) {
                    int id = rs.getInt(FIELD_ID);
                    String type = rs.getString(FIELD_TYPE);
                    int duration = rs.getInt(FIELD_DURATION);
                    String iconPath = rs.getString(FIELD_ICON);
                    String description = rs.getString(FIELD_DESCRIPTION);
                    e = new Effect(id, type, name, duration, iconPath, description);
                }
                return e;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get effect with name", ex);
        }
    }

    @Override
    public Effect getEffectWithId(int id) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_EFFECT_WITH_ID)) {
            
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()) {
                Effect e = null;
                while(rs.next()) {
                    String type = rs.getString(FIELD_TYPE);
                    String name = rs.getString(FIELD_NAME);
                    int duration = rs.getInt(FIELD_DURATION);
                    String iconPath = rs.getString(FIELD_ICON);
                    String description = rs.getString(FIELD_DESCRIPTION);
                    e = new Effect(id, type, name, duration, iconPath, description);
                }
                return e;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get effect with id", ex);
        }
    }

    @Override
    public void addEffect(Effect e) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(ADD_EFFECT)) {
            
            stmt.setString(1, e.getType());
            stmt.setString(2, e.getName());
            stmt.setInt(3, e.getDuration());
            stmt.setString(4, e.getIconPath());
            stmt.setString(5, e.getDescription());
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't add effect", ex);
        }
    }

    @Override
    public void deleteEffect(Effect e) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE_EFFECT)) {
            
            stmt.setInt(1, e.getId());
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't delete effect with id", ex);
        }
    }
    
}
