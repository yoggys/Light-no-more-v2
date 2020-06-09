package pl.edu.pw.fizyka.pojava.LNM.GameState;

import pl.edu.pw.fizyka.pojava.LNM.System.Background;
import pl.edu.pw.fizyka.pojava.LNM.System.LoadGame;
import pl.edu.pw.fizyka.pojava.LNM.System.Music;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class MenuScene extends Scene {

	// zmienne gui
	private Background bg;
	public static String[] options;
	private Color titleColor;
	private Font titleFont;
	private Font font;

	// zmienne obslugi
	private int currentChoice = 0;

	public MenuScene(SceneManager gsm) {
		this.gsm = gsm;

		// test
		try {
			bg = new Background("Resources/Backgrounds/xd.png");
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 96);
			font = new Font("Arial", Font.PLAIN, 44);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// rysowanie gui
	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);

		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Light no more", 310, 160);
		g.setFont(font);

		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.RED);
			}

			if (i == 0) {
				g.drawString(options[i], 60, 340 + 2 * i * 35);
			} else if (i == 1) {
				g.drawString(options[i], 60, 340 + 2 * i * 35);
			} else if (i == 2) {
				g.drawString(options[i], 60, 340 + 2 * i * 35);
			} else {
				g.drawString(options[i], 60, 340 + 2 * i * 35);
			}
		}
	}

	// wybor aktualnego trybu pracy / opcji menu etc.
	private void select() {
		if (currentChoice == 0) {
			gsm.setScene(SceneManager.TUT0);
		}

		if (currentChoice == 1) {
			try {
				LoadGame.loadGame();
			} catch (Exception e) {
				e.printStackTrace();
			}
			gsm.setScene(SceneManager.TOWN);
			Music.change("Resources/Music/muz22.wav");
		}

		if (currentChoice == 2) {
			gsm.setScene(SceneManager.ENDCREDITS);
			//gsm.setState(SceneManager.HELP);
		}
		if (currentChoice == 3) {
			System.exit(0);
		}
	}

	// keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_UP) {
			currentChoice--;

			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}

		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;

			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
	}
}