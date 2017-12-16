/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import data.util.MySQLConnection;
import domain.BrickData;
import java.sql.Connection;
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
public class MySQLBrickRepository implements BrickRepository {
    private static final String FIELD_ID = "id";
    private static final String FIELD_COLOR = "skin";
    private static final String FIELD_BASE_LENGTH = "baseLength";
    private static final String FIELD_HITS = "hits";
    private static final String FIELD_SCORE = "score";
    
    private static final String GET_ALL_BRICKS = "SELECT * FROM breakout.bricks";
    private static final String ADD_BRICK = "INSERT INTO breakout.bricks (color, baseLength, hits, score) VALUES (?, ?, ?, ?)";
    private static final String DELETE_BRICK = "DELETE FROM breakout.bricks WHERE id = ?";
    
    @Override
    public List<BrickData> getAllBricks() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_BRICKS)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<BrickData> brickData = new ArrayList<>();
                while(rs.next()) {
                    int id = rs.getInt(FIELD_ID);
                    String color = rs.getString(FIELD_COLOR);
                    int baseLenght = rs.getInt(FIELD_BASE_LENGTH);
                    int baseHits = rs.getInt(FIELD_HITS);
                    int baseScore = rs.getInt(FIELD_SCORE);
                    brickData.add(new BrickData(id, color, baseLenght, baseHits, baseScore));
                }
                return brickData;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get all bricks", ex);
        }
    }

    @Override
    public void addBrick(BrickData b) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(ADD_BRICK)) {
            
            stmt.setString(1, b.getColor());
            stmt.setInt(2, b.getBaseLen());
            stmt.setInt(3, b.getBaseHits());
            stmt.setInt(4, b.getBaseScore());
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't add brick", ex);
        }
    }

    @Override
    public void deleteBrick(BrickData b) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(ADD_BRICK)) {
            
            stmt.setString(1, b.getColor());
            stmt.setInt(2, b.getBaseLen());
            stmt.setInt(3, b.getBaseHits());
            stmt.setInt(4, b.getBaseScore());
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't delete brick", ex);
        }
    }
    
}
