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
import powerUps.AllPowerUps;
import powerUps.PowerDownGravity;
import powerUps.PowerDownShrunk;
import powerUps.PowerDownSlowed;
import powerUps.PowerDownSuddenDeath;
import powerUps.PowerUpBulletTime;
import powerUps.PowerUpDoubleTrouble;
import powerUps.PowerUpOrDown;
import powerUps.PowerUpScaffolds;
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
    public AllPowerUps getAllPowerUpsAndDowns() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_EFFECTS)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                AllPowerUps allPowerUps = new AllPowerUps();
                while(rs.next()) {
                    createPowerUpOrDown(rs, allPowerUps);
                }
                return allPowerUps;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get all powerUps", ex);
        }
    }
    
    private void createPowerUpOrDown(ResultSet rs, AllPowerUps powerUpList) throws SQLException{
        PowerUpOrDown p = givePowerUpOrDown(rs);
        if(p.getType().equals("U")){
            powerUpList.addToPowerUpList(p);
        } else {
            powerUpList.addToPowerDownList(p);
        }
    }
    
    private PowerUpOrDown givePowerUpOrDown(ResultSet rs) throws SQLException{
        int id = rs.getInt(FIELD_ID);
        String type = rs.getString(FIELD_TYPE);
        String name = rs.getString(FIELD_NAME);
        int duration = rs.getInt(FIELD_DURATION);
        String iconPath = rs.getString(FIELD_ICON);
        String description = rs.getString(FIELD_DESCRIPTION);
        PowerUpOrDown p = null;
         
        switch(name){
            case"Bullet time":
                p = new PowerUpBulletTime(id, name, type, duration, iconPath, description);
                break;
            case"Scaffolds":
                p = new PowerUpScaffolds(id, name, type, duration, iconPath, description);
                break;
            case"Double Trouble":
                p = new PowerUpDoubleTrouble(id, name, type, duration, iconPath, description);
                break;
            case"Gravity":
                p = new PowerDownGravity(id, name, type, duration, iconPath, description);
                break;
            case"Slowed":
                p = new PowerDownSlowed(id, name, type, duration, iconPath, description);
                break;
            case"Shrunk":
                p = new PowerDownShrunk(id, name, type, duration, iconPath, description);
                break;
            case"Sudden death":
                p = new PowerDownSuddenDeath(id, name, type, duration, iconPath, description);
                break;
        }
        
        return p;
    }

    @Override
    public PowerUpOrDown getPowerUpOrDownWithName(String name) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_EFFECT_WITH_NAME)) {
            
            stmt.setString(1, name);
            
            try(ResultSet rs = stmt.executeQuery()) {
                PowerUpOrDown p = null;
                while(rs.next()) {
                    p = givePowerUpOrDown(rs);
                }
                return p;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get effect with name", ex);
        }
    }

    @Override
    public PowerUpOrDown getPowerUpOrDownWithId(int id) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_EFFECT_WITH_ID)) {
            
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()) {
                PowerUpOrDown p = null;
                while(rs.next()) {
                    p = givePowerUpOrDown(rs);
                }
                return p;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get effect with id", ex);
        }
    }

    @Override
    public void addPowerUpOrDown(PowerUpOrDown p) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(ADD_EFFECT)) {
            
            stmt.setString(1, p.getType());
            stmt.setString(2, p.getName());
            stmt.setInt(3, p.getDuration());
            stmt.setString(4, p.getIconPath());
            stmt.setString(5, p.getDescription());
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't add effect", ex);
        }
    }

    @Override
    public void deletePowerUpOrDown(PowerUpOrDown p) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE_EFFECT)) {
            
            stmt.setInt(1, p.getId());
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't delete effect with id", ex);
        }
    }
}
