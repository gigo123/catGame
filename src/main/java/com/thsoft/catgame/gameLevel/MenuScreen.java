/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thsoft.catgame.gameLevel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.thsoft.catgame.game.*;
import com.thsoft.catgame.game.PasswordStorage.CannotPerformOperationException;
import com.thsoft.catgame.game.PasswordStorage.InvalidHashException;
import com.badlogic.gdx.utils.viewport.*;

/**
 * головне меню програми
 *
 * @author Taras Hopka 2019
 */
public class MenuScreen extends BaseScreen {
	private GameSettings gameseting;
	private BaseActor title;
	private TextButton quitButton;
	private Label mainLable;
	private TextField loginField;
	private User newUser;
	private SqlWorker db;
	private SoundControl soundControl;
	private MenuStages curenStage;

	@Override
	public void initialize() {
		gameseting = CatGame.getSetings();
		soundControl = new SoundControl();
		gameseting.setSoundConrol(soundControl);
		// System.out.println(Gdx.graphics.getWidth());
		// System.out.println(Gdx.graphics.getHeight());
		int screanWidth = 1040;
		int screanHeight = 650;
		Viewport viewportsMain = new FitViewport(screanWidth, screanHeight, mainStage.getCamera());
		mainStage.setViewport(viewportsMain);
		Viewport viewportsUi = new FitViewport(screanWidth, screanHeight, uiStage.getCamera());
		uiStage.setViewport(viewportsUi);

		BaseActor ocean = new BaseActor(0, 0, mainStage);
		ocean.loadTexture("assets/water.jpg");// загружаємо картинку фону
		ocean.setSize(screanWidth, screanHeight);
		title = new BaseActor(0, 0, mainStage);
		title.loadTexture("assets/WilesCatLogo.png"); // загружаємо картинку - назву гри
		title.centerAtPosition(400, 300);
		title.moveBy(0, 100);
		title.setSize(450, 128); // 350 :100
		// CatGame.motherSkin - загальний стиль
		// "menu1" - конткретна реалізацуія

		mainLable = new Label("enter your name", CatGame.motherSkin, "menu1");
		mainLable.setAlignment(Align.center);
		loginField = new TextField("", CatGame.motherSkin);
		quitButton = new TextButton("Quit", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		quitButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			Gdx.app.exit();
			return true;
		});
		if (gameseting.isUserIslogin()) {
			System.out.println("start from level");
			mainMenu();
		} else {
			System.out.println("start from login");
			setLoginText();
		}
	}

	@Override
	public void update(float dt) {
	}

	/**
	 * обробка натискання копки клавіатури
	 *
	 * @param keyCode код клавіші
	 * @return це фінальний обробник
	 */
	@Override
	public boolean keyDown(int keyCode) {
		// uiStage.getKeyboardFocus()
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			switch (curenStage) {
			case LOGIN:
				setLogin();
				break;
			case PASSWORD:
				setPassword();
				break;
			case START:
				CatGame.setActiveScreen(new LevelScreen());
				break;
			default:
				break;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		return false;
	}

	/**
	 * метод встановлння локалі і відкриття ресурсів
	 */
	private void setLoginText() {
		uiStage.setKeyboardFocus(loginField);
		mainLable.setText("enter your name");
		TextButton nextButton = new TextButton("next", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		loginField.setText("");
		uiTable.clear();
		uiTable.add(title).colspan(2);
		uiTable.row();
		uiTable.add(mainLable).colspan(2).width(550);
		uiTable.row().pad(25, 0, 25, 0);
		uiTable.add(loginField).width(500).colspan(2);
		uiTable.row().pad(0, 0, 25, 0);
		uiTable.add(nextButton).width(200);
		uiTable.add(quitButton).width(200);
		curenStage = MenuStages.LOGIN;
		nextButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			setLogin();
			return false;
		});
	}

	public void setLogin() {
		String loginString = loginField.getText();
		if (loginString == null || loginString.equals("")) {
			System.out.println("incorect login");
		} else {
			newUser = new User();
			loginString= loginString.toUpperCase();
			newUser.setLogin(loginString);
			setPasswordText();
		}
	}

	/**
	 * метод для відображення інтерфейсу встновлення паролю
	 * 
	 * @return
	 */
	private boolean setPasswordText() {
		curenStage = MenuStages.PASSWORD;
		mainLable.setText("enter your password");
		TextButton nextButton = new TextButton("next", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		loginField.setText("");
		uiTable.clear();
		uiStage.setKeyboardFocus(loginField);
		uiTable.add(title).colspan(2);
		uiTable.row();
		uiTable.add(mainLable).colspan(2).width(550);
		uiTable.row().pad(25, 0, 25, 0);
		uiTable.add(loginField).width(500).colspan(2);
		uiTable.row().pad(0, 0, 25, 0);

		uiTable.add(nextButton).width(200);
		uiTable.add(quitButton).width(200);
		nextButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}

			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			setPassword();
			return false;
		});
		return true;
	}

	public void setPassword() {
		String passString = loginField.getText();
		if (passString == null || passString.equals("")) {
			System.out.println("no password");
			return;
		} else {
			String hashpass = null;
			try {
				passString= passString.toUpperCase();
				hashpass = PasswordStorage.createHash(passString); // hash pasword
				newUser.setPassword(hashpass);
				System.out.println("conec to db");
				db = CatGame.getDbConect();
				if (db.userExist(newUser.getLogin())) {
					String dbPAss = db.returnPass(newUser.getLogin());
					if (PasswordStorage.verifyPassword(passString, dbPAss)) {
						System.out.println("user pass match? launch");
						mainMenu();
						return;
					} else {
						System.out.println("wrong name pass, try again");
						setLoginText();
						return;
					}
				} else {
					db.addUser(newUser);
					System.out.println("new user created");
					mainMenu();
				}
				gameseting.setLogin(newUser.getLogin());
				gameseting.saveFile();
			} catch (CannotPerformOperationException | InvalidHashException e1) {
				System.out.println("error set hash pass");
				e1.printStackTrace();
				setLoginText();
				return;
			}
		}
	}

	/**
	 * метод для відображення головного меню
	 *
	 * @return
	 */
	private boolean mainMenu() {
		curenStage = MenuStages.START;
		TextButton firstButton = new TextButton("start", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		TextButton secondButton = new TextButton("setting", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		TextButton thirdButtob = new TextButton("players", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		uiTable.clear();
		uiTable.add(title);
		uiTable.row();
		uiTable.add(firstButton).width(250);
		uiTable.row().pad(25, 0, 25, 0);
		uiTable.add(secondButton).width(250);
		uiTable.row();
		uiTable.add(thirdButtob).width(250);
		uiTable.row().pad(25, 0, 25, 0);
		uiTable.add(quitButton).width(250);

		firstButton.addListener( // задаємо обобник натискання кнопки
				(Event e) -> {
					if (!(e instanceof InputEvent)) {
						return false;
					}
					if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
						return false;
					}
					gameseting.setUser(newUser);
					gameseting.setUserIslogin(true);
					CatGame.setActiveScreen(new LevelScreen()); // запускаємо перший рівень
					return false;
				});

		secondButton.addListener( // задаємо обобник натискання кнопки
				(Event e) -> {
					if (!(e instanceof InputEvent)) {
						return false;
					}
					if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
						return false;
					}
					setSettingText();
					return false;
				});

		thirdButtob.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			setUserSettingText();
			return false;
		});

		return true;
	}

	/*
	 * /** метод для відображення інтерфейсу налаштувань
	 */
	private void setSettingText() {
		mainLable.setText("sound");
		CheckBox checkBox1 = new CheckBox("mute", CatGame.motherSkin);
		if (!gameseting.getSoundMute()) {
			checkBox1.setChecked(true);
		} else {
			checkBox1.setChecked(false);
		}

		TextButton firstButton = new TextButton("edit players", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		TextButton returnButton = new TextButton("return", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		Slider soundVolume = new Slider(1, 100, 1, false, CatGame.motherSkin);
		soundVolume.setValue(gameseting.getSoundVolume());
		uiTable.clear();
		uiTable.add(title).colspan(2);
		uiTable.row();
		uiTable.add(firstButton).width(400).colspan(2);
		uiTable.row().pad(25, 0, 25, 0);
		uiTable.add(mainLable).width(400).colspan(2);
		uiTable.row();
		uiTable.add(checkBox1);
		uiTable.add(soundVolume);
		uiTable.row().pad(25, 0, 0, 0);
		uiTable.add(returnButton);
		uiTable.add(quitButton);
		returnButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			mainMenu();
			return true;
		});
		checkBox1.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			if (gameseting.getSoundMute()) {
				gameseting.setSoundMute(false);
				soundControl.mute(true);
				System.out.println("sound turn off");

			} else {
				gameseting.setSoundMute(true);
				soundControl.mute(false);
				System.out.println("sound turn on");
			}
			gameseting.saveFile();
			return true;
		});

		soundVolume.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			gameseting.setSoundVolume(soundVolume.getValue());
			System.out.println(soundVolume.getValue());
			soundControl.setVolume(soundVolume.getValue());
			gameseting.saveFile();
			checkBox1.setChecked(false);
			gameseting.setSoundMute(true);
			soundControl.mute(false);
			return false;
		});
		firstButton.addListener( // задаємо обобник натискання кнопки
				(Event e) -> {
					if (!(e instanceof InputEvent)) {
						return false;
					}
					if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
						return false;
					}
					setPlayersSettingText();
					return false;
				});

	}

	private void setUserSettingText() {
		CheckBox checkBox1 = new CheckBox("auto login", CatGame.motherSkin);
		// add metod to get seting
		TextButton firstButton = new TextButton("change password", CatGame.motherSkin, "menu1"); // створення кнопки
		TextButton returnButton = new TextButton("return", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		uiTable.clear();
		uiTable.add(title).colspan(2);
		uiTable.row();
		uiTable.add(firstButton).width(400).colspan(2);
		uiTable.row().pad(25, 0, 25, 0);
		uiTable.add(checkBox1).width(400).colspan(2);
		uiTable.row();
		uiTable.add(returnButton).width(400).colspan(2);
		uiTable.row().pad(25, 0, 25, 0);
		uiTable.add(quitButton).width(400).colspan(2);

		checkBox1.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			if (gameseting.isAutologin()) {
				gameseting.setAutologin(false);
				System.out.println("autologin off");
			} else {
				gameseting.setAutologin(true);
				System.out.println("autologin on");
			}
			gameseting.saveFile();
			return true;
		});

		returnButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			mainMenu();
			return true;
		});

	}

	private void setPlayersSettingText() {
		TextButton firstButton = new TextButton("add/change player", CatGame.motherSkin, "menu1"); // створення кнопки
		TextButton thirdButtob = new TextButton("remove player", CatGame.motherSkin, "menu1"); // створення кнопки
		TextButton returnButton = new TextButton("return", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		uiTable.clear();
		uiTable.add(title).colspan(2);
		uiTable.row();
		uiTable.add(firstButton).width(350).colspan(2);
		uiTable.row().pad(25, 0, 25, 0);
		uiTable.add(thirdButtob).width(350).colspan(2);
		uiTable.row().pad(0, 0, 25, 0);
		uiTable.add(returnButton);
		uiTable.add(quitButton);
		returnButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}

			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			setSettingText();
			return true;
		});
		firstButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			setLoginText();
			return true;
		});
	}

	enum MenuStages {
		START, LOGIN, PASSWORD, SETTING, USERSEING, PLAYERSETING, LOGINSTART;
	}
}
