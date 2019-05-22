package com.thsoft.catgame.gameLogik;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.tools.particleeditor.Chart.Point;
import com.thsoft.catgame.game.BaseActor;

public class TrowTraectory {
	private  ArrayList<BaseActor> traectory;
	private float startX;
	private float startY;
	private float[][] calculatetTraectory;
	private TrowTraectoryParameters traectoryParameters;
	private boolean traectoryCreated;
	private TraectoryActor traectoryActor;
	
	public TrowTraectoryParameters getTraectoryParameters() {
		return traectoryParameters;
	}

	public void setTraectoryParameters(TrowTraectoryParameters traectoryParameters) {
		this.traectoryParameters = traectoryParameters;
	}

	public TraectoryActor getTraectoryActor() {
		return traectoryActor;
	}

	public void setTraectoryActor(TraectoryActor traectoryActor) {
		this.traectoryActor = traectoryActor;
	}

	public TrowTraectory( float startX, float startY, TrowTraectoryParameters traectoryParameters, TraectoryActor traectoryActor) {
		this.startX = startX;
		this.startY= startY;
		this.traectoryParameters=traectoryParameters;
		this.traectoryActor = traectoryActor;
		traectory = traectoryActor.getTraectory();
		calculatetTraectory = new float[0][0];
		traectoryCreated = false;
	}
	
	/**
	 * очищення відображення траекторї
	 */
	public void clearTraectory() {
		Iterator<BaseActor> iter = traectory.iterator();
		while (iter.hasNext()) {
			BaseActor raindrop = iter.next();
			raindrop.setVisible(false);// приходуваня елементів
		}
		traectoryCreated = false;
	}

	/**
	 * створення траектрії бе зараметрів, запускає reCreateTraectory(float speead,
	 * double angle) з парапетрами по замовчуванню
	 * 
	 * @see reCreateTraectory
	 */
	
	/*public void CreateTraectory(float speead, double angle) {
		double g = 9.81;
		angle = angle * Math.PI / 180;
		int xOfset = 0;
		int xreal = (int) startX;
		float circleX;
		float circleY;
		Iterator<BaseActor> iter = traectory.iterator();
		while (iter.hasNext()) {
			BaseActor traectoryElement = iter.next();
			circleX = xOfset + xreal;
			circleY = (float) ((xOfset * Math.tan(angle))
					- (g / (2 * speead * speead * Math.cos(angle) * Math.cos(angle)) * xOfset * xOfset));
			circleY += startY ;
			traectoryElement.setX(circleX);
			traectoryElement.setY(circleY);
			traectoryElement.setVisible(true);
			xOfset += 50;
			if (xOfset > 1100) {
				break;
			}
		}
	}
*/
	/**
	 * створення траектрої при заданих параметрів створюется масив точок ( обєктів
	 * BaseActor) і записуєтся в traectory
	 */
	public void createTraectory() {
		float maxXcoordinate =1100;
		int curentPointX = 0;
		int xreal = (int) startX;
		float intervalX = 50;
		int counterCalulatetTraectory = 0;
		calculatetTraectory= new float[(int)maxXcoordinate - xreal][2];
		ScreanPoint  point = new ScreanPoint(0, 0);
		Iterator<BaseActor> iter = traectory.iterator();
		while (curentPointX + xreal < maxXcoordinate) {
			point = traectoryCalulation(curentPointX);
				BaseActor traectoryElement = iter.next();
				traectoryElement.setX(point.getPointX());
				traectoryElement.setY(point.getPointY());
				traectoryElement.setVisible(true);
			calculatetTraectory[counterCalulatetTraectory][0] = point.getPointX();
			calculatetTraectory[counterCalulatetTraectory][1] = point.getPointY();
			counterCalulatetTraectory++;
			curentPointX += intervalX;
			if (point.getPointY() < 0) {
				break;
			}
		}
		traectoryCreated =true;
	}
	
	private ScreanPoint traectoryCalulation(float curentPointX) {
		float pointX;
		float pointY;
		double g = 9.81;
		double angle = traectoryParameters.getAngleTrow() * Math.PI / 180;
		float speead = traectoryParameters.getSpeeadTrow();
		int xreal = (int) startX;
		pointX = curentPointX + xreal;
		pointY = (float) ((curentPointX * Math.tan(angle))
				- (g / (2 * speead * speead * Math.cos(angle) * Math.cos(angle)) * curentPointX * curentPointX));
		pointY += startY;
		ScreanPoint point= new ScreanPoint(pointX, pointY);
		return point;
		
	}

}
