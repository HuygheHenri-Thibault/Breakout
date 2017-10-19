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
public interface UserRepository {
    public List<User> getAllUsers();
    public User getUserWithId(int id);
    public void addUser(User u);
    public void deleteUser(User u);
}
