package pl.edu.pw.fizyka.pojava.LNM.GameState;

import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.KeyEvent;

import pl.edu.pw.fizyka.pojava.LNM.System.Background;

//class by Mateusz Karbownik
public class DeadScene extends Scene {

    private Background bg;
    public static String options[];

    public DeadScene(SceneManager gsm)
    {
        this.gsm =gsm;
        bg = new Background("Resources/Backgrounds/deadbg.png");
    }

    @Override
    public void draw(Graphics2D g)
    {
        Font font = new Font("Arial", Font.PLAIN, 44);
        g.setFont(font);
        bg.draw(g);
        g.setColor(Color.BLACK);
        g.drawString(options[0], 540, 200);
        g.setColor(Color.WHITE);
        g.drawString(options[1], 570, 690);
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