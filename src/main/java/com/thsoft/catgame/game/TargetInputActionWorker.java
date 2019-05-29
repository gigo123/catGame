package com.thsoft.catgame.game;

import com.thsoft.catgame.gameLogik.TrowTraectory;
import com.thsoft.catgame.gameLogik.TrowTraectoryParameters;
public class TargetInputActionWorker  implements InputActionWorker{
	private TrowTraectoryParameters  trowTraectoryParameters;
	private TrowTraectory trowTraectory;

	
	public TargetInputActionWorker( TrowTraectoryParameters  trowTraectoryParameters, TrowTraectory trowTraectory) {
		super();
		this.trowTraectoryParameters = trowTraectoryParameters;
		this.trowTraectory = trowTraectory;
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void SpareKey() {
		// TODO Auto-generated method stub
		
	}
}
