package com.thsoft.catgame.gameLevel;

import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;
import com.thsoft.catgame.game.FireInputActionWorker;
import com.thsoft.catgame.game.InputActionWorker;
import com.thsoft.catgame.game.MoveIputActionWorker;
import com.thsoft.catgame.game.TargetInputActionWorker;
import com.thsoft.catgame.game.TilemapActor;
import com.thsoft.catgame.gameLogik.BarierActor;
import com.thsoft.catgame.gameLogik.CatHitExplosion;
import com.thsoft.catgame.gameLogik.CatMapLevel;
import com.thsoft.catgame.gameLogik.NewThrowItem;
import com.thsoft.catgame.gameLogik.OldMen;
import com.thsoft.catgame.gameLogik.SolidActor;
import com.thsoft.catgame.gameLogik.TraectoryActor;
import com.thsoft.catgame.gameLogik.TrowTraectory;
import com.thsoft.catgame.gameLogik.TrowTraectoryParameters;

public class MapLevelLogik {
	//private Stage mainStage;
	private OldMen mainCharacter;

	private InputActionWorker iputActionWork;
	private TrowTraectory trowTraectory;
	private TrowTraectoryParameters trowTraectoryParameters;
	private TraectoryActor traectoryActor;
	private NewThrowItem throwItem;
	private MapLevelVaribles mapLevelVaribles;

	public MapLevelLogik(MapLevelVaribles mapLevelVaribles) {
		super();
		this.mapLevelVaribles = mapLevelVaribles;
		mainCharacter=mapLevelVaribles.getMainCharacter();
		iputActionWork = new MoveIputActionWorker(mapLevelVaribles);
		traectoryActor = new TraectoryActor(mapLevelVaribles.getMainStage());
		iniTraectoryParametr(mapLevelVaribles.getWorldWidth());
		
	}
	

	private void iniTraectoryParametr(float worldSize) {
		float startSpeeadTrow = 100;
		float startAngleTrow = 0;
		trowTraectoryParameters = new TrowTraectoryParameters(startSpeeadTrow, startAngleTrow, false);
		trowTraectoryParameters.setMaxSpead(300);
		trowTraectoryParameters.setMinSpead(40);
		trowTraectoryParameters.setMaxAngle(159);
		trowTraectoryParameters.setMinAngle(-90);
		trowTraectoryParameters.setMaxXcoordinate(worldSize);

	}

	
	public void update() {
		if(mapLevelVaribles.isLevelStageChanged()) {
			swichLevelMode();
			mapLevelVaribles.setLevelStageChanged(false);
		}
		if (mapLevelVaribles.getLevelStage() == LevelState.MOVING) {
			List<Vector2> overlapList = SolidActor.overlapBarierActor(mainCharacter, mapLevelVaribles.getMainStage());
			overlapPrevent(overlapList);
			return;
		}
		if (mapLevelVaribles.getLevelStage() == LevelState.FIREING)
		{
			if (!throwItem.isThrow()) {
				mapLevelVaribles.setLevelStage( LevelState.TARGETING);
				swichLevelMode();
				return;
			}
			
			for (BaseActor barier : BaseActor.getList(mapLevelVaribles.getMainStage(), CatMapLevel.class)) {
				if (throwItem.overlaps(barier)) {
					CatHitExplosion exposion1 = new CatHitExplosion(0, 0, mapLevelVaribles.getMainStage());
					exposion1.setSize(40, 40);
					exposion1.centerAtActor(throwItem);
					throwItem.stopTrow();
					mapLevelVaribles.setLevelStage( LevelState.TARGETING);
					swichLevelMode();
					return;
				}
				}
		}
	}

	public void overlapPrevent(List<Vector2> overlapList) {
		for (Vector2 offset : overlapList) {
			if (Math.abs(offset.x) > Math.abs(offset.y)) {
				mainCharacter.getVelocityVec().x = 0;
			} else {// collided in Y direction
				mainCharacter.getVelocityVec().y = 0;
			}
		}
	}

	public void swichLevelMode() {
		System.out.println(mapLevelVaribles.getLevelStage());
		switch (mapLevelVaribles.getLevelStage()) {
		case TARGETING:
			if (mainCharacter.isMoveEnding()) {
				mainCharacter.setMoveAllowed(false);
				float startTraectoryX = 30;
				float startTraectoryY = 20;
				trowTraectory = new TrowTraectory(mainCharacter.getX() + startTraectoryX,
						mainCharacter.getY() + startTraectoryY, trowTraectoryParameters, traectoryActor);
				iputActionWork = new TargetInputActionWorker(trowTraectoryParameters, trowTraectory,mapLevelVaribles);
				mapLevelVaribles.setIputActionWork(iputActionWork);
				trowTraectory.createTraectory();

			}
			break;

		case MOVING:
			traectoryActor.hideTraectory();
			mainCharacter.setMoveAllowed(true);
			iputActionWork = new MoveIputActionWorker(mapLevelVaribles);
			mapLevelVaribles.setIputActionWork(iputActionWork);
			break;

		case FIREING:
			iputActionWork = new FireInputActionWorker();
			mapLevelVaribles.setIputActionWork(iputActionWork);
			lauchTrowInem();
			break;
		default:
			break;

		}

	}

	private void lauchTrowInem() {
		throwItem = new NewThrowItem(mainCharacter.getX(), mainCharacter.getY(), trowTraectory.getCalculatetTraectory(),
				mapLevelVaribles.getMainStage());

	}

	public InputActionWorker getIputActionWork() {
		return iputActionWork;
	}

}
