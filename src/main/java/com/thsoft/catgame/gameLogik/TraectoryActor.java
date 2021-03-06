package com.thsoft.catgame.gameLogik;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class TraectoryActor {
	private  ArrayList<BaseActor> traectory;
	private Stage mainStage;

	public TraectoryActor(Stage mainStage) {
		this.mainStage = mainStage;
		traectory = new ArrayList<BaseActor>();
		traectoryCreation();
	}
	private void traectoryCreation() {
		for(int i=0;i<300;i++) {
			BaseActor circle = new BaseActor(0, 0, mainStage);
			circle.loadTexture("assets/circle1.png");
			circle.setVisible(false);
			traectory.add(circle);
		}
	}
	public void hideTraectory() {
		Iterator<BaseActor> iter = traectory.iterator();
		while (iter.hasNext()) {
			BaseActor circle = iter.next();
			circle.setVisible(false);
		}
	}
	public void hideTraectoryFromNuber(int i) {

		if(i<traectory.size()) {
			Iterator<BaseActor> iter =traectory.listIterator(i);
			while (iter.hasNext()) {
				BaseActor circle = iter.next();
				circle.setVisible(false);
			}
		}

		
	}

	public ArrayList<BaseActor> getTraectory() {
		return traectory;
	}

	public void setTraectory(ArrayList<BaseActor> traectory) {
		this.traectory = traectory;
	}
	
	
}
