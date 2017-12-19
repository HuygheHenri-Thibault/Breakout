/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import be.howest.ti.breakout.domain.game.Highscore;
import be.howest.ti.breakout.domain.game.SinglePlayerHighscore;
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
    
    private static final String GET_ALL_SINGLEPLAYERHIGHSCORES = "SELECT username, spHighscore FORM breakout.user";
    private static final String GET_USERS_SINGLEPLAYERHIGHSCORES = "SELECT username, spHighscore FROM breakout.userhighscore WHERE user_id = ?";
    private static final String UPDATE_SINGLEPLAYERHIGHSCORE = "UPDATE breakout.user SET spHighscore = ? WHERE id = ?";
    private static final String DELETE_HIGHSCORE = "DELETE * FROM breakout.userhighscore WHERE user_id = ? AND highscore = ?";
    
    @Override
    public List<SinglePlayerHighscore> getAllSingleplayerHighscores() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_SINGLEPLAYERHIGHSCORES)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<SinglePlayerHighscore> spHighscores = new ArrayList<>();
                while(rs.next()) {
                    User player = Repositories.getUserRepository().getUserWithUsername(rs.getString("username"));
                    int spHighscore = rs.getInt("spHighscore");
                    SinglePlayerHighscore sph = new SinglePlayerHighscore(player, spHighscore);
                }
                return spHighscores;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get all single player highscores", ex);
        }
    }

    @Override
    public SinglePlayerHighscore getUserHighscore(User u) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_USERS_SINGLEPLAYERHIGHSCORES)) {
            
            stmt.setInt(1, u.getUserId());
            try(ResultSet rs = stmt.executeQuery()) {
                SinglePlayerHighscore sph = null;
                if(rs.next()) {
                    User player = Repositories.getUserRepository().getUserWithUsername(rs.getString("username"));
                    int spHighscore = rs.getInt("spHighscore");
                    sph = new SinglePlayerHighscore(player, spHighscore);
                }
                return sph;
            }
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get the single player highscore of that user", ex);
        }
    }

    @Override
    public void addSinglePlayerHighscore(SinglePlayerHighscore sph) {
        if (sph.getScore() > Repositories.getUserRepository().getUserWithUsername(sph.getUser().getUsername()).getSinglePlayerScore()){
            try(Connection con = MySQLConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(UPDATE_SINGLEPLAYERHIGHSCORE)) {

                stmt.setInt(1, sph.getScore());
                stmt.setString(2, sph.getUser().getUsername());
                stmt.executeUpdate();            

            } catch(SQLException ex) {
                throw new BreakoutException("Couldn't add the singleplayer highscore", ex);
            }
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
