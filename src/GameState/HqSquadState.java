package GameState;

import TileMap.Background;
import Entity.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class HqSquadState extends GameState {
	
	//zmienne gui
	private Background bg;
	private Images image;
	private Font font;

	//zmienne obslugi
	private int[] currentChoice = {0, 0, 0};
	private boolean[] lock = {false,false};
	private int row = 0;

	//konstruktor
	public HqSquadState(GameStateManager gsm) {	
		this.gsm = gsm;
		
		//test
		try {
			bg = new Background("Resources/Backgrounds/hqbg3.png");
			font = new Font("Arial", Font.PLAIN, 18);
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
			g.drawString("RESERVE CHAMPS", 40, 440);
			g.setColor(Color.WHITE);
			g.drawString("CURRENT SQUAD", 40, 140);
		}
		else if(row == 2){
			g.setColor(Color.WHITE);
			g.drawString("RESERVE CHAMPS", 40, 440);			
			g.setColor(Color.RED);
			g.drawString("CURRENT SQUAD", 40, 140);
		}
		else{
			g.setColor(Color.RED);
			g.drawString("RESERVE CHAMPS", 40, 440);
			g.drawString("CURRENT SQUAD", 40, 140);
		}

		//card draw	
		if(!lock[0] && !lock[1]) {	
			if(row == 1){
				Player.championCurCard(currentChoice[1], g, image, 900, 180);
			}
			if(row == 2){
				Player.championResCard(currentChoice[2], g, image, 900, 180);
			}
		}
		else{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 24));
			g.drawString("Change current squad", 770, 110);
			g.setColor(Color.RED);
			g.drawString("THIS", 720, 160);
			g.setColor(Color.GREEN);
			g.drawString("FOR", 1020, 160);
			g.setFont(font);

			if(lock[0]) {		
				Player.championCurCard(currentChoice[1], g, image, 600, 180);
				Player.championResCard(currentChoice[2], g, image, 900, 180);	
			}
			if(lock[1]) {
				Player.championResCard(currentChoice[2], g, image, 900, 180);
				Player.championCurCard(currentChoice[1], g, image, 600, 180);
			}
		}


		//current squad
		for(int i = 0; i < Player.champions.size(); i++) {
			if((i == currentChoice[1] && row == 1) || (i == currentChoice[1] && lock [0])) {
				g.setColor(Color.WHITE);
				image.draw(g, -69 + 150*i, 80, "Resources/HUD/selectedframe.png");
			}
			else {
				g.setColor(Color.RED);
			}
			image.draw(g, -69 + 150*i, 80, Player.champions.get(i).getAvatar());
			g.drawString(Player.champions.get(i).getName(), 40 + (150*i), 315);		
		}

		//reserve champs
		if(Player.reserve.size() == 0){
			g.setColor(Color.GREEN);
			g.drawString("No champs in reserve", 40, 500);
		}
		else{
			for(int i = 0; i < Player.reserve.size(); i++) {
				if((i == currentChoice[2] && row == 2) || (i == currentChoice[2] && lock [1])) {
					g.setColor(Color.WHITE);
					image.draw(g, -69 + 150*i, 380, "Resources/HUD/selectedframe.png");
				}
				else {
					g.setColor(Color.RED);
				}
				image.draw(g, -69 + 150*i, 380, Player.reserve.get(i).getAvatar());
				g.drawString(Player.reserve.get(i).getName(), 40 + (150*i), 615);		
			}
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
		boolean done = false;
		if(row == 0){
			gsm.setState(GameStateManager.HEADQUARTERSSTATE);
		}
		if(row == 1 && Player.reserve.size() > 0 && !lock[0]){
			lock[0] = true;
			done = true;
			row = 2;
		}
		if(row == 2 && !lock[1] && !done){
			lock[1] = true;
			row = 1;
		}
		if(lock[0] && lock[1]){
			Player.changeSquad(currentChoice[1], currentChoice[2]);
			lock[0] = false;	
			lock[1] = false;
		}
	}

	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}

		if(k == KeyEvent.VK_DOWN && !lock[0] && !lock[1]) {
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
		if(k == KeyEvent.VK_UP && !lock[0] && !lock[1]) {
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