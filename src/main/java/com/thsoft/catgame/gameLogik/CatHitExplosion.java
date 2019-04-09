/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thsoft.catgame.gameLogik;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.*;

/**
 * клас для об'єкта відображення ефекту влучання(вибуху)
 * 
 * @author Taras Hopka 2019
 */
public class CatHitExplosion extends BaseActor {

	/**
	 *
	 * @param x
	 * @param y
	 * @param s
	 */
	public CatHitExplosion(float x, float y, Stage s) {
		super(x, y, s);

		loadAnimationFromSheet("assets/level1/explosion.png", 6, 6, 0.04f, false);
	}

	@Override
	public void act(float dt) {
		super.act(dt);

		if (isAnimationFinished())
			remove();
	}

}
