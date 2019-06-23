package com.thsoft.catgame.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.gameLevel.MapLevelVaribles;
import com.thsoft.catgame.gameLogik.CatMapLevel;
import com.thsoft.catgame.gameLogik.OldMen;
import com.thsoft.catgame.gameLogik.SolidActor;

public class TilemapActorBuilder {
	private String sourseFile;
	private MapLevelVaribles mapLevelVaribles;
	
	
	public TilemapActorBuilder( String sourseFile,MapLevelVaribles mapLevelVaribles)
	 {
		super();
		Stage mainStage = mapLevelVaribles.getMainStage();
		this.sourseFile = sourseFile;
		this.mapLevelVaribles = mapLevelVaribles;
		TilemapActor tma = new TilemapActor(sourseFile, mainStage);

		for (MapObject obj : tma.getRectangleList("SolidActor")) {
			MapProperties props = obj.getProperties();
			new SolidActor((float) props.get("x"), (float) props.get("y"), (float) props.get("width"),
					(float) props.get("height"), mainStage);

		}

		MapObject startPoint = tma.getRectangleList("start").get(0);
		MapProperties startProps = startPoint.getProperties();
		mapLevelVaribles
				.setMainCharacter(new OldMen((float) startProps.get("x"), (float) startProps.get("y"), mainStage));

		for (MapObject obj : tma.getRectangleList("cat1")) {
			MapProperties props = obj.getProperties();
			new CatMapLevel((float) props.get("x"), (float) props.get("y"), (float) props.get("width"),
					(float) props.get("height"), mainStage);

		}

	}

}
