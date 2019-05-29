package com.thsoft.catgame.gameLevel;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;
import com.thsoft.catgame.game.FireInputActionWorker;
import com.thsoft.catgame.game.InputActionWorker;
import com.thsoft.catgame.game.MoveIputActionWorker;
import com.thsoft.catgame.game.TargetInputActionWorker;
import com.thsoft.catgame.gameLogik.NewThrowItem;
import com.thsoft.catgame.gameLogik.OldMen;
import com.thsoft.catgame.gameLogik.SolidActor;
import com.thsoft.catgame.gameLogik.TraectoryActor;
import com.thsoft.catgame.gameLogik.TrowTraectory;
import com.thsoft.catgame.gameLogik.TrowTraectoryParameters;

public class MapLevelLogik {
	private Stage mainStage;
	private OldMen mainCharacter;
	private LevelState levelStage;
	private InputActionWorker iputActionWork;
	private TrowTraectory trowTraectory;
	private TrowTraectoryParameters trowTraectoryParameters;
	private TraectoryActor traectoryActor;
	private NewThrowItem throwItem;
	
	

	public MapLevelLogik(Stage mainStage, float x,float y) {
		super();
		levelStage = LevelState.MOVING;
		this.mainStage = mainStage;
		this.mainCharacter  = new OldMen(x, y, mainStage);
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
	public void update() {
	

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
	public InputActionWorker getIputActionWork() {
		return iputActionWork;
	}

}
