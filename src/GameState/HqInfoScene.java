package GameState;

import System.*;
import Player.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class HqInfoScene extends Scene {

	// zmienne gui
	private Background bg;
	public static String options[];
	private HUDgold hud;
	private Images image;
	private Font font;

	// zmienne obslugi
	private int currentChoice = 0;
	private int row = 0;

	// konstruktor
	public HqInfoScene(SceneManager gsm) {
		this.gsm = gsm;

		// test
		try {
			bg = new Background("Resources/Backgrounds/hqbg3.png");
			font = new Font("Arial", Font.PLAIN, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}

		hud = new HUDgold();
		image = new Images();
	}

	// rysowanie gui
	@Override
	public void draw(Graphics2D g) {

		bg.draw(g);
		hud.draw(g);

		g.setFont(font);
		g.setColor(Color.RED);

		g.drawString("INFO: ", 33, 200);
		g.drawString(options[0], 33, 360);
		if (row == 1) {
			g.setColor(Color.WHITE);
		}
		if (Inventory.invSize() > 0 && row == 1) {
			g.drawString(Inventory.getInfo(currentChoice), 33, 230);
			g.setColor(Color.ORANGE);
			g.drawString(String.valueOf(Inventory.getPrice(currentChoice)), 33, 390);
		} else {
			g.drawString(options[2], 33, 230);
			g.drawString(options[2], 33, 390);
		}
		if (Inventory.invSize() == 0) {
			g.setColor(Color.GREEN);
			g.drawString(options[3], 33, 560);
		}
		g.setColor(Color.RED);
		if (row == 0) {
			g.setColor(Color.WHITE);
		}
		g.drawString(options[1], 620, 680);

		for (int i = 0; i < Inventory.invSize(); i++) {

			if (i == currentChoice && row == 1) {
				image.draw(g, -85 + 126 * i, 393, "Resources/Items/selectedframe.png");
			}
			image.draw(g, 33 + 126 * i, 510, "Resources/Items/" + Inventory.getName(Inventory.getId(i)) + ".png");
		}

	}

	// wybor aktualnego trybu pracy / opcji menu etc.
	private void select() {
		if (row == 0) {
			currentChoice = 0;
			gsm.setState(SceneManager.HEADQUARTERS);
		}
	}

	// keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_UP && Inventory.invSize() > 0) {
			if (row == 1) {
				row = 0;
			} else {
				row++;
			}

		}
		if (k == KeyEvent.VK_DOWN) {
			if (row == 0) {
				if (Inventory.invSize() > 0) {
					row = 1;
				}

			} else {
				row--;
			}

		}
		if (k == KeyEvent.VK_RIGHT) {
			if (row == 1) {
				currentChoice++;
				if (currentChoice == Inventory.invSize()) {
					currentChoice = 0;
				}
			}
		}
		if (k == KeyEvent.VK_LEFT) {
			if (row == 1) {
				currentChoice--;
				if (currentChoice == -1) {
					currentChoice = Inventory.invSize() - 1;
				}
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