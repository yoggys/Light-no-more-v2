package GameState;

import System.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class DungeonState extends GameState {
	
	//zmienne gui
	private Images image;
	private int x = 0;
	private int xmax = 2780;
	private int y = 0;

	//konstruktor
	public DungeonState(GameStateManager gsm) {
		this.gsm = gsm;
		
		//test
		try {
			image = new Images();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//rysowanie gui
	@Override
	public void draw(Graphics2D g) {	

		image.draw(g, x, y,"Resources/Map/Background/test2.png");

	}
	
	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if(k == KeyEvent.VK_RIGHT && x != xmax){
			x-=2;
		}
		if(k == KeyEvent.VK_LEFT && x != 0){
			x+=2;
		}
	}

	public void keyReleased(int k) { }
}