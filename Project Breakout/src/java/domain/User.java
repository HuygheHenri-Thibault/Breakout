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
    private String bio;
    private int score;
    
    public User(int id, String username, String password, String email, int lvl, String bio) {
        this.userId = id;
        this.email = email;
        this.username = username;
        this.hashPassword = password; //HASH THIS!!!
        this.level = lvl;
        this.bio = bio;
        this.score = 0;
    }
    
    public User(String username, String password, String email, int lvl, String bio) {
        this(-1, username, password, email, lvl, bio);
    }
    public User(String username, String password, String email, int lvl) {
        this(username, password, email, lvl, null);
    }
    public User(String username, String password, String email) {
        this(username, password, email, -1);
    }
    public User(String username, String password) {
        this(username, password, null);
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

    public String getBio() {
        return bio;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
