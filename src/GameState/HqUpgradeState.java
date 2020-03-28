package GameState;

import TileMap.Background;
import Entity.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class HqUpgradeState extends GameState {
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
	
	//konstruktor
	public HqUpgradeState(GameStateManager gsm) {	
		this.gsm = gsm;
		
		//test
		try {
			bg = new Background("Resources/Backgrounds/hqbg3.png");
			font = new Font("Arial", Font.PLAIN, 18);
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
		
		if(row != 0){
			Player.championCurCard(currentChoice[1], g, image, 499, 20);
		}

		g.setFont(new Font("Arial", Font.PLAIN, 24));
		g.setColor(Color.RED);
		if(row == 0){
			g.setColor(Color.WHITE);
		}
		g.drawString(options, 620, 680);

		g.setColor(Color.RED);
		if(row == 1){
			g.setColor(Color.WHITE);
		}
		g.drawString("UPGRADE", 586, 450);

		for(int i = 0; i < Player.champions.size(); i++) {
			if(i == currentChoice[1] && row == 1) {
				g.setColor(Color.WHITE);
				image.draw(g, 298 + 200*i, 380, "Resources/HUD/selectedframe.png");
			}
			else {
				g.setColor(Color.RED);
			}

			image.draw(g, 298 + 200*i, 380, Player.champions.get(i).getAvatar());
			g.drawString("500", 427 + (200*i), 615);		

		}
		if(state == 1){
			g.setColor(Color.WHITE);
			g.drawString("NOT ENOUGH MONEY!", 40, 680);
			state= 0;
		}
	}

	//wybor aktualnego trybu pracy / opcji menu etc.
	private void select() {
		if(row == 0){
			gsm.setState(GameStateManager.HEADQUARTERSSTATE);
		}
		else if(row == 1){
			if(Inventory.getgold() - 500 >= 0 && Player.champions.get(currentChoice[1]).skills != null){
				Player.champions.get(currentChoice[1]).upgradeChamp();
				Inventory.pay(500);
			}
			else{
				state = 1;
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
				if(currentChoice[row] > 2) {
					currentChoice[row] = 0;
				}
			}
		}
		if(k == KeyEvent.VK_LEFT) {
			if(row == 1){
				currentChoice[row]--;
				if(currentChoice[row] < 0) {
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
		if(k == KeyEvent.VK_ESCAPE) {
			EscState.back = gsm.getState();
			gsm.setState(GameStateManager.ESCSTATE);
		}
	}

	//z dziedziczenia
	public void keyReleased(int k) {}
}