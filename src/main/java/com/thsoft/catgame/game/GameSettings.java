package com.thsoft.catgame.game;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GameSettings  {

	private String login;
	private boolean autologin;
	private Properties prop;
	private boolean soundMute;
	private float soundVolume;
	//private Locale gameLocale;
	private User user;
	private boolean userIslogin;
	private SoundControl soundConrol;
	

	//public Locale getGameLocale() {
	//	return gameLocale;
	//}

	public SoundControl getSoundConrol() {
		return soundConrol;
	}

	public void setSoundConrol(SoundControl soundConrol) {
		this.soundConrol = soundConrol;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUserIslogin() {
		return userIslogin;
	}

	public void setUserIslogin(boolean userIslogin) {
		this.userIslogin = userIslogin;
	}

	public GameSettings() {
		prop = new Properties();
	}

	//public void setGameLocale(Locale gameLocale) {
	//	this.gameLocale = gameLocale;
	//}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isAutologin() {
		return autologin;
	}

	public void setAutologin(boolean autologin) {
		this.autologin = autologin;
	}

	public boolean saveFile() {
		prop.setProperty("login", login);
		prop.setProperty("autologin", String.valueOf(autologin));
		prop.setProperty("volume", String.valueOf(soundVolume));
		prop.setProperty("mute", String.valueOf(soundMute));
		FileOutputStream fr;
		try {
			System.out.println("begin file save");
			fr = new FileOutputStream("config.properties");
			prop.store(fr, "Properties");
			fr.close();
			System.out.println("After saving properties: " + prop);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean readFile() {
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);
			System.out.println("read property");
			//user = new User(prop.getProperty("login"), prop.getProperty("password"),
					//Boolean.getBoolean(prop.getProperty("autologin")));
			login =prop.getProperty("login");
			autologin = Boolean.getBoolean(prop.getProperty("autologin"));
			// get the property value and print it out
			System.out.println(prop.getProperty("login"));
			System.out.println(prop.getProperty("password"));
			System.out.println(prop.getProperty("autologin"));
			
			
			String scoreString = prop.getProperty("volume");
			if (scoreString.equals("")) {

			} else {
				soundVolume = Float.valueOf(scoreString);
			}
			System.out.println(prop.getProperty("volume"));
			scoreString = prop.getProperty("mute");
			if (scoreString.equals("")) {

			} else {
				soundMute = Boolean.getBoolean(scoreString);
			}
			System.out.println(prop.getProperty("mute"));
			
			
			System.out.println(" end read property");
			return true;

		} catch (IOException ex) {
			//ex.printStackTrace();
			return false;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean getSoundMute() {
		return soundMute;
	}

	public void setSoundMute(boolean soundMute) {
		this.soundMute = soundMute;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}
}
