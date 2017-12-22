/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

/**
 *
 * @author Henri
 */
public class Repositories {
    private final static UserRepository USER_REPOSITORY = new MySQLUserRepository();
    private final static BrickRepository BRICK_REPOSITORY = new MySQLBrickRepository();
    private final static HighscoreRepository HIGHSCORE_REPOSITORY = new MySQLHighscoreRepository();
    private final static PowerUpOrDownRepository POWERUPDOWN_REPOSITORY = new MySQLPowerUpOrDownRepository();
    private final static EffectRepository EFFECT_REPOSITORY = new MySQLEffectRepository();
    private final static SpellRepository SPELL_REPOSITORY = new MySQLSpellRepository();
    private final static FieldEffectRepository FIELDEFFECT_REPOSITORY = new MySQLFieldEffectRepository();
    private final static DifficultyRepository DIFFICULTY_REPOSITORY = new MySQLDifficultyRepository();
    
    public static UserRepository getUserRepository() {
        return USER_REPOSITORY;
    }

    public static BrickRepository getBrickRepository() {
        return BRICK_REPOSITORY;
    }

    public static HighscoreRepository getHighscoreRepository() {
        return HIGHSCORE_REPOSITORY;
    }

    public static PowerUpOrDownRepository getPowerUpDownRepository() {
        return POWERUPDOWN_REPOSITORY;
    }

    public static EffectRepository getEffect_Repository() {
        return EFFECT_REPOSITORY;
    }
    
    public static SpellRepository getSpellRepository() {
        return SPELL_REPOSITORY;
    }
    
    public static FieldEffectRepository getFieldEffectRepository(){
        return FIELDEFFECT_REPOSITORY;
    }

    public static DifficultyRepository getDifficultyRepository() {
        return DIFFICULTY_REPOSITORY;
    }
}
