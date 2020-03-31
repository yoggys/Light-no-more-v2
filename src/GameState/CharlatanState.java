package GameState;

import System.*;
import Player.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//class by Mateusz Karbownik
public class CharlatanState extends GameState {
	
	//zmienne gui
	private Background bg;
	public static String options[];
	private HUDgold hud;
	private Images image;
	private Font font;

	//zmienne obslugi
	private int[] currentChoice = {0,0};
	private int row = 0;
	private int state = 0;
	
	//konstruktor
	public CharlatanState(GameStateManager gsm) {	
		this.gsm = gsm;
		
		//test
		try {
			bg = new Background("Resources/Backgrounds/charbg.png");
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

		double check[] = {0,0,0};

		bg.draw(g);
		hud.draw(g);
		
		g.setFont(font);
		g.setColor(Color.RED);


		
		if(row == 0){
			g.setColor(Color.WHITE);
		}
		g.drawString(options[0], 620, 680);

		g.setColor(Color.RED);
		if(row == 1){
			g.setColor(Color.WHITE);
		}
		g.drawString(options[1], 615, 450);

		for(int i = 0; i < 3; i++){
			if(Player.champions.get(i).payHeal() >= 100){
				check[i] = 0;
			}
			else if(Player.champions.get(i).payHeal() <= 99 && Player.champions.get(i).payHeal() >= 10){
				check[i] = 0.5;
			}
			else{
				check[i] = 1;
			}
		}

		//antyglitch (jakby wybral w gore i wyleczyl w walce)
		if((int)Player.champions.get(0).payHeal() == 0 && (int)Player.champions.get(1).payHeal() == 0  && (int)Player.champions.get(2).payHeal() == 0 ){
			currentChoice[row] = 0;
			row = 0;
		}

		for(int i = 0; i < Player.champions.size(); i++) {
			if(i == currentChoice[1] && row == 1) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.ORANGE);
			}

			if(Player.champions.get(i).getPercent() >= 90){
				image.draw(g, 298 + 200*i, 380, "Resources/HUD/highframe2.png");					
			}
			
			else if(Player.champions.get(i).getPercent() < 90 && Player.champions.get(i).getPercent() >= 50){
				image.draw(g, 298 + 200*i, 380, "Resources/HUD/midframe2.png");
			}
			else{
				image.draw(g, 298 + 200*i, 380, "Resources/HUD/lowframe2.png");
			}

			image.draw(g, 298 + 200*i, 380, Player.champions.get(i).getAvatar());	
			g.drawString(String.valueOf((int)Player.champions.get(i).payHeal()), 427 + (200*i) + (int)(check[i]*12), 615);
		}
		if(state == 1){
			g.setColor(Color.WHITE);
			g.drawString(options[2], 40, 680);
			state = 0;
		}
	}
	
	//leczenie do selecta nizej
	public void heal(int i){
		Inventory.pay((int)Player.champions.get(i).payHeal());
		Player.champions.get(i).setHp();
		currentChoice[1]++;

		if((int)Player.champions.get(0).payHeal() == 0 && (int)Player.champions.get(1).payHeal() == 0  && (int)Player.champions.get(2).payHeal() == 0 ){
			row = 0;	
		}
		else{
			if(currentChoice[1] > 2){
				currentChoice[1] = 0;
			}
			while((int)Player.champions.get(currentChoice[row]).payHeal() == 0){
				currentChoice[row]++;
				if(currentChoice[row] >= 3) {
					currentChoice[row] = 0;
				}
			}
		}
	}

	//wybor aktualnego trybu pracy / opcji menu etc.
	private void select() {
		if(row == 0){
			gsm.setState(GameStateManager.TOWNSTATE);
		}
		else if(row == 1){
			if(currentChoice[row] == 0 && Inventory.getgold() - (int)Player.champions.get(0).payHeal() >= 0){
				heal(0);
			}
			else if(currentChoice[row] == 1 && Inventory.getgold() - (int)Player.champions.get(1).payHeal() >= 0){
				heal(1);
			}
			else if(currentChoice[row] == 2 && Inventory.getgold() - (int)Player.champions.get(2).payHeal() >= 0){
				heal(2);
			}
			else{
				state = 1;
			}
		}
	}

	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_RIGHT) {
			if(row == 1){
				currentChoice[row]++;
				if(currentChoice[row] > 2) {
					currentChoice[row] = 0;
				}
				while((int)Player.champions.get(currentChoice[row]).payHeal() == 0){
					currentChoice[row]++;
					if(currentChoice[row] > 2) {
						currentChoice[row] = 0;
					}
				}
			}
		}
		if(k == KeyEvent.VK_LEFT) {
			if(row == 1){
				currentChoice[row]--;
				if(currentChoice[row] < 0) {
					currentChoice[row] = 2;
				}
				while((int)Player.champions.get(currentChoice[row]).payHeal() == 0){
					currentChoice[row]--;
					if(currentChoice[row] < 0) {
						currentChoice[row] = 2;
					}
				}
			}
			
		}
		if(k == KeyEvent.VK_UP) {
			if(row == 1){
				row = 0;
			}
			else{
				if((int)Player.champions.get(0).payHeal() != 0 || (int)Player.champions.get(1).payHeal() != 0  || (int)Player.champions.get(2).payHeal() != 0 ){
					row++;
					while((int)Player.champions.get(currentChoice[row]).payHeal() == 0){
						currentChoice[row]++;
						if(currentChoice[row] >= 3) {
							currentChoice[row] = 0;
						}
					}
				}
			}
			
		}
		if(k == KeyEvent.VK_DOWN) {
			if(row == 0){
				if((int)Player.champions.get(0).payHeal() != 0 || (int)Player.champions.get(1).payHeal() != 0  || (int)Player.champions.get(2).payHeal() != 0 ){
					row = 1;
				}
			}
			else{
				row--;
				while((int)Player.champions.get(currentChoice[row]).payHeal() == 0){
					currentChoice[row]++;
					if(currentChoice[row] >= 3) {
						currentChoice[row] = 0;
					}
				}
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
