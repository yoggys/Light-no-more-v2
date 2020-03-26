package GameState;

import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;

public class EasterEgg extends GameState {
	
	//zmienna gui
	private Background bg;
	
	//konstruktor
	public EasterEgg(GameStateManager gsm) {
		this.gsm = gsm;
		
		//test
		try {
			bg = new Background("/Backgrounds/easterbg.png");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
	}	

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	//z dziedziczenia
	public void keyReleased(int k) {}
}