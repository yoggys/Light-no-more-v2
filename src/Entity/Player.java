package Entity;

import TileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject {
	
	public static int sumlevel;
	public static int sumchamp;

	public static ArrayList<Champion> champions = new ArrayList<Champion>();
	public static ArrayList<Champion> reserve = new ArrayList<Champion>();
	public static ArrayList<Someone> enemys = new ArrayList<Someone>();
	public static ArrayList<Champion> tavernChampions = new ArrayList<Champion>();

	private int level;
	private int exp;
	private String name;
	private String profession;
	private boolean dead;
	
	private int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	private boolean flinching;

	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 2, 8, 1, 2, 4, 2, 5 };

	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;

	public Player(final TileMap tm) {


		

		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;

		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;

		facingRight = true;

		health = maxHealth = 5;

		// load sprites
		try {

			final BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/playerspritesxd.png"));

			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 7; i++) {

				final BufferedImage[] bi = new BufferedImage[numFrames[i]];

				sprites.add(bi);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);

	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	public int getFire() {
		return fire;
	}

	public int getMaxFire() {
		return maxFire;
	}


	public void checkAttack(final ArrayList<Enemy> enemies) {

		for (int i = 0; i < enemies.size(); i++) {

			final Enemy e = enemies.get(i);

			// check enemy collision
			if (intersects(e)) {
				hit(e.getDamage());
			}

		}

	}

	public void hit(final int damage) {
		if (flinching)
			return;
		health -= damage;
		if (health < 0)
			health = 0;
		if (health == 0)
			dead = true;
		flinching = true;
	}

	private void getNextPosition() {

		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} 
		else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} 
		else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} 
			else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}
	}

	public void update() {

		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		} else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}

		animation.update();
	}

	public void draw(final Graphics2D g) {

		setMapPosition();		
		super.draw(g);
		
	}

	public static void changeSquad(int posCurrent, int posReserve){
		Champion tmp = new Champion(0,0,0,"");
		tmp = champions.get(posCurrent);
		champions.set(posCurrent, reserve.get(posReserve));
		reserve.set(posReserve, tmp);
	}
	
	public static void buyChampion(Champion buyChamp){
		reserve.add(buyChamp);
	}

	public static void sellChampion(int posReserve){
		reserve.remove(posReserve);
	}
	
	public static void championCurCard(int choice, Graphics2D g, Images image, int x, int y){
		g.setColor(Color.BLACK);
		image.draw(g, x, y, "Resources/HUD/card.png");
		image.draw(g, x, y-44, "Resources/HUD/cardframe.png");
		image.draw(g, x, y-44, Player.champions.get(choice).getAvatar());
		g.drawString("NAME: " + Player.champions.get(choice).getName(), x+50, y+30*6);
		g.drawString("HP/MAX_HP: " + Player.champions.get(choice).getHp() +"/"+ Player.champions.get(choice).getMaxHp(), x+50, y+30*7);	
		g.drawString("SA/MAX_SA: " + Player.champions.get(choice).getStamina() +"/"+ Player.champions.get(choice).getMaxStamina(), x+50, y+30*8);
		g.drawString("SKILLS(DMG/HEAL): ", x+50, y+30*9);
		if(Player.champions.get(choice).skills.size() != 0){
			if(Player.champions.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.champions.get(choice).skills.get(0).getName() + "(" +
				-1*(Player.champions.get(choice).skills.get(0).getDamage()) +")", x+50, y+30*10);
			}
			else{
				g.drawString("*"+ Player.champions.get(choice).skills.get(0).getName() + "(" +
				-1*((Player.champions.get(choice).skills.get(0).getDamage() + 
				Player.champions.get(choice).skills.get(0).getEfect().getTotal())) + ")", x+50, y+30*10);
			}
			if(Player.champions.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.champions.get(choice).skills.get(1).getName() + "(" +
				-1*(Player.champions.get(choice).skills.get(1).getDamage()) +")", x+50, y+30*11);
			}
			else{
				g.drawString("*"+ Player.champions.get(choice).skills.get(1).getName() + "(" +
				-1*((Player.champions.get(choice).skills.get(1).getDamage() + 
				Player.champions.get(choice).skills.get(1).getEfect().getTotal())) + ")", x+50, y+30*11);
			}
		}
		else{
			g.drawString("NONE!", x+50, y+30*10);
		}
	}

	public static void championResCard(int choice, Graphics2D g, Images image, int x, int y){
		g.setColor(Color.BLACK);
		image.draw(g, x, y, "Resources/HUD/card.png");
		image.draw(g, x, y-44, "Resources/HUD/cardframe.png");
		image.draw(g, x, y-44, Player.reserve.get(choice).getAvatar());
		g.drawString("NAME: " + Player.reserve.get(choice).getName(), x+50, y+30*6);
		g.drawString("HP/MAX_HP: " + Player.reserve.get(choice).getHp() +"/"+ Player.reserve.get(choice).getMaxHp(), x+50, y+30*7);	
		g.drawString("SA/MAX_SA: " + Player.reserve.get(choice).getStamina() +"/"+ Player.reserve.get(choice).getMaxStamina(), x+50, y+30*8);
		g.drawString("SKILLS(DMG/HEAL): ", x+50, y+30*9);
		if(Player.reserve.get(choice).skills.size() != 0){
			if(Player.reserve.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.reserve.get(choice).skills.get(0).getName() + "(" +
				-1*(Player.reserve.get(choice).skills.get(0).getDamage()) +")", x+50, y+30*10);
			}
			else{
				g.drawString("*"+ Player.reserve.get(choice).skills.get(0).getName() + "(" +
				-1*((Player.reserve.get(choice).skills.get(0).getDamage() + 
				Player.reserve.get(choice).skills.get(0).getEfect().getTotal())) + ")", x+50, y+30*10);
			}
			if(Player.reserve.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.reserve.get(choice).skills.get(1).getName() + "(" +
				-1*(Player.reserve.get(choice).skills.get(1).getDamage()) +")", x+50, y+30*11);
			}
			else{
				g.drawString("*"+ Player.reserve.get(choice).skills.get(1).getName() + "(" +
				-1*((Player.reserve.get(choice).skills.get(1).getDamage() + 
				Player.reserve.get(choice).skills.get(1).getEfect().getTotal())) + ")", x+50, y+30*11);
			}
		}
		else{
			g.drawString("NONE!", x+50, y+30*10);
		}
	}

	public static void championTavCard(int choice, Graphics2D g, Images image, int x, int y){
		g.setColor(Color.BLACK);
		image.draw(g, x, y, "Resources/HUD/card.png");
		image.draw(g, x, y-44, "Resources/HUD/cardframe.png");
		image.draw(g, x, y-44, Player.tavernChampions.get(choice).getAvatar());
		g.drawString("NAME: " + Player.tavernChampions.get(choice).getName(), x+50, y+30*6);
		g.drawString("HP/MAX_HP: " + Player.tavernChampions.get(choice).getHp() +"/"+ Player.tavernChampions.get(choice).getMaxHp(), x+50, y+30*7);	
		g.drawString("SA/MAX_SA: " + Player.tavernChampions.get(choice).getStamina() +"/"+ Player.tavernChampions.get(choice).getMaxStamina(), x+50, y+30*8);
		g.drawString("SKILLS(DMG/HEAL): ", x+50, y+30*9);
		if(Player.tavernChampions.get(choice).skills.size() != 0){
			if(Player.tavernChampions.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.tavernChampions.get(choice).skills.get(0).getName() + "(" +
				-1*(Player.tavernChampions.get(choice).skills.get(0).getDamage()) +")", x+50, y+30*10);
			}
			else{
				g.drawString("*"+ Player.tavernChampions.get(choice).skills.get(0).getName() + "(" +
				-1*((Player.tavernChampions.get(choice).skills.get(0).getDamage() + 
				Player.tavernChampions.get(choice).skills.get(0).getEfect().getTotal())) + ")", x+50, y+30*10);
			}
			if(Player.tavernChampions.get(choice).skills.get(0).getEfect() == null){
				g.drawString("*"+ Player.tavernChampions.get(choice).skills.get(1).getName() + "(" +
				-1*(Player.tavernChampions.get(choice).skills.get(1).getDamage()) +")", x+50, y+30*11);
			}
			else{
				g.drawString("*"+ Player.tavernChampions.get(choice).skills.get(1).getName() + "(" +
				-1*((Player.tavernChampions.get(choice).skills.get(1).getDamage() + 
				Player.tavernChampions.get(choice).skills.get(1).getEfect().getTotal())) + ")", x+50, y+30*11);
			}
		}
		else{
			g.drawString("NONE!", x+50, y+30*10);
		}
	}
}

















