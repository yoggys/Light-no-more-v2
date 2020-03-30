package Entity;

import java.awt.Graphics2D;

public class Skill
{
    private int staminaUse;
    private String name;
    private Efect efect;
    private int damage;

    public String getName(){ return name; }

    public Skill(String name, int damage, int staminaUse)
    {
        this.name=name;
        this.staminaUse=staminaUse;
        this.damage=damage;
    }

    public Skill(String name, int damage, int staminaUse, Efect efect)
    {
        this.name=name;
        this.staminaUse=staminaUse;
        this.damage = damage;
        this.efect = efect;
    }
    
    public Skill(Skill s)
    {
        this.name = s.name;
        this.staminaUse =s.staminaUse;
        this.damage = s.damage;
        this.efect = s.efect;
    }

    public Efect getEfect() { return efect; }

    public int getDamage() { return damage; }

    public int getStaminaUse() { return staminaUse; }

    //by Mateusz Karbownik
    public int getEfectDamage(){
        return efect.getDamage();
    }
    public int getEfectTime(){
        return efect.getTime();
    }
    public void upgradeSkill(double rand){
        this.damage += damage*rand;
        if(this.efect != null){
            this.efect.upgradeEfect(rand);
        } 
    }

    public void drawSkill(int x, int y, Graphics2D g)
    {
        g.drawString(this.getName(), x, y);
        g.drawString(""+this.getDamage(), x, y +20);
        g.drawString(""+this.getStaminaUse(), x + 20, y + 20);
    }


}