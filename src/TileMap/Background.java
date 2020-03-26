package TileMap;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Background {
	
	//zmienne ustawiania obiektu i pozycji
	private BufferedImage image;
	private double x;
	private double y;

	//test
	public Background(String s) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//rysowanie obiekut
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







