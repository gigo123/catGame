package com.thsoft.catgame.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;
import com.thsoft.catgame.game.BaseScreen;
import com.thsoft.catgame.game.InputActionWorker;
import com.thsoft.catgame.game.MoveIputActionWorker;
import com.thsoft.catgame.game.TilemapActor;
import com.thsoft.catgame.gameLevel.MapLevelLogik;
import com.thsoft.catgame.gameLevel.MapLevelVaribles;
import com.thsoft.catgame.gameLogik.OldMen;
import com.thsoft.catgame.gameLogik.SolidActor;

public class MapLevel1 extends  BaseScreen {
	private MapLevelVaribles mapLevelVaribles;
	private InputActionWorker iputActionWork;
	private MapLevelLogik mapLevelLogik;

	public MapLevel1() {
		super();
		mapLevelVaribles = new MapLevelVaribles();
	//	mapLevelSrean = new MapLevelScrean(mapLevelVaribles);
		mapLevelVaribles.setMainStage(mainStage);
		createTilemapActor("assets/maplevel1/map.tmx");
		mapLevelLogik = new MapLevelLogik(mapLevelVaribles);
		//setMapLevelVaribles(mapLevelVaribles);
		
	}

	private void createTilemapActor(String sourseFile) {
		Stage mainStage = mapLevelVaribles.getMainStage();
		TilemapActor tma = new TilemapActor(sourseFile, mainStage);

		for (MapObject obj : tma.getRectangleList("SolidActor")) {
			MapProperties props = obj.getProperties();
			new SolidActor((float) props.get("x"), (float) props.get("y"), (float) props.get("width"),
					(float) props.get("height"), mainStage);

		}

		MapObject startPoint = tma.getRectangleList("start").get(0);
		MapProperties startProps = startPoint.getProperties();
		mapLevelVaribles
				.setMainCharacter(new OldMen((float) startProps.get("x"), (float) startProps.get("y"), mainStage));
		mapLevelVaribles.setIputActionWork( new MoveIputActionWorker(mapLevelVaribles.getMainCharacter()));

	}
	@Override
	public void update(float dt) {
		iputActionWork=mapLevelVaribles.getIputActionWork();
		keyPressedSwich();
		mapLevelLogik.update();		
	}

	public boolean keyDown(int keyCode) {
		//if (levelStage == LevelState.GAMEOVER)
	//		return false;

		if (keyCode == Keys.L) {
			iputActionWork.Lkey();
		/*	System.out.println(levelStage.name());

			if (levelStage == LevelState.TARGETING) {
				levelStage = LevelState.MOVING;
				swichLevelMode();
				return false;
			}
			if (levelStage == LevelState.MOVING) {
				levelStage = LevelState.TARGETING;
				swichLevelMode();
			}
			*/
			return false;
		}
		if (keyCode == Keys.SPACE) {
			iputActionWork.SpareKey();
		//	if (levelStage == LevelState.TARGETING) {
		//		levelStage = LevelState.FIREING;
		//		swichLevelMode();
		//	}
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
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
