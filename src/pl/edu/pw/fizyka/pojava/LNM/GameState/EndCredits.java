package pl.edu.pw.fizyka.pojava.LNM.GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import pl.edu.pw.fizyka.pojava.LNM.Entity.Vector2D;
import pl.edu.pw.fizyka.pojava.LNM.System.Background;

public class EndCredits extends Scene {

    Background bg;
    Vector2D bgPos = Vector2D.zero;
    ArrayList<String> credits = new ArrayList<>();

    public EndCredits(SceneManager gsm)
    {
        credits.add("Dziękujemy za zagranie w naszą grę");
        credits.add("Twórcy :");
        credits.add("Mateusz Karbownik");
        credits.add("Cyprian Siwy");
        bg = new Background("Resources/Backgrounds/darkbg.png");
    }

    @Override
    public void draw(Graphics2D g)
    {
        if(bgPos.y > - 2000)
        {
            g.setBackground(Color.BLACK);
            for(int i = 0; i<credits.size(); i++)
            {
                g.drawString(credits.get(i), 400, bgPos.y + 1000 + i * 100);
            }
            bgPos.y -= 5;
        }

    }

    @Override
    public void keyPressed(int k) 
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(int k) {
        // TODO Auto-generated method stub

    }
    
}