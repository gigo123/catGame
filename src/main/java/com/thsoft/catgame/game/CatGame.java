/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thsoft.catgame.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.thsoft.catgame.gameLevel.MenuScreen;

/**
 * базовий клас гри, включає створення шрифту з файлу
 * 
 * @author Taras Hopka 2019
 */
public class CatGame extends Game {
	/**
	 * Stores reference to game; used when calling setActiveScreen method.
	 */
	private static CatGame game;
	public static LabelStyle labelStyle; // BitmapFont + Color
	public static TextButtonStyle textButtonStyle; // NPD + BitmapFont + Color
	private static GameSettings setings;
	private static SqlWorker dbConect;
	private static boolean firsRun;
	public static Skin motherSkin;

	/**
	 * Called when game is initialized; stores global reference to game object.
	 */
	public CatGame() {
		game = this;
	}

	public static GameSettings getSetings() {
		return setings;
	}

	public static void setSetings(GameSettings setings) {
		CatGame.setings = setings;
	}

	public static boolean isFirsRun() {
		return firsRun;
	}

	public static void setFirsRun(boolean firsRun) {
		CatGame.firsRun = firsRun;
	}

	/**
	 * Called when game is initialized, after Gdx.input and other objects have been
	 * initialized.
	 */
	@Override
	public void create() {
		// prepare for multiple classes/stages/actors to receive discrete input
		InputMultiplexer im = new InputMultiplexer();
		Gdx.input.setInputProcessor(im);

		// parameters for generating a custom bitmap font
		FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("assets/OpenSans.ttf"));
		FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
		fontParameters.size = 48;
		fontParameters.color = Color.WHITE;
		fontParameters.borderWidth = 2;
		fontParameters.borderColor = Color.BLACK;
		fontParameters.borderStraight = true;
		fontParameters.minFilter = Texture.TextureFilter.Linear;
		fontParameters.magFilter = Texture.TextureFilter.Linear;

		BitmapFont customFont = fontGenerator.generateFont(fontParameters);

		labelStyle = new LabelStyle();
		labelStyle.font = customFont;

		textButtonStyle = new TextButtonStyle();

		Texture buttonTex = new Texture(Gdx.files.internal("assets/button.png"));
		NinePatch buttonPatch = new NinePatch(buttonTex, 24, 24, 24, 24);
		textButtonStyle.up = new NinePatchDrawable(buttonPatch);
		textButtonStyle.font = customFont;
		textButtonStyle.fontColor = Color.GRAY;
		// new skin
		motherSkin = new Skin(Gdx.files.internal("assets/skin/menu1_data/menu1.json"));

//читання файлу налаштуваня
		setings = new GameSettings();
		firsRun = setings.readFile();
		// inversoin of re
		firsRun = !firsRun;
		// open databases
		dbConect = new SqlWorker();
		setActiveScreen(new MenuScreen());
	}

	public static SqlWorker getDbConect() {
		return dbConect;
	}

	public static void setDbConect(SqlWorker dbConect) {
		CatGame.dbConect = dbConect;
	}

	/**
	 * Used to switch screens while game is running. Method is static to simplify
	 * usage.
	 * 
	 * @param s
	 */
	public static void setActiveScreen(BaseScreen s) {
		game.setScreen(s);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		dbConect.close();
	}

}
