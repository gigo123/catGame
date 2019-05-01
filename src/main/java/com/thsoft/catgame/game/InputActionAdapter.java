package com.thsoft.catgame.game;

import com.badlogic.gdx.math.Vector2;
import com.thsoft.catgame.gameLevel.LevelState;
import com.thsoft.catgame.gameLogik.LaunchPadS;
import com.thsoft.catgame.gameLogik.OldMen;

public class InputActionAdapter {
	private OldMen mainCharacter;
	private LaunchPadS lauchPAd;

	public OldMen getMainCharacter() {
		return mainCharacter;
	}

	public void setMainCharacter(OldMen mainCharacter) {
		this.mainCharacter = mainCharacter;
	}

	public LaunchPadS getLauchPAd() {
		return lauchPAd;
	}

	public void setLauchPAd(LaunchPadS lauchPAd) {
		this.lauchPAd = lauchPAd;
	}

	public InputActionAdapter(OldMen mainCharacter) {
		this.mainCharacter = mainCharacter;
	}

	public void LeftKey(LevelState CurentState) {
		if (CurentState == LevelState.MOVING) {
			Vector2 acselVector = mainCharacter.getAccelerationVec();
			acselVector.add(-mainCharacter.getWalkAcceleration(), 0);

		}
		if (CurentState == LevelState.TARGETING) {
			lauchPAd.downSpeead();
			lauchPAd.clearTraectory();
			lauchPAd.createTraectory();
		}

	}

	public void RightKey(LevelState CurentState) {
		if (CurentState == LevelState.MOVING) {
			Vector2 acselVector = mainCharacter.getAccelerationVec();
			acselVector.add(+mainCharacter.getWalkAcceleration(), 0);

		}
		if (CurentState == LevelState.TARGETING) {
			lauchPAd.addSpeead();
			lauchPAd.clearTraectory();
			lauchPAd.createTraectory();
		}
	}

	public void UpKey(LevelState CurentState) {

		if (CurentState == LevelState.TARGETING) {
			lauchPAd.upAngle();
			lauchPAd.clearTraectory();
			lauchPAd.createTraectory();
		}
	}

	public void DownKey(LevelState CurentState) {

		if (CurentState == LevelState.TARGETING) {
			lauchPAd.downAngle();
			lauchPAd.clearTraectory();
			lauchPAd.createTraectory();
		}
	}
}
