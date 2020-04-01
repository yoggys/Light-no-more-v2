package Main;

import javax.swing.JFrame;

import System.Music;

//class by Mateusz Karbownik
public class Game {

	public static void main(String[] args) {
		JFrame window = new JFrame("Light no more");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		Music.set("Resources/Music/muz11.wav");
		Music.play();
	}
}