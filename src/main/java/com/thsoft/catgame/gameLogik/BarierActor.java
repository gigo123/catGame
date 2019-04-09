/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thsoft.catgame.gameLogik;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

/**
 * клас для обєктів бар'єрів ( непробивних)
 *
 * @author Taras Hopka 2019
 */
public class BarierActor extends BaseActor {

	private static Class classNameObject;

	public BarierActor(float x, float y, Stage s) {
		super(x, y, s);
		// loadTexture("assets/barier.png");
		classNameObject = this.getClass();
	}

	/**
	 * метод для отримання імені класу
	 * 
	 * @return імя класу
	 */
	public static Class getClassNameObject() {
		return classNameObject;
	}

}
