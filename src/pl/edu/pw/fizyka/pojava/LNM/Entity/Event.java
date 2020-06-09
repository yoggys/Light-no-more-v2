package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;
import java.util.Random;

import pl.edu.pw.fizyka.pojava.LNM.Player.Player;

public class Event 
{
    public enum eventType {CHEST, FIGHT , TEXT, LEAVEDOOR};    

    public eventType evType;
    public ArrayList<Someone> enemys;
    public int posX;
    public boolean isActive = true;
    public String text;
    
    

    Random rand = new Random(System.currentTimeMillis());

    public Event(eventType evType, int posX)
    {
        this.evType = evType;
        this.posX = posX;
        if(evType == eventType.FIGHT)
        {
            enemys = new ArrayList<Someone>();
            for(int i =0; i < 2; i++)
            {
                enemys.add( new Someone( Player.enemysBase.get( ( rand.nextInt(Player.enemysBase.size() - 1) ) ) ) );
            }
        }
    }

    void setText(String text)
    {
        this.text = text;
    }
}