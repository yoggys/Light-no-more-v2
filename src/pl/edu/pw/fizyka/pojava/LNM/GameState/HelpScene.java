package pl.edu.pw.fizyka.pojava.LNM.GameState;

import pl.edu.pw.fizyka.pojava.LNM.System.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class HelpScene extends Scene {

	// zmienne gui
	private Background bg;
	public static String options;
	private String gamever = "alpha v1.7";
	private String authors = "by Mateusz Karbownik, Cyprian Siwy";
	private Color titleColor;
	private Font titleFont;
	private Font auth;
	private Font font;

	// zmienna easteregga
	private int egg = 0;

	// konstruktor
	public HelpScene(SceneManager gsm) {
		this.gsm = gsm;

		// test
		try {
			bg = new Background("Resources/Backgrounds/darkbg.png");

			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 96);

			auth = new Font("Arial", Font.PLAIN, 52);

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

		g.setFont(auth);
		g.setColor(Color.RED);
		g.drawString(gamever, 500, 300);

		g.setFont(font);
		g.drawString(authors, 274, 370);

		g.setColor(Color.WHITE);
		g.drawString(options, 566, 540);
	}

	// keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		// easteregg
		if (k == KeyEvent.VK_X) {
			egg += 1;
			if (egg++ == 101) {
				gsm.setScene(SceneManager.EASTEREGG);
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_ENTER) {
			gsm.setScene(SceneManager.MENU);
		}
	}
}