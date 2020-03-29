package GameState;

import Entity.*;
import System.*;
import Player.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//class by Mateusz Karbownik
public class TavernState extends GameState {
	
	//zmienne gui
	private Background bg;
	private HUDgold hud;
	private Images image;
	private Font font;

	//zmienne obslugi
	private int[] currentChoice = {0, 0, 0};
	private int row = 0;
	private int state = 0;
	private Random random = new Random();

	private ArrayList<Integer> priceSell = new ArrayList<>(Arrays.asList(rand(),rand(),rand())); 
	private ArrayList<Integer> priceBuy = new ArrayList<>(Arrays.asList(rand(),rand(),rand())); 
	
	//konstruktor
	public TavernState(GameStateManager gsm) {	
		this.gsm = gsm;
		
		//test
		try {
			bg = new Background("Resources/Backgrounds/tavernbg1.png");
			font = new Font("Arial", Font.PLAIN, 18);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		image = new Images();
		hud = new HUDgold();

		Player.reserve.add(new Champion(25, 10, 20, "test1"));
		Player.reserve.add(new Champion(25, 10, 20, "test2"));
		Player.reserve.add(new Champion(100, 10, 30, "test3"));

		Player.tavernChampions.add(new Champion(25, 10, 20, "test4"));
		Player.tavernChampions.add(new Champion(25, 10, 20, "test5"));
		Player.tavernChampions.add(new Champion(100, 10, 30, "test6"));
	}
	
	//rysowanie gui
	@Override
	public void draw(Graphics2D g) {

		if(state == 1){
			Player.buyChampion(Player.tavernChampions.get(currentChoice[row]));
			Player.tavernChampions.remove(currentChoice[row]);
			Inventory.pay(priceSell.get(currentChoice[row]));

			if(currentChoice[row] != 0){
				currentChoice[row]--;
			}

			if(Player.tavernChampions.size() == 0){
				if(Player.reserve.size() > 0){
					row++;
				}
				else{
					row=0;
				}
			}
			state = 0;
		}
		else if(state == 2){
			Player.sellChampion(currentChoice[row]);
			Inventory.sell(priceBuy.get(currentChoice[row]));

			if(currentChoice[row] != 0){
				currentChoice[row]--;
			}
			
			if(Player.reserve.size() == 0){
				if(Player.tavernChampions.size() > 0){
					row--;
				}
				else{
					row=0;
				}
			}
			state = 0;
		}

		bg.draw(g);	
		hud.draw(g);	
		g.setFont(font);

		//INFO COL
		if(row == 1){
			g.setColor(Color.RED);
			g.drawString("RESERVE CHAMPS (SELL)", 40, 440);
			g.setColor(Color.WHITE);
			g.drawString("TAVERN CHAMPS (BUY)", 40, 140);
		}
		else if(row == 2){
			g.setColor(Color.WHITE);
			g.drawString("RESERVE CHAMPS (SELL)", 40, 440);			
			g.setColor(Color.RED);
			g.drawString("TAVERN CHAMPS (BUY)", 40, 140);
		}
		else{
			g.setColor(Color.RED);
			g.drawString("RESERVE CHAMPS (SELL)", 40, 440);
			g.drawString("TAVERN CHAMPS (BUY)", 40, 140);
		}

		//card draw	
		if(row == 1 && Player.tavernChampions.size()>0){
			Player.championTavCard(currentChoice[row], g, image, 900, 180);
		}
		if(row == 2 && Player.reserve.size()>0){
			Player.championResCard(currentChoice[row], g, image, 900, 180);
		}
		

		//tavern champs
		if(Player.tavernChampions.size() == 0){
			g.setColor(Color.GREEN);
			g.drawString("No new champs in tavern", 40, 200);
		}
		else{
			for(int i = 0; i < Player.tavernChampions.size(); i++) {
				g.setColor(Color.ORANGE);
				g.drawString(Integer.toString(this.priceSell.get(i)), 40 + (150*i), 330);
				if(i == currentChoice[row] && row == 1) {
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(this.priceSell.get(i)), 40 + (150*i), 330);
					image.draw(g, -69 + 150*i, 80, "Resources/HUD/selectedframe.png");
				}
				else {
					g.setColor(Color.RED);
				}
				image.draw(g, -69 + 150*i, 80, Player.tavernChampions.get(i).getAvatar());
				g.drawString(Player.tavernChampions.get(i).getName(), 40 + (150*i), 300);		
			}
		}

		//reserve champs
		if(Player.reserve.size() == 0){
			g.setColor(Color.GREEN);
			g.drawString("No champs in reserve", 40, 500);
		}
		else{
			for(int i = 0; i < Player.reserve.size(); i++) {
				g.setColor(Color.ORANGE);
				g.drawString(Integer.toString(this.priceBuy.get(i)), 40 + (150*i), 645);
				if(i == currentChoice[row] && row == 2) {
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(this.priceBuy.get(i)), 40 + (150*i), 645);
					image.draw(g, -69 + 150*i, 380, "Resources/HUD/selectedframe.png");
				}
				else {
					g.setColor(Color.RED);
				}
				image.draw(g, -69 + 150*i, 380, Player.reserve.get(i).getAvatar());
				g.drawString(Player.reserve.get(i).getName(), 40 + (150*i), 615);			
			}
		}

		if(state == 3){
			g.setColor(Color.WHITE);
			g.drawString("NOT ENOUGH MONEY!", 40, 680);
			state = 0;
		}

		if(state == 4){
			g.setColor(Color.WHITE);
			g.drawString("RESERVE FULL!", 40, 680);
			state = 0;
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
			gsm.setState(GameStateManager.TOWNSTATE);
		}
		else if(row == 1 && Player.tavernChampions.size() > 0 && Player.reserve.size() < 3 && Inventory.getgold() - priceSell.get(currentChoice[row]) >= 0 && state == 0){
			state = 1;
		}
		else if(row == 2 && Player.reserve.size() > 0 && state == 0){
			state = 2;
		}
		if(Inventory.getgold() - priceSell.get(currentChoice[row]) < 0 && row == 1){
			state = 3;
		}
		if(Player.reserve.size() >= 3 && row == 1){
			state = 4;
		}

	}

	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if(k == KeyEvent.VK_DOWN) {
			if(row == 0){
				if(Player.tavernChampions.size() > 0){
					row = 1;
				}
				else{
					if(Player.reserve.size() > 0){
						row = 2;
					}
				}
			}
			else if(row == 1){
				if(Player.reserve.size() > 0){
					row = 2;
				}
				else{
					row = 0;
				}
			}
			else if(row == 2){
				row = 0;
			}
		}

		if(k == KeyEvent.VK_UP) {
			if(row == 0){
				if(Player.reserve.size() > 0){
					row=2;
				}
				else{
					if(Player.tavernChampions.size() > 0){
						row = 1;
					}
				}
			}
			else if(row == 1){
				row = 0;
			}
			else if(row == 2){
				if(Player.tavernChampions.size() > 0){
					row = 1;
				}
				else{
					row = 0;
				}
			}
		}
		if(k == KeyEvent.VK_LEFT) {
			if(row == 1){
				if(currentChoice[row] == 0){ 
					currentChoice[row] = Player.tavernChampions.size()-1; 
				}
				else{
					currentChoice[row]--;
				}
			}
			else if(row == 2){
				if(currentChoice[row] == 0){ 
					currentChoice[row] = Player.reserve.size()-1; 
				}
				else{
					currentChoice[row]--;
				}
			}
		}
		if(k == KeyEvent.VK_RIGHT) {
			if(row == 1){
				if(currentChoice[row] == Player.tavernChampions.size()-1){ 
					currentChoice[row] = 0; 
				}
				else{
					currentChoice[row]++;
				}
			}
			else if(row == 2){
				if(currentChoice[row] == Player.reserve.size()-1){ 
					currentChoice[row] = 0; 
				}
				else{
					currentChoice[row]++;
				}
			}
		}
		if(k == KeyEvent.VK_ESCAPE) {
			EscState.back = gsm.getState();
			gsm.setState(GameStateManager.ESCSTATE);
		}
	}

	public int rand(){
		return 500+random.nextInt(500);
	}

	//z dziedziczenia
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
	}
}