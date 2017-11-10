/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import domain.Ball;
import domain.Brick;
import domain.Game;
import domain.Pallet;
import domain.Rectangle;
import domain.Sprite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.json.simple.parser.ParseException;
import util.BreakoutException;
/**
 *
 * @author Henri
 */
@ServerEndpoint("/gamepoint")
public class GameSocket {
    Map<Session, Game> sessionGame = new HashMap<>();
    
    int width = 750;
    int height = 400;
    int levens = 3;
    int players = 1;

    @OnMessage
    public String onMessage(String message, Session in) {
        JSONParser jparse = new JSONParser();
        try {
            JSONObject obj = (JSONObject) jparse.parse(message);
            
            switch ((String)obj.get("type")) {
                case "startGame":
                    startGame(in, obj);
                    JSONObject posistions = makeJSONPosistionObj(sessionGame.get(in).getLevels().get(0).getAllEntities());
                    // return sessionGame.get(in).getLevels().get(0).getAllEntities().toString();
                    return posistions.toJSONString();
//                    return "Started game";
                case "":
                    break;
                default:
                    return "No match found for type of message..";
            }
            
            return sessionGame.get(in).getLevels().get(0).getAllEntities().toString();
        } catch(ParseException ex) {
            throw new BreakoutException("Couldn't process message", ex);
        }
    }
    
    private void startGame(Session in, JSONObject obj) {
        int aantalPlayers = Integer.parseInt((String)obj.get("playerAmount"));
        sessionGame.replace(in, new Game(height, width, levens, aantalPlayers));
    }
    
    private JSONObject makeJSONPosistionObj(List<Sprite> listOfSprites) {
        JSONObject resultObj = new JSONObject();
        int itr = 0;
        for(Sprite aSpirte : listOfSprites) {
            JSONObject spriteJSON = makeSpriteJSONObj(aSpirte, resultObj);
            resultObj.put(itr+"", spriteJSON);
            itr++;
        }
        return resultObj;
    }

    private JSONObject makeSpriteJSONObj(Sprite aSpirte, JSONObject resultObj) {
        JSONObject spriteObj = new JSONObject();
        
        String typeOfSprite = aSpirte.toString();
        spriteObj.put("type", typeOfSprite);
        
        int xPos = aSpirte.getX();
        int yPos = aSpirte.getY();
        spriteObj.put("x", xPos);
        spriteObj.put("y", yPos);
        
        setDimension(typeOfSprite, aSpirte, spriteObj);
        return spriteObj;
    }
    private void setDimension(String typeOfSprite, Sprite aSpirte, JSONObject spriteObj) {
        switch (typeOfSprite) {
            case "Pallet":
                Pallet pallet = (Pallet)aSpirte;
                spriteObj.put("width", Math.round(pallet.getLength())); // x
                spriteObj.put("height", Math.round(pallet.getHeight())); // y
                break;
            case "Ball":
                Ball ball = (Ball)aSpirte;
                spriteObj.put("radius", ball.getRadius()); // r
                break;
            case "Brick":
                Brick brick = (Brick)aSpirte;
                spriteObj.put("width", Math.round(brick.getLength())); // x
                spriteObj.put("height", Math.round(brick.getHeight())); // y
                break;
            default:
                spriteObj.put("width", -1); // x
                spriteObj.put("height", -1); // y
        }
    }
    
    // Game game = new Game(score, height, width, levens, aantal_speleres);
     // = new Game(score, height, width, levens, players); dit moet nog in een init na data van de game van frontend te krijgen
    
    @OnOpen
    public void onOpen(Session s) {
        Game newGame = null;
        sessionGame.put(s, newGame);
    }
    
    @OnClose
    public void onClose(Session s) {
        //sessionGame.get(s).stopGame();
    }
}
