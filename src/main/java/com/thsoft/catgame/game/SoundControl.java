package com.thsoft.catgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundControl {
	Music backgroundMusic;
    Sound sparkleSound;
    Sound explosionSound;
    Sound catHitSound;
    Sound winSound;
    Sound endSound;
    Sound launchSound;
    Sound missSound;
    volatile float volume;
    float oldvolume;
    public SoundControl() {
    	volume =oldvolume =1.00f;
    	 backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/sound/Prelude-and-Action.mp3"));
	        sparkleSound   = Gdx.audio.newSound(Gdx.files.internal("assets/sound/sparkle.mp3"));
	        explosionSound   = Gdx.audio.newSound(Gdx.files.internal("assets/sound/explosion.wav"));
	        catHitSound = Gdx.audio.newSound(Gdx.files.internal("assets/sound/cathit.wav"));
	        winSound = Gdx.audio.newSound(Gdx.files.internal("assets/sound/wingame.mp3"));
	        endSound = Gdx.audio.newSound(Gdx.files.internal("assets/sound/endgame.wav"));
	        launchSound=Gdx.audio.newSound(Gdx.files.internal("assets/sound/launch.mp3")); 
	        missSound = Gdx.audio.newSound(Gdx.files.internal("assets/sound/miss-cat.wav"));
	        backgroundMusic.setLooping(true);
	        backgroundMusic.setVolume(volume);
	        backgroundMusic.play();
	}
    private synchronized void changeVoulume() {
    	backgroundMusic.setVolume(volume);
    	
    }
    public void mute( boolean mute) {
    
    	if(mute) {
    		oldvolume =volume;
    		volume =0;
    	}
    	else {
    		volume = oldvolume;
    	}
    	changeVoulume();
    }
    public synchronized void setVolume( float volume) {
    	this.volume =  volume/100;
    	System.out.println(this.volume);
    	changeVoulume();
    }
    public void playExplosion() {
    	explosionSound.play(volume);
    	
    }
    public void playcatHit() {
    	catHitSound.play(volume);
    	
    }
    public void playBackground() {
    	backgroundMusic.play();
    }
    public void stopBackground() {
    	backgroundMusic.stop();
    	
    }
    public void playWinSound() {
    	winSound.play(volume);
    }
    public void playEndSound() {
    	endSound.play(volume);
    }
    public void playLauchSound() {
    	launchSound.play(volume);
    }
    public void playMissSound() {
    	missSound.play(volume);
    }
}
