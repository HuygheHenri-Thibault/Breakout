
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import be.howest.ti.breakout.domain.game.Chapter;
import be.howest.ti.breakout.util.BreakoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLChapterRepository implements ChapterRepository{
    
    private static final String GET_CHAPTER_BY_ID = "SELECT * FROM breakout.story WHERE IDstory = ?";

    @Override
    public Chapter getChapterById(int id) {
        try(Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(GET_CHAPTER_BY_ID)){
            stmt.setInt(1, id);
            try(ResultSet res = stmt.executeQuery()){
                Chapter chapter = null;
                while (res.next()){
                    String text = res.getString("storyText");
                    String name = res.getString("storyName");
                    int campaignId = res.getInt("campaignId");
                    chapter = new Chapter(id, text, name, campaignId);
                }
                return chapter;
            }
        } catch (SQLException ex) {
            throw new BreakoutException("Couldn't get the chapter by id", ex);
        }
    }
    
}
