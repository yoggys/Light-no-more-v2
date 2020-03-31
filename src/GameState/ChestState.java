package GameState;

import System.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import Player.Inventory;

//class by Mateusz Karbownik
public class ChestState extends GameState {
	
	//zmienne gui
	private Background bg;
	public static String options = "Back";
	private int row;
	private int currentChoice[] = {0,0};
	private int state = 0;

	private Font font;
	private Images image;
	private Random rand = new Random();

	private ArrayList<Integer> chest = new ArrayList<>();
	private int chestGold = 0;

	//konstruktor
	public ChestState(GameStateManager gsm) {
		this.gsm = gsm;
		
		//test
		try {
			bg = new Background("Resources/Backgrounds/darkbg.png");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		font = new Font("Arial", Font.PLAIN, 24);	
		image = new Images();

		rollItems();
		rollGold();
	}
	
	//rysowanie gui
	@Override
	public void draw(Graphics2D g) {	
		bg.draw(g);

		g.setFont(font);
		g.setColor(Color.WHITE);

		for(int i = 0; i < chest.size(); i++) {
				
			if(i == currentChoice[1] && row == 1) {
				image.draw(g, -85 + 126*i, 393, "Resources/Items/selectedframe.png");
			}
			
			image.draw(g, 33 + 60*i, 510, "Resources/Items/"+ Inventory.getname(chest.get(i)) +".png");
		}

		if(state == 1){
			g.drawString(options, 620, 680);
		}

		g.drawString(options, 620, 680);
	}
	
	private void select() {
		if(row == 0){
			gsm.setState(gsm.getLastState());
		}
		if(row == 1 && Inventory.invsize() < 10){

		}
		else{
			state = 1;
		}
	}

	//keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if(k == KeyEvent.VK_UP){
			if(row == 1){
				row = 0;
			}
			else{
				if(chest.size() != 0){
					row++;
				}
			}
		}
		if(k == KeyEvent.VK_DOWN){
			if(row == 0){
				row = 1;
			}
			else{
				if(chest.size() != 0){
					row--;
				}
			}
		}
		if(k == KeyEvent.VK_LEFT){
			if(row == 1){
				if(currentChoice[row] == 0){
					currentChoice[row] = chest.size()-1;
				}
				else{
					currentChoice[row]--;
				}
			}
		}
		if(k == KeyEvent.VK_RIGHT){
			if(row == 1){
				if(currentChoice[row] == chest.size()-1){
					currentChoice[row] = 0;
				}
				else{
					currentChoice[row]++;
				}
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
	}

	private void rollItems(){
		for(int i = 0; i < 1+rand.nextInt(4); i++){
			chest.add(1+rand.nextInt(8));
		}
	}

	private void rollGold(){
		chestGold = 250 + rand.nextInt(250);
	}

	public void openChest(){
		Inventory.sell(chestGold);
	}
}