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
    private final static BrickRepository BRICK_REPOSITORY = new MySQLBrickRepository();
    private final static HighscoreRepository HIGHSCORE_REPOSITORY = new MySQLHighscoreRepository();
    private final static EffectRepository EFFECT_REPOSITORY = new MySQLEffectRepository();

    public static UserRepository getUserRepository() {
        return USER_REPOSITORY;
    }

    public static BrickRepository getBrickRepository() {
        return BRICK_REPOSITORY;
    }

    public static HighscoreRepository getHighscoreRepository() {
        return HIGHSCORE_REPOSITORY;
    }

    public static EffectRepository getEffectRepository() {
        return EFFECT_REPOSITORY;
    }
}
