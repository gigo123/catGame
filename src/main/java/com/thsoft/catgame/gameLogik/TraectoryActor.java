package com.thsoft.catgame.gameLogik;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class TraectoryActor {
	private  ArrayList<BaseActor> traectory;

	public TraectoryActor(Stage mainStage) {
		traectory = new ArrayList<BaseActor>();
		for(int i=0;i<300;i++) {
			BaseActor circle1 = new BaseActor(0, 0, mainStage);
			circle1.loadTexture("assets/circle1.png");
			traectory.add(circle1);
		}
	}

	public ArrayList<BaseActor> getTraectory() {
		return traectory;
	}

	public void setTraectory(ArrayList<BaseActor> traectory) {
		this.traectory = traectory;
	}
	
	
}
