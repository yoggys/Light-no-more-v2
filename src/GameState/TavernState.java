package GameState;

import TileMap.Background;
import Entity.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TavernState extends GameState {
	
	//zmienne gui
	private Background bg;
	private String options = "Back";
	private HUDgold hud;
	private Images image;
	private Font font;

	//zmienne obslugi
	private int[] currentChoice = {0,0};
	private int row = 0;
	private int state = 0;

	private int hp[] = {20, 70, 100};

	//konstruktor
	public TavernState(GameStateManager gsm) {	
		this.gsm = gsm;
		
		Player.reserve.add(new Champion(25, 10, 20, "test1", "0"));
		Player.reserve.add(new Champion(25, 10, 20, "test2", "1"));
		Player.reserve.add(new Champion(100, 10, 30, "test3", "2"));

		//test
		try {
			bg = new Background("Resources/Backgrounds/tavernbg1.png");
			font = new Font("Arial", Font.PLAIN, 24);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		hud = new HUDgold();
		image = new Images();
	}
	
	//rysowanie gui
	@Override
	public void draw(Graphics2D g) {

		int check[] = {0,0,0};

		bg.draw(g);
		hud.draw(g);
		
		g.setFont(font);
		g.setColor(Color.RED);

		if(row == 0){
			g.setColor(Color.WHITE);
		}
		g.drawString(options, 620, 680);
		
		
	}
	
	//wybor aktualnego trybu pracy / opcji menu etc.
	private void select() {
		if(row == 0){
			gsm.setState(GameStateManager.TOWNSTATE);
		}
	}

	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
/*
		if(k == KeyEvent.VK_RIGHT) {
			if(row == 1){
				currentChoice[row]++;
				if(currentChoice[row] == 3) {
					currentChoice[row] = 0;
				}
			}
		}
		if(k == KeyEvent.VK_LEFT) {
			if(row == 1){
				currentChoice[row]--;
				if(currentChoice[row] == -1) {
					currentChoice[row] = 2;
				}
			}
			
		}
		if(k == KeyEvent.VK_UP) {
			if(row == 1){
				row = 0;
			}
			else{
				row++;
			}

		}
		if(k == KeyEvent.VK_DOWN) {
			if(row == 0){
				row = 1;
			}
			else{
				row--;
			}
			
		}
	*/
		if(k == KeyEvent.VK_ESCAPE) {
			EscState.back = gsm.getState();
			gsm.setState(GameStateManager.ESCSTATE);
		}		
	}

	//z dziedziczenia
	public void keyReleased(int k) {}
}