package pl.edu.pw.fizyka.pojava.LNM.GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.KeyEvent;

import pl.edu.pw.fizyka.pojava.LNM.Entity.Vector2D;
import pl.edu.pw.fizyka.pojava.LNM.System.Background;

public class EndCredits extends Scene {

    Background bg;
    Vector2D bgPos = Vector2D.zero;
    ArrayList<String> credits = new ArrayList<>();

    public EndCredits(SceneManager gsm)
    {
        this.gsm =gsm;
        credits.add("Projekt PO Java - Light no more");
        credits.add("Twórcy :");
        
        credits.add("Mateusz Karbownik");
        credits.add("Cyprian Siwy");
        bg = new Background("Resources/Backgrounds/darkbg.png");
    }

    @Override
    public void draw(Graphics2D g)
    {
        if(bgPos.y > - 850)
        {
            bg.draw(g);
            for(int i = 0; i<credits.size(); i++)
            {
                if(i == 2 || i == 3){
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.RED);
                }
                g.drawString(credits.get(i), 300, bgPos.y + 1000 + i * 100);
            }
            bgPos.y -= 10;
        }
        

    }

    @Override
    public void keyPressed(int k) 
    {
		if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_ENTER) {
            System.exit(0);
		}
    }

    @Override
    public void keyReleased(int k) {  }
    
}