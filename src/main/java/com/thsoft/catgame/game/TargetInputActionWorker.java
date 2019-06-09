package com.thsoft.catgame.game;

import com.thsoft.catgame.gameLevel.LevelState;
import com.thsoft.catgame.gameLevel.MapLevelVaribles;
import com.thsoft.catgame.gameLogik.TrowTraectory;
import com.thsoft.catgame.gameLogik.TrowTraectoryParameters;
public class TargetInputActionWorker  implements InputActionWorker{
	private TrowTraectoryParameters  trowTraectoryParameters;
	private TrowTraectory trowTraectory;
	private MapLevelVaribles mapLevelVaribles;

	
	public TargetInputActionWorker( TrowTraectoryParameters  trowTraectoryParameters, TrowTraectory trowTraectory,MapLevelVaribles mapLevelVaribles) {
		super();
		this.trowTraectoryParameters = trowTraectoryParameters;
		this.trowTraectory = trowTraectory;
		this.mapLevelVaribles =mapLevelVaribles;
	}
	

	public void LeftKey() {
		trowTraectoryParameters.setSpeeadTrow((float)(trowTraectoryParameters.getSpeeadTrow() - 0.5));
			trowTraectory.createTraectory();
	}

	public void RightKey() {
		
		trowTraectoryParameters.setSpeeadTrow((float)(trowTraectoryParameters.getSpeeadTrow() + 0.5));
		trowTraectory.createTraectory();
		
	}

	public void UpKey() {
		trowTraectoryParameters.setAngleTrow((float)(trowTraectoryParameters.getAngleTrow() + 0.5));
		trowTraectory.createTraectory();
			
	}

	public void DownKey() {	
		trowTraectoryParameters.setAngleTrow((float)(trowTraectoryParameters.getAngleTrow() - 0.5));
		trowTraectory.createTraectory();
	}


	@Override
	public void Lkey() {
		mapLevelVaribles.setLevelStage(LevelState.MOVING);
		
	}


	@Override
	public void SpareKey() {
		
	}
}
