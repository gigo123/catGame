package com.thsoft.catgame.gameLogik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class CatMapLevel extends BaseActor {
	
	private Animation<TextureRegion> stand;
	private Animation<TextureRegion> walk;
	private float walkAcceleration;
	private float walkDeceleration;
	private float maxHorizontalSpeed;
	private float gravity;
	private float maxVerticalSpeed;
	private boolean moveEnding;
	private Animation<TextureRegion> jump;
	private float jumpSpeed;
	private BaseActor belowSensor;
	private boolean moveAllowed;
	
	public CatMapLevel(float x, float y, Stage s) {
		super(x, y, s);
		String[] walkFileNames = { "assets/level1/kat_pod1.png", "assets/level1/kat_pod1.png",
				"assets/level1/kat_pod2.png", "assets/level1/kat_pod3.png", "assets/level1/kat_pod4.png",
				"assets/level1/kat_pod5.png", "assets/level1/kat_pod5.png", "assets/level1/kat_pod5.png",
				"assets/level1/kat_pod4.png", "assets/level1/kat_pod3.png", "assets/level1/kat_pod2.png",
				"assets/level1/kat_pod1.png" };

		stand = loadAnimationFromFiles(walkFileNames, 0.2f, true);
		setAnimation(stand);
		setWidth(32);
		setHeight(32);
		setBoundaryRectangle();
		
		maxHorizontalSpeed = 100;
		walkAcceleration = 200;
		walkDeceleration = 200;
		gravity = 700;
		maxVerticalSpeed = 1000;


		jump = loadTexture("assets/level1/kat_pod1.png");
		jumpSpeed = 450;

		// set up the below sensor
		belowSensor = new BaseActor(0, 0, s);
		// belowSensor.loadTexture("assets/barier.png");
		belowSensor.setSize(this.getWidth() - 8, 8);
		belowSensor.setBoundaryRectangle();
		belowSensor.setVisible(false);
		moveEnding = false;
		moveAllowed = true;
		
	}
	
	public CatMapLevel(float x, float y,  float width, float height,Stage s) {
		this(x, y, s);
		setWidth(width);
		setHeight(height);
		setBoundaryRectangle();
	}
	public void act(float dt) {
		super.act(dt);

		
		// decelerate when not accelerating
	/*	if (moveAllowed) {

			if (!Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT)) {	
				float decelerationAmount = walkDeceleration * dt;

				float walkDirection;

				if (getVelocityVec().x > 0)
					walkDirection = 1;
				else
					walkDirection = -1;

				float walkSpeed = Math.abs(getVelocityVec().x);

				walkSpeed -= decelerationAmount;

				if (walkSpeed < 0)
					walkSpeed = 0;

				getVelocityVec().x = walkSpeed * walkDirection;

			}

			// apply gravity
			accelerationVec.add(0, -gravity);

			getVelocityVec().add(accelerationVec.x * dt, accelerationVec.y * dt);

			getVelocityVec().x = MathUtils.clamp(getVelocityVec().x, -maxHorizontalSpeed, maxHorizontalSpeed);

			moveBy(getVelocityVec().x * dt, getVelocityVec().y * dt);

			if (getVelocityVec().x == 0) {
				moveEnding = true;
			//	System.out.println("true");
			}
			// reset acceleration
			accelerationVec.set(0, 0);

			// move the below sensor below the koala
			belowSensor.setPosition(getX() + 4, getY() - 8);
			// manage animations
			if (this.isOnSolid()) {
				belowSensor.setColor(Color.GREEN);
				if (getVelocityVec().x == 0) {
					setAnimation(stand);
		
				}
				else {
					setAnimation(walk);
		
					}	
				
			} else {
	
				belowSensor.setColor(Color.RED);
				setAnimation(jump);
			}

			if (getVelocityVec().x > 0) // face right
				setScaleX(1);

			if (getVelocityVec().x < 0) // face left
				setScaleX(-1);

			alignCamera();
			boundToWorld();
		}
		else {
			
			setAnimation(stand);
		}
		*/
	}
	public boolean isOnSolid() {
		for (BaseActor actor : BaseActor.getList(getStage(), SolidActor.class)) {
			SolidActor solid = (SolidActor) actor;
		
			if (belowOverlaps(solid) && solid.isEnabled()) {
				return true;
			}
		}

		return false;
	}
	public boolean isMoveEnding() {
		return moveEnding;
	}

	public void setMoveEnding(boolean moveEnding) {
		this.moveEnding = moveEnding;
	}

	public boolean belowOverlaps(BaseActor actor) {
		return belowSensor.overlaps(actor);
	}
}
