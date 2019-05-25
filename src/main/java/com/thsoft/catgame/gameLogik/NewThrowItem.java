package com.thsoft.catgame.gameLogik;
import java.util.concurrent.Semaphore;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.*;

/**
 * клас для відображення об'екта , котрий кидають (снаряду)
 *
 * @author Taras Hopka 2019
 */
public class NewThrowItem  extends BaseActor{

	private float[][] traectoryLine;
	private int throwEtap = 0;
	private boolean isThrow;
	
	public NewThrowItem(float x, float y, float[][] traectoryLine, Stage s) {
		super(x, y, s);
		this.traectoryLine = traectoryLine;
		isThrow = true;
	loadTexture("assets/level1/banan.png");
		setSize(60, 30);

		setBoundaryPolygon(10, 10, 10, 10);
		setVisible(true);

	}
	
	/**
	 * процес польоту сняраду
	 *
	 * @throws notTrowingStage при досягнені країв екрани чи попаданні , політ буде
	 *                         зупинено
	 */
	public void stopTrow() {
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

			moveBy(x, y);
			throwEtap++;
		System.out.println(throwEtap);
		System.out.println(traectoryLine.length);
			if (throwEtap == traectoryLine.length |TrowEndingCalc.hitBarier(this,getStage())) {
				System.out.println("stop");
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

	

	
	


}
