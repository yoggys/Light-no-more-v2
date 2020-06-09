package pl.edu.pw.fizyka.pojava.LNM.GameState;

import pl.edu.pw.fizyka.pojava.LNM.System.Background;
import pl.edu.pw.fizyka.pojava.LNM.Main.*;

import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class ResolutionScene extends Scene {

	// zmienna gui
	private Background bg;
	private int current = 0;
	private String options[] = {"1280x720","1600x900","1920x1080"};
	private int row = 0;
	private Font font;

	// konstruktor
	public ResolutionScene(SceneManager gsm) {
		this.gsm = gsm;

		// test
		try {
			bg = new Background("Resources/Backgrounds/xd.png");
			font = new Font("Arial", Font.PLAIN, 30);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setFont(font);

		for(int ii = 0; ii < 3; ii++){
			if (ii == current && row == 0) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.RED);
			}

			g.drawString(options[ii], 260 + ii * 300, 360);
		}

		if(row == 1){
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.RED);
		}
		g.drawString("OK", 609, 540);
	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_RIGHT && row == 0) {
			if (current == 2) {
				current = 0;
			} else {
				current++;
			}
		}
		if (k == KeyEvent.VK_LEFT && row == 0) {
			if (current == 0) {
				current = 2;
			} else {
				current--;
			}
		}
	
		if (k == KeyEvent.VK_UP) {
			if (row == 1) {
				row = 0;
			} else {
				row++;
			}

		}
		if (k == KeyEvent.VK_DOWN) {
			if (row == 0) {
				row = 1;
			} else {
				row--;
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_ENTER) {
			if(row == 0){
				if (current == 0) {
					GamePanel.SCALE = 1;
					Game.window.setSize(1296, 758);
					Game.window.setLocationRelativeTo(null);
				} else if (current == 1) {
					GamePanel.SCALE = 1.25;
					Game.window.setSize(1616, 935);
					Game.window.setLocationRelativeTo(null);
				} else if (current == 2) {
					GamePanel.SCALE = 1.5;
					Game.window.setSize(1936, 1115);
					Game.window.setLocation(-10,0);
				}
			}
			else {
				gsm.setScene(SceneManager.LANG);
			}		
		}
	}
}