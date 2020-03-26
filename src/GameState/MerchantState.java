package GameState;

import TileMap.Background;
import Entity.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MerchantState extends GameState {
	
	//zmienne gui
	private Background bg;
	private HUDgold hud;
	private Images image;
	private Font font;
	private String options = "Back";


	//zmienne obslugi
	private int[] currentChoice = {0,0,0};
	private int row = 0;
	private int state[] = {0,0};

	//konstruktor
	public MerchantState(GameStateManager gsm) {	
		this.gsm = gsm;

		try {
			bg = new Background("/Backgrounds/merchbg.png");
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

		bg.draw(g);
		hud.draw(g);
		
		g.setFont(font);
		g.setColor(Color.RED);

		if(row == 0){
			g.setColor(Color.WHITE);
		}
		g.drawString(options, 620, 680);
		if(row == 1){
			g.setColor(Color.RED);
			g.drawString("BUY ITEMS", 40, 240);
			g.setColor(Color.WHITE);
			g.drawString("SELL ITEMS", 40, 470);
		}
		else if(row == 2){
			g.setColor(Color.RED);
			g.drawString("SELL ITEMS", 40, 470);
			g.setColor(Color.WHITE);
			g.drawString("BUY ITEMS", 40, 240);
		}
		else{
			g.setColor(Color.RED);
			g.drawString("SELL ITEMS", 40, 470);
			g.drawString("BUY ITEMS", 40, 240);
		}

		image.draw(g, 33, 280, "/Items/sapot.png");
		image.draw(g, 33 + 60, 280, "/Items/hppot.png");
		image.draw(g, 33 + 120, 280, "/Items/poisonpot.png");
		image.draw(g, 33 + 180, 280, "/Items/curepot.png");
		
		for(int i = 0; i < 4; i++) {
				
			if(i == currentChoice[2] && row == 2) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}

			g.drawString(String.valueOf(Store.buy(i)), 42 + i*60, 370);		
		}

		for(int i = 0; i < Inventory.invsize(); i++) {
				
			if(i == currentChoice[1] && row == 1) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			image.draw(g, 33 + 60*i, 510, "/Items/"+ Inventory.getname(Inventory.getid(i)) +".png");
			g.drawString(String.valueOf(Inventory.getprice(i)), 42 + i*60, 600);
		}
		if(Inventory.invsize() == 0){
			g.setColor(Color.GREEN);
			g.drawString("No items to sell", 40, 530);
		}
		if(state[0] == 1){
			g.setColor(Color.WHITE);
			g.drawString("NOT ENOUGH INVENTORY SPACE!", 40, 680);
			state[0] = 0;
		}
		if(state[1] == 1){
			g.setColor(Color.WHITE);
			g.drawString("NOT ENOUGH MONEY!", 40, 680);
			state[1] = 0;
		}
		
	}
	

	//rysowanie gui
	private void select() {
		if(row == 0){
			gsm.setState(GameStateManager.TOWNSTATE);
		}
		else if(row == 1 && Inventory.getid(currentChoice[row]) != 0 && Inventory.invsize() != 0){
			Inventory.sellitem(Inventory.getid(currentChoice[row]));
			if(currentChoice[row] != 0){
				currentChoice[row]--;
			}
			else if(currentChoice[row] == 0 && Inventory.invsize() != 0){
				currentChoice[row] = 0;
			}
			else{
				row--;
			}
		}
		else if(row == 2){
			if(Inventory.invsize() >= 10){
				state[0] = 1;
			}
			else if(Inventory.getgold() - Store.buy(currentChoice[row]) < 0){
				state[1] = 1;
			}
			else{
				Inventory.buyitem(currentChoice[row]);
			}
		}
	}

	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}

		if(k == KeyEvent.VK_RIGHT) {
			if(row == 1){
				currentChoice[row]++;
				if(currentChoice[row] == Inventory.invsize()) {
					currentChoice[row] = 0;
				}
			}
			if(row == 2){
				currentChoice[row]++;
				if(currentChoice[row] == 4) {
					currentChoice[row] = 0;
				}
			}
		}
		if(k == KeyEvent.VK_LEFT) {
			if(row == 1){
				currentChoice[row]--;
				if(currentChoice[row] == -1) {
					currentChoice[row] = Inventory.invsize()-1;
				}
			}
			if(row == 2){
				currentChoice[row]--;
				if(currentChoice[row] == -1) {
					currentChoice[row] = 3;
				}
			}
			
		}
		if(k == KeyEvent.VK_UP) {
			if(row == 2){
				row = 0;
			}
			else if(Inventory.invsize()==0){
				row += 2;
			}
			else{
				row++;
			}

		}
		if(k == KeyEvent.VK_DOWN) {
			if(Inventory.invsize()==0 && row == 2){
				row -= 2;
			}
			else{
				row--;
			}
			if(row == -1){
				row = 2;
			}
		}
	}

	//z dziedziczenia
	public void keyReleased(int k) {}	
}