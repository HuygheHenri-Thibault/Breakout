/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import be.howest.ti.breakout.domain.game.GameDifficulty;
import be.howest.ti.breakout.util.BreakoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author micha
 */
public class MySQLDifficultyRepository implements DifficultyRepository {

    private static final String GET_ALL_DIFFICULTIES = "SELECT * FROM difficulty";
    private static final String GET_DIFFICULTY_BY_NAME = "SELECT * FROM difficulty where name = ?";
    private static final String GET_DIFFICULTY_BY_ID = "SELECT * FROM difficulty where id = ?";

    @Override
    public List<GameDifficulty> getAllDifficulties() {
        try (Connection con = MySQLConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(GET_ALL_DIFFICULTIES);
                ResultSet rs = prep.executeQuery()) {

            List<GameDifficulty> difficulties = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                float ratiochange = rs.getFloat("ratiochange");
                int changeBricks = rs.getInt("changeBricks");
                GameDifficulty difficulty = new GameDifficulty(name, ratiochange, changeBricks);
                difficulties.add(difficulty);
            }
            return difficulties;
        } catch (SQLException ex) {
            throw new BreakoutException("couldn't get all difficulties", ex);
        }
    }
    
    @Override
    public GameDifficulty getDifficultyByID(int id) {
        try (Connection con = MySQLConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(GET_DIFFICULTY_BY_ID);) {
            prep.setInt(1, id);

            try (ResultSet rs = prep.executeQuery()) {
                GameDifficulty difficulty = null;
                while (rs.next()) {
                    String name = rs.getString("name");
                    float ratiochange = rs.getFloat("ratiochange");
                    int changeBricks = rs.getInt("changeBricks");
                    difficulty = new GameDifficulty(name, ratiochange, changeBricks);
                }
                return difficulty;
            }
        } catch (SQLException ex) {
            throw new BreakoutException("couldn't get all difficulties", ex);
        }
    }

    @Override
    public GameDifficulty getDifficultyByName(String name) {
        try (Connection con = MySQLConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(GET_DIFFICULTY_BY_NAME);) {
            prep.setString(1, name);

            try (ResultSet rs = prep.executeQuery()) {
                GameDifficulty difficulty = null;
                while (rs.next()) {
                    float ratiochange = rs.getFloat("ratiochange");
                    int changeBricks = rs.getInt("changeBricks");
                    difficulty = new GameDifficulty(name, ratiochange, changeBricks);
                }
                return difficulty;
            }
        } catch (SQLException ex) {
            throw new BreakoutException("couldn't get all difficulties", ex);
        }
    }

}
