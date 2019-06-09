package com.thsoft.catgame.game;

import com.badlogic.gdx.math.Vector2;
import com.thsoft.catgame.gameLevel.LevelState;
import com.thsoft.catgame.gameLevel.MapLevelVaribles;
import com.thsoft.catgame.gameLogik.OldMen;

public class MoveIputActionWorker implements InputActionWorker {
	private OldMen mainCharacter;
	private MapLevelVaribles mapLevelVaribles;

	public MoveIputActionWorker(MapLevelVaribles mapLevelVaribles) {
		super();
		this.mapLevelVaribles =mapLevelVaribles;
		this.mainCharacter = mapLevelVaribles.getMainCharacter();
	}

	public void setMainCharacter(OldMen mainCharacter) {
		this.mainCharacter = mainCharacter;
	}
	public void LeftKey() {
			Vector2 acselVector = mainCharacter.getAccelerationVec();
			acselVector.add(-mainCharacter.getWalkAcceleration(), 0);
	}

	public void RightKey() {
		
			Vector2 acselVector = mainCharacter.getAccelerationVec();
			acselVector.add(+mainCharacter.getWalkAcceleration(), 0);
	}

	public void UpKey() {

		
	}

	public void DownKey() {

		
	}

	@Override
	public void Lkey() {
		mapLevelVaribles.setLevelStage(LevelState.TARGETING);
		
	}

	@Override
	public void SpareKey() {
		// TODO Auto-generated method stub
		
	}
}
