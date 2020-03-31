package GameState;

import System.*;
import java.awt.*;
import java.awt.event.KeyEvent;


//class by Mateusz Karbownik
public class TownState extends GameState {
	
	//zmienne gui
	private Background bg;
	private HUDgold hud;
	private Font font;

	//zmienne obslugi
	private int currentChoice = 0;
	public static String objects[];
	private int position[]= {1010,320, 180,680, 385,350, 540,500, 720,660};


	//konstruktor
	public TownState(GameStateManager gsm) {
		this.gsm = gsm;	
	
		//test
		try {
			bg = new Background("Resources/Backgrounds/townbg3.png");
			font = new Font("Arial", Font.PLAIN, 28);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		hud = new HUDgold();
	}
	
	//rysowanie gui
	@Override
	public void draw(Graphics2D g) {
		if(hud != null){
			int stringposition = 0;
		
			bg.draw(g);
			hud.draw(g);
		
			g.setFont(font);

			for(int i = 0; i < objects.length; i++) {
				if(i == currentChoice) {
					g.setColor(Color.WHITE);
				}
				else {
					g.setColor(Color.RED);
				}

				g.drawString(objects[i], position[stringposition++], position[stringposition++]);
			}
		}
		
	}
	
	//wybor aktualnego trybu pracy / opcji menu
	private void select() {
		if(currentChoice == 0) {
			Music.change("Resources/Music/muz11.wav");
			gsm.setState(GameStateManager.DARKSTATE);
		}

		if(currentChoice == 1) {
			gsm.setState(GameStateManager.TAVERNSTATE);
		}

		if(currentChoice == 2) {
			gsm.setState(GameStateManager.CHARLATANSTATE);		
		}

		if(currentChoice == 3) {
			gsm.setState(GameStateManager.MERCHANTSTATE);		
		}

		if(currentChoice == 4) {
			gsm.setState(GameStateManager.HEADQUARTERSSTATE);
		}
	}

	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_RIGHT) {
			currentChoice--;

			if(currentChoice == -1) {
				currentChoice = objects.length - 1;
			}
		}

		if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_LEFT) {
			currentChoice++;

			if(currentChoice == objects.length) {
				currentChoice = 0;
			}
		}
		
		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.ESCSTATE);
		}
	}
	
	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
	}
	
}