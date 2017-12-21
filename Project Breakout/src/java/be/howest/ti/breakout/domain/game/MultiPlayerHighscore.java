package be.howest.ti.breakout.domain.game;


import java.util.HashMap;
import java.util.Map;


public class MultiPlayerHighscore{
    
    private Map<Player, Integer> playerList = new HashMap<>();
    private int gameID;
    
    public MultiPlayerHighscore(int gameID, Map<Player, Integer> playerList) {
        this.playerList = playerList;
        this.gameID = gameID;
    }
    
    public MultiPlayerHighscore(Map<Player, Integer> userList) {
        this(0, userList);
    }

    public int getTotalScore() {
        int som = 0;
        for (Map.Entry<Player, Integer> entry : playerList.entrySet()) {
            som += entry.getValue();
        }
        return som;
    }

    public Map<Player, Integer> getPlayerList() {
        return playerList;
    }
    
    

    public void setPlayerList(Map<Player, Integer> playerList) {
        this.playerList = playerList;
    }
    
    public int getGameID() {
        return gameID;
    }
}
