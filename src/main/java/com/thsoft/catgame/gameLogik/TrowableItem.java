/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thsoft.catgame.gameLogik;

import java.util.concurrent.Semaphore;

//import com.badlogic.gdx.math.Intersector;
//import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.*;

/**
 * клас для відображення об'екта , котрий кидають (снаряду)
 *
 * @author Taras Hopka 2019
 */
public class TrowableItem extends BaseActor {

	private float[][] traectoryLine;
	private int throwEtap = 0;
	private boolean isThrow;
	private float x;
	private float y;

	public TrowableItem(float x, float y, Stage s) {
		super(x, y, s);
		this.x = x;
		this.y = y;
	}

	/**
	 * процес польоту сняраду
	 *
	 * @throws notTrowingStage при досягнені країв екрани чи попаданні , політ буде
	 *                         зупинено
	 */
	public void torw(Semaphore semp) {
		if (traectoryLine == null) {
			return;
		}
		try {
			semp.acquire();
			setVisible(true);
			isThrow = true;
			setX(x);
			setY(y);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stopTrow() {
		clearTraectory();
		isThrow = false;
		setVisible(false);
	}

	@Override
	public void act(float dt) {
		super.act(dt);
		if (isThrow) {
			float x = getX();
			float y = getY();
			x = traectoryLine[throwEtap][0] - x;
			y = traectoryLine[throwEtap][1] - y;
			// System.out.println(traectoryLine.length);
			// System.out.println(throwEtap);
			moveBy(x, y);
			throwEtap++;
			if (throwEtap == traectoryLine.length | traectoryLine[throwEtap - 1][1] < 0 | getX() > 960) {
				stopTrow();
			}
		}
	}

	public boolean isThrow() {
		return isThrow;
	}

	public void setThrow(boolean isThrow) {
		this.isThrow = isThrow;
	}

	/**
	 * @param traectoryLine the traectoryLine to set
	 */
	public void setTraectoryLine(float[][] traectoryLine) {
		this.traectoryLine = traectoryLine;
	}

	/**
	 * трераеторії і обнулення показників очитстка
	 */
	public void clearTraectory() {
		this.traectoryLine = null;
		throwEtap = 0;
	}

}
