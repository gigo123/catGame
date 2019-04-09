package com.thsoft.catgame.game;

public class User {
	private String login;
	private String password;
	private boolean autologin;
	private int score;

	public User() {

	}

	public User(String login, String password, boolean autologin) {
		super();
		this.login = login;
		this.password = password;
		this.autologin = autologin;
		this.score = 0;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAutologin() {
		return autologin;
	}

	public void setAutologin(boolean autologin) {
		this.autologin = autologin;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
