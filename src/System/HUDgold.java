package System;

import Player.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//class by Mateusz Karbownik
public class HUDgold {
	
	private BufferedImage image;
	private Font font;
	
	public HUDgold() {
		
		try {
			File file = new File("Resources/HUD/hudgold.png");
			image = ImageIO.read(file);
			font = new Font("Arial", Font.PLAIN, 28);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, 0, 10, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(Inventory.getgold() + "g",70,50);
		
	}
	
}













