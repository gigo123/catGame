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
		traectoryActor = new TraectoryActor(mainStage);
		iniTraectoryParametr();

	}

	private void iniTraectoryParametr() {
		float startSpeeadTrow = 100;
		float startAngleTrow = 0;
		trowTraectoryParameters = new TrowTraectoryParameters(startSpeeadTrow, startAngleTrow, false);
		trowTraectoryParameters.setMaxSpead(300);
		trowTraectoryParameters.setMinSpead(40);
		trowTraectoryParameters.setMaxAngle(159);
		trowTraectoryParameters.setMinAngle(-90);
		trowTraectoryParameters.setMaxXcoordinate(BaseActor.getWorldBounds().width);
	}

	@Override
	public void update(float dt) {

		keyPressedSwich();
		if (levelStage == LevelState.MOVING) {
			SolidActor.overlapBarierActor(mainCharacter, mainStage);
			return;
		}

		if (levelStage == LevelState.FIREING) {
			if (!throwItem.isThrow()) {
				levelStage = LevelState.TARGETING;
				swichLevelMode();
			}
		}
	}

	public boolean keyDown(int keyCode) {
		if (levelStage == LevelState.GAMEOVER)
			return false;

		if (keyCode == Keys.L) {
			System.out.println(levelStage.name());

			if (levelStage == LevelState.TARGETING) {
				levelStage = LevelState.MOVING;
				swichLevelMode();
				return false;
			}
			if (levelStage == LevelState.MOVING) {
				levelStage = LevelState.TARGETING;
				swichLevelMode();
			}
			return false;
		}
		if (keyCode == Keys.SPACE) {
			if (levelStage == LevelState.TARGETING) {
				levelStage = LevelState.FIREING;
				swichLevelMode();
			}
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

	private void swichLevelMode() {
		switch (levelStage) {
		case TARGETING:
			if (mainCharacter.isMoveEnding()) {
				mainCharacter.setMoveAllowed(false);
				float startTraectoryX = 30;
				float startTraectoryY = 20;
				trowTraectory = new TrowTraectory(mainCharacter.getX()+startTraectoryX, mainCharacter.getY() + startTraectoryY,
						trowTraectoryParameters, traectoryActor);
				iputActionWork = new TargetInputActionWorker(trowTraectoryParameters, trowTraectory);
				trowTraectory.createTraectory();

			}
			break;

		case MOVING:
			traectoryActor.hideTraectory();
			mainCharacter.setMoveAllowed(true);
			iputActionWork = new MoveIputActionWorker(mainCharacter);
			break;

		case FIREING:
			iputActionWork = new FireInputActionWorker();
			lauchTrowInem();
			break;
		default:
			break;

		}

	}

	private void lauchTrowInem() {
		throwItem = new NewThrowItem(mainCharacter.getX(), mainCharacter.getY(), trowTraectory.getCalculatetTraectory(),
				mainStage);

	}

	public OldMen getMainCharacter() {
		return mainCharacter;
	}

	public void setMainCharacter(OldMen mainCharacter) {
		this.mainCharacter = mainCharacter;
	}

	public LevelState getLevelStage() {
		return levelStage;
	}

	public void setLevelStage(LevelState levelStage) {
		this.levelStage = levelStage;
	}

	public InputActionWorker getIputActionWork() {
		return iputActionWork;
	}

	public void setIputActionWork(InputActionWorker iputActionWork) {
		this.iputActionWork = iputActionWork;
	}

	public TrowTraectory getTrowTraectory() {
		return trowTraectory;
	}

	public void setTrowTraectory(TrowTraectory trowTraectory) {
		this.trowTraectory = trowTraectory;
	}

	public TrowTraectoryParameters getTrowTraectoryParameters() {
		return trowTraectoryParameters;
	}

	public void setTrowTraectoryParameters(TrowTraectoryParameters trowTraectoryParameters) {
		this.trowTraectoryParameters = trowTraectoryParameters;
	}

	public TraectoryActor getTraectoryActor() {
		return traectoryActor;
	}

	public void setTraectoryActor(TraectoryActor traectoryActor) {
		this.traectoryActor = traectoryActor;
	}

	public NewThrowItem getThrowItem() {
		return throwItem;
	}

	public void setThrowItem(NewThrowItem throwItem) {
		this.throwItem = throwItem;
	}

}
