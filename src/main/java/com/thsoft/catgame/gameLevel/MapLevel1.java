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
	private InputActionWorker iputActionWork;
	private MapLevelLogik mapLevelLogik;

	
	@Override
	public void initialize() {
		createTilemapActor("assets/maplevel1/map.tmx");
		mapLevelLogik = new MapLevelLogik(mainStage, mainCharacter,BaseActor.getWorldBounds().width);
	}
	private void createTilemapActor(String sourseFile) {
		TilemapActor tma = new TilemapActor(sourseFile, mainStage);

		for (MapObject obj : tma.getRectangleList("SolidActor")) {
			MapProperties props = obj.getProperties();
			new SolidActor((float) props.get("x"), (float) props.get("y"), (float) props.get("width"),
					(float) props.get("height"), mainStage);
			
		}

		MapObject startPoint = tma.getRectangleList("start").get(0);
		MapProperties startProps = startPoint.getProperties();
		mainCharacter  = new OldMen((float) startProps.get("x"),(float) startProps.get("y"), mainStage);
		
	}
	
	@Override
	public void update(float dt) {
		iputActionWork=mapLevelLogik.getIputActionWork();
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


}
