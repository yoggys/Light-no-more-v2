package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

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













