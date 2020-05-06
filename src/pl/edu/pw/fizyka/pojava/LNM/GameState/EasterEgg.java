package pl.edu.pw.fizyka.pojava.LNM.GameState;

import pl.edu.pw.fizyka.pojava.LNM.System.Background;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class EasterEgg extends Scene {

	// zmienna gui
	private Background bg;

	// konstruktor
	public EasterEgg(SceneManager gsm) {
		this.gsm = gsm;

		// test
		try {
			bg = new Background("Resources/Backgrounds/darkbg.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// cos tu bedzie kiedys
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
	}

	@Override
	public void keyPressed(int k) {
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_ENTER) {
			gsm.setState(SceneManager.MENU);
		}
	}
}