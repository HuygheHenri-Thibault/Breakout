/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Henri
 */
public class Repositories {
    private final static UserRepository USER_REPOSITORY = new MySQLUserRepository();

    public static UserRepository getUser_Repository() {
        return USER_REPOSITORY;
    }
}
