/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import domain.Game;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
/**
 *
 * @author Henri
 */
@ServerEndpoint("/gamepoint")
public class GameSocket {
    
    int score = 0;
    int width = 750;
    int height = 400;
    int levens = 3;
    int players = 1;
    
    // Game game = new Game(score, height, width, levens, aantal_speleres);
    Game game = new Game(score, height, width, levens, players); // dit moet nog in een init na data van de game van frontend te krijgen

    @OnMessage
    public String onMessage(String message) {
        return null;
    }
    
}
