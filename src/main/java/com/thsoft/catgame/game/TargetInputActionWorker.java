package com.thsoft.catgame.game;

import com.thsoft.catgame.gameLogik.TraectotyInputCalc;
public class TargetInputActionWorker  implements InputActionWorker{
	private TraectotyInputCalc traectotyInputCalc;

	
	public TargetInputActionWorker(TraectotyInputCalc traectotyInputCalc) {
		super();
		this.traectotyInputCalc = traectotyInputCalc;
	}
	
	public void settraectotyInputCalc(TraectotyInputCalc traectotyInputCalc) {
		this.traectotyInputCalc = traectotyInputCalc;
	}

	public void LeftKey() {
			traectotyInputCalc.downSpeead();
	}

	public void RightKey() {
			traectotyInputCalc.addSpeead();
			
	}

	public void UpKey() {
			traectotyInputCalc.upAngle();
			
	}

	public void DownKey() {	
			traectotyInputCalc.downAngle();
	}
}
