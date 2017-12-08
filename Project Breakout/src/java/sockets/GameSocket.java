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
import domain.Shape;
import domain.SinglePlayerGame;
import domain.Sprite;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    //added for smartphone controller
    private static final Set<Session> activeSessions = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnMessage
    public String onMessage(String message, Session in) {
        JSONParser jparse = new JSONParser();
        try {
            JSONObject obj = (JSONObject) jparse.parse(message);
     
            switch ((String)obj.get("type")) { // moet herschreven worden -> visitor pattern
                case "startGame":
                    //System.out.println("started");
                    startGame(in, obj);
                    return makeJSONPosistionObj(sessionGame.get(in).getLevels().get(0).getAllEntities()).toJSONString();
                case "updateMe":
                    //System.out.println("updated");
                    return makeJSONPosistionObj(sessionGame.get(in).getLevels().get(0).getAllEntities()).toJSONString();
                case "gameInfo":
                    //System.out.println("gameInfo");
                    return makeJSONGameInfo(in).toJSONString();
                case "move":
                    movePalletToDirection(in, obj);
                default:
                    JSONObject resultObj = new JSONObject();
                    resultObj.put("type", "ERROR");
                    resultObj.put("Message", "No type found for that message.");
                    return resultObj.toJSONString();
            }
        } catch(ParseException ex) {
            throw new BreakoutException("Couldn't process message", ex);
        }
    }
    
    private void startGame(Session in, JSONObject obj) {
        int aantalPlayers = Integer.parseInt((String)obj.get("playerAmount"));
        sessionGame.replace(in, new SinglePlayerGame(height, width, aantalPlayers));
        sessionGame.get(in).getLevelPlayedRightNow().startLevel();
    }
    
    private JSONObject makeJSONGameInfo(Session in) {
        JSONObject resultObj = new JSONObject();
        resultObj.put("type", "gameInfo");
        resultObj.put("lives", sessionGame.get(in).getLives());
        resultObj.put("score", sessionGame.get(in).getScore());
        return resultObj;
    }
    
    private JSONObject makeJSONPosistionObj(List<Shape> listOfSprites) {
        JSONObject resultObj = new JSONObject();
        resultObj.put("type", "posistion");
        int itr = 0;
        for(Shape aSpirte : listOfSprites) {
            JSONObject spriteJSON = makeSpriteJSONObj(aSpirte, resultObj);
            resultObj.put(itr+"", spriteJSON);
            itr++;
        }
        return resultObj;
    }

    private JSONObject makeSpriteJSONObj(Shape aShape, JSONObject resultObj) {
        JSONObject spriteObj = new JSONObject();
        
        String spriteString[] = aShape.toString().split(" ");
        String typeOfSprite = spriteString[0];
        if(typeOfSprite.equals("Brick")) {
            String color = spriteString[1];
            spriteObj.put("color", color);
        }
        spriteObj.put("type", typeOfSprite);
        
        int xPos = aShape.getX();
        int yPos = aShape.getY();
        spriteObj.put("x", xPos);
        spriteObj.put("y", yPos);
        
        setDimension(typeOfSprite, aShape, spriteObj);
        return spriteObj;
    }
    private void setDimension(String typeOfSprite, Shape aSpirte, JSONObject spriteObj) {
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
            //boundaries
            case "Rectangle":
                Rectangle rect = (Rectangle) aSpirte;
                spriteObj.put("width", Math.round(rect.getLength())); // x
                spriteObj.put("height", Math.round(rect.getHeight())); // y
                break;
            default:
                spriteObj.put("width", -1); // x
                spriteObj.put("height", -1); // y
        }
    }
    
    private void movePalletToDirection(Session in, JSONObject obj){
        int playerID = Integer.parseInt((String) obj.get("player"));
        System.out.println(playerID);
        String direction = (String) obj.get("direction");
        System.out.println(direction);
        Pallet playerPallet = sessionGame.get(in).getLevelPlayedRightNow().getUserPallet(playerID);
        switch(direction){
            case "left":
                playerPallet.moveLeft();
                break;
            case "right":
                playerPallet.moveRight();
                break;
            case "stop":
                playerPallet.stopMoving();
                break;
        }
    }
    
    private void sendPosistionUpdate() {
        for(Map.Entry<Session, Game> entry : sessionGame.entrySet()) {
            Session key = entry.getKey();
            Game value = entry.getValue();
            try {
                key.getBasicRemote().sendText(makeJSONPosistionObj(value.getLevels().get(0).getAllEntities()).toJSONString());
            } catch(IOException ex) {
                throw new BreakoutException("Couldn't update posistion in game", ex);
            }
            
        }
    }
    
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
