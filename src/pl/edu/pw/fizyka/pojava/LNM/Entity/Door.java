package pl.edu.pw.fizyka.pojava.LNM.Entity;

//by Cyprian Siwy
public class Door 
{
    public Room leadTo;
    public int posX;
    
    public Door(Room  leadTo)
    {
        this.leadTo = leadTo;
    }

    void setPosX(int posX)
    {
        this.posX = posX;
    }
}