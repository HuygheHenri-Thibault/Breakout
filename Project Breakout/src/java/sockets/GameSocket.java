/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import domain.Game;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;


import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
/**
 *
 * @author Henri
 */
@ServerEndpoint("/gamepoint")
public class GameSocket {
    Map<Session, Game> sessionGame = new HashMap<>();
    
    int score = 0;
    int width = 750;
    int height = 400;
    int levens = 3;
    int players = 1;
    
    // Game game = new Game(score, height, width, levens, aantal_speleres);
     // = new Game(score, height, width, levens, players); dit moet nog in een init na data van de game van frontend te krijgen
    
    @OnOpen
    public void onOpen(Session s) {
        Game newGame = null;
        sessionGame.put(s, newGame);
    }
    
    @OnClose
    public void onClose(Session s) {
        sessionGame.get(s).stopGame();
    }

    @OnMessage
    public String onMessage(String message) {
        JsonObject jObj = new JsonObject(message);
        switch 
        // check type of message (gameStart, posistion, gameEnd,..)
        return null;
    }
}
