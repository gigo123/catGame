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
	}
	

}
