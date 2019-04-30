/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thsoft.catgame.gameLevel;

import java.util.concurrent.Semaphore;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.thsoft.catgame.gameLogik.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.thsoft.catgame.game.*;

/**
 * перший рівень гри
 * 
 * @author Taras Hopka 2019
 */
public class LevelScreen extends BaseScreen {

	private LaunchigPad launch1;
	private Array<Cat> catsArray;
	private Cat cat1;
	private Cat cat2;
	private TrowableItem tItem1;
	private LevelState levelStage;
	private LaunchPadInputListener launchMouseListener;
	private Label gamePoint;
	private int trowLeft;
	private int numberOfHit;
	private int catPosition;
	private int numberPoints;
	private GameSettings gameSettings;
	private SoundControl soundControl;
	private Semaphore trowSemaphore;
	private BananBars bananbar;
	private boolean hit;
	private float hitTime;

	@Override
	public void initialize() {
		levelStage = LevelState.TARGETING;
		BaseActor ocean = new BaseActor(0, 0, mainStage);
		ocean.loadTexture("assets/level1/Screane1b.png");
		ocean.setSize(1040, 650);
		BaseActor.setWorldBounds(ocean);

		Viewport viewportsMain = new FitViewport(1040, 650, mainStage.getCamera());
		mainStage.setViewport(viewportsMain);
		Viewport viewportsUi = new FitViewport(1040, 650, uiStage.getCamera());
		uiStage.setViewport(viewportsUi);

		gameSettings = CatGame.getSetings();
		soundControl = gameSettings.getSoundConrol();
		catsArray = new Array<>();
		
		catsArray.add(new Cat(300, 740, 150, mainStage));
		cat1 = catsArray.get(0);

		String[] filenames = { "assets/level1/kat_tele4.png", "assets/level1/kat_tele1.png",
				"assets/level1/kat_tele2.png", "assets/level1/kat_tele3.png", "assets/level1/kat_tele2.png",
				"assets/level1/kat_tele1.png" };
		cat1.loadAnimationFromFiles(filenames, 0.3f, true);

		cat1.setSize(140, 102); // x 1.375 y = 0,73
		cat1.setBoundaryPolygonCatLevel1_1(10, 10, 10, 0);
		//cat1.setBoundaryRectangle();

		catsArray.add(new Cat(300, 790, 15, mainStage));
		// cat2 = new Cat(200, 580, 10, mainStage);
		cat2 = catsArray.get(1);

		String[] filenames2 = { "assets/level1/kat_pod1.png", "assets/level1/kat_pod1.png",
				"assets/level1/kat_pod2.png", "assets/level1/kat_pod3.png", "assets/level1/kat_pod4.png",
				"assets/level1/kat_pod5.png", "assets/level1/kat_pod5.png", "assets/level1/kat_pod5.png",
				"assets/level1/kat_pod4.png", "assets/level1/kat_pod3.png", "assets/level1/kat_pod2.png",
				"assets/level1/kat_pod1.png" };
		cat2.loadAnimationFromFiles(filenames2, 0.4f, true);

		cat2.setSize(160, 96); // x 1.66 y = 0,6
		cat2.setBoundaryPolygon(20, 30, 20, 10);
		cat2.setVisible(false);
		// third cat position
		catsArray.add(new Cat(300, 790, 15, mainStage));

		cat2 = catsArray.get(2);
		String[] filenames3 = { "assets/level1/Kat_shtora1.png", "assets/level1/Kat_shtora2.png",
				"assets/level1/Kat_shtora3.png", "assets/level1/Kat_shtora4.png", "assets/level1/Kat_shtora5.png",
				"assets/level1/Kat_shtora6.png", "assets/level1/Kat_shtora7.png", "assets/level1/Kat_shtora8.png",
				"assets/level1/Kat_shtora7.png", "assets/level1/Kat_shtora6.png", "assets/level1/Kat_shtora5.png",
				"assets/level1/Kat_shtora4.png", "assets/level1/Kat_shtora3.png", "assets/level1/Kat_shtora2.png",
				"assets/level1/Kat_shtora1.png", };
		cat2.loadAnimationFromFiles(filenames3, 0.1f, true);

		cat2.setSize(100, 168); // x=0,6 y = 1.68
		cat2.setBoundaryPolygon(20, 30, 20, 10);
		cat2.setVisible(false);

		launch1 = new LaunchigPad(140, 40, 80, -30, 190, 230, mainStage);
		launch1.createTraectory();
		launchMouseListener = new LaunchPadInputListener(launch1, mainStage, levelStage);
		launch1.addListener(launchMouseListener);

		tItem1 = new TrowableItem(0, 0, mainStage);
		tItem1.loadTexture("assets/level1/banan.png");
		tItem1.setSize(60, 30);

		tItem1.setBoundaryPolygon(10, 10, 10, 10);
		tItem1.setVisible(false);

		// тестова інофрмація зліва
		gamePoint = new Label("points: 0", CatGame.labelStyle);
		gamePoint.setColor(Color.CYAN);
		trowSemaphore = new Semaphore(1);
		// кнопка виходу
		Button.ButtonStyle buttonStyle = new Button.ButtonStyle();

		Texture buttonTex = new Texture(Gdx.files.internal("assets/undo.png"));
		TextureRegion buttonRegion = new TextureRegion(buttonTex);
		buttonStyle.up = new TextureRegionDrawable(buttonRegion);

		Button restartButton = new Button(buttonStyle);
		restartButton.setColor(Color.CYAN);

		restartButton.addListener((Event e) -> {
			InputEvent ie = (InputEvent) e;
			if (ie.getType().equals(InputEvent.Type.touchDown)) {
				System.out.println("return to menu ");
				CatGame.setActiveScreen(new MenuScreen());
			}
			return false;
		});

		uiTable.pad(10);
		uiTable.add(gamePoint).top();
		uiTable.add().expandX().expandY();
		uiTable.add(restartButton).top();

		trowLeft = 5;
		numberOfHit = 0;
		catPosition = 1;
		numberPoints = 0;

		BarierActor barier1 = new BarierActor(770, 110, mainStage);
		barier1.setSize(110, 90);
		barier1.setBoundaryRectangle();
		barier1 = new BarierActor(500, 420, mainStage);
		barier1.setSize(70, 70);
		barier1.setBoundaryRectangle();
		bananbar = new BananBars(0, 0, mainStage, 5);
	}

	@Override
	public void update(float dt) {

		boolean LauncSetingChenging = false;
		levelStage = launchMouseListener.getState();
		switch (levelStage) {
		case TARGETING:

			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				launch1.downSpeead();
				LauncSetingChenging = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				launch1.addSpeead();
				LauncSetingChenging = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				launch1.upAngle();
				LauncSetingChenging = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				launch1.downAngle();
				LauncSetingChenging = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				levelStage = LevelState.STARTFIRE;
				launchMouseListener.setState(levelStage);
			}
			if (LauncSetingChenging == true) {
				launch1.clearTraectory();// РѕС‡РёС‰РµРЅРЅСЏ С‚СЂР°РµРєС‚РѕСЂС–С—
				launch1.createTraectory();// СЃС‚РІРѕСЂРµРЅРЅСЏ С‚СЂР°РµРєС‚РѕСЂС–С—
			}
			break;
		case STARTFIRE:
			if( hit) {
				hitTime += dt;
				if(hitTime>0.3) {
			tItem1.setTraectoryLine(launch1.calculateTraectory((float) 3.0));
			levelStage = LevelState.FIREING;
			trowSemaphore.release();
			launch1.setVisible(false);
			bananbar.removeItem();
			tItem1.torw(trowSemaphore);
			launchMouseListener.setState(levelStage);
			hit=false;
			hitTime =0;
				}
			
			}
			else {
				soundControl.playLauchSound();
				hit = true;
			}
			break;

		case FIREING:

			if (tItem1.isThrow()) {
				if( hit) {
					hitTime += dt;
					
					if(hitTime>0.3) {
						tItem1.stopTrow();
						CatHitExplosion exposion1 = new CatHitExplosion(0, 0, mainStage);
						exposion1.setSize(400, 400);
						exposion1.centerAtActor(cat1);
						soundControl.playExplosion();
						cat1.setHitPoint(cat1.getHitPoint() - 100);
						numberOfHit++;
						numberPoints += 100;
						gamePoint.setText("points: " + numberPoints);

						if (numberOfHit == 1 && catPosition == 1) {

							cat1.setVisible(false);
							catsArray.get(1).setHitPoint(cat1.getHitPoint());
							cat1.remove();
							cat1 = catsArray.get(1);
							cat1.setVisible(true);
							catPosition = 2;

						}
						if (numberOfHit == 2 && catPosition == 2) {

							cat1.setVisible(false);
							catsArray.get(2).setHitPoint(cat1.getHitPoint());
							cat1.remove();
							cat1 = catsArray.get(2);
							cat1.setVisible(true);
							catPosition = 3;
							cat1.constanMove(0, 0.9f, 1000, 350);

						}
						if (cat1.getHitPoint() <= 0) {
							levelStage = LevelState.GAMEWIN;
							launchMouseListener.setState(levelStage);
							gameWin();
						} else {
							trowLeft--;
							if (trowLeft > 0) {

								levelStage = LevelState.TARGETING;
								launch1.setVisible(true);
								launchMouseListener.setState(levelStage);
							} else {
								levelStage = LevelState.GAMEOVER;
								launchMouseListener.setState(levelStage);
								gameOver();
							}
						}
						hit=false;
						hitTime =0;
					}
					break;
				}
				if (tItem1.overlaps(cat1)) {
					soundControl.playcatHit();
					hit=true;
				
				}
				for (BaseActor barier : BaseActor.getList(mainStage, BarierActor.class)) {
					if (tItem1.overlaps(barier)) {

						tItem1.stopTrow();
						CatHitExplosion exposion1 = new CatHitExplosion(0, 0, mainStage);
						exposion1.setSize(40, 40);
						exposion1.centerAtActor(tItem1);
						soundControl.playExplosion();
						trowLeft--;
						soundControl.playMissSound();
						if (trowLeft == 0) {
							levelStage = LevelState.GAMEOVER;
							launchMouseListener.setState(levelStage);
							gameOver();
						} else {
							levelStage = LevelState.TARGETING;
							launch1.setVisible(true);
							launchMouseListener.setState(levelStage);
						}
					}
				}
			} else {
				trowLeft--;
				soundControl.playMissSound();
				if (trowLeft == 0) {
					levelStage = LevelState.GAMEOVER;
					launchMouseListener.setState(levelStage);
					gameOver();
				} else {
					levelStage = LevelState.TARGETING;
					launch1.setVisible(true);
					launchMouseListener.setState(levelStage);
				}
			}

			break;

		case HITING:

			break;
		case GAMEOVER:

		default:
			// System.out.println("no fire!!");

		}

	}

	public void gameOver() {
		soundControl.stopBackground();
		soundControl.playEndSound();
		BaseActor messageLose = new BaseActor(0, 0, uiStage);
		messageLose.loadTexture("assets/message-lose.png");
		messageLose.centerAtPosition(550, 450);
		messageLose.setOpacity(0);
		messageLose.addAction(Actions.fadeIn(3));

		DialogBox dialogBox = new DialogBox(100, 100, uiStage);
		dialogBox.setDialogSize(600, 180);
		dialogBox.setBackgroundColor(new Color(0.6f, 0.6f, 0.8f, 1));
		dialogBox.setFontScale(0.75f);
		dialogBox.setVisible(false);
		TextButton quitButton = new TextButton("Exit", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		quitButton.align(Align.center);
		dialogBox.addActor(quitButton);
		quitButton.setPosition(dialogBox.getWidth() - quitButton.getWidth(), 0);
		quitButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			Gdx.app.exit();
			return false;
		});
		TextButton replayButton = new TextButton("try again", CatGame.motherSkin, "menu1"); // replay button
		replayButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			soundControl.playBackground();
			CatGame.setActiveScreen(new LevelScreen());
			return false;
		});
		dialogBox.addActor(replayButton);
		dialogBox.addActor(quitButton);
		replayButton.setPosition(dialogBox.getWidth() / 2 - replayButton.getWidth() / 2, 100);
		quitButton.setPosition(dialogBox.getWidth() / 2 - quitButton.getWidth() / 2, 20);

		uiTable.clear();
		uiTable.add(dialogBox).expandX().expandY().center();

		Scene scene = new Scene();
		mainStage.addActor(scene);
//показ діалону
		scene.addSegment(new SceneSegment(dialogBox, SceneActions.fadeIn(4)));
		scene.addSegment(new SceneSegment(dialogBox, Actions.show()));
		// scene.addSegment( new SceneSegment( dialogBox, Actions.fadeIn(6) ));

		scene.start();
	}

	public void gameWin() {
		soundControl.stopBackground();
		soundControl.playWinSound();
		cat1.remove();
		numberPoints = numberPoints + trowLeft * 50;

		// scene.start();
		BaseActor messageWin = new BaseActor(0, 0, uiStage);
		messageWin.loadTexture("assets/message-win.png");
		messageWin.centerAtPosition(550, 450);
		messageWin.setOpacity(0);
		messageWin.addAction(Actions.fadeIn(5));

		DialogBox dialogBox = new DialogBox(100, 100, uiStage);
		dialogBox.setDialogSize(600, 180);
		dialogBox.setBackgroundColor(new Color(0.6f, 0.6f, 0.8f, 1));
		dialogBox.setFontScale(0.75f);
		dialogBox.setVisible(false);
		TextButton quitButton = new TextButton("Exit to menu", CatGame.motherSkin, "menu1"); // створення кнопки виходу
		quitButton.align(Align.center);
		dialogBox.addActor(quitButton);
		quitButton.setPosition(dialogBox.getWidth() - quitButton.getWidth(), 0);
		quitButton.addListener((Event e) -> {
			if (!(e instanceof InputEvent)) {
				return false;
			}
			if (!((InputEvent) e).getType().equals(InputEvent.Type.touchDown)) {
				return false;
			}
			soundControl.playBackground();
			CatGame.setActiveScreen(new MenuScreen());
			
			return true;
		});
		Label mainLable = new Label("Yuor score " + numberPoints, CatGame.motherSkin, "menu1");
		// mainLable.setAlignment(Align.center);
		dialogBox.addActor(mainLable);
		dialogBox.addActor(quitButton);
		mainLable.setPosition(dialogBox.getWidth() / 2 - mainLable.getWidth() / 2, 100);
		quitButton.setPosition(dialogBox.getWidth() / 2 - quitButton.getWidth() / 2, 20);

		uiTable.clear();
		uiTable.add(dialogBox).expandX().expandY().center();

		Scene scene = new Scene();
		mainStage.addActor(scene);
// показ діалону
		scene.addSegment(new SceneSegment(dialogBox, SceneActions.fadeIn(3)));
		scene.addSegment(new SceneSegment(dialogBox, Actions.show()));

		scene.start();

	}

	
}
