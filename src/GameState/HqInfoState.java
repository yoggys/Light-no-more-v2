package GameState;

import TileMap.Background;
import Entity.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class HqInfoState extends GameState {
	
	//zmienne gui
	private Background bg;
	private String options = "Back";
	private Images image;
	private Font font;

	//zmienne obslugi
	private int[] currentChoice = {0,0};
	private int row = 0;

	//konstruktor
	public HqInfoState(GameStateManager gsm) {	
		this.gsm = gsm;
		
		//test
		try {
			bg = new Background("Resources/Backgrounds/hqbg.png");
			font = new Font("Arial", Font.PLAIN, 24);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		image = new Images();
	}
	
	//rysowanie gui
	@Override
	public void draw(Graphics2D g) {

		bg.draw(g);
		
		g.setFont(font);
		
		//INFO COL
		if(row == 1){
			g.setColor(Color.RED);
			g.drawString("RESERVE CHAMPS", 40, 470);
			g.setColor(Color.WHITE);
			g.drawString("CURRENT SQUAD", 40, 170);
		}
		else if(row == 2){
			g.setColor(Color.WHITE);
			g.drawString("RESERVE CHAMPS", 40, 470);			
			g.setColor(Color.RED);
			g.drawString("CURRENT SQUAD", 40, 170);
		}
		else{
			g.setColor(Color.RED);
			g.drawString("RESERVE CHAMPS", 40, 470);
			g.drawString("CURRENT SQUAD", 40, 170);
		}

		//current squad
		for(int i = 0; i < Player.champions.size(); i++) {
			if(i == currentChoice[1] && row == 1) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			if(Player.champions.get(i).getPercent() >= 90){
				image.draw(g, -69 + 200*i, 110, Player.champions.get(i).getAvatar());
				g.drawString(Player.champions.get(i).getName(), 40 + (200*i), 345);		
			}
		}

		//reserve champs
		if(Player.reserve.size() == 0){
			g.setColor(Color.GREEN);
			g.drawString("No champs in reserve", 40, 530);
		}
		else{

		}

		
		//back
		if(row == 0) {
			g.setColor(Color.WHITE);
		}
		else{
			g.setColor(Color.RED);
		}
		g.drawString("Back", 620, 680);
	}
	
	//wybor aktualnego trybu pracy / opcji menu etc.
	private void select() {
		if(row == 0){
			gsm.setState(GameStateManager.HEADQUARTERSSTATE);
		}
	}

	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}

		if(k == KeyEvent.VK_DOWN) {
			if(row == 2){
				row = 0;
			}
			else{
				if(row == 1){
					if(Player.reserve.size() != 0){
						row = 2;
					}
					else{
						row = 0;
					}
				}
				else{
					row++;
				}
			}	
		}
		if(k == KeyEvent.VK_UP) {
			if(row == 0){
				if(Player.reserve.size() != 0){
					row = 2;
				}
				else{
					row = 1;
				}
			}
			else{
				row--;
			}	
		}
		if(k == KeyEvent.VK_LEFT) {
			if(row == 1){
				if(currentChoice[1] == 0){ 
					currentChoice[1] = Player.champions.size()-1; 
				}
				else{
					currentChoice[1]--;
				}
			}
			else if(row == 2){
				if(currentChoice[2] == 0){ 
					currentChoice[2] = Player.reserve.size()-1; 
				}
				else{
					currentChoice[2]--;
				}
			}
		}
		if(k == KeyEvent.VK_RIGHT) {
			if(row == 1){
				if(currentChoice[1] == Player.champions.size()-1){ 
					currentChoice[1] = 0; 
				}
				else{
					currentChoice[1]++;
				}
			}
			else if(row == 2){
				if(currentChoice[2] == Player.reserve.size()-1){ 
					currentChoice[2] = 0; 
				}
				else{
					currentChoice[2]++;
				}
			}
		}
		if(k == KeyEvent.VK_ESCAPE) {
			EscState.back = gsm.getState();
			gsm.setState(GameStateManager.ESCSTATE);
		}	
	}

	//z dziedziczenia
	public void keyReleased(int k) {}
}