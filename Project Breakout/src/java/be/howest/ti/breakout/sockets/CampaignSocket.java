/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.sockets;

import be.howest.ti.breakout.util.BreakoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Fredr
 */
@ServerEndpoint("/campaignpoint")
public class CampaignSocket {

    @OnMessage
    public String onMessage(String message, Session in) {
        JSONParser jparse = new JSONParser();
        try {
            JSONObject obj = (JSONObject) jparse.parse(message);
     
            switch ((String)obj.get("type")) {
                case "campaignInfo":
                    break;
                default:
                    return null;
            }
        } catch(ParseException ex) {
            throw new BreakoutException("Couldn't process message", ex);
        }
    }
}
