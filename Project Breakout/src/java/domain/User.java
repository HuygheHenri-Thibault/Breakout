/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Henri
 */
public class User {
    private int userId;
    private String username;
    private String hashPassword;
    
    public User(int id, String username, String password) {
        this.userId = id;
        this.username = username;
        this.hashPassword = password; //HASH THIS!!!
    }
    
    public User(String username, String password) {
        this(-1, username, password);
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getHashPassword() {
        return hashPassword;
    }
}
