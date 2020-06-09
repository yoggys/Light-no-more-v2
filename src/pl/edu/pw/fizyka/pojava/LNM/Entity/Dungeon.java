package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;

import pl.edu.pw.fizyka.pojava.LNM.Entity.Event.eventType;

public class Dungeon 
{
    public ArrayList<Room> rooms = new ArrayList<Room>();
    int roomCount = 0;

    public Dungeon(int i)
    {

        switch(i)
        {
            case 1:
            {
                Room 
                r1 = new Room(1750), 
                r2 = new Room(1000),
                r3 = new Room(1500);
        
                
                rooms.add(r1);
                rooms.add(r2);
                rooms.add(r3);
        
                
                r1.addDoor(new Door(r2), 750);
                    r2.setExitDoor(new Door(r1));
                    r2.addEvent( new Event(eventType.CHEST, 500) );
                    
                        
                r1.addDoor(new Door(r3), 1500);
                    r3.setExitDoor(new Door(r1));
                    r3.addEvent( new Event(eventType.FIGHT, 500) );  
                    r3.addEvent(new Event(eventType.LEAVEDOOR, 1000)); 

                break;
            }

            case 2:
            {
                Room r1 = new Room(1750);

                rooms.add(r1);

                r1.addEvent(new Event(eventType.TEXT, 500));
                r1.events.get(0).setText("Dziękujemy za zagranie w naszą grę");
                r1.addEvent(new Event(eventType.TEXT, 1000));
                r1.events.get(1).setText("Wykonana przez Mateusz Karbownik i Cyprian Siwy");
            }
        }
       

    }
}