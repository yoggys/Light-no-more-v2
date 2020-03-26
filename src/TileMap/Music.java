package TileMap;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {

	//zmienne odtwarzania
	private static AudioInputStream audio;
	private static File path;
	private static Clip clip;
	//true - unmuted / false - muted
	public static boolean unmuted = true;

	//zmienna nazwy pliku
	private static String playing;

	//ustawienie nazwy
	public static void set(String name){
		playing = name;
	}

	//graj utwor
	public static void play(){
		
		//test
		if(unmuted){
			try{
				path = new File(playing);
				audio = AudioSystem.getAudioInputStream(path);
				clip = AudioSystem.getClip();
	
				clip.open(audio);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	//zmien utwor
	public static void change(String name){
		clip.stop();
		if(unmuted){
			playing = name;
			Music.play();
		}
	}

	public static void stop(){
		clip.stop();
	}
}
