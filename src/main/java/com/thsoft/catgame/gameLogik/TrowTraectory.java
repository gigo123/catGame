package com.thsoft.catgame.gameLogik;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class TrowTraectory {
	private  ArrayList<BaseActor> traectory;
	private float startX;
	private float startY;
	private Stage mainStage;
	private float[][] calculatetTraectory;
	
	public TrowTraectory( float startX, float startY,Stage mainStage) {
	
		
		this.startX = startX;
		this.startY= startY;
		this.mainStage=mainStage;
		traectory = new ArrayList<>();
		
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
	 * 
	 * @see #traectory
	 * @param speead швидкість кидка
	 * @param angle  кут кидка
	 */
	public void createTraectory(float speead, double angle) {

		
		double g = 9.81;
		angle = angle * Math.PI / 180;
		int xOfset = 0;
		int xreal = (int) startX;
		float circleX;
		float circleY;
		while (xOfset + xreal < 1100) {

			circleX = xOfset + xreal;
			circleY = (float) ((xOfset * Math.tan(angle))
					- (g / (2 * speead * speead * Math.cos(angle) * Math.cos(angle)) * xOfset * xOfset));
			circleY += startY;
			BaseActor circle1 = new BaseActor(circleX, circleY, mainStage);
			circle1.loadTexture("assets/circle1.png");
			traectory.add(circle1);
			xOfset += 50;
			if (circleY < 0) {
				break;
			}
		}
	}

	/**
	 * розраховує траектрою польоту обєкта
	 * 
	 * @return повертає матрицю координат float[][0] = координата х , float[][1] =
	 *         координата y
	 */
	public float[][] calculateTraectory(float speeadTrow, double angleTrow) {
		double g = 9.81;
		float intervalX = 50;
		float angle = (float) (angleTrow * Math.PI / 180);
		int xreal = (int) startX;
		float circleX;
		float circleY;
		int i = 0;
		float j = 0;
		float[][] traectoryCoord = new float[1000 - xreal][2];
		while (j + xreal < 1060) {
			circleX = j + xreal;
			circleY = (float) ((j * Math.tan(angle))
					- (g / (2 * speeadTrow * speeadTrow * Math.cos(angle) * Math.cos(angle)) * j * j));
			circleY += startY;
			traectoryCoord[i][0] = circleX;
			traectoryCoord[i][1] = circleY;
			i++;
			j = j +intervalX; 
			if (circleY < 0)
				break;
		}
		return traectoryCoord;
	}

}
