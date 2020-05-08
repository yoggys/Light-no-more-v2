package pl.edu.pw.fizyka.pojava.LNM.Entity;

import java.util.ArrayList;

//by Cyprian Siwy
public class Champion extends Someone {

    public Champion(int maxHp, int maxStamina, String name, String imagePath ,Vector2D offSet) {
        super(maxHp, maxStamina, name, imagePath, offSet);
        skills = new ArrayList<Skill>();
        super.setAvatar();
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    // by Mateusz Karbownik
    public Champion(int hp, int maxHp, int sa, int maxSa, String name, String av) {
        super(hp, maxHp, sa, maxSa, name);
        skills = new ArrayList<Skill>();
        super.setAvatar(av);
    }
}