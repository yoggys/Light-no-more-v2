package pl.edu.pw.fizyka.pojava.LNM.GameState;

import pl.edu.pw.fizyka.pojava.LNM.Entity.*;
import pl.edu.pw.fizyka.pojava.LNM.Entity.Event;
import pl.edu.pw.fizyka.pojava.LNM.Entity.Event.eventType;
import pl.edu.pw.fizyka.pojava.LNM.System.*;
import pl.edu.pw.fizyka.pojava.LNM.Player.*;
import java.awt.*;
import java.awt.event.KeyEvent;


//by Cyprian Siwy
public class DarkScene extends Scene {

	private Background bg;
	private Champion activeChamp = null;
	private int activeChampId = 999;
	private Effect poison;
	private Effect healOverTime;

	public static int state = 0;

	private Skill selectedSkill = null;
	private int selectedSkillId = 999;

	private Someone target = null;
	private int targetId = 999;

	private Someone enemy = null;

	private int currentChoice = 0;

	private Font font;
	private Font font2;

	private Dungeon activeDungeon;
	private Room activeRoom;
	private Door activeDoor;
	private Event activeEvent;
	private boolean tmp = true;

	private Images image = new Images();

	public enum dungeonState {
		CombatPhase, MovementPhace
	};

	private dungeonState dungState = dungeonState.CombatPhase;
	private float speed = 600; // pixel per secend

	Champion emptyChamp = new Champion(0, 0, "", "Resources/Entity/patyczak.png", new Vector2D(-20, -420) );

	private Vector2D firstChampPos = new Vector2D(200, 600);
	private Vector2D firstEnemyPos = new Vector2D(600, 600);

	private Vector2D firstChampMovemntPos = new Vector2D(200, 600);

	private Vector2D imput = Vector2D.zero;

	private MovementManager MM = new MovementManager();


	long lastTime;
	long currentTime;
	float timeStep; 

	EnemyBrain enemyAI = new EnemyBrain();


	public DarkScene(SceneManager gsm) {
		changeStateTo(dungeonState.MovementPhace);

		activeChamp = emptyChamp;
		poison = new Effect(2, 3);
		healOverTime = new Effect(0, 3);

		//tworzenie umiejętności
		Skill skillPoison = new Skill("Poison", 50, 2, poison);
		Skill skillSlise = new Skill("Slise", 0, 0);
		Skill skillSmite = new Skill("Smite", 0, 0);
		Skill skillHeal = new Skill("Heal", 0, 0, healOverTime);

		Skill skillBite = new Skill("Bite", 7, 0);
		Skill skillWeekBite = new Skill("Bite", 4, 0);
		Skill skillSlash = new Skill("Slash", 3, 0);
		Skill skillAcidVomit = new Skill("Acid Vomit",10,5);

		//Tworzenie Bochaterów i przeciwników
		Player.champions.add(new Champion(25, 20, "Yogi" , "Resources/Entity/patyczak.png", new Vector2D(-20, -420) ));
		Player.champions.add(new Champion(25, 20, "Sasha",  "Resources/Entity/patyczak.png", new Vector2D(-20, -420) ));
		Player.champions.add(new Champion(100, 30, "Siwy" , "Resources/Entity/patyczak.png", new Vector2D(-20, -420) ));
	
		Player.enemysBase.add( new Someone(20, 10, "Wolf" , "Resources/Entity/wolf.png", new Vector2D(-120, -300) ) );
			Player.enemysBase.get(0).addSkill(new Skill(skillBite));
		Player.enemysBase.add( new Someone(50, 10, "Zombie" , "Resources/Entity/zombie.png", new Vector2D(-20, -420) ) );
			Player.enemysBase.get(1).addSkill(new Skill(skillSlash));
			Player.enemysBase.get(1).addSkill(new Skill(skillAcidVomit));
		Player.enemysBase.add( new Someone(10, 0, "Rat" , "Resources/Entity/rat.png", new Vector2D(-50, -220) ) );
			Player.enemysBase.get(2).addSkill(new Skill(skillWeekBite));

		//Dawanie umiejętności
		Player.champions.get(0).addSkill(new Skill(skillSlise));
		Player.champions.get(0).addSkill(new Skill(skillSmite));
		Player.champions.get(1).addSkill(new Skill(skillHeal));
		Player.champions.get(1).addSkill(new Skill(skillPoison));
		Player.champions.get(2).addSkill(new Skill(skillSmite));
		Player.champions.get(2).addSkill(new Skill(skillHeal));

		activeDungeon = new Dungeon(Player.currentDungeon);
		activeRoom = activeDungeon.rooms.get(0);

 		this.gsm = gsm;

		try 
		{
			bg = new Background("Resources/Backgrounds/fightbg.png");
			
			font = new Font("Arial", Font.PLAIN, 12);
			font2 = new Font("Arial", Font.BOLD, 24);
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics2D g) {

		if(state == 1){
			loadDungeon();
			state = 0;
		}
		
		//sprawdzanie czasu trwania funcji draw potrzebne do uzyskanie prędkości niezależnej od liczby klatek
		lastTime = currentTime;
		currentTime = System.currentTimeMillis();
		if(lastTime!=0)
		{
			timeStep = currentTime-lastTime;
			timeStep/=1000;
		}
		bg.draw(g);
		
		g.setFont(font);
		g.setColor(Color.WHITE);

		//rysowanie drzwi
		for (Door door : activeRoom.doors) 
			{
				image.draw(g, (int)( door.posX + bg.pos.x) - 280, 170, "Resources/Entity/doors.png");
			}

			//róch bochaterów poza walką
		if (dungState == dungeonState.MovementPhace) 
		{
			if( imput.x == Vector2D.right.x && firstChampPos.x - bg.pos.x <activeRoom.lenght)
			{
				if(firstChampPos.x >= 500 )
					bg.pos = MM.moveToDirection(bg.pos, Vector2D.multiply(imput, -1), speed, timeStep);
				else
					firstChampPos = MM.moveToDirection(firstChampPos, imput, speed, timeStep);
			}

			if( imput.x == Vector2D.left.x )
			{
				if( bg.pos.x<0 && firstChampPos.x <= 100 )
					bg.pos = MM.moveToDirection(bg.pos, Vector2D.multiply(imput, -1), speed, timeStep);	
				else
				{
					firstChampPos = MM.moveToDirection(firstChampPos, imput, speed, timeStep);
					if(firstChampPos.x<0)
						firstChampPos.x=0;
				}
			}
						
			//rysowanie napisów nad drzwiami aby poinformować o możliwości ich aktywacji
			for (Door door : activeRoom.doors) 
			{
				if(door.posX - 100 - (firstChampPos.x - bg.pos.x) < 100 && door.posX - 100 - (firstChampPos.x - bg.pos.x) > -100)
				{
					g.drawString("Enter Door", door.posX + bg.pos.x, 300);
					activeDoor = door;
					break;
				}
				else
				{
					activeDoor = null;
				}	
			}

			
			
			if((firstChampPos.x - bg.pos.x) < 100 && activeRoom != activeDungeon.rooms.get(0))
			{
				g.drawString("Exit Room", bg.pos.x + 100, 300);
				
				activeDoor = activeRoom.exitDoor;
			}

	

			if (Player.champions.size() == 0) {
				g.setColor(Color.WHITE);
			}

			//rysowanie grafik eventów
			for(pl.edu.pw.fizyka.pojava.LNM.Entity.Event event: activeRoom.events)
			{
				if(event.evType == eventType.CHEST)
				{
					image.draw(g, (int)( event.posX + bg.pos.x) - 100, 400, "Resources/Entity/chest2.png");
				}
				else if(event.evType == eventType.FIGHT)
				{
					for (int i = 0; i < event.enemys.size(); i++) 
					{
						event.enemys.get(i).drawSomeone((int) (event.posX + bg.pos.x) + 300+ i * 100, (int) 600, g);
					}
				}
				else if(event.evType == eventType.TEXT)
				{
					g.setFont(font2);
					g.drawString(event.text,event.posX + bg.pos.x, 150);
					g.setFont(font);
				}
				else if(event.evType == eventType.LEAVEDOOR)
				{
					image.draw(g, (int)( event.posX + bg.pos.x)  - 280, 170, "Resources/Entity/doorsout.png");
				}
			}

			activeEvent = null;
			//rysowanie napisów nad eventami aby poinformować o możliwości ich aktywacji
			for(pl.edu.pw.fizyka.pojava.LNM.Entity.Event event: activeRoom.events)
			{
				if(event.isActive)
				{
					if(event.posX - 100 - (firstChampPos.x - bg.pos.x) < 100 && event.posX - 100 - (firstChampPos.x - bg.pos.x) > -100)
					{
						if(event.evType == eventType.FIGHT)
						{
							Player.enemys = event.enemys;
							changeStateTo(dungState.CombatPhase);
							activeEvent = event;
						}
						else if(event.evType == eventType.CHEST)
						{
							g.drawString("Open Chect", event.posX + bg.pos.x, 350);
							activeEvent = event;
							break;
						}
						if(event.evType == eventType.LEAVEDOOR)
						{
							g.drawString("Exit Dugeon", event.posX + bg.pos.x, 350);
							activeEvent = event;
							break;
						}
					}
				}
			}

		}

		//Rysowanie chempionów i przeciwników w trakcie walki 
		for (int i = 0; i < Player.champions.size() + Player.enemys.size() + activeChamp.skills.size(); i++) {

			if (i == currentChoice) {
				g.setColor(Color.WHITE);
			} else if (i == activeChampId || i == selectedSkillId) {
				g.setColor(Color.green);
			} else if (i == targetId) {
				g.setColor(Color.ORANGE);
			} else {
				g.setColor(Color.RED);
			}

			if (i < Player.champions.size()) {
				if (!Player.champions.get(i).isActive()) {
					if (g.getColor() == Color.WHITE)
						g.setColor(Color.LIGHT_GRAY);
					else
						g.setColor(Color.GRAY);
				}

				Player.champions.get(i).drawSomeone((int) firstChampPos.x + i * 100, (int) firstChampPos.y, g);
			} else if (i < Player.champions.size() + Player.enemys.size()) 
			{
				if(dungState == dungeonState.CombatPhase)
				{
					Player.enemys.get(i - Player.champions.size()).drawSomeone((int) firstEnemyPos.x + i * 100, (int) firstEnemyPos.y, g);
				}
			} else if (activeChamp != null && i < Player.champions.size() + Player.enemys.size() + activeChamp.skills.size())
			{
				int j = i - Player.champions.size() - Player.enemys.size();

				if (activeChamp.skills.get(j).getStaminaUse() > activeChamp.getStamina()) 
				{
					if (g.getColor() == Color.WHITE)
						g.setColor(Color.LIGHT_GRAY);
					else
						g.setColor(Color.GRAY);
				}

				activeChamp.skills.get(j).drawSkill((int) firstChampPos.x + j * 75, (int) firstChampPos.y + 50, g);
				

			} 
		}

			if (dungState == dungeonState.CombatPhase) {
				if (activeChamp != emptyChamp && target != null && selectedSkill != null) {
					calculateMove();
					currentChoice = 0;
				}

				int numberOfActiveChamps = 0;
				for (Champion champion : Player.champions) 
				{
					if (champion.isActive())
						numberOfActiveChamps++;
					
				}

				if (numberOfActiveChamps == 0) {
					endTurn();
				}

				int numberOfAliveEnemys = 0;
				for (Someone enemy : Player.enemys) 
				{
					if(enemy.isAlive())
					{
						numberOfAliveEnemys++;
					}
				}

				if(numberOfAliveEnemys == 0)
				{
					changeStateTo(dungeonState.MovementPhace);

					activeEvent.isActive = false;
					activeEvent = null;
					for (Champion champion : Player.champions) 
					{
						champion.setActive(true);
					}
				}
			}
		
	}

	void calculateMove() {
		if (selectedSkill.getEffect() != null)
			target.addEfect(new Effect(selectedSkill.getEffect()));

		activeChamp.useStamina(selectedSkill.getStaminaUse());

		target.takeDamage(selectedSkill.getDamage());
		activeChamp.setActive(false);

		
		if(target.isAlive() == false)
		{
			Player.enemys.remove(target);

			Player.champions.remove(target);
		}
		

		resetChoises();
	}

	void resetChoises() {
		activeChamp = emptyChamp;
		activeChampId = 999;
		target = null;
		targetId = 999;
		selectedSkill = null;
		selectedSkillId = 999;
	}

	void endTurn() 
	{
		for (Someone enemy : Player.enemys) 
		{
			enemyAI.calculateEnemyMove(Player.champions, enemy);	
			if(enemy.isAlive() == false)
			{
				Player.enemys.remove(enemy);
			}
		}

		for (Champion champion : Player.champions) 
		{
			if (champion.isAlive()) 
			{
				for (int i = 0; i < champion.efects.size(); i++) 
				{
					champion.efects.get(i).use(champion);

					if (champion.efects.get(i).getTime() == 0) 
					{
						champion.efects.remove(i);
					}
				}

				champion.setActive(true);
			}
		}

		for (int i = 0; i < Player.enemys.size(); i++) 
		{
			enemy = Player.enemys.get(i);
			for (int j = 0; j < enemy.efects.size(); j++)
			// for (Efect efect : enemy.efects)
			{
				enemy.efects.get(j).use(enemy);

				if (enemy.efects.get(j).getTime() == 0) 
				{
					enemy.efects.remove(j);
				}
			}
		}

		for (Someone enemy : Player.enemys) 
		{
			enemy.setActive(true);
		}
	}
	

	private void select() {
		if (dungState == dungeonState.CombatPhase) 
		{
			if (selectedSkill != null && currentChoice < Player.champions.size() + Player.enemys.size()) 
			{
				//wybór celu
				if (currentChoice < Player.champions.size()) 
				{
					//wybór postac jako celu
					target = Player.champions.get(currentChoice);
					targetId = currentChoice;
				} else 
				{
					//wybór przeciwnika jako celu
					target = Player.enemys.get(currentChoice - Player.champions.size());
					targetId = currentChoice;
				}
			}

			if (currentChoice < Player.champions.size() && selectedSkill == null) {
				if (activeChamp.isActive()) {
					if (Player.champions.get(currentChoice).isActive()) {
						activeChamp = Player.champions.get(currentChoice);
						activeChampId = currentChoice;
						selectedSkill = null;
						selectedSkillId = 999;
						currentChoice = Player.champions.size() + Player.enemys.size();
					}
				}
			} else if (currentChoice < Player.champions.size() + Player.enemys.size() + activeChamp.skills.size()
					&& currentChoice >= Player.champions.size() + Player.enemys.size()) {
				selectedSkill = activeChamp.skills.get(currentChoice - Player.champions.size() - Player.enemys.size());
				if (selectedSkill.getStaminaUse() <= activeChamp.getStamina()) {
					selectedSkillId = currentChoice;
					currentChoice = 0;
				} else {
					selectedSkill = null;
					selectedSkillId = 999;
				}
			}
		}
	}

	@Override
	public void keyPressed(int k) 
	{

		if (dungState == dungeonState.MovementPhace) {
			imput = Vector2D.zero;

			if (k == KeyEvent.VK_LEFT) {
				imput = Vector2D.add(imput, Vector2D.left);
			} else if (k == KeyEvent.VK_RIGHT)
			 {
				imput = Vector2D.add(imput, Vector2D.right);
			}
		
			if(k== KeyEvent.VK_SPACE)
			{
				if(activeDoor != null)
				{
					{
						if(activeDoor == activeRoom.exitDoor)
						{
							activeRoom = activeDoor.leadTo;
							firstChampPos.x=activeRoom.lastKonwnPlayerPos;
							bg.pos.x = activeRoom.lastKnownBgPos;
						}
						else
						{
							activeRoom.lastKnownBgPos = (int) bg.pos.x;
							activeRoom.lastKonwnPlayerPos = (int) firstChampPos.x;
							activeRoom = activeDoor.leadTo;
							bg.pos.x = 0;
							firstChampPos.x = 200;
						}
						activeDoor = null;
					}
				}

				if(activeEvent!=null && activeEvent.evType == eventType.CHEST)
				{
					gsm.setScene(SceneManager.CHEST);
				}

				if(activeEvent!=null && activeEvent.evType == eventType.LEAVEDOOR)
				{
					nextDungeon();
				}
			}			
		}

		if (dungState == dungeonState.CombatPhase) {
			if (k == KeyEvent.VK_ENTER) {
				if (tmp) {
					select();
					tmp = false;
				}
			}

			if (k == KeyEvent.VK_UP) {

				if (currentChoice == Player.champions.size() + Player.enemys.size() + activeChamp.skills.size()) {
					if (activeChamp.skills.size() > 0)
						currentChoice = Player.champions.size() + Player.enemys.size();
					else
						currentChoice = 0;
				} else if (currentChoice >= Player.champions.size() + Player.enemys.size()) {
					currentChoice = activeChampId;
				}
			}

			if (k == KeyEvent.VK_RIGHT) {
				if (currentChoice == Player.champions.size() + Player.enemys.size() - 1
						|| currentChoice >= Player.champions.size() + Player.enemys.size() + activeChamp.skills.size()
								- 1) {
				} else
					currentChoice++;
			}

			if (k == KeyEvent.VK_DOWN) {
				if (activeChamp != emptyChamp && currentChoice < Player.champions.size() + Player.enemys.size()) 
				{
					currentChoice = Player.champions.size() + Player.enemys.size();
				}
			}

			if (k == KeyEvent.VK_LEFT) {
				if (currentChoice == 0 || currentChoice == Player.champions.size() + Player.enemys.size()
						|| currentChoice == Player.champions.size() + Player.enemys.size()
								+ activeChamp.skills.size()) {
				} else
					currentChoice--;
			}

		}

		if (k == KeyEvent.VK_ESCAPE) {
			gsm.setScene(SceneManager.ESC);
		}
	}

	public void keyReleased(int k) 
	{
		imput = Vector2D.zero;

		if (k == KeyEvent.VK_ENTER) {
			tmp = true;
			if (currentChoice == Player.champions.size() + Player.enemys.size() + activeChamp.skills.size()) {
				gsm.setScene(SceneManager.TOWN);
			}
		}

		if (k == KeyEvent.VK_W || k == KeyEvent.VK_S || k == KeyEvent.VK_A || k == KeyEvent.VK_D) {
			imput = Vector2D.zero;
		}
	}

	void changeStateTo(dungeonState S) 
	{
		switch (S) 
		{
			case CombatPhase: 
			{
				currentChoice = 0;
				dungState = dungeonState.CombatPhase;
				firstChampMovemntPos = firstChampPos;
				firstChampPos = new Vector2D(200, 600);
				//bg =new Background();

			}
				break;
			case MovementPhace: 
			{
				currentChoice = -1;
				dungState = dungeonState.MovementPhace;
				firstChampPos = firstChampMovemntPos;
				bg = new Background("Resources/Backgrounds/fightbg.png");
			}
				break;
			default:
				break;
		}
	}

	public void nextDungeon(){
		gsm.setScene(SceneManager.TOWN);

		Player.currentDungeon++;
		activeDungeon = new Dungeon(Player.currentDungeon);
		activeRoom = activeDungeon.rooms.get(0);
		firstChampPos = new Vector2D(200, 600);
		bg.pos = Vector2D.zero;
	}

	public void loadDungeon(){
		activeDungeon = new Dungeon(Player.currentDungeon);
		activeRoom = activeDungeon.rooms.get(0);
		firstChampPos = new Vector2D(200, 600);
		bg.pos = Vector2D.zero;
	}

}