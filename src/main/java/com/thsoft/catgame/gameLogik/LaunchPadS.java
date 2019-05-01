package com.thsoft.catgame.gameLogik;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class LaunchPadS extends BaseActor {
	/**
	 * масив точок для відображення таретої
	 */
	public ArrayList<BaseActor> traectory;
	/**
	 * швидкить кидка ( потужніть кидка)
	 */
	private float speeadTrow;
	/**
	 * початковий кут кидка
	 */
	private double angleTrow;
	/**
	 * якщо відбувается кидання - постріл - правда
	 */
	private boolean fireing;
	/**
	 * відстянь від нижньої точки моделі до точки метання по Х
	 */
//	float sizeX;
	/**
	 * відстянь від нижньої точки моделі до точки метання по Y
	 */
	//float sizeY;
	/**
	 * абсолютна відстаеь( від початку координат до точки метання по Х
	 */
	float ofsettX;
	/**
	 * абсолютна відстаеь( від початку координат до точки метання по Y
	 */
	float ofsettY;
	/**
	 * максимална потужність кидка
	 */
	private float maxSpead;
	/**
	 * мінімальна потужність кидка
	 */
	private float minSpead;
	/**
	 * максимальний кут кидка
	 */
	private float maxAngle;
	/**
	 * мінімальний кут кидка
	 */
	private float minAngle;
	// керування мишкоя
	private float grabOffsetX; // зміщення мишкою( тачем) по X
	private float grabOffsetY; // зміщення мишкою( тачем) по Y
	/**
	 * чи можна переміщати даний обєкт
	 */
	private boolean draggable;

	public LaunchPadS (int maxSpead, int minSpead, int maxAngle, int minAngle, float x, float y, Stage s) {
		super(x, y, s);
		this.maxSpead = maxSpead;
		this.minSpead = minSpead;
		this.maxAngle = maxAngle;
		this.minAngle = minAngle;
		//sizeX = x; // відстянь від нижньої точки моделі до точки метання по Х
	//	sizeY = y; // відстянь від нижньої точки моделі до точки метання по Y
		loadTexture("assets/level1/banan.png"); // встановлення тестури
		setSize(60, 30);
		angleTrow = 0; // початковий кут
		speeadTrow = 100; // початкова швидкіть
		traectory = new ArrayList<>();
		draggable = true; // оюет можна переміщати

		createTraectory(100, 45);
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
	public void createTraectory() {

		reCreateTraectory(speeadTrow, angleTrow);
	}

	/**
	 * перерахунок траекторії при зміні параметрів для точки вираховуься нові
	 * координати
	 * 
	 * @param speead швидкість кидка
	 * @param angle  кут кидка
	 */
	public void reCreateTraectory(float speead, double angle) {
		double g = 9.81;
		angle = angle * Math.PI / 180;
		int xOfset = 0;
		int xreal = (int) ofsettX - 4;
		float circleX;
		float circleY;
		Iterator<BaseActor> iter = traectory.iterator();
		while (iter.hasNext()) {
			BaseActor traectoryElement = iter.next();
			circleX = xOfset + xreal;
			circleY = (float) ((xOfset * Math.tan(angle))
					- (g / (2 * speead * speead * Math.cos(angle) * Math.cos(angle)) * xOfset * xOfset));
			circleY += ofsettY - 4;
			traectoryElement.setX(circleX);
			traectoryElement.setY(circleY);
			traectoryElement.setVisible(true);
			xOfset += 50;
			if (xOfset > 1100) {
				break;
			}
		}
	}

	/**
	 * створення траектрої при заданих параметрів створюется масив точок ( обєктів
	 * BaseActor) і записуєтся в traectory
	 * 
	 * @see #traectory
	 * @param speead швидкість кидка
	 * @param angle  кут кидка
	 */
	public void createTraectory(int speead, double angle) {

		//ofsettX = sizeX + getWidth() / 2; // абсолютна відстаеь( від початку координат до точки метання по Х
		//ofsettY = sizeY + getHeight() / 2; // абсолютна відстаеь( від початку координат до точки метання по Y
		ofsettX= getX();
		ofsettY= getY();

		double g = 9.81;
		angle = angle * Math.PI / 180;
		int xOfset = 0;
		int xreal = (int) ofsettX - 4;
		float circleX;
		float circleY;
		while (xOfset + xreal < 1100) {

			circleX = xOfset + xreal;
			circleY = (float) ((xOfset * Math.tan(angle))
					- (g / (2 * speead * speead * Math.cos(angle) * Math.cos(angle)) * xOfset * xOfset));
			circleY += ofsettY - 4;
			BaseActor circle1 = new BaseActor(circleX, circleY, super.getStage());
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
	 * @param speead швидкімть польоту в пікселях за кадр( стандарт 1 піклесь за
	 *               кадр)
	 * @return повертає матрицю координат float[][0] = координата х , float[][1] =
	 *         координата y
	 */
	public float[][] calculateTraectory(float speead) {
		double g = 9.81;
		float angle = (float) (angleTrow * Math.PI / 180);
		int xreal = (int) ofsettX - 4;
		float circleX;
		float circleY;
		int i = 0;
		float j = 0;
		float[][] traectoryCoord = new float[1000 - xreal][2];
		while (j + xreal < 1060) {
			circleX = j + xreal;
			circleY = (float) ((j * Math.tan(angle))
					- (g / (2 * speeadTrow * speeadTrow * Math.cos(angle) * Math.cos(angle)) * j * j));
			circleY += ofsettY - 4;
			traectoryCoord[i][0] = circleX;
			traectoryCoord[i][1] = circleY;
			i++;
			j = j + speead;
			if (circleY < 0)
				break;
		}
		return traectoryCoord;
	}

	@Override
	public void act(float dt) {
		super.act(dt);

	}

	/**
	 * збільшення швидкості к клавіатури
	 */
	public void addSpeead() {
		speeadTrow += 0.5;
		if (speeadTrow > maxSpead) {
			speeadTrow = (int) maxSpead;
		}
	}

	/**
	 * 
	 */
	public void downSpeead() {
		speeadTrow = (float) (speeadTrow - 0.5);
		if (speeadTrow < minSpead) {
			speeadTrow = (int) minSpead;
		}
	}

	/**
	 * 
	 */
	public void upAngle() {
		angleTrow += 0.5;
		if (angleTrow > maxAngle) {
			angleTrow = maxAngle;
		}
	}

	/**
	 * 
	 */
	public void downAngle() {
		angleTrow -= 0.5;
		if (angleTrow < minAngle) {
			angleTrow = minAngle;
		}
	}

	/**
	 * @return the speeadTrow
	 */
	public float getSpeeadTrow() {
		return speeadTrow;
	}

	/**
	 * @param speeadTrow the speeadTrow to set
	 */
	public void setSpeeadTrow(int speeadTrow) {
		this.speeadTrow = speeadTrow;
	}

	/**
	 * @return the angleTrow
	 */
	public double getAngleTrow() {
		return angleTrow;
	}

	/**
	 * @param angleTrow the angleTrow to set
	 */
	public void setAngleTrow(double angleTrow) {
		this.angleTrow = angleTrow;
	}

	/**
	 * @return the fireing
	 */
	public boolean isFireing() {
		return fireing;
	}

	/**
	 * @param fireing the fireing to set
	 */
	public void setFireing(boolean fireing) {

		this.fireing = fireing;
	}

	public float getGrabOffsetX() {
		return grabOffsetX;
	}

	/**
	 * @param grabOffsetX the grabOffsetX to set
	 */
	public void setGrabOffsetX(float grabOffsetX) {
		this.grabOffsetX = grabOffsetX;
	}

	/**
	 * @return the grabOffsetY
	 */
	public float getGrabOffsetY() {
		return grabOffsetY;
	}

	/**
	 * @param grabOffsetY the grabOffsetY to set
	 */
	public void setGrabOffsetY(float grabOffsetY) {
		this.grabOffsetY = grabOffsetY;
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

	/**
	 * 
	 * @param x
	 * @param y
	 */
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
			clearTraectory();
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

//  отримання розмірів , визначення кординат відносного центру
	@Override
	public void setSize(float width, float height) {
		// centerX = width / 2;
		// centerY = height / 2;
		super.setSize(width, height); // To change body of generated methods, choose Tools | Templates.
	}

	/**
	 * @return the maxSpead
	 */
	public int getMaxSpead() {
		return (int) maxSpead;
	}

	/**
	 * @param maxSpead the maxSpead to set
	 */
	public void setMaxSpead(int maxSpead) {
		this.maxSpead = maxSpead;
	}

	/**
	 * @return the minSpead
	 */
	public int getMinSpead() {
		return (int) minSpead;
	}

	/**
	 * @param minSpead the minSpead to set
	 */
	public void setMinSpead(int minSpead) {
		this.minSpead = minSpead;
	}

	/**
	 * @return the maxAngle
	 */
	public float getMaxAngle() {
		return maxAngle;
	}

	/**
	 * @param maxAngle the maxAngle to set
	 */
	public void setMaxAngle(int maxAngle) {
		this.maxAngle = maxAngle;
	}

	/**
	 * @return the minAngle
	 */
	public float getMinAngle() {
		return minAngle;
	}

	/**
	 * @param minAngle the minAngle to set
	 */
	public void setMinAngle(int minAngle) {
		this.minAngle = minAngle;
	}

}
