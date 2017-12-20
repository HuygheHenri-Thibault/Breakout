package be.howest.ti.breakout.domain.game;


import java.util.HashMap;
import java.util.Map;


public class MultiPlayerHighscore{
    
    private Map<User, Integer> playerList = new HashMap<>();
    private int gameID;
    
    public MultiPlayerHighscore(int gameID, Map<User, Integer> userList) {
        playerList = userList;
        this.gameID = gameID;
    }
    
    public MultiPlayerHighscore(Map<User, Integer> userList) {
        this(0, userList);
    }

    public int getTotalScore() {
        int som = 0;
        for (Map.Entry<User, Integer> entry : playerList.entrySet()) {
            som += entry.getValue();
        }
        return som;
    }

    public Map<User, Integer> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Map<User, Integer> playerList) {
        this.playerList = playerList;
    }
    
    public int getGameID() {
        return gameID;
    }
}
