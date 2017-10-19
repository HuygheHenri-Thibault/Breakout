/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.User;
import java.util.List;

/**
 *
 * @author Henri
 */
public class MySQLUserRepository implements UserRepository {
    private static final String FIELD_ID = "id";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_PASSWORD = "password";
    
    private static final String GET_ALL_USERS = "SELECT * FROM breakout.user";
    private static final String GET_USER_WITH_ID = "SELECT * FROM breakout.user WHERE id = ?";
    private static final String ADD_USER = "INSERT INTO breakout.user (username, password) VALUES(?, ?)";
    private static final String DELETE_USER = "DELETE FROM breakout.user WHERE id = ? AND username = ? AND password = ?";
    
    protected MySQLUserRepository() {
    }
    
    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserWithId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addUser(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
