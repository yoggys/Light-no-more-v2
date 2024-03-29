package pl.edu.pw.fizyka.pojava.LNM.GameState;

//by Cyprian Siwy & Mateusz Karbownik
public abstract class Scene {

	protected SceneManager gsm;

	public abstract void draw(java.awt.Graphics2D g);

	public abstract void keyPressed(int k);

	public abstract void keyReleased(int k);
}
