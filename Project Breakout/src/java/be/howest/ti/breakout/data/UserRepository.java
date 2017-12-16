/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import domain.User;
import java.util.List;

/**
 *
 * @author Henri
 */
public interface UserRepository {
    public List<User> getAllUsers();
    public User getUserWithId(int id);
    public User getUserWithUsername(String username);
    public void addUser(User u);
    public void deleteUser(User u);
    public void updateUserStringField(int userId, String field, String value);
    public void updateUserIntField(int userId, String field, int value);
}
