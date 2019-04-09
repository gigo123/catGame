/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thsoft.catgame.gameLogik;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.*;

/**
 * клас для об'єкта кота
 * 
 * @author Taras Hopka 2019
 */
public class Cat extends BaseActor {
	private int hitPoint;
	private boolean isMoving;
	private float xSpeead;
	private float ySpeead;
	private float xEnd;
	private float yEnd;

	public Cat(int hitPoint, float x, float y, Stage s) {
		super(x, y, s);
		this.hitPoint = hitPoint;
	}

	/**
	 * 
	 * @see #setBoundaryPolygon(int, int, int, int)
	 * @param left
	 * @param right
	 * @param top
	 * @param bottom
	 */
	public void setBoundaryPolygonCatLevel1_1(int left, int right, int top, int bottom) {

		float w = getWidth();
		float h = getHeight();

		float[] vertices = new float[8];
		vertices[0] = left;

		vertices[1] = h / 2;
		// x-coordinate
		vertices[2] = w - right;
		// y-coordinate
		vertices[3] = h / 2;
		vertices[4] = w - right;
		vertices[5] = h - top;
		vertices[6] = left;
		vertices[7] = h - top;
//         for(int i =0 ;i<8;i++){
//             System.out.println(vertices[i]);
//         }
		Polygon boundaryPolygon = new Polygon(vertices);
		setBoundaryPolygon(boundaryPolygon);
	}

	/**
	 * @return the hitPoint
	 */
	public int getHitPoint() {

		return hitPoint;
	}

	/**
	 * @param hitPoint the hitPoint to set
	 */
	public void setHitPoint(int hitPoint) {

		this.hitPoint = hitPoint;
	}
	public void constanMove(float xSpeead,float ySpeead,float xEnd,float yEnd) {
		
		this.xSpeead = xSpeead;
		this.ySpeead = ySpeead;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		isMoving = true;
	}
	@Override
	public void act(float dt) {
		super.act(dt);
		float x = getX();
		float y = getY();
		if(isMoving) {
			moveBy(xSpeead, ySpeead);
			if((x+xSpeead)>xEnd||(y+ySpeead)>yEnd) {
				isMoving= false;
				setAnimationPaused(true);
			}
			
		}
		
	}

}
