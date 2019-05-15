package com.thsoft.catgame.game;

import com.badlogic.gdx.math.Vector2;
import com.thsoft.catgame.gameLogik.OldMen;

public class MoveIputActionWorker implements InputActionWorker {
	private OldMen mainCharacter;

	public MoveIputActionWorker(OldMen mainCharacter) {
		super();
		this.mainCharacter = mainCharacter;
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
}
