package com.thsoft.catgame.gameLogik;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class BananBars {
	float x;
	float y;
	ArrayList<BaseActor> annoList;
	Stage stage;
	int bananCount;
	
	public BananBars(float x, float y, Stage stage, int bananCount) {
		super();
		this.x = x;
		this.y = y;
		this.stage = stage;
		this.bananCount = bananCount;
		 annoList= new ArrayList<BaseActor>();
		 float bananX=x+5;
		 float bananY=y+2;
		for(int i=0;i<bananCount;i++) {
			BaseActor banan = new BaseActor(bananX, bananY, stage);
			banan.loadTexture("assets/level1/banan.png"); // встановлення тестури
			banan.setSize(60, 30);
			annoList.add(banan);
			bananX+=65;
		}
	}
	public void removeItem() {
		(annoList.get(annoList.size()-1)).remove();
		annoList.remove(annoList.size()-1);
	}

}
