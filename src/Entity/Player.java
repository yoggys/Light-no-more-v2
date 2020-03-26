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
    public static ArrayList<Someone> enemys = new ArrayList<Someone>();

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

	
	
}

















