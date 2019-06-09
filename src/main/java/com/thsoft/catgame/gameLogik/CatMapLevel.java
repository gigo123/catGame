package com.thsoft.catgame.gameLogik;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class CatMapLevel extends BaseActor {
	
	private Animation<TextureRegion> stand;
	
	public CatMapLevel(float x, float y, Stage s) {
		super(x, y, s);
		String[] walkFileNames = { "assets/level1/kat_pod1.png", "assets/level1/kat_pod1.png",
				"assets/level1/kat_pod2.png", "assets/level1/kat_pod3.png", "assets/level1/kat_pod4.png",
				"assets/level1/kat_pod5.png", "assets/level1/kat_pod5.png", "assets/level1/kat_pod5.png",
				"assets/level1/kat_pod4.png", "assets/level1/kat_pod3.png", "assets/level1/kat_pod2.png",
				"assets/level1/kat_pod1.png" };

		stand = loadAnimationFromFiles(walkFileNames, 0.2f, true);
		setAnimation(stand);
		setWidth(32);
		setHeight(32);
		setBoundaryRectangle();
	}
	
	public CatMapLevel(float x, float y,  float width, float height,Stage s) {
		this(x, y, s);
		setWidth(width);
		setHeight(height);
		setBoundaryRectangle();
	}
	

}
