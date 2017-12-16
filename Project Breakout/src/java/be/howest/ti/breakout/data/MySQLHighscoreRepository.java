/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import be.howest.ti.breakout.domain.game.Highscore;
import be.howest.ti.breakout.domain.game.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import be.howest.ti.breakout.util.BreakoutException;

/**
 *
 * @author Henri
 */
public class MySQLHighscoreRepository implements HighscoreRepository {
    private static final String FIELD_USERID = "user_id";
    private static final String FIELD_HIGHSCORE = "highscore";
    
    private static final String GET_ALL_HIGHSCORES = "SELECT * FORM breakout.userhighscore";
    private static final String GET_USERS_HIGHSCORES = "SELECT * FROM breakout.userhighscore WHERE user_id = ?";
    private static final String ADD_HIGHSCORE = "INSERT INTO breakout.userhighscore (user_id, highscore) VALUES (?, ?)";
    private static final String UPDATE_USER = "UPDATE breakout.userhighscore SET highscore = ? WHERE user_id = ?";
    private static final String DELETE_HIGHSCORE = "DELETE * FROM breakout.userhighscore WHERE user_id = ? AND highscore = ?";
    
    @Override
    public List<Highscore> getAllHighscores() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_HIGHSCORES)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<Highscore> highscores = new ArrayList<>();
                while(rs.next()) {
                    int highscore = rs.getInt(FIELD_HIGHSCORE);
                    int userid = rs.getInt(FIELD_USERID);
                    User u = Repositories.getUserRepository().getUserWithId(userid);
                    highscores.add(new Highscore(u, highscore));
                }
                return highscores;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get all highscores", ex);
        }
    }

    @Override
    public Highscore getUserHighscore(User u) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_USERS_HIGHSCORES)) {
            
            stmt.setInt(1, u.getUserId());
            try(ResultSet rs = stmt.executeQuery()) {
                Highscore h = null;
                if(rs.next()) {
                    int highscore = rs.getInt(FIELD_HIGHSCORE);
                    h = new Highscore(u, highscore);
                }
                return h;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get the highscore of that user", ex);
        }
    }

    @Override
    public void addHighscore(Highscore h) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_USERS_HIGHSCORES)) {
            
            stmt.setInt(1, h.getUserWithHighscore().getUserId());
            stmt.setInt(2, h.getHighscore());
            stmt.executeUpdate();            
            
        } catch(SQLException ex) {
            System.out.println(ex);
            if(!updateHighscore(h)) {
                throw new BreakoutException("Couldn't add that user", ex);
            }
        }
    }
    
    public boolean updateHighscore(Highscore h) {
        try(Connection con = MySQLConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(UPDATE_USER)) {
                
                stmt.setInt(1, h.getHighscore());
                stmt.setInt(2, h.getUserWithHighscore().getUserId());
                stmt.executeUpdate();
                return true;
                
            } catch(SQLException exception) {
                throw new BreakoutException("Couldn't update that user", exception);
            }
    }

    @Override
    public void removeHighscore(Highscore h) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE_HIGHSCORE)) {
            
            stmt.setInt(1, h.getUserWithHighscore().getUserId());
            stmt.setInt(2, h.getHighscore());
            stmt.executeUpdate();            
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't delete that user", ex);
        }
    }
}
