package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;

import pl.edu.pw.fizyka.pojava.LNM.Entity.Event.eventType;

public class Dungeon 
{
    public ArrayList<Room> rooms = new ArrayList<Room>();
    int roomCount = 0;

    public Dungeon()
    {
        Room 
        r1 = new Room(1750), 
        r2 = new Room(1500),
        r3 = new Room(1000),
        r4 = new Room(1250);
        
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        rooms.add(r4);

        r1.addDoor(new Door(r2), 750);
            r2.setExitDoor(new Door(r1));
            r2.addEvent(new Event(eventType.CHEST, 1200));
            r2.addDoor(new Door(r3), 500);
                r3.setExitDoor(new Door(r2));
                
        r1.addEvent( new Event(eventType.FIGHT, 1100) );

        r1.addDoor(new Door(r4), 1500);
        r4.setExitDoor(new Door(r1));
        


    }
}