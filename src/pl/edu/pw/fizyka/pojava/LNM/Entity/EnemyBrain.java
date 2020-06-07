package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;
import java.util.Random;

public class EnemyBrain 
{
    Random rand = new Random();
    public void calculateEnemyMove(ArrayList<Champion> champions, Someone thisEnemy)
    {
        Skill skillToUse = null;
        Someone target;

        for (Champion champion : champions) 
        {
            for (Skill skill : thisEnemy.skills) 
            {
                if(thisEnemy.getStamina() >= skill.getStaminaUse())
                {
                    if(champion.getHp() <= skill.getDamage() );
                    skillToUse=skill;
                    target = champion;
                }
            }    
        }

        while(skillToUse == null)
        {
            skillToUse = thisEnemy.skills.get( rand.nextInt( thisEnemy.skills.size() ) );
            if(thisEnemy.getStamina() < skillToUse.getStaminaUse())
            {
                skillToUse = null;
            }
        }

        target = champions.get( rand.nextInt( champions.size() ) );
        
        thisEnemy.useStamina(skillToUse.getStaminaUse());
        
		target.takeDamage(skillToUse.getDamage());
        thisEnemy.setActive(false);
    }
}

