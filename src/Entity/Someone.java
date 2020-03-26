package Entity;

import java.util.ArrayList;

public class Someone
{
    
    private int hp;
    private int maxHp;

    private int stamina;
    private int maxStamina;

	private int level;
	private int exp;
	private String name;
	private String profession;
    private boolean dead = false;
    private boolean isActive = true;
    
    public ArrayList<Efect> efects;
    
    public Someone(int maxHp, int stamina, int maxStamina, String name)
    {
        this.maxHp = maxHp;
        this.hp = this.maxHp;

        this.stamina = stamina;
        this.maxStamina = maxStamina;
        
        
        this.name=name;
        efects = new ArrayList<Efect>();
    }

    public int getHp() { return hp; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean value) { isActive = value; }
    public int getStamina() { return stamina; }

    public void takeDamage(int damage)
    {
        if( ! dead )
        hp -=damage;
        if(hp<=0)
        {
            dead=true;
            isActive=false;
            die();
        }
    }

    public void addEfect(Efect efect) { efects.add(efect); }

    private void die()
    {
        hp=0;
        //some awesome death
    }
    
    public void takeHeal(int heal)
    {
        hp+=heal;
        if (hp > maxHp) 
        {
            hp = maxHp;    
        }
    }
    public String getName() { return name; }

    public int getPercent() { return hp*100/maxHp; }

    public float payHeal() { return (100-((hp*100)/maxHp))*5; }
    
    public void setHp() { hp = maxHp; }

    public void useStamina(int staminaUsed) { stamina -= staminaUsed; }

}