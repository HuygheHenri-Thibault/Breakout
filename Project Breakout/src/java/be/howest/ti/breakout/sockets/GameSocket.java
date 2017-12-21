/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.sockets;

import be.howest.ti.breakout.data.Repositories;
import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.game.Game;
import be.howest.ti.breakout.domain.game.GameDifficulty;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.Rectangle;
import be.howest.ti.breakout.domain.Shape;
import be.howest.ti.breakout.domain.game.Guest;
import be.howest.ti.breakout.domain.game.Player;
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
import be.howest.ti.breakout.util.BCrypt;
import be.howest.ti.breakout.util.BreakoutException;
import java.util.ArrayList;
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
                    return "";
                case "login":
                    loginUser(in, obj);
                    for (Player u : sessionGame.get(in).getPlayers()) {
                        System.out.println(u.getPlayerID());
                    }
                    //makeLevel(in);
                    //return createSpellsOfLevel(in).toJSONString();
                    return "";
                case "selectedSpells":
                    selectSpellOfUser(in, obj);
                    if(sessionGame.get(in).getLevelPlayedRightNow().areAllSpellsSelected()){
                        startLevel(in);
                        JSONObject startGameObj = new JSONObject();
                        startGameObj.put("type", "gameStarted");
                        return startGameObj.toJSONString();
                    }
                    return "";
//                case "startGame":
//                    startLevel(in);
//                    return makeJSONPosistionObj(sessionGame.get(in).getLevels().get(0).getAllEntities()).toJSONString();
                case "updateMe":
                    //System.out.println("updated");
                    return makeJSONPosistionObj(sessionGame.get(in).getLevels().get(0).getAllEntities()).toJSONString();
                case "gameInfo":
                    return makeJSONGameInfo(in).toJSONString();
                case "move":
                    movePalletToDirection(in, obj);
                    return "";
                case "spellActivate":
                    int playerID = Integer.parseInt((String) obj.get("player"));
                    Player player = sessionGame.get(in).getPlayers().get(playerID - 1);
                    Spell spell = sessionGame.get(in).getLevelPlayedRightNow().getSpellByPlayer(player);
                    spell.setReadyToCast();
                    return "";
                case "pause":
                    if(sessionGame.get(in).getLevelPlayedRightNow().isPaused()){
                        sessionGame.get(in).getLevelPlayedRightNow().unpauseLevel();
                    } else {
                        sessionGame.get(in).getLevelPlayedRightNow().pauseLevel();
                    }
                    return "";
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
        String dificulty = (String)obj.get("playerAmount");
        List<User> players = new ArrayList<>();
//        if(!username.equals("Guest")) {
//            User player = Repositories.getUserRepository().getUserWithUsername(username);
//            players.add(player)
//        }
//        
            // get dificulty from db
            sessionGame.replace(in, new Game(height, width, aantalPlayers, new GameDifficulty("easy", 0.2f, 1)));
    }
    
    private void loginUser(Session in, JSONObject obj){
        String username = (String) obj.get("username");
        String password = (String) obj.get("password");
        int playerID = Integer.parseInt( (String) obj.get("player"));
        User user  = Repositories.getUserRepository().getUserWithUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getHashPassword())) {
            sessionGame.get(in).replaceGuestByUser(playerID, user);
        } else {
            Guest guest = new Guest(playerID, "guest");
            sessionGame.get(in).replaceGuestByUser(playerID, guest);
        }
    }
    
    private void makeLevel(Session in){
        sessionGame.get(in).createNewLevel();
    }
    
    private JSONObject createSpellsOfLevel(Session in){
        JSONObject resultObj = new JSONObject();
        resultObj.put("type", "spells");
        Map<Player, List<Spell>> spellsChoices = sessionGame.get(in).getLevelPlayedRightNow().getAllSpellsChoices();
        for (Map.Entry<Player, List<Spell>> spellsOfUser : spellsChoices.entrySet()) {
            JSONObject spellNames = new JSONObject();
            for (int i = 0; i < spellsOfUser.getValue().size(); i++) {
                spellNames.put(i, spellsOfUser.getValue().get(i).getName());
            }
            resultObj.put("player " + spellsOfUser.getKey().getPlayerID(), spellNames);
        }
        return resultObj;
    }
    
    private void selectSpellOfUser(Session in, JSONObject jsonObject){
        int playerID = Integer.parseInt((String) jsonObject.get("player"));
        String spellName = (String) jsonObject.get("spell");
        Player u = sessionGame.get(in).getLevelPlayedRightNow().getPlayers().get(playerID - 1);
        Spell s = sessionGame.get(in).getLevelPlayedRightNow().getSpellofPlayerChoices(u, spellName);
        System.out.println(s.getName());
        sessionGame.get(in).getLevelPlayedRightNow().setPlayerSpell(u, s);
    }

    public void startLevel(Session in){
      sessionGame.get(in).getLevelPlayedRightNow().startLevel();
    } 
    
        
//    private void startGame(Session in, JSONObject obj) {
//        int aantalPlayers = Integer.parseInt((String)obj.get("playerAmount"));
//        if(aantalPlayers > 1){
//            sessionGame.replace(in, new MultiPlayerGame(height, width, aantalPlayers, new GameDifficulty("easy", 0.2f, 1)));
//        } else {
//            sessionGame.replace(in, new SinglePlayerGame(height, width, aantalPlayers, new GameDifficulty("easy", 0.2f, 1)));
//        }
//        makeLevel(in);
//        sessionGame.get(in).getLevelPlayedRightNow().startLevel();
//    }
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
                spriteObj.put("shown", pallet.IsVisible());
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
        Pallet playerPallet = sessionGame.get(in).getLevelPlayedRightNow().getPlayerPallet(playerID);
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
        //sessionGame.get(s).getLevelPlayedRightNow().endLevel();
    }
}
