package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;

import pl.edu.pw.fizyka.pojava.LNM.GameState.ChestScene;

public class Event 
{
    public enum eventType {CHEST, FIGHT , DOOR};    

    public eventType evType;
    public ArrayList<Someone> enemys = new ArrayList<Someone>();
    public int posX;
    public boolean isActive = true;
    
    Skill skillBite = new Skill("Bite", 5, 0);
	Skill skillSmite = new Skill("", 10, 0);

    public Event(eventType evType, int posX)
    {
        this.evType = evType;
        this.posX = posX;
        if(evType == eventType.FIGHT)
        {
            enemys.add(new Someone(50, 10, "wolf" , "Resources/Entity/wolf.png", new Vector2D(-150, -300)));
            enemys.add(new Someone(50, 10, "wolf" , "Resources/Entity/wolf.png", new Vector2D(-150, -300)));
            enemys.get(0).addSkill(new Skill(skillBite));
            enemys.get(1).addSkill(new Skill(skillBite));
        }
        else if(evType == eventType.CHEST)
        {
            
        }
    }
}