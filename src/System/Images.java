package System;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//class by Mateusz Karbownik
public class Images {
	
	private BufferedImage image;
	
	public Images() {}
	
	public void draw(Graphics2D g, int x, int y, String name) {
		try {
			File file = new File(name);
			image = ImageIO.read(file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		g.drawImage(image, x, y, null);
	}
	
}













