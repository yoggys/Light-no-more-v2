package GameState;

import System.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class TownScene extends Scene {

	// zmienne gui
	private Background bg;
	private HUDgold hud;
	private Font font;

	// zmienne obslugi
	private int currentChoice = 0;
	public static String objects[];
	private int position[] = { 1010, 320, 180, 680, 385, 350, 540, 500, 720, 660 };

	// konstruktor
	public TownScene(SceneManager gsm) {
		this.gsm = gsm;

		// test
		try {
			bg = new Background("Resources/Backgrounds/townbg3.png");
			font = new Font("Arial", Font.PLAIN, 28);
		} catch (Exception e) {
			e.printStackTrace();
		}

		hud = new HUDgold();
	}

	// rysowanie gui
	@Override
	public void draw(Graphics2D g) {
		if (hud != null) {
			int stringposition = 0;

			bg.draw(g);
			hud.draw(g);

			g.setFont(font);

			for (int i = 0; i < objects.length; i++) {
				if (i == currentChoice) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.RED);
				}

				g.drawString(objects[i], position[stringposition++], position[stringposition++]);
			}
		}

	}

	// wybor aktualnego trybu pracy / opcji menu
	private void select() {
		if (currentChoice == 0) {
			Music.change("Resources/Music/muz11.wav");
			gsm.setState(SceneManager.DARK);
		}

		if (currentChoice == 1) {
			gsm.setState(SceneManager.TAVERN);
		}

		if (currentChoice == 2) {
			gsm.setState(SceneManager.CHARLATAN);
		}

		if (currentChoice == 3) {
			gsm.setState(SceneManager.MERCHANT);
		}

		if (currentChoice == 4) {
			gsm.setState(SceneManager.HEADQUARTERS);
		}
	}

	// keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_UP || k == KeyEvent.VK_RIGHT) {
			currentChoice--;

			if (currentChoice == -1) {
				currentChoice = objects.length - 1;
			}
		}

		if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_LEFT) {
			currentChoice++;

			if (currentChoice == objects.length) {
				currentChoice = 0;
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