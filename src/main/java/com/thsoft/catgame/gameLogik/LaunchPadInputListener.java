/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thsoft.catgame.gameLogik;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.thsoft.catgame.game.*;
import com.thsoft.catgame.gameLevel.*;

/**
 * обробник взаємодії мишкою для об'єкта LaunchigPad
 *
 * @author Taras Hopka 2019
 */
public class LaunchPadInputListener extends InputListener {

	private LaunchigPad launch;
	private LevelState state;

	public LaunchPadInputListener(LaunchigPad launch, Stage s, LevelState state) {
		this.launch = launch;
		this.state = state;
	}

	@Override
	public boolean touchDown(InputEvent event, float eventOffsetX, float eventOffsetY, int pointer, int button) {

		if (state != LevelState.TARGETING) {
			return false;
		}
		System.out.println("hello touch");
		if (!launch.isDraggable()) {
			return false;
		}

		launch.setGrabOffsetX(eventOffsetX);
		launch.setGrabOffsetY(eventOffsetY);

//                    // store original position
//                    // in case actor needs to return to it later
//                    launch.startPositionX = launch.getX();
//                    launch.startPositionY = launch.getY();
		launch.toFront();

		// increase size; object appears larger when lifted by player
		launch.addAction(Actions.scaleTo(1.1f, 1.1f, 0.25f));

		// launch.onDragStart();
		return true; // returning true indicates other touch methods are called
	}

	@Override
	public void touchDragged(InputEvent event, float eventOffsetX, float eventOffsetY, int pointer) {
		if (state != LevelState.TARGETING) {
			return;
		}

		launch.moveBy(eventOffsetX, eventOffsetY);

	}

	@Override
	public void touchUp(InputEvent event, float eventOffsetX, float eventOffsetY, int pointer, int button) {
		if (state != LevelState.TARGETING) {
			return;
		}
		this.state = LevelState.STARTFIRE;

	}

	/**
	 * @return the state
	 */
	public LevelState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(LevelState state) {
		this.state = state;
	}
}
