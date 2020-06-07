package pl.edu.pw.fizyka.pojava.LNM.GameState;

import pl.edu.pw.fizyka.pojava.LNM.Entity.*;
import pl.edu.pw.fizyka.pojava.LNM.System.*;
import pl.edu.pw.fizyka.pojava.LNM.Player.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;


//by Cyprian Siwy
public class DarkScene extends Scene {

	private Background bg;
	private Champion activeChamp = null;
	private int activeChampId = 999;
	private Effect poison;
	private Effect healOverTime;

	private Skill selectedSkill = null;
	private int selectedSkillId = 999;

	private Someone target = null;
	private int targetId = 999;

	private Someone enemy = null;

	private int currentChoice = 0;

	private Font font;

	private Dungeon activeDungeon = new Dungeon();
	private Room activeRoom = activeDungeon.rooms.get(0);
	private Door activeDoor;

	private boolean tmp = true;

	private Images image = new Images();

	public enum dungeonState {
		CombatPhase, MovementPhace
	};

	private dungeonState dungState = dungeonState.CombatPhase;
	private float speed = 200; // pixel per secend

	Champion emptyChamp = new Champion(0, 0, "", "Resources/Entity/patyczak.png", new Vector2D(-20, -420) );

	private Vector2D firstChampPos = new Vector2D(200, 600);
	private Vector2D firstEnemyPos = new Vector2D(600, 600);

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
		healOverTime = new Effect(-2, 3);

		//tworzenie umiejętności
		Skill skillPoison = new Skill("Poison", 4, 2, poison);
		Skill skillSlise = new Skill("Slise", 20, 0);
		Skill skillSmite = new Skill("Smite", 10, 0);
		Skill skillHeal = new Skill("Heal", -4, 0, healOverTime);

		//Tworzenie Bochaterów i przeciwników
		Player.champions.add(new Champion(25, 20, "AleXXX" , "Resources/Entity/patyczak.png", new Vector2D(-20, -420) ));
		Player.champions.add(new Champion(25, 20, "Sasha",  "Resources/Entity/patyczak.png", new Vector2D(-20, -420) ));
		Player.champions.add(new Champion(100, 30, "Siwy" , "Resources/Entity/patyczak.png", new Vector2D(-20, -420) ));
		//Player.enemys.add(new Someone(50, 10, "wolf" , "Resources/Entity/wolf.png", new Vector2D(-150, -300)));

		//Dawanie umiejętności
		Player.champions.get(0).addSkill(new Skill(skillSlise));
		Player.champions.get(0).addSkill(new Skill(skillSmite));
		Player.champions.get(1).addSkill(new Skill(skillHeal));
		Player.champions.get(1).addSkill(new Skill(skillPoison));
		Player.champions.get(2).addSkill(new Skill(skillSmite));
		Player.champions.get(2).addSkill(new Skill(skillHeal));

		

 		this.gsm = gsm;

		try 
		{
			bg = new Background("Resources/Backgrounds/fightbg.png");
			
			font = new Font("Arial", Font.PLAIN, 12);
			//bg.pos.x+=100;
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		lastTime = currentTime;
		currentTime = System.currentTimeMillis();
		if(lastTime!=0)
		{
			timeStep = currentTime-lastTime;
			timeStep/=1000;
			//System.out.println(timeStep);

		}
		bg.draw(g);
		
		g.setFont(font);
		g.setColor(Color.WHITE);

		for (Door door : activeRoom.doors) 
			{
				image.draw(g, (int)( door.posX + bg.pos.x) - 280, 170, "Resources/Entity/doors.png");
			}

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
			
			
			//System.out.println(firstChampPos.x - bg.pos.x);
			
			for (Door door : activeRoom.doors) 
			{
				if((firstChampPos.x - bg.pos.x) < 100)
				{
					g.drawString("Exit Room", bg.pos.x + 100, 300);
					activeDoor = activeRoom.exitDoor;
				}
				else if(door.posX - 100 - (firstChampPos.x - bg.pos.x) < 100 && door.posX - 100 - (firstChampPos.x - bg.pos.x) > -100)
				{
					//image.draw(g, (int) door.posX + (int) bg.pos.x - 280, 170, "Resources/Entity/doors.png");
					g.drawString("Enter Door", door.posX + bg.pos.x, 300);
					activeDoor = door;
					break;
				}
				else
				{
					activeDoor = null;
					//image.draw(g, (int) door.posX + (int) bg.pos.x - 280, 170, "Resources/Entity/doors.png");
				}	
				
			}

			for(pl.edu.pw.fizyka.pojava.LNM.Entity.Event event: activeRoom.events)
			{
				if(event.posX - 100 - (firstChampPos.x - bg.pos.x) < 100 && event.posX - 100 - (firstChampPos.x - bg.pos.x) > -100)
				{
					Player.enemys = event.enemys;
					changeStateTo(dungState.CombatPhase);
				}
			}

		}
		

		
		if (Player.champions.size() == 0) {
			g.setColor(Color.WHITE);
			g.drawString("back", 586, 700);
		}

		for (int i = 0; i < Player.champions.size() + Player.enemys.size() + activeChamp.skills.size() + 1; i++) {

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
				// g.drawString(activeChamp.skills.get(j).getName(), firstChampX + j*50,
				// firstChampY + 50);
			} else {
				g.drawString("back", 586, 700);
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
			}
		}
	}

	void calculateMove() {
		if (selectedSkill.getEffect() != null)
			target.addEfect(new Effect(selectedSkill.getEffect()));

		activeChamp.useStamina(selectedSkill.getStaminaUse());

		target.takeDamage(selectedSkill.getDamage());
		activeChamp.setActive(false);

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
			TimeUnit.MICROSECONDS.sleep(500);
		}

		for (Champion champion : Player.champions) {
			if (champion.isAlive()) {
				for (int i = 0; i < champion.efects.size(); i++) {
					champion.efects.get(i).use(champion);

					if (champion.efects.get(i).getTime() == 0) {
						champion.efects.remove(i);
					}
				}

				champion.setActive(true);
			}
		}

		for (int i = 0; i < Player.enemys.size(); i++) {
			enemy = Player.enemys.get(i);
			for (int j = 0; j < enemy.efects.size(); j++)
			// for (Efect efect : enemy.efects)
			{
				enemy.efects.get(j).use(enemy);

				if (enemy.efects.get(j).getTime() == 0) {
					enemy.efects.remove(j);
				}
			}
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
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_K) {
			if (dungState == dungeonState.MovementPhace)
				changeStateTo(dungeonState.CombatPhase);
			else if (dungState == dungeonState.CombatPhase)
				changeStateTo(dungeonState.MovementPhace);
		}

		if (dungState == dungeonState.MovementPhace) {
			imput = Vector2D.zero;

			if (k == KeyEvent.VK_LEFT) {
				imput = Vector2D.add(imput, Vector2D.left);
			} else if (k == KeyEvent.VK_RIGHT) {
				imput = Vector2D.add(imput, Vector2D.right);
			}

			if(activeDoor != null)
			{
				if(k== KeyEvent.VK_SPACE)
				{
					activeRoom = activeDoor.leadTo;
					firstChampPos.x = 200;
					bg.pos.x = 0;
					activeDoor = null;
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
				if (currentChoice < Player.champions.size() + Player.enemys.size()) {
					currentChoice = Player.champions.size() + Player.enemys.size();
				} else if (currentChoice >= Player.champions.size() + Player.enemys.size()) {
					currentChoice = Player.champions.size() + Player.enemys.size() + activeChamp.skills.size();
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
			gsm.setState(SceneManager.ESC);
		}
	}

	public void keyReleased(int k) 
	{
		imput = Vector2D.zero;

		if (k == KeyEvent.VK_ENTER) {
			tmp = true;
			if (currentChoice == Player.champions.size() + Player.enemys.size() + activeChamp.skills.size()) {
				gsm.setState(SceneManager.TOWN);
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
			}
				break;
			case MovementPhace: 
			{
				currentChoice = -1;
				dungState = dungeonState.MovementPhace;
			}
				break;
			default:
				break;
		}
	}
}