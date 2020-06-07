package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import pl.edu.pw.fizyka.pojava.LNM.System.Images;

import java.awt.Graphics2D;

//by Cyprian Siwy
public class Someone {

    private int hp;
    private int maxHp;

    private int stamina;
    private int maxStamina;

    private String name;
    private String avatar;
    private boolean dead = false;
    private boolean isActive = true;

    private ArrayList<String> avatars = new ArrayList<String>(
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8"));
    private Random random = new Random();

    public ArrayList<Skill> skills;
    public ArrayList<Effect> efects;

    String imagePath;
    Vector2D offSet;

    private Images image = new Images();
    public Someone(int maxHp, int maxStamina, String name, String imagePath, Vector2D offSet) {
        this.maxHp = maxHp;
        this.hp = this.maxHp;

        this.maxStamina = maxStamina;
        this.stamina = this.maxStamina;

        this.name = name;
        this.imagePath = imagePath;
        this.offSet = offSet;
        efects = new ArrayList<Effect>();
    }

    public Someone(int hp, int maxHp, int sa, int maxSa, String name) {
        this.hp = hp;
        this.maxHp = maxHp;

        this.maxStamina = maxSa;
        this.stamina = sa;

        this.name = name;

        efects = new ArrayList<Effect>();
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean value) {
        isActive = value;
    }

    public int getStamina() {
        return stamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public boolean isAlive() {
        return !dead;
    }

    public void takeDamage(int damage) 
    {

        if (!dead)
        {
            hp -= damage;
            if(hp>maxHp)
            {
                hp=maxHp;
            }
        }
        if (hp <= 0) 
        {
            hp=0;
            die();
        }
    }

    public void addEfect(Effect efect) {
        efects.add(efect);
    }

    private void die() {
        efects.clear();
        isActive = false;
        dead = true;
        hp = 0;
        
    }

    public void takeHeal(int heal) {
        hp += heal;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public String getName() {
        return name;
    }

    public void useStamina(int staminaUsed) {
        stamina -= staminaUsed;
    }

    public void drawSomeone(int x, int y, Graphics2D g) {
        g.drawString("" + this.getName(), x, y);
        g.drawString("" + this.getHp() + "HP", x, y + 20);
        g.drawString("" + this.getStamina() + "ST", x + 50, y + 20);
        image.draw(g, x + (int) offSet.x , y + (int) offSet.y, imagePath);
        //20 420
    }

    // by Mateusz Karbownik
    public float payHeal() {
        return (100 - ((hp * 100) / maxHp)) * 5;
    }

    public int getPercent() {
        return hp * 100 / maxHp;
    }

    public void setHp() {
        hp = maxHp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar() {
        if (avatars.size() != 0) {
            int id = 0;
            id = random.nextInt(avatars.size());
            avatar = "Resources/HUD/champ" + avatars.get(id) + ".png";
            avatars.remove(id);
        }
    }

    public void setAvatar(String av) {
        avatar = av;
    }

    public void upgradeChamp() {
        double tmp = random.nextDouble() / 2;
        this.maxHp += maxHp * tmp;
        this.hp += hp * tmp;

        tmp = random.nextDouble() / 2;
        this.stamina += stamina * tmp;
        this.maxStamina += maxStamina * tmp;

        if (skills != null && skills.size() > 0) {
            tmp = random.nextDouble() / 3;
            this.skills.get(0).upgradeSkill(tmp);
            this.skills.get(1).upgradeSkill(tmp);
        }
    }
    public void setImage(String s)
    {
        imagePath = s;
    }
    public void setOffSet(Vector2D v1)
    {
        offSet = v1;
    }
}
