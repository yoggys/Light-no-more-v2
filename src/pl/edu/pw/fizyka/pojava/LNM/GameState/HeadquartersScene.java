package pl.edu.pw.fizyka.pojava.LNM.GameState;

import pl.edu.pw.fizyka.pojava.LNM.System.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class HeadquartersScene extends Scene {

	// zmienne gui
	private Background bg;
	public static String options[];
	private Font font;

	// zmienne obslugi
	private int currentChoice = 3;

	// konstruktor
	public HeadquartersScene(SceneManager gsm) {
		this.gsm = gsm;

		// test
		try {
			bg = new Background("Resources/Backgrounds/hqbg.png");
			font = new Font("Arial", Font.PLAIN, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// rysowanie gui
	@Override
	public void draw(Graphics2D g) {

		bg.draw(g);

		g.setFont(font);

		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.RED);
			}
			if (i < 3) {
				g.drawString(options[i], 66, 270 + 2 * i * 35);
			} else {
				g.drawString(options[i], 620, 680);
			}
		}

	}

	// wybor aktualnego trybu pracy / opcji menu etc.
	private void select() {
		if (currentChoice == 0) {
			gsm.setState(SceneManager.HQUPGRADE);
		} else if (currentChoice == 1) {
			gsm.setState(SceneManager.HQSQUAD);
		} else if (currentChoice == 2) {
			gsm.setState(SceneManager.HQINFO);
		} else if (currentChoice == 3) {
			gsm.setState(SceneManager.TOWN);
		}
	}

	// keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_DOWN) {
			if (currentChoice == 3) {
				currentChoice = 0;
			} else {
				currentChoice++;
			}

		}
		if (k == KeyEvent.VK_UP) {
			if (currentChoice == 0) {
				currentChoice = 3;
			} else {
				currentChoice--;
			}

		}
		if (k == KeyEvent.VK_ESCAPE) {
			gsm.setState(SceneManager.ESC);
		}
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
	}
}