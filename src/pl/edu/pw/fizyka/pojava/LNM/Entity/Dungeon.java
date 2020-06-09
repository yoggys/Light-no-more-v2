package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;

import pl.edu.pw.fizyka.pojava.LNM.Entity.Event.eventType;

//by Cyprian Siwy
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
                    r3.addEvent( new Event(eventType.FIGHT, 600) );  
                    r3.addEvent(new Event(eventType.LEAVEDOOR, 1400)); 

                break;
            }

            case 2:
            {
                Room r1 = new Room(1300);

                rooms.add(r1);

                r1.addEvent(new Event(eventType.TEXT, 200));
                r1.events.get(0).setText("Dziękujemy za zagranie w naszą grę");
                r1.addEvent(new Event(eventType.TEXT, 1100));
                r1.events.get(1).setText("Wykonana przez Mateusz Karbownik i Cyprian Siwy");
                r1.addEvent(new Event(eventType.LEAVEDOOR,800));
            }
        }
       

    }
}