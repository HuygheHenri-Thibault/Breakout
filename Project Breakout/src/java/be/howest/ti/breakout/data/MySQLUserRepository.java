/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import be.howest.ti.breakout.domain.game.Guest;
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
public class MySQLUserRepository implements UserRepository {
    public static final String FIELD_ID = "id";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_XP = "exp";
    public static final String FIELD_LEVEL = "level";
    public static final String FIELD_BIO = "bio";
    public static final String FIELD_SINGLEPLAYERHIGHSCORE = "spHighscore";
    public static final String FIELD_TOTALSCORE = "totalHighscore";
    public static final String FIELD_GEMS = "gems";
    public static final String FIELD_COINS = "coins";
    
    private static final String GET_ALL_USERS = "SELECT * FROM breakout.user WHERE id > 4";
    private static final String GET_USER_WITH_ID = "SELECT * FROM breakout.user WHERE id = ?";
    private static final String GET_USER_WITH_USERNAME = "SELECT * FROM breakout.user WHERE username like ?";
    private static final String GET_TOP_10_TOTALSCORES_FROM_USERS = "SELECT * FROM breakout.user WHERE id > 4 ORDER BY totalHighscore DESC LIMIT 10";
    private static final String ADD_USER = "INSERT INTO breakout.user (username, password, email) VALUES(?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM breakout.user WHERE id = ? AND username = ? AND password = ?";
    private static final String UPDATE_TOTALSCORE_USER = "UPDATE breakout.user SET totalHighscore = ? WHERE id = ?";
    private static final String UPDATE_XP_USER = "UPDATE breakout.user SET exp = ? WHERE id = ?";
    private static final String UPDATE_LEVEL_USER = "UPDATE breakout.user SET level = ? WHERE id = ?";
    
    protected MySQLUserRepository() {
    }
    
    @Override
    public List<User> getAllUsers() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_USERS)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<User> users = new ArrayList<>();
                while(rs.next()) {
                    users.add(this.createUser(rs));
                }
                return users;
            }
            
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't get all users", ex);
        }
    }

    @Override
    public User getUserWithId(int id) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_USER_WITH_ID)) {
            
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                User userWithId = null;
                while(rs.next()) {
                    userWithId = this.createUser(rs);
                }
                return userWithId;
            }
            
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't user with id " + id, ex);
        }
    }
    
    @Override
    public User getUserWithUsername(String username) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_USER_WITH_USERNAME)) {
            
            stmt.setString(1, username);
            try(ResultSet rs = stmt.executeQuery()) {
                User userWithUsername = null;
                if(rs.next()) {
                    userWithUsername = this.createUser(rs);
                }
                return userWithUsername;
            }
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get user with username", ex);
        }
    }
    
    @Override
    public Guest getGuest(int id) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_USER_WITH_ID)) {
            
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                Guest guest = null;
                if(rs.next()) {
                    String username = rs.getString(FIELD_USERNAME);
                    guest = new Guest(username);
                }
                return guest;
            }
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get guest", ex);
        }
    }

    @Override
    public void addUser(User u) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(ADD_USER)) {
            
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getHashPassword());
            stmt.setString(3, u.getEmail());
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't add user", ex);
        }
    }

    @Override
    public void deleteUser(User u) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE_USER)) {
            
            stmt.setInt(1, u.getUserId());
            stmt.setString(2, u.getUsername());
            stmt.setString(3, u.getHashPassword());
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't delete user", ex);
        }
    }

    @Override
    public void updateUserStringField(int userId, String field, String value) {
        String QUERY = "UPDATE breakout.user SET "+field+" = ? WHERE id = ?";
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(QUERY)) {
                        
            stmt.setString(1, value);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't update user field specified", ex);
        }
    }

    @Override
    public void updateUserIntField(int userId, String field, int value) {
        String QUERY = "UPDATE breakout.user SET "+field+" = ? WHERE id = ?";
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(QUERY)) {
                        
            stmt.setInt(1, value);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't update user field specified", ex);
        }
    }

    @Override
    public void updateUserTotalScore(User u) {
        try(Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_TOTALSCORE_USER)){
            stmt.setInt(1, u.getTotalScore());
            stmt.setInt(2, u.getUserId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't update total score for specific user", ex);        
        }
    }
    
    @Override
    public void updateUserXP(User u) {
        try(Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_XP_USER)){
            stmt.setInt(1, u.getXP());
            stmt.setInt(2, u.getUserId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't update exp for specific user", ex);        
        }
    }
    
    @Override
    public void updateUserLevel(User u) {
        try(Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_LEVEL_USER)){
            stmt.setInt(1, u.getLevel());
            stmt.setInt(2, u.getUserId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't update level for specific user", ex);       
        }
    }

    @Override
    public List<User> getTop10TotalScores() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_USERS)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<User> users = new ArrayList<>();
                while(rs.next()) {
                    users.add(this.createUser(rs));
                }
                return users;
            }
            
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't get top 10 total scores from users", ex);
        }
    }
    
    private User createUser(ResultSet rs) throws SQLException{
        int id = rs.getInt(FIELD_ID);
        String email = rs.getString(FIELD_EMAIL);
        String username = rs.getString(FIELD_USERNAME);
        String password = rs.getString(FIELD_PASSWORD);
        int XP = rs.getInt(FIELD_XP);
        int lvl = rs.getInt(FIELD_LEVEL);
        String bio = rs.getString(FIELD_BIO);
        int spHighscore = rs.getInt(FIELD_SINGLEPLAYERHIGHSCORE);
        int totalScore = rs.getInt(FIELD_TOTALSCORE);
        int gems = rs.getInt(FIELD_GEMS);
        int coins = rs.getInt(FIELD_COINS);
        String profilePic = rs.getString("profilePic");
        return new User(id, username, password, email, XP, lvl, bio, spHighscore, totalScore, gems, coins, profilePic);
    }
}
