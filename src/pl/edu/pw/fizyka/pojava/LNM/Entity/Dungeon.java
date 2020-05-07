package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;

public class Dungeon 
{
    public ArrayList<Room> rooms = new ArrayList<Room>();
    int roomCount = 0;

    public Dungeon()
    {
        Room 
        r1 = new Room(2250), 
        r2 = new Room(1500),
        r3 = new Room(1000),
        r4 = new Room(1250);
        
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        rooms.add(r4);

        r1.addDoor(new Door(r2), 750);
            r2.addDoor(new Door(r3), 500);

        r1.addDoor(new Door(r4), 1500);

        


    }
}