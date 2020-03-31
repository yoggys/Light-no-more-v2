package Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.Graphics2D;

public class Someone
{
    
    private int hp;
    private int maxHp;

    private int stamina;
    private int maxStamina;

    private String name;
    private String avatar;
    private boolean dead = false;
    private boolean isActive = true;
    

    private ArrayList<String> avatars = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8"));
    private Random random = new Random();
    
    public ArrayList<Skill> skills;
    public ArrayList<Efect> efects;
    

    public Someone(int maxHp, int maxStamina, String name)
    {
        this.maxHp = maxHp;
        this.hp = this.maxHp;

        this.maxStamina = maxStamina;
        this.stamina = this.maxStamina;
        
        this.name=name;

        efects = new ArrayList<Efect>();
    }

    public Someone(int hp, int maxHp, int sa, int maxSa,  String name)
    {
        this.hp = hp;
        this.maxHp = maxHp;

        this.maxStamina = maxSa;
        this.stamina = sa;
        
        this.name=name;

        efects = new ArrayList<Efect>();
    }

    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean value) { isActive = value; }
    public int getStamina() { return stamina; }
    public int getMaxStamina() { return maxStamina; }

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



    public void useStamina(int staminaUsed) { stamina -= staminaUsed; }
    

    //by Mateusz Karbownik
    public float payHeal() { return (100-((hp*100)/maxHp))*5; }
    public int getPercent() { return hp*100/maxHp; }
    public void setHp() { hp = maxHp; }
    public String getAvatar(){ return avatar; }
    
    public void setAvatar(){ 
        if(avatars.size() != 0){
            int id = 0;
            id = random.nextInt(avatars.size());
            avatar = "Resources/HUD/champ"+ avatars.get(id) + ".png";
            avatars.remove(id);
        }
    }
    public void setAvatar(String av){
        avatar = av;
    }


    public void drawSomeone(int x, int y, Graphics2D g)
    {
        g.drawString(""+this.getName(),x , y);
		g.drawString(""+this.getHp()+ "HP", x, y + 20);
		g.drawString(""+this.getStamina()+"ST", x + 50, y + 20);
    }

    public void upgradeChamp()
    {
        double tmp = random.nextDouble()/2;
        this.maxHp += maxHp*tmp;
        this.hp += hp*tmp;

        tmp = random.nextDouble()/2;
        this.stamina += stamina*tmp;
        this.maxStamina += maxStamina*tmp;

        if(skills != null && skills.size()>0){
            tmp = random.nextDouble()/3;
            this.skills.get(0).upgradeSkill(tmp);
            this.skills.get(1).upgradeSkill(tmp);
        }

    }
}
