
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.domain.game.MultiPlayerHighscore;
import be.howest.ti.breakout.domain.game.Player;
import be.howest.ti.breakout.domain.game.SinglePlayerHighscore;
import be.howest.ti.breakout.domain.game.User;
import java.util.List;

public interface HighscoreRepository {
    public List<SinglePlayerHighscore> getTop10SingleplayerHighscores();
    public SinglePlayerHighscore getUserHighscore(User u);
    public void updateSinglePlayerHighscore(SinglePlayerHighscore sph);
    public List<MultiPlayerHighscore> getTop10MultiplayerScores();
    public int insertScoreIntoMultiplayerScores(int totalscore);
    public void insertPlayerScoresForMultiplayer(Player player, int multiplayerID, int score);
}
