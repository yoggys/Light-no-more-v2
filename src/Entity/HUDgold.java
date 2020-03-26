package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HUDgold {
	
	private BufferedImage image;
	private Font font;
	
	public HUDgold() {
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/HUD/hudgold.png")
			);
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













