package com.orestis.puzzle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
	
	private String musicFile;
	private Clip clip = null;
	
	public SoundPlayer(String musicFile) {
		this.musicFile = musicFile;
	}


	public void play(boolean loop){	
		AudioInputStream inputStream = null;
		try {
			clip = AudioSystem.getClip();
			inputStream = AudioSystem.getAudioInputStream(getClass().getResource(musicFile));
			clip.open(inputStream);
			clip.start();
		} catch(Exception e){}
		
		if(loop == true){
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}	
	}
	public void stop(){
		clip.stop();
	}

}
