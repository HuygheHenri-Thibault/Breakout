/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import be.howest.ti.breakout.domain.effects.Effect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import be.howest.ti.breakout.domain.spells.BijvoegelijkNaamwoord;
import be.howest.ti.breakout.domain.spells.Woord;
import be.howest.ti.breakout.domain.spells.ZelfstandigNaamwoord;
import be.howest.ti.breakout.util.BreakoutException;

/**
 *
 * @author micha
 */
public class MySQLSpellRepository implements SpellRepository{
    
    private static final String SELECT_ALL_WORDS = "SELECT * FROM breakout.spells";
    private static final String SELECT_ALL_NOUNS = "SELECT * FROM breakout.spells where type = 'N';";
    private static final String SELECT_ALL_ADJACTIVES = "SELECT * FROM breakout.spells where type = 'A';";
    
    public MySQLSpellRepository() {}

    @Override
    public List<Woord> getAllWords() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SELECT_ALL_WORDS);
            ResultSet rs = prep.executeQuery()){
            
            List<Woord> woorden = new ArrayList<>();
            while(rs.next()){
                
            }
            return woorden;
        } catch (SQLException ex) {
            throw new BreakoutException("couldn't get all woorden", ex);
        }
    }

    //voor test
    @Override
    public List<ZelfstandigNaamwoord> getHardcodedZelfstandigeNaamwoorden() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SELECT_ALL_NOUNS);
            ResultSet rs = prep.executeQuery();){
            
                List<ZelfstandigNaamwoord> nouns = new ArrayList<>();
                while(rs.next()){
                    String name = rs.getString("name");
                    int damage = rs.getInt("damage");
                    int effectID = rs.getInt("effectid");
                    Effect effect = Repositories.getEffect_Repository().getEffect(effectID);
                    ZelfstandigNaamwoord noun = new ZelfstandigNaamwoord(name, damage, effect);
                    nouns.add(noun);
                }
                return nouns;
               
        } catch (SQLException ex) {
            throw new BreakoutException("couldn't find nouns", ex);
        }
    }
    
    @Override
    public List<BijvoegelijkNaamwoord> getHardCodedBijvoegelijkeNaamwoorden() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SELECT_ALL_ADJACTIVES);
            ResultSet rs = prep.executeQuery();){
            
                List<BijvoegelijkNaamwoord> adjectives = new ArrayList<>();
                while(rs.next()){
                    String name = rs.getString("name");
                    int damage = rs.getInt("damage");
                    int effectID = rs.getInt("effectid");
                    Effect effect = Repositories.getEffect_Repository().getEffect(effectID);
                    BijvoegelijkNaamwoord adjective = new BijvoegelijkNaamwoord(name, damage, effect);
                    adjectives.add(adjective);
                }
                return adjectives;
               
        } catch (SQLException ex) {
            throw new BreakoutException("couldn't find adjectives", ex);
        }
    }
    
}
