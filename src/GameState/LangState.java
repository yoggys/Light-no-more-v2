package GameState;

import System.Background;
import System.Images;
import System.Language;

import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class LangState extends GameState {
	
	//zmienna gui
	private Background bg;
	private int current = 0;
	private Images image;
	
	//konstruktor
	public LangState(GameStateManager gsm) {
		this.gsm = gsm;
		
		//test
		try {
			bg = new Background("Resources/Backgrounds/xd.png");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		image = new Images();
	}
	
	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);

		if(current == 0){
			image.draw(g,0,0,"Resources/Backgrounds/english.png");
		}
		else if(current == 1){
			image.draw(g,0,0,"Resources/Backgrounds/polish.png");
		}
	}	

	@Override
	public void keyPressed(int k) { 
		if(k == KeyEvent.VK_RIGHT) {
			if(current == 1){
				current = 0;
			}
			else{
				current++;
			}
		}
		if(k == KeyEvent.VK_LEFT) {
			if(current == 0){
				current = 1;
			}
			else{
				current--;
			}
		}
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_ENTER){
			if(current == 0){
				Language.setLanguage("en");
			}
			else if(current == 1){
				Language.setLanguage("pl");
			}
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}
}