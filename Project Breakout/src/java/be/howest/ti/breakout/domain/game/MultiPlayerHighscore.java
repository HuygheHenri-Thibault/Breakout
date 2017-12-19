package be.howest.ti.breakout.domain.game;


import java.util.HashMap;
import java.util.Map;


public class MultiPlayerHighscore{
    
    private Map<User, Integer> playerList = new HashMap<>();
    
    public MultiPlayerHighscore(Map<User, Integer> userList) {
        playerList = userList;
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

//    @Override
//    public String toString() {
//        return "MultiPlayerHighscore{" + "totalScore=" + totalScore + ", playerList=" + playerList + '}';
//    }
}
