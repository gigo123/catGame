package com.thsoft.catgame.gameLevel;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.InputActionWorker;
import com.thsoft.catgame.gameLogik.OldMen;

public class MapLevelVaribles {
	private Stage mainStage;
	private OldMen mainCharacter;
	private LevelState levelStage;
	private float  worldWidth;
	private InputActionWorker iputActionWork;
	private MapLevelLogik mapLevelLogik;
	private boolean levelStageChanged;
	
	
	public MapLevelVaribles() {
		super();
		levelStageChanged= false;
	}
	public boolean isLevelStageChanged() {
		return levelStageChanged;
	}
	public void setLevelStageChanged(boolean levelStageChanged) {
		this.levelStageChanged = levelStageChanged;
	}
	public MapLevelLogik getMapLevelLogik() {
		return mapLevelLogik;
	}
	public void setMapLevelLogik(MapLevelLogik mapLevelLogik) {
		this.mapLevelLogik = mapLevelLogik;
	}
	public InputActionWorker getIputActionWork() {
		return iputActionWork;
	}
	public void setIputActionWork(InputActionWorker iputActionWork) {
		this.iputActionWork = iputActionWork;
	}
	public float getWorldWidth() {
		return worldWidth;
	}
	public void setWorldWidth(float worldWidth) {
		this.worldWidth = worldWidth;
	}
	public Stage getMainStage() {
		return mainStage;
	}
	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	public OldMen getMainCharacter() {
		return mainCharacter;
	}
	public void setMainCharacter(OldMen mainCharacter) {
		this.mainCharacter = mainCharacter;
	}
	public LevelState getLevelStage() {
		return levelStage;
	}
	public void setLevelStage(LevelState levelStage) {
		this.levelStage = levelStage;
		levelStageChanged =true;
	}
	

}
