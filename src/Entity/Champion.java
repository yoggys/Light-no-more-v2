package Entity;

import java.util.ArrayList;

public class Champion extends Someone 
{

    public Champion(int maxHp , int maxStamina,  String name)
    {
        super(maxHp, maxStamina, name);
        skills= new ArrayList<Skill>();
        super.setAvatar();
    }

    public void addSkill(Skill skill)
    {
        skills.add(skill);
    }

}