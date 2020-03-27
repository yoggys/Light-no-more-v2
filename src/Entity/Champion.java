package Entity;

import java.util.ArrayList;

public class Champion extends Someone 
{
    public ArrayList<Skill> skills;


    public Champion(int maxHp, int stamina, int maxStamina,  String name, String av)
    {
        super(maxHp, stamina, maxStamina, name, av);
        skills= new ArrayList<Skill>();
    }

    public void addSkill(Skill skill)
    {
        skills.add(skill);
    }

}