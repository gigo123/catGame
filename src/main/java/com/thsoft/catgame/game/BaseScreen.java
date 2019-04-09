/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thsoft.catgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * базовий абстрактний клас для відображення на екран
 *
 * @author Taras Hopka 2019
 */
public abstract class BaseScreen implements Screen, InputProcessor {

	/**
	 * базова сцена для обєктів
	 */
	protected Stage mainStage;
	/**
	 * сцена для інтерфесу
	 */
	protected Stage uiStage;
	/**
	 * таблиця для побудови інтерфейсу
	 */
	protected Table uiTable;

	public BaseScreen() {
		mainStage = new Stage();
		uiStage = new Stage();
		uiTable = new Table();
		uiTable.setFillParent(true);
		uiStage.addActor(uiTable);

		initialize();
	}

	public abstract void initialize();

	public abstract void update(float dt);

	/**
	 * Gameloop: (1) process input (discrete handled by listener; continuous in
	 * update) (2) update game logic (3) render the graphics
	 *
	 */
	@Override
	public void render(float dt) {
		// act methods
		uiStage.act(dt);
		mainStage.act(dt);

		// defined by user
		update(dt);

		// clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw the graphics
		mainStage.draw();
		uiStage.draw();
	}

	/**
	 * methods required by Screen interface
	 *
	 * @param width
	 * @param height
	 */
	@Override
	public void resize (int width, int height) {
		mainStage.getViewport().update(width, height, true);
		uiStage.getViewport().update(width, height, true);
	}

	/**
	 * methods required by Screen interface
	 */
	@Override
	public void pause() {
	}

	/**
	 * methods required by Screen interface
	 */
	@Override
	public void resume() {
	}

	/**
	 * methods required by Screen interface
	 */
	@Override
	public void dispose() {
	}

	/**
	 * Called when this becomes the active screen in a Game. Set up InputMultiplexer
	 * here, in case screen is reactivated at a later time.
	 */
	@Override
	public void show() {
		InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
		im.addProcessor(this);
		im.addProcessor(uiStage);
		im.addProcessor(mainStage);
	}

	/**
	 * Called when this is no longer the active screen in a Game. Screen class and
	 * Stages no longer process input. Other InputProcessors must be removed
	 * manually.
	 */
	@Override
	public void hide() {
		InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
		im.removeProcessor(this);
		im.removeProcessor(uiStage);
		im.removeProcessor(mainStage);
	}

	/**
	 * methods required by InputProcessor interface
	 */
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	/**
	 * methods required by InputProcessor interface
	 *
	 */
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * methods required by InputProcessor interfac
	 */
	@Override
	public boolean keyTyped(char c) {
		return false;
	}

	/**
	 * methods required by InputProcessor interface
	 *
	 * @param screenX
	 * @param screenY
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * methods required by InputProcessor interface
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	/**
	 * methods required by InputProcessor interface
	 *
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * methods required by InputProcessor interface
	 *
	 * @param screenX
	 * @param screenY
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * methods required by InputProcessor interface
	 *
	 * @param screenX
	 * @param screenY
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
}
