package com.example.christmascandy;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;

public class SoundManager {
	 public static void playSound(String path) {
		 
		 URL soundURL = SoundManager.class.getResource(path);
		 System.out.println("Sound URL: " + soundURL);

	        try {
	        	File soundFile = new File(soundURL.getPath());
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
	            Clip clip = AudioSystem.getClip();
	            clip.open(audioInputStream);
	            clip.start();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }
}