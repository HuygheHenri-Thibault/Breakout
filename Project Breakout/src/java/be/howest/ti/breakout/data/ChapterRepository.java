
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.domain.game.Chapter;

public interface ChapterRepository {
    
    public Chapter getChapterById(int id);
    
}
