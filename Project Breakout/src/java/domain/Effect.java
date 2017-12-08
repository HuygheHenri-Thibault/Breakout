/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Henri
 */
public class Effect {
    private int id;
    private String type;
    private String name;
    private int duration;
    private String iconPath;
    private String description;
    
    public Effect(int id, String type, String name, int duration, String iconPath, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.duration = duration;
        this.iconPath = iconPath;
        this.description = description;
    }
//    public Effect(String type, String name, int duration, String iconPath, String description) { 
//        this(-1, type, name, duration, iconPath, description);
//    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getDescription() {
        return description;
    }
    
}
