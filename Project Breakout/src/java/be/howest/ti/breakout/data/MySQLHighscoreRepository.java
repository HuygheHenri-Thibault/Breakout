package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import be.howest.ti.breakout.domain.game.MultiPlayerHighscore;
import be.howest.ti.breakout.domain.game.Player;
import be.howest.ti.breakout.domain.game.SinglePlayerHighscore;
import be.howest.ti.breakout.domain.game.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import be.howest.ti.breakout.util.BreakoutException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Henri
 */
public class MySQLHighscoreRepository implements HighscoreRepository {

    private static final String FIELD_USERID = "user_id";
    private static final String FIELD_HIGHSCORE = "highscore";

    private static final String GET_ALL_SINGLEPLAYERHIGHSCORES = "SELECT username, spHighscore FORM breakout.user";
    private static final String GET_USERS_SINGLEPLAYERHIGHSCORES = "SELECT username, spHighscore FROM breakout.userhighscore WHERE user_id = ?";
    private static final String UPDATE_SINGLEPLAYERHIGHSCORE = "UPDATE breakout.user SET spHighscore = ? WHERE username = ?";
    private static final String DELETE_HIGHSCORE = "DELETE * FROM breakout.userhighscore WHERE user_id = ? AND highscore = ?";
    private static final String GET_ALL_MULTIPLAYER_SCORES = "select * from multiplayerhighscore";
    private static final String GET_ALL_PLAYERS_FROM_MULTIPLAYER = "select * from multiplayerscores where multiplayerID = ?";
    private static final String INSERT_MULTIPLAYER_SCORE = "INSERT INTO multiplayerhighscore(totalScore) values(?)";
    private static final String INSERT_PLAYER_SCORE_FROM_MULTIPLAYER = "INSERT INTO multiplayerscores(username, multiplayerID, score) values(?, ?, ?)";

    @Override
    public List<SinglePlayerHighscore> getAllSingleplayerHighscores() {
        try (Connection con = MySQLConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_ALL_SINGLEPLAYERHIGHSCORES)) {

            try (ResultSet rs = stmt.executeQuery()) {
                List<SinglePlayerHighscore> spHighscores = new ArrayList<>();
                while (rs.next()) {
                    User player = Repositories.getUserRepository().getUserWithUsername(rs.getString("username"));
                    int spHighscore = rs.getInt("spHighscore");
                    SinglePlayerHighscore sph = new SinglePlayerHighscore(player, spHighscore);
                }
                return spHighscores;
            }

        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't get all single player highscores", ex);
        }
    }

    @Override
    public SinglePlayerHighscore getUserHighscore(User u) {
        try (Connection con = MySQLConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_USERS_SINGLEPLAYERHIGHSCORES)) {

            stmt.setInt(1, u.getUserId());
            try (ResultSet rs = stmt.executeQuery()) {
                SinglePlayerHighscore sph = null;
                if (rs.next()) {
                    User player = Repositories.getUserRepository().getUserWithUsername(rs.getString("username"));
                    int spHighscore = rs.getInt("spHighscore");
                    sph = new SinglePlayerHighscore(player, spHighscore);
                }
                return sph;
            }

        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't get the single player highscore of that user", ex);
        }
    }

    @Override
    public void updateSinglePlayerHighscore(SinglePlayerHighscore sph) {
        if (sph.getUser() != null) {
            if (sph.getScore() > Repositories.getUserRepository().getUserWithUsername(sph.getUser().getUsername()).getSinglePlayerScore()) {
                try (Connection con = MySQLConnection.getConnection();
                        PreparedStatement stmt = con.prepareStatement(UPDATE_SINGLEPLAYERHIGHSCORE)) {

                    stmt.setInt(1, sph.getScore());
                    stmt.setString(2, sph.getUser().getUsername());
                    stmt.executeUpdate();

                } catch (SQLException ex) {
                    throw new BreakoutException("Couldn't update the singleplayer highscore", ex);
                }
            }
        }
    }

    @Override
    public List<MultiPlayerHighscore> getAllMultiplayerScores() {
        try (Connection con = MySQLConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_ALL_MULTIPLAYER_SCORES)) {
            try (ResultSet rs = stmt.executeQuery()) {
                List<MultiPlayerHighscore> mpScores = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    SinglePlayerHighscore sph = this.getIndividualScore(id);
                    Map<Player, Integer> playerList = new HashMap<>();
                    playerList.put(sph.getUser(), sph.getScore());
                    MultiPlayerHighscore mph = new MultiPlayerHighscore(id, playerList);
                    mpScores.add(mph);
                }
                return mpScores;
            }
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't get all multi player highscores", ex);
        }
    }

    private SinglePlayerHighscore getIndividualScore(int id) {
        try (Connection con = MySQLConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_ALL_PLAYERS_FROM_MULTIPLAYER)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                SinglePlayerHighscore sph = null;
                while (rs.next()) {
                    User player = Repositories.getUserRepository().getUserWithUsername(rs.getString("username"));
                    int individualScore = rs.getInt("score");
                    sph = new SinglePlayerHighscore(player, individualScore);
                }
                return sph;
            }
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't get all the individual scores from multiplayer", ex);
        }
    }

    @Override
    public int insertScoreIntoMultiplayerScores(int totalscore) {
        try (Connection con = MySQLConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(INSERT_MULTIPLAYER_SCORE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, totalscore);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            return generatedKey;
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't add the totalscore", ex);
        }
    }

    @Override
    public void insertPlayerScoresForMultiplayer(Player player, int multiplayerID, int score) {
        try (Connection con = MySQLConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(INSERT_PLAYER_SCORE_FROM_MULTIPLAYER)) {
            stmt.setString(1, player.getName());
            stmt.setInt(2, multiplayerID);
            stmt.setInt(3, score);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't add the player scores", ex);
        }
    }
}
