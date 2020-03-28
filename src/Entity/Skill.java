package Entity;

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

    public Efect getEfect() { return efect; }
    
    public int getDamage() { return damage; }

    public int getStaminaUse() { return staminaUse; }

    public void upgradeSkill(double rand){
        this.damage += damage*rand;
        if(this.efect != null){
            this.efect.upgradeEfect(rand);
        } 
    }

}