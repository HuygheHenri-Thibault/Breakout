
package be.howest.ti.breakout.domain.game;

public class Chapter {
    
    int id;
    String text;
    String name;
    int campaignID;

    public Chapter(int id, String text, String name, int campaignID) {
        this.id = id;
        this.text = text;
        this.name = name;
        this.campaignID = campaignID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(int campaignID) {
        this.campaignID = campaignID;
    }
}
