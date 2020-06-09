package pl.edu.pw.fizyka.pojava.LNM.GameState;

import pl.edu.pw.fizyka.pojava.LNM.GameState.Tutorial.Tutorial0;
import pl.edu.pw.fizyka.pojava.LNM.System.Background;
import pl.edu.pw.fizyka.pojava.LNM.System.Images;
import pl.edu.pw.fizyka.pojava.LNM.System.Language;

import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class LangScene extends Scene {

	// zmienna gui
	private Background bg;
	private int current = 0;
	private Images image;

	// konstruktor
	public LangScene(SceneManager gsm) {
		this.gsm = gsm;

		// test
		try {
			bg = new Background("Resources/Backgrounds/xd.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		image = new Images();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);

		if (current == 0) {
			image.draw(g, 0, 0, "Resources/Backgrounds/english.png");
		} else if (current == 1) {
			image.draw(g, 0, 0, "Resources/Backgrounds/polish.png");
		}
	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_RIGHT) {
			if (current == 1) {
				current = 0;
			} else {
				current++;
			}
		}
		if (k == KeyEvent.VK_LEFT) {
			if (current == 0) {
				current = 1;
			} else {
				current--;
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_ENTER) {
			if (current == 0) {
				Tutorial0.isPolish = false;
				Language.setLanguage("en");
			} else if (current == 1) {
				Tutorial0.isPolish = true;
				Language.setLanguage("pl");
			}
			gsm.setScene(SceneManager.MENU);
		}
	}
}