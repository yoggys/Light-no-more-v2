package Entity;

import java.awt.Graphics2D;

//by Cyprian Siwy
public class Skill
{
    private int staminaUse;
    private String name;
    private Effect effect;
    private int damage;

    public String getName(){ return name; }

    public Skill(String name, int damage, int staminaUse)
    {
        this.name=name;
        this.staminaUse=staminaUse;
        this.damage=damage;
    }

    public Skill(String name, int damage, int staminaUse, Effect effect)
    {
        this.name=name;
        this.staminaUse=staminaUse;
        this.damage = damage;
        this.effect = effect;
    }
    
    public Skill(Skill s)
    {
        this.name = s.name;
        this.staminaUse =s.staminaUse;
        this.damage = s.damage;
        this.effect = s.effect;
    }

    public Effect getEffect() { return effect; }

    public int getDamage() { return damage; }

    public int getStaminaUse() { return staminaUse; }

    //by Mateusz Karbownik
    public int getEfectDamage(){
        return effect.getDamage();
    }
    public int getEfectTime(){
        return effect.getTime();
    }
    public void upgradeSkill(double rand){
        this.damage += damage*rand;
        if(this.effect != null){
            this.effect.upgradeEfect(rand);
        } 
    }

    public void drawSkill(int x, int y, Graphics2D g)
    {
        g.drawString(this.getName(), x, y);
        g.drawString(""+this.getDamage(), x, y +20);
        g.drawString(""+this.getStaminaUse(), x + 20, y + 20);
    }


}