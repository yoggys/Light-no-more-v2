package pl.edu.pw.fizyka.pojava.LNM.System;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

//class by Mateusz Karbownik
public class Music {

	// zmienne odtwarzania
	private static AudioInputStream audio;
	private static File path;
	private static Clip clip;
	private static FloatControl gain;
	public static int level = 0;
	// true - unmuted / false - muted
	public static boolean unmuted = true;
	// zmienna nazwy pliku
	private static String playing;

	// ustawienie nazwy
	public static void set(String name) {
		playing = name;
	}

	// graj utwor
	public static void play() {
		
		// test
		try {
			path = new File(playing);
			audio = AudioSystem.getAudioInputStream(path);
			clip = AudioSystem.getClip();

			clip.open(audio);

			if (unmuted) {
				setVal(-8*level);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// zmien utwor
	public static void change(String name) {
		clip.stop();
		playing = name;

		if (unmuted) {
			Music.play();
		}
	}

	// zatrzymaj
	public static void stop() {
		clip.stop();
	}

	private static void setVal(float dB){
		gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		gain.setValue(dB);
	}

	public static void setLevel(boolean side){
		if(side){
			if(level < 4){
				level++;
			}
			if(level == 4){
				Music.unmuted = false;
				Music.stop();
			}
			if(level != 4){
				setVal(-8*level);
			}
		} 
		else {
			if(level > 0){
				if(!Music.unmuted){
					Music.unmuted = true;
					Music.play();
				}
				level--;
				setVal(-8*level);
			}
		}
	}

}
