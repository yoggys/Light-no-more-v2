package pl.edu.pw.fizyka.pojava.LNM.System;

import pl.edu.pw.fizyka.pojava.LNM.Entity.Vector2D;
import pl.edu.pw.fizyka.pojava.LNM.Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;

//class by Mateusz Karbownik
public class Background {

	// zmienne ustawiania obiektu i pozycji
	private BufferedImage image;
	public Vector2D pos = new Vector2D();

	// test
	public Background(String s) {
		try {
			File file = new File(s);
			image = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// rysowanie
	public void draw(Graphics2D g) 
	{
		g.drawImage(image, (int) pos.x, (int) pos.y, null);

		if (pos.x < 0) {
			g.drawImage(image,(int) pos.x + GamePanel.WIDTH, (int) pos.y, null);
		}

		if (pos.x > 0) {
			g.drawImage(image, (int) pos.x - GamePanel.WIDTH, (int) pos.y, null);
		}
	}

	
	public float getPosX()
	{
		return pos.x;
	}

}
