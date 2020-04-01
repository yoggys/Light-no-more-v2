package GameState;

import System.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class EscScene extends Scene {
	
	private Background bg;
	public static String[] options;
	private Color titleColor;
	private Font titleFont;
	private Font font;

	//zmienne obslugi
	private int currentChoice = 0;
	
	public EscScene(SceneManager gsm) {
		this.gsm = gsm;
		
		//test
		try {
			bg = new Background("Resources/Backgrounds/xd.png");
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 96);
			font = new Font("Arial", Font.PLAIN, 44);			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//rysowanie gui
	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Light no more", 310, 160);
		g.setFont(font);

		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}

			if(i == 0){
				g.drawString(options[i], 60, 340 + 2 * i * 35);
			}
			else if(i == 2){
				if(Music.unmuted){
					g.drawString(options[i], 60, 340 + 2 * i * 35);
				}
				else{
					g.drawString(options[4], 60, 340 + 2 * i * 35);
				}
			}
			else if(i == 3){
				g.drawString(options[i], 60, 340 + 2 * i * 35);
			}
			else if(i == 1){
				g.drawString(options[i], 60, 340 + 2 * i * 35);
			}
		}
	}
	
	//wybor aktualnego trybu pracy / opcji menu etc.
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(gsm.getLastState());
		}
		
		if(currentChoice == 1) {
			try{
				SaveGame.saveGame();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			gsm.setState(gsm.getLastState());
		}

		if(currentChoice == 2 || currentChoice == 4) {
			if(Music.unmuted){
				Music.unmuted = false;
				Music.stop();
			}
			else{
				Music.unmuted = true;
				Music.play();
			}
			
		}

		if(currentChoice == 3) {
			System.exit(0);
		}
	}
	
	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if(k == KeyEvent.VK_UP) {
			currentChoice--;

			if(currentChoice == -1) {
				currentChoice = options.length - 2;
			}
		}

		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;

			if(currentChoice == options.length-1) {
				currentChoice = 0;
			}
		}

		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(gsm.getLastState());
		}
	}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
	}
}