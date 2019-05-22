package com.thsoft.catgame.gameLogik;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class TraectoryActor {
	private  ArrayList<BaseActor> traectory;

	public TraectoryActor(Stage mainStage) {
		traectory = new ArrayList<BaseActor>();
		for(int i=0;i<300;i++) {
			BaseActor circle = new BaseActor(0, 0, mainStage);
			circle.loadTexture("assets/circle1.png");
			circle.setVisible(false);
			traectory.add(circle);
		}
		System.out.println("endig create traectory actor");
	}
	public void hideTraectory() {
		Iterator<BaseActor> iter = traectory.iterator();
		while (iter.hasNext()) {
			BaseActor circle = iter.next();
			circle.setVisible(false);
		}
	}

	public ArrayList<BaseActor> getTraectory() {
		return traectory;
	}

	public void setTraectory(ArrayList<BaseActor> traectory) {
		this.traectory = traectory;
	}
	
	
}
