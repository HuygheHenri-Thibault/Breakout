/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import java.sql.Connection;
import be.howest.ti.breakout.data.util.MySQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import be.howest.ti.breakout.domain.powerUps.AllPowerUps;
import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;
import be.howest.ti.breakout.util.BreakoutException;

/**
 *
 * @author Henri
 */
public class MySQLPowerUpOrDownRepository implements PowerUpOrDownRepository {
    private static final String FIELD_ID = "id";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DURATION = "duration";
    private static final String FIELD_ICON = "icon";
    private static final String FIELD_DESCRIPTION = "description";
    
    private static final String GET_ALL_POWERUPS = "SELECT * FROM powerupsdowns";
    private static final String GET_POWERUP_WITH_NAME = "SELECT * FROM powerupsdowns WHERE name is like ?";
    private static final String GET_POWERUP_WITH_ID = "SELECT * FROM powerupsdowns WHERE id = ?";
    private static final String ADD_POWERUP = "INSERT INTO powerupsdowns(type, name, duration, icon, description) VALUES(?, ?, ?, ?, ?)";
    private static final String DELETE_POWERUP = "DELETE FROM powerupsdowns WHERE id = ?";
    
    @Override
    public AllPowerUps getAllPowerUpsAndDowns() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_POWERUPS)) {
            
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
        PowerUpOrDown p = new PowerUpOrDown(id, name, type, duration, iconPath, description);
        List<Effect> allEffectOfPowerUp = Repositories.getEffect_Repository().getEffectOfPowerUp(id); 
        for (Effect effect : allEffectOfPowerUp) {
            p.addEffect(effect);
        }
        return p;
    }

    @Override
    public PowerUpOrDown getPowerUpOrDownWithName(String name) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_POWERUP_WITH_NAME)) {
            
            stmt.setString(1, name);
            
            try(ResultSet rs = stmt.executeQuery()) {
                PowerUpOrDown p = null;
                while(rs.next()) {
                    p = givePowerUpOrDown(rs);
                }
                return p;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get powerUp with name", ex);
        }
    }

    @Override
    public PowerUpOrDown getPowerUpOrDownWithId(int id) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_POWERUP_WITH_ID)) {
            
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
            PreparedStatement stmt = con.prepareStatement(ADD_POWERUP)) {
            
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
            PreparedStatement stmt = con.prepareStatement(DELETE_POWERUP)) {
            
            stmt.setInt(1, p.getId());
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't delete effect with id", ex);
        }
    }
}
