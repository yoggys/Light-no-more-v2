package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;
import java.util.Random;

import pl.edu.pw.fizyka.pojava.LNM.GameState.ChestScene;
import pl.edu.pw.fizyka.pojava.LNM.Player.Player;

public class Event 
{
    public enum eventType {CHEST, FIGHT , DOOR};    

    public eventType evType;
    public ArrayList<Someone> enemys = new ArrayList<Someone>();
    public int posX;
    public boolean isActive = true;
    
    

    Random rand = new Random(System.currentTimeMillis());

    public Event(eventType evType, int posX)
    {
        this.evType = evType;
        this.posX = posX;
        if(evType == eventType.FIGHT)
        {
            for(int i =0; i < 2; i++)
            {
                enemys.add( new Someone( Player.enemysBase.get( ( rand.nextInt(Player.enemysBase.size() - 1) ) ) ) );
            }
        }
    }
}