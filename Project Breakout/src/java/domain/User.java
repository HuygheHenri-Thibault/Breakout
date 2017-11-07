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
    private String email;
    private String username;
    private String hashPassword;
    private int level;
    
    public User(int id, String username, String password, String email, int lvl) {
        this.userId = id;
        this.email = email;
        this.username = username;
        this.hashPassword = password; //HASH THIS!!!
        this.level = lvl;
    }
    
    public User(int id, String username, String password, String email) {
        this(id, username, password, email, -1);
    }
    
    public User(String username, String password, String email) {
        this(-1, username, password, email);
    }
    
    public User(String username, String password) {
        this(-1, username, password, null);
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

    public String getEmail() {
        return email;
    }

    public int getLevel() {
        return level;
    }
}
