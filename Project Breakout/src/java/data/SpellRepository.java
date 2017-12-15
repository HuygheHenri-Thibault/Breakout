/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import spells.BijvoegelijkNaamwoord;
import spells.Woord;
import spells.ZelfstandigNaamwoord;

/**
 *
 * @author micha
 */
public interface SpellRepository {
    public List<Woord> getAllWords();
    public List<ZelfstandigNaamwoord> getHardcodedZelfstandigeNaamwoorden(); //voor testen
    public List<BijvoegelijkNaamwoord> getHardCodedBijvoegelijkeNaamwoorden();
}
