package be.howest.ti.breakout.domain.game;

public class SinglePlayerHighscore{
    
    private User user;
    private int score;

    public SinglePlayerHighscore(User user, int score) {
        this.user = user;
        this.score = score;
    }
 
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SinglePlayerHighscore{" + "user=" + user + ", score=" + score + '}';
    }


}
