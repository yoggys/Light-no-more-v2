package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Images {
	
	private BufferedImage image;
	
	public Images() {}
	
	public void draw(Graphics2D g, int x, int y, String name) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(name));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		g.drawImage(image, x, y, null);
	}
	
}













