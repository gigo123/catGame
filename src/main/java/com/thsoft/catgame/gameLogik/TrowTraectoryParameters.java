package com.thsoft.catgame.gameLogik;

public class TrowTraectoryParameters {
	private float speeadTrow;
	private double angleTrow;

	private float maxSpead;
	private float minSpead;
	private float maxAngle;
	private float minAngle;
	private boolean direction;
	private float maxXcoordinate;
	public TrowTraectoryParameters(float speeadTrow, double angleTrow,boolean direction  ) {
		super();
		this.speeadTrow = speeadTrow;
		this.angleTrow = angleTrow;
		this.direction =direction;
	
	}
	public boolean isDirection() {
		return direction;
	}
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	public float getMaxXcoordinate() {
		return maxXcoordinate;
	}
	public void setMaxXcoordinate(float maxXcoordinate) {
		this.maxXcoordinate = maxXcoordinate;
	}
	public float getSpeeadTrow() {
		return speeadTrow;
	}
	public void setSpeeadTrow(float speeadTrow) {
		this.speeadTrow = speeadTrow;
		if (speeadTrow > maxSpead) {
			speeadTrow = (int) maxSpead;
		}
		if (speeadTrow < minSpead) {
			speeadTrow = (int) minSpead;
		}
	}
	public double getAngleTrow() {
		return angleTrow;
	}
	public void setAngleTrow(double angleTrow) {
		this.angleTrow = angleTrow;
		if (angleTrow > maxAngle) {
			angleTrow = maxAngle;
		}
		if (angleTrow < minAngle) {
			angleTrow = minAngle;
		}
	}
	public float getMaxSpead() {
		return maxSpead;
	}
	public void setMaxSpead(float maxSpead) {
		this.maxSpead = maxSpead;
	}
	public float getMinSpead() {
		return minSpead;
	}
	public void setMinSpead(float minSpead) {
		this.minSpead = minSpead;
	}
	public float getMaxAngle() {
		return maxAngle;
	}
	public void setMaxAngle(float maxAngle) {
		this.maxAngle = maxAngle;
	}
	public float getMinAngle() {
		return minAngle;
	}
	public void setMinAngle(float minAngle) {
		this.minAngle = minAngle;
	}
	
	

}
