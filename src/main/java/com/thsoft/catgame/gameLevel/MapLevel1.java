package com.thsoft.catgame.gameLevel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.thsoft.catgame.game.BaseActor;
import com.thsoft.catgame.game.BaseScreen;
import com.thsoft.catgame.game.InputActionWorker;
import com.thsoft.catgame.game.MoveIputActionWorker;
import com.thsoft.catgame.game.TilemapActorBuilder;

public class MapLevel1 extends BaseScreen {
	private MapLevelVaribles mapLevelVaribles;
	private InputActionWorker iputActionWork;
	private MapLevelLogik mapLevelLogik;
	

	public MapLevel1(String mapFile) {
		super();
		mapLevelVaribles = new MapLevelVaribles();
		mapLevelVaribles.setMainStage(mainStage);
		new TilemapActorBuilder(mapFile, mapLevelVaribles);
		mapLevelVaribles.setWorldWidth(BaseActor.getWorldBounds().getWidth());
		mapLevelLogik = new MapLevelLogik(mapLevelVaribles);
		mapLevelVaribles.setMapLevelLogik(mapLevelLogik);
		mapLevelVaribles.setLevelStage(LevelState.MOVING);
		mapLevelVaribles.setIputActionWork(new MoveIputActionWorker(mapLevelVaribles));

	}

	@Override
	public void update(float dt) {
		iputActionWork = mapLevelVaribles.getIputActionWork();
		keyPressedSwich();
		mapLevelLogik.update();
	}

	public boolean keyDown(int keyCode) {
		// if (levelStage == LevelState.GAMEOVER)
		// return false;

		if (keyCode == Keys.L) {
			iputActionWork.Lkey();

			//mapLevelLogik.swichLevelMode();
			return false;
		}
		if (keyCode == Keys.SPACE) {
			if (mapLevelVaribles.getLevelStage() == LevelState.TARGETING) {
				mapLevelVaribles.setLevelStage(LevelState.FIREING);
			//	mapLevelLogik.swichLevelMode();
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

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

}
