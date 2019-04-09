package com.thsoft.catgame.App;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.thsoft.catgame.game.*;

/**
 * клас для запуску гри
 * 
 * @author Taras Hopka 2019
 */
public class LauncherAlpha {

	public static void main(String[] args) {

		Game myGame = new CatGame();
		// назва вікна, і розмір у пікселях - ширина, висота
		//LwjglApplication launcher = new LwjglApplication(myGame, "Wiles Cat", 1040, 650);
		new LwjglApplication(myGame, "Wiles Cat", 1040, 650);
	}
}
