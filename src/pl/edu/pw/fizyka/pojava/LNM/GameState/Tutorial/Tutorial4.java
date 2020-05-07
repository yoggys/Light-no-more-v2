package pl.edu.pw.fizyka.pojava.LNM.GameState.Tutorial;

import pl.edu.pw.fizyka.pojava.LNM.System.*;
import pl.edu.pw.fizyka.pojava.LNM.GameState.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class Tutorial4 extends Scene {

	// zmienne gui
	private Background bg;
	private Font font;

	// zmienne obslugi
	private int currentChoice = 1;

	// konstruktor
	public Tutorial4(SceneManager gsm) {
		this.gsm = gsm;
	}

	// rysowanie gui
	@Override
	public void draw(Graphics2D g) {
		try {
			if(Tutorial0.isPolish){
				bg = new Background("Resources/Backgrounds/Tutorial/hq2.png");
			}
			else{
				bg = new Background("Resources/Backgrounds/Tutorial/hq.png");
			}
			font = new Font("Arial", Font.PLAIN, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}

		bg.draw(g);

		g.setFont(font);
		g.setColor(Color.RED);

		for (int i = 0; i < 3; i++){
			if(i == currentChoice){
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.RED);
			}
			if(!Tutorial0.isPolish) {
				if(i < 2){
					g.drawString(Tutorial0.options[i], 429 + 200 * i, 680);
				} else {
					g.drawString(Tutorial0.options[i], 375 + 200 * i, 680);
				}
			} else {
				if(i < 2){
					g.drawString(Tutorial0.options[i], 403 + 200 * i, 680);
				} else {
					g.drawString(Tutorial0.options[i], 388 + 200 * i, 680);
				}
			}
		}
	}

	// wybor aktualnego trybu pracy / opcji menu etc.
	private void select() {
		if (currentChoice == 0) {
			gsm.setState(pl.edu.pw.fizyka.pojava.LNM.GameState.SceneManager.TUT3);
		} 
		if (currentChoice == 1) {
			gsm.setState(pl.edu.pw.fizyka.pojava.LNM.GameState.SceneManager.TOWN);
			Music.change("Resources/Music/muz22.wav");
		} 
		if (currentChoice == 2) {
			gsm.setState(pl.edu.pw.fizyka.pojava.LNM.GameState.SceneManager.TOWN);
			Music.change("Resources/Music/muz22.wav");
		} 
	}

	// keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_RIGHT) {
			currentChoice++;
			if (currentChoice > 2) {
				currentChoice = 0;
			}
		}
		if (k == KeyEvent.VK_LEFT) {
			currentChoice--;
			if (currentChoice < 0) {
				currentChoice = 2;
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
