/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.util.MySQLConnection;
import domain.User;
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
public class MySQLUserRepository implements UserRepository {
    private static final String FIELD_ID = "id";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_PASSWORD = "password";
    
    private static final String GET_ALL_USERS = "SELECT * FROM breakout.user";
    private static final String GET_USER_WITH_ID = "SELECT * FROM breakout.user WHERE id = ?";
    private static final String GET_USER_WITH_USERNAME = "SELECT * FROM breakout.user WHERE username like ?";
    private static final String ADD_USER = "INSERT INTO breakout.user (username, password, email) VALUES(?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM breakout.user WHERE id = ? AND username = ? AND password = ?";
    
    protected MySQLUserRepository() {
    }
    
    @Override
    public List<User> getAllUsers() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_USERS)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<User> users = new ArrayList<>();
                while(rs.next()) {
                    int id = rs.getInt(FIELD_ID);
                    String email = rs.getString(FIELD_EMAIL);
                    String username = rs.getString(FIELD_USERNAME);
                    String password = rs.getString(FIELD_PASSWORD);
                    users.add(new User(id, username, password, email));
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
                if(rs.next()) {
                    String email = rs.getString(FIELD_EMAIL);
                    String username = rs.getString(FIELD_USERNAME);
                    String password = rs.getString(FIELD_PASSWORD);
                    userWithId = new User(id, username, password, email);
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
                    int id = rs.getInt(FIELD_ID);
                    String email = rs.getString(FIELD_EMAIL);
                    String password = rs.getString(FIELD_PASSWORD);
                    userWithUsername = new User(id, username, password, email);
                }
                return userWithUsername;
            }
        } catch(SQLException ex) {
            throw new BreakoutException("Couldn't get user with username", ex);
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
    
}
