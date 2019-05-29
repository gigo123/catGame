package com.thsoft.catgame.gameLogik;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class TrowEndingCalc {
	private static TrowTraectoryParameters traectoryParameters;


	public TrowEndingCalc(TrowTraectoryParameters traectoryParameters) {
		super();
		TrowEndingCalc.traectoryParameters = traectoryParameters;
	}
	public static  boolean hitBarier(BaseActor actor, Stage mainStage ) {
	
		if(actor.getX()<0) {
			return true;
		}
		if(actor.getX()>traectoryParameters.getMaxXcoordinate()) {
			return true;
		}
	
		if(SolidActor.overlapBarierActorB(actor, mainStage)) {
			return true;
		}
		return false;
	}
}
