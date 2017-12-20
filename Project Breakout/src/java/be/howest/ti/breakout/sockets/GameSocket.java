/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.sockets;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.game.Game;
import be.howest.ti.breakout.domain.game.GameDifficulty;
import be.howest.ti.breakout.domain.game.MultiPlayerGame;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.Rectangle;
import be.howest.ti.breakout.domain.Shape;
import be.howest.ti.breakout.domain.game.SinglePlayerGame;
import be.howest.ti.breakout.domain.game.User;
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
import be.howest.ti.breakout.domain.spells.Spell;
import be.howest.ti.breakout.util.BreakoutException;
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
    private static final Set<Session> ACTIVE_SESSIONS = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnMessage
    public String onMessage(String message, Session in) {
        JSONParser jparse = new JSONParser();
        try {
            JSONObject obj = (JSONObject) jparse.parse(message);
     
            switch ((String)obj.get("type")) { // moet herschreven worden -> visitor pattern toch niet want dit zijn geen java objecten
                case "playerAmount":
                    makeGame(in, obj);
                    makeLevel(in);
                    return createSpellsOfLevel(in).toJSONString();
                    // spel wordt gemaakt met dit aantal spelers
                    // level wordt ook gemaakt
                    // spells worden gemaakt en doorgestuurd naar de front-end met msg type "showSpells"
                    //return "";
                case "selectedSpells":
                    selectSpellOfUser(in, obj);
                    //hier megeven welke spells gekozen zijn door de spelers
                    return "";
                case "startGame":
                    //System.out.println("started");
                    startLevel(in, obj);
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
    
    private void makeGame(Session in, JSONObject obj){
        int aantalPlayers = Integer.parseInt((String)obj.get("playerAmount"));
        if(aantalPlayers > 1){
            sessionGame.replace(in, new MultiPlayerGame(height, width, aantalPlayers, new GameDifficulty("easy", 0.2f, 1)));
        } else {
            sessionGame.replace(in, new SinglePlayerGame(height, width, aantalPlayers, new GameDifficulty("easy", 0.2f, 1)));
        }
    }
    
    private void makeLevel(Session in){
        sessionGame.get(in).createNewLevel();
    }
    
    private JSONObject createSpellsOfLevel(Session in){
        JSONObject resultObj = new JSONObject();
        Map<User, List<Spell>> spellsChoices = sessionGame.get(in).getLevelPlayedRightNow().getAllSpellsChoices();
        for (Map.Entry<User, List<Spell>> spellsOfUser : spellsChoices.entrySet()) {
            for (Spell spell : spellsOfUser.getValue()) {
                resultObj.put("player " + spellsOfUser.getKey().getUserId(), spell.getName());
            }
        }
        return resultObj;
    }
    
    private void selectSpellOfUser(Session in, JSONObject jsonObject){
        int playerID = Integer.parseInt((String) jsonObject.get("player"));
        int spellID = Integer.parseInt((String) jsonObject.get("spellName"));
        User u = sessionGame.get(in).getLevelPlayedRightNow().getPlayers().get(playerID - 1);
        Spell s = sessionGame.get(in).getLevelPlayedRightNow().getAllSpellChoicesOfUser(u).get(spellID);
        sessionGame.get(in).getLevelPlayedRightNow().setUserSpell(u, s);
    }
    
    private void startLevel(Session in, JSONObject obj) {
        int aantalPlayers = Integer.parseInt((String)obj.get("playerAmount"));
        if(aantalPlayers > 1){
            sessionGame.replace(in, new MultiPlayerGame(height, width, aantalPlayers, new GameDifficulty("easy", 0.2f, 1)));
        } else {
            sessionGame.replace(in, new SinglePlayerGame(height, width, aantalPlayers, new GameDifficulty("easy", 0.2f, 1)));
        }
        makeLevel(in);
        sessionGame.get(in).getLevelPlayedRightNow().startLevel();
    }
    //new
    public void startGame(Session in){
      sessionGame.get(in).getLevelPlayedRightNow().startLevel();
    } 
//    
//    private JSONObject makeJSONSpells(List<Spell> spells){
//        JSONObject resultObj = new JSONObject();
//        int itr = 1;
//        for (Spell spell : spells) {
//           resultObj.put("spell " + itr, spell.getName());
//           itr++;
//        }
//        return resultObj;
//    }
    //
    
    private JSONObject makeJSONGameInfo(Session in) {
        JSONObject resultObj = new JSONObject();
        resultObj.put("type", "gameInfo");
        resultObj.put("lives", sessionGame.get(in).getLives());
        resultObj.put("score", sessionGame.get(in).getTotalGameScore());
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
        spriteObj.put("type", spriteString[0]);
        if(spriteString.length > 1) {
            String icon = spriteString[1];
            spriteObj.put("icon", icon);
        }

        int xPos = aShape.getX();
        int yPos = aShape.getY();
        spriteObj.put("x", xPos);
        spriteObj.put("y", yPos);
        
        setDimension(spriteString[0], aShape, spriteObj);
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
            case "Rectangle":
                Rectangle rect = (Rectangle) aSpirte;
                spriteObj.put("width", Math.round(rect.getLength())); // x
                spriteObj.put("height", Math.round(rect.getHeight())); // y
                break;
            case "Powerup":
                spriteObj.put("width", 20); // x // FIXME!!!!!!
                spriteObj.put("height", 20); // y // FIXME!!!!!!
                break;
            case "FieldEffect":
                
                break;
            default:
                spriteObj.put("width", -1); // x
                spriteObj.put("height", -1); // y
        }
    }
    
    private void movePalletToDirection(Session in, JSONObject obj){
        int playerID = Integer.parseInt((String) obj.get("player"));
        String direction = (String) obj.get("direction");
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
        sessionGame.put(s, null);
    }
    
    @OnClose
    public void onClose(Session s) {
        //sessionGame.get(s).stopGame();
    }
}
