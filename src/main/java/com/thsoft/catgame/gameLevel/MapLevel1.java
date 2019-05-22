package com.thsoft.catgame.gameLevel;

import com.thsoft.catgame.game.BaseActor;
import com.thsoft.catgame.game.BaseScreen;
import com.thsoft.catgame.game.FireInputActionWorker;
import com.thsoft.catgame.game.InputActionWorker;
import com.thsoft.catgame.game.MoveIputActionWorker;
import com.thsoft.catgame.game.TargetInputActionWorker;
import com.thsoft.catgame.game.TilemapActor;
import com.thsoft.catgame.gameLogik.NewThrowItem;
import com.thsoft.catgame.gameLogik.OldMen;
import com.thsoft.catgame.gameLogik.SolidActor;
import com.thsoft.catgame.gameLogik.TraectoryActor;
import com.thsoft.catgame.gameLogik.TrowTraectory;
import com.thsoft.catgame.gameLogik.TrowTraectoryParameters;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MapLevel1 extends BaseScreen {
	private OldMen mainCharacter;
	private LevelState levelStage;
	private InputActionWorker iputActionWork;
	private TrowTraectory trowTraectory;
	private TrowTraectoryParameters trowTraectoryParameters;
	private TraectoryActor traectoryActor;
	private NewThrowItem throwItem;

	@Override
	public void initialize() {
		levelStage = LevelState.MOVING;
		TilemapActor tma = new TilemapActor("assets/maplevel1/map.tmx", mainStage);

		for (MapObject obj : tma.getRectangleList("SolidActor")) {
			MapProperties props = obj.getProperties();
			new SolidActor((float) props.get("x"), (float) props.get("y"), (float) props.get("width"),
					(float) props.get("height"), mainStage);
		}

		MapObject startPoint = tma.getRectangleList("start").get(0);
		MapProperties startProps = startPoint.getProperties();
		mainCharacter = new OldMen((float) startProps.get("x"), (float) startProps.get("y"), mainStage);
		iputActionWork = new MoveIputActionWorker(mainCharacter);
		
		float startSpeeadTrow  =100;
		float startAngleTrow = 0;
		float trowMaxSpeead = 300;
		float trowMinSpeead = 40;
		float trowMaxAngle = 120;
		float trowMinAngle = -30;
		traectoryActor = new TraectoryActor(mainStage);
		trowTraectoryParameters= new TrowTraectoryParameters(startSpeeadTrow, startAngleTrow, trowMaxSpeead, trowMinSpeead, trowMaxAngle, trowMinAngle);

	}

	@Override
	public void update(float dt) {
		for (BaseActor actor : BaseActor.getList(mainStage, SolidActor.class)) {
			SolidActor solid = (SolidActor) actor;
			if (mainCharacter.overlaps(solid) && solid.isEnabled()) {
				Vector2 offset = mainCharacter.preventOverlap(solid);
				if (offset != null) {
					// collided in X direction
					if (Math.abs(offset.x) > Math.abs(offset.y))
						mainCharacter.getVelocityVec().x = 0;
					else // collided in Y direction
						mainCharacter.getVelocityVec().y = 0;
				}
			}
		}

		keyPressedSwich();
		
		if (levelStage == LevelState.TARGETING) {
			if (mainCharacter.isMoveEnding()) {
				mainCharacter.setMoveAllowed(false);
				float startTraectoryX=300;
				float startTraectoryY=40;
				trowTraectory=new TrowTraectory(mainCharacter.getX(), mainCharacter.getY(), trowTraectoryParameters, traectoryActor);
				iputActionWork = new TargetInputActionWorker(trowTraectoryParameters, trowTraectory);
			//	mainCharacter.setMoveEnding(false);
				trowTraectory.createTraectory();
			}
			return;
		}
		if (levelStage == LevelState.FIREING) {
			if( !throwItem.isThrow()) {
				levelStage = LevelState.TARGETING;
			}
		}
	}

	public boolean keyDown(int keyCode) {
		if (levelStage == LevelState.GAMEOVER)
			return false;

		if (keyCode == Keys.L) {
			swichTargetMOveMode();
			return false;
		}
		if (keyCode == Keys.SPACE) {
			levelStage = LevelState.FIREING;
			iputActionWork =new FireInputActionWorker();
			lauchTrowInem();
		}

		return false;
	}

	private void keyPressedSwich() {

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			iputActionWork.LeftKey();
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			iputActionWork.RightKey();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			iputActionWork.UpKey();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			iputActionWork.DownKey();
		}
	}

	private void swichTargetMOveMode() {
		if (levelStage != LevelState.TARGETING) {
			levelStage = LevelState.TARGETING;
		} else {
			levelStage = LevelState.MOVING;
			iputActionWork = new MoveIputActionWorker(mainCharacter);
		}
	}

	private void lauchTrowInem() {
		throwItem = new NewThrowItem(mainCharacter.getX(), mainCharacter.getY(), trowTraectory.getCalculatetTraectory(), mainStage);
		
		
	}
}
