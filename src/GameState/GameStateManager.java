package GameState;

import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	private int lastState;
	
	public static int MENUSTATE = 0;
	public static int TOWNSTATE = 1;
	public static int HELPSTATE = 2;
	public static int EASTEREGG = 3;
	public static int DARKSTATE = 4;
	public static int TAVERNSTATE = 5;
	public static int CHARLATANSTATE = 6;
	public static int MERCHANTSTATE = 7;
	public static int HEADQUARTERSSTATE = 8;
	public static int HQSQUADSTATE = 9;
	public static int HQUPGRADESTATE = 10;
	public static int HQINFOSTATE = 11;
	public static int ESCSTATE = 12;
	public static int DUNGEONSTATE = 13;
	public static int LANGSTATE = 14;
	public static int CHESTSTATE = 15;

	
	public GameStateManager() {
		
		gameStates = new ArrayList<GameState>();
		
		currentState = LANGSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new TownState(this));
		gameStates.add(new HelpState(this));
		gameStates.add(new EasterEgg(this));
		gameStates.add(new DarkState(this));
		gameStates.add(new TavernState(this));
		gameStates.add(new CharlatanState(this));
		gameStates.add(new MerchantState(this));
		gameStates.add(new HeadquartersState(this));
		gameStates.add(new HqSquadState(this));
		gameStates.add(new HqUpgradeState(this));
		gameStates.add(new HqInfoState(this));
		gameStates.add(new EscState(this));
		gameStates.add(new DungeonState(this));
		gameStates.add(new LangState(this));
		gameStates.add(new ChestState(this));
	}
	
	public void setState(int state) {
		lastState = currentState;
		currentState = state;
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}

	public int getState(){
		return currentState;
	}

	public int getLastState(){
		return lastState;
	}
}









