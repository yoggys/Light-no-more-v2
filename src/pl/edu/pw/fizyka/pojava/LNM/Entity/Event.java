package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;

public class Event 
{
    public enum eventType {CHEST, FIGHT , DOOR};    

    eventType evType;
    public static ArrayList<Someone> enemys = new ArrayList<Someone>();
    public int posX;

    public Event(eventType evType, int posX)
    {
        this.evType = evType;
        this.posX = posX;
        if(evType == eventType.FIGHT)
        {
            enemys.add(new Someone(50, 10, "wolf" , "Resources/Entity/wolf.png", new Vector2D(-150, -300)));
            enemys.add(new Someone(50, 10, "wolf" , "Resources/Entity/wolf.png", new Vector2D(-150, -300)));
        }
    }
}