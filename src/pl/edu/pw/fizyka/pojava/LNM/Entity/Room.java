package pl.edu.pw.fizyka.pojava.LNM.Entity;


import java.util.ArrayList;

public class Room 
{
    public ArrayList<Door> doors = new ArrayList<Door>();
    public Door exitDoor;
    public int lenght;

    public ArrayList<Event> events = new ArrayList<Event>();

    public Room(int lenght)
    {
        this.lenght = lenght;
    }

    void setExitDoor(Door d1)
    {
        exitDoor = d1;
    }

    void addDoor(Door d1,int posX)
    {
        d1.setPosX(posX);
        doors.add(d1);
    }
    
    void addEvent(Event ev1)
    {
        events.add(ev1);
    }
}