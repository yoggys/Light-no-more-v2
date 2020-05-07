package pl.edu.pw.fizyka.pojava.LNM.GameState;

import java.util.ArrayList;
import pl.edu.pw.fizyka.pojava.LNM.GameState.Tutorial.*;

//by Cyprian Siwy & Mateusz Karbownik
public class SceneManager {

	private ArrayList<Scene> scenes;
	private int currentScene;
	private int lastScene;

	public static int MENU = 0;
	public static int TOWN = 1;
	public static int HELP = 2;
	public static int EASTEREGG = 3;
	public static int DARK = 4;
	public static int TAVERN = 5;
	public static int CHARLATAN = 6;
	public static int MERCHANT = 7;
	public static int HEADQUARTERS = 8;
	public static int HQSQUAD = 9;
	public static int HQUPGRADE = 10;
	public static int HQINFO = 11;
	public static int ESC = 12;
	public static int LANG = 13;
	public static int CHEST = 14;
	public static int TUT0 = 15;
	public static int TUT1 = 16;
	public static int TUT2 = 17;
	public static int TUT3 = 18;
	public static int TUT4 = 19;
	public static int TUT5 = 20;
	


	public SceneManager() {

		scenes = new ArrayList<Scene>();

		currentScene = LANG;
		scenes.add(new MenuScene(this));
		scenes.add(new TownScene(this));
		scenes.add(new HelpScene(this));
		scenes.add(new EasterEgg(this));
		scenes.add(new DarkScene(this));
		scenes.add(new TavernScene(this));
		scenes.add(new CharlatanScene(this));
		scenes.add(new MerchantScene(this));
		scenes.add(new HeadquartersScene(this));
		scenes.add(new HqSquadScene(this));
		scenes.add(new HqUpgradeScene(this));
		scenes.add(new HqInfoScene(this));
		scenes.add(new EscScene(this));
		scenes.add(new LangScene(this));
		scenes.add(new ChestScene(this));

		scenes.add(new Tutorial0(this));
		scenes.add(new Tutorial1(this));
		scenes.add(new Tutorial2(this));
		scenes.add(new Tutorial3(this));
		scenes.add(new Tutorial4(this));
	}

	public void setState(int state) {
		lastScene = currentScene;
		currentScene = state;
	}

	public void draw(java.awt.Graphics2D g) {
		scenes.get(currentScene).draw(g);
	}

	public void keyPressed(int k) {
		scenes.get(currentScene).keyPressed(k);
	}

	public void keyReleased(int k) {
		scenes.get(currentScene).keyReleased(k);
	}

	public int getState() {
		return currentScene;
	}

	public int getLastState() {
		return lastScene;
	}
}
