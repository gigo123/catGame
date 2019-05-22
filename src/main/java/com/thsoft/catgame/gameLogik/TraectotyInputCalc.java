package com.thsoft.catgame.gameLogik;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class TraectotyInputCalc {
	private TrowTraectoryParameters  trowTraectoryParameters;
	private boolean fireing;
	private float ofsettX;
	private float ofsettY;
	private float grabOffsetX; // зміщення мишкою( тачем) по X
	private float grabOffsetY; // зміщення мишкою( тачем) по Y
	private boolean draggable;
	private TrowTraectory trowTraectory;

	public TraectotyInputCalc ( TrowTraectoryParameters  trowTraectoryParameters) {
		this.trowTraectoryParameters = trowTraectoryParameters;
	

		draggable = true; // оюет можна переміщати
		
	}



	

	public void addSpeead() {
		trowTraectoryParameters.setSpeeadTrow((float)(trowTraectoryParameters.getSpeeadTrow() + 0.5));
	}

	public void downSpeead() {
		trowTraectoryParameters.setSpeeadTrow((float)(trowTraectoryParameters.getSpeeadTrow() - 0.5));
	}

	public void upAngle() {
		trowTraectoryParameters.setAngleTrow((float)(trowTraectoryParameters.getAngleTrow() + 0.5));
		
	}

	public void downAngle() {
		trowTraectoryParameters.setAngleTrow((float)(trowTraectoryParameters.getAngleTrow() - 0.5));
	}

	
	


	
	/**
	 * @return the draggable
	 */
	public boolean isDraggable() {
		return draggable;
	}

	/**
	 * @param draggable the draggable to set
	 */
	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}

	/*
	@Override
	public void moveBy(float x, float y) {
		float objectX = getX();
		float minAngleMod;
		float sizeX = getX();
		float sizeY = getY();

		if (minAngle < 0) {
			minAngleMod = Math.abs(minAngle);
		} else {
			minAngleMod = minAngle;
		}

		if (objectX >= (sizeX - 100) && objectX <= sizeX && getY() >= (sizeY - maxAngle / 2)
				&& getY() <= (sizeY + minAngleMod / 2)) {

			super.moveBy(x, y);

			speeadTrow = (int) (speeadTrow + x);

			if (speeadTrow > maxSpead) {
				speeadTrow = (int) maxSpead;
			}

			if (speeadTrow < minSpead) {
				speeadTrow = (int) minSpead;
			}

			if (y > 0) {

				angleTrow = angleTrow - y * 2;
				if (angleTrow < minAngle) {
					angleTrow = minAngle;
				}
			}
			if (y < 0) {

				angleTrow = angleTrow + (-y) * 2;

				if (angleTrow > maxAngle) {
					angleTrow = maxAngle;
				}
			}
		//	clearTraectory();
			createTraectory();
			// System.out.println(angleTrow);
		}
		if (objectX > sizeX & x < 0) {
			super.moveBy(x, y);
		}
		if (objectX < (sizeX - 100) & x > 0) {
			super.moveBy(x, y);
		}

		if (getY() > sizeY + minAngleMod / 2 & y < 0) {
			super.moveBy(x, y);
		}
		if (getY() < (sizeY - maxAngle / 2) & y > 0) {
			super.moveBy(x, y);
		}
	}
*/
//  
}
