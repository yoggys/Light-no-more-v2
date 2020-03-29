package System;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;

//class by Mateusz Karbownik
public class Background {
	
	//zmienne ustawiania obiektu i pozycji
	private BufferedImage image;
	private double x;
	private double y;

	//test
	public Background(String s) {
		try {
			File file = new File(s);
			image = ImageIO.read(file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//rysowanie
	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y, null);
		}

		if(x > 0) {
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
		}
	}
	
}






