package pl.edu.pw.fizyka.pojava.LNM.Entity;


import java.util.ArrayList;

public class Room 
{
    public ArrayList<Door> doors = new ArrayList<Door>();
    int lenght;

    public Room(int lenght)
    {
        this.lenght = lenght;
    }

    void addDoor(Door d1,int posX)
    {
        d1.setPosX(posX);
        doors.add(d1);
    }
}