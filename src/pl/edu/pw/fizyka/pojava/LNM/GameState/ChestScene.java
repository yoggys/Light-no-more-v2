package pl.edu.pw.fizyka.pojava.LNM.GameState;

import pl.edu.pw.fizyka.pojava.LNM.System.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import pl.edu.pw.fizyka.pojava.LNM.Player.Inventory;

//class by Mateusz Karbownik
public class ChestScene extends Scene {

	// zmienne gui
	private Background bg;
	public static String options[];
	private int row;
	private HUDgold hud;
	private int currentChoice[] = { 0, 0, 0};
	private int state = 0;

	private Font font;
	private Images image;
	private Random rand = new Random();

	private ArrayList<Integer> chest = new ArrayList<>();
	private int chestGold = 0;

	// konstruktor
	public ChestScene(SceneManager gsm) {
		this.gsm = gsm;

		// test
		try {
			bg = new Background("Resources/Backgrounds/chest2bg.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		font = new Font("Arial", Font.PLAIN, 24);
		hud = new HUDgold();
		image = new Images();

		rollItems();
		rollGold();
	}

	// rysowanie gui
	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		hud.draw(g);

		g.setFont(font);

		if(state == 3){ 
			Inventory.removeItems(currentChoice[row]);
			if (currentChoice[row] != 0) {
				currentChoice[row]--;
			}

			if (Inventory.invSize() == 0) {
				if (chest.size() > 0) {
					row--;
				} else {
					row = 0;
				}
			}
			state = 0;
		}

		if (state == 1) {
			if (Inventory.invSize() >= 10) {
				g.setColor(Color.WHITE);
				g.drawString(options[4], 40, 680);
			} else {

				Inventory.addItem(chest.get(currentChoice[row]));
				chest.remove(currentChoice[row]);
				if (currentChoice[row] != 0) {
					currentChoice[row]--;
				}

				if (chest.size() == 0) {
					if (Inventory.invSize() > 0) {
						row++;
					} else {
						row = 0;
					}
				}
				state = 0;
			}
		}
		if (state == 2) {

			if(chest.size() >= 10) {
				g.setColor(Color.WHITE);
				g.drawString(options[5], 40, 680);
			} else {
				
				chest.add(Inventory.getId(currentChoice[row]));
				Inventory.removeItems(currentChoice[row]);
				if (currentChoice[row] != 0) {
					currentChoice[row]--;
				}

				if (Inventory.invSize() == 0) {
					if (chest.size() > 0) {
						row--;
					} else {
						row = 0;
					}
				}
				state = 0;
			}
		}

		// INFO COL
		if (row == 1) {
			g.setColor(Color.RED);
			g.drawString(options[1], 40, 140);
			g.setColor(Color.WHITE);
			g.drawString(options[2], 40, 440);
		} else if (row == 2) {
			g.setColor(Color.WHITE);
			g.drawString(options[1], 40, 140);
			g.setColor(Color.RED);
			g.drawString(options[2], 40, 440);

		} else {
			g.setColor(Color.RED);
			g.drawString(options[2], 40, 440);
			g.drawString(options[1], 40, 140);
		}

		if (Inventory.invSize() == 0) {
			g.setColor(Color.GREEN);
			g.drawString(options[6], 40, 200);
		} else {
			for (int i = 0; i < Inventory.invSize(); i++) {

				if (i == currentChoice[2] && row == 2) {
					image.draw(g, -65 + 126 * i, 93, "Resources/Items/selectedframe.png");
				}
				image.draw(g, 53 + 126 * i, 210, "Resources/Items/" + Inventory.getName(Inventory.getId(i)) + ".png");
			}
		}
		if (chest.size() == 0) {
			g.setColor(Color.GREEN);
			g.drawString(options[7], 40, 500);
		} else {
			for (int i = 0; i < chest.size(); i++) {

				if (i == currentChoice[1] && row == 1) {
					image.draw(g, -65 + 126 * i, 393, "Resources/Items/selectedframe.png");
				}

				image.draw(g, 53 + 126 * i, 510, "Resources/Items/" + Inventory.getName(chest.get(i)) + ".png");
			}
		}
		if (row == 0 && currentChoice[0] == 0) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.RED);
		}
		g.drawString(options[0], 500, 680);

		if (row == 0 && currentChoice[0] == 1) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.RED);
		}
		g.drawString(options[3] + ": "+chestGold, 620, 680);
		
	}

	private void select() {
		if (row == 2) {
			state = 2;
		}
		if (row == 1) {
			state = 1;
		}
		if (row == 0 && currentChoice[0] == 0) {
			gsm.setScene(gsm.getLastScene());
		}
		if (row == 0 && currentChoice[0] == 1) {
			openChest();
		}

	}

	// keyevent poszczegolnych klawiszy
	@Override
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_UP) {
			row++;
			if (row == 3) {
				row = 0;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			row--;
			if (row == -1) {
				row = 2;
			}
		}
		if (k == KeyEvent.VK_LEFT) {
			if (row == 1) {
				if (currentChoice[row] == 0) {
					currentChoice[row] = chest.size() - 1;
				} else {
					currentChoice[row]--;
				}
			}
			if(row == 0){
				currentChoice[row]--;
				if(currentChoice[row] < 0){
					currentChoice[row] = 1;
				}
			}
			if(row == 2){
				currentChoice[row]--;
				if(currentChoice[row] < 0){
					currentChoice[row] = Inventory.invSize()-1;
				}
			}
		}
		if (k == KeyEvent.VK_RIGHT) {
			if (row == 1) {
				if (currentChoice[row] == chest.size() - 1) {
					currentChoice[row] = 0;
				} else {
					currentChoice[row]++;
				}
			}
			if(row == 0){
				currentChoice[row]++;
				if (currentChoice[row] == 2){
					currentChoice[row] = 0;
				}
			}
			if(row == 2){
				currentChoice[row]++;
				if (currentChoice[row] == Inventory.invSize()){
					currentChoice[row] = 0;
				}
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}

		if (k == KeyEvent.VK_SPACE) {
			if(row == 2){
				int id = Inventory.getId(currentChoice[2]);
				Inventory.usePotion(id);
				if(id == 6 || id == 7){
					state = 3;
				}
			}
		}
	}

	private void rollItems() {
		for (int i = 0; i < 1 + rand.nextInt(4); i++) {
			chest.add(1 + rand.nextInt(8));
		}
	}

	private void rollGold() {
		chestGold = 250 + rand.nextInt(250);
	}

	public void openChest() {
		Inventory.sell(chestGold);
		chestGold = 0;
	}
}