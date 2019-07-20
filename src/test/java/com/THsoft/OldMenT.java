package com.THsoft;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.thsoft.catgame.gameLevel.LevelState;
import com.thsoft.catgame.gameLevel.MapLevelLogik;
import com.thsoft.catgame.gameLevel.MapLevelVaribles;
import com.thsoft.catgame.gameLogik.OldMen;
import com.thsoft.catgame.gameLogik.TrowTraectoryParameters;

class OldMenT {
	private static Application application;
	private static MapLevelVaribles mapLevelVaribles;
	private static OldMen oldMen; 
	@BeforeClass
	public static void init() {
		// Note that we don't need to implement any of the listener's methods
		application = new HeadlessApplication(new ApplicationListener() {

			@Override
			public void create() {

			}

			@Override
			public void resize(int width, int height) {
			}

			@Override
			public void render() {
			}

			@Override
			public void pause() {
			}

			@Override
			public void resume() {
			}

			@Override
			public void dispose() {
			}
		});

		// Use Mockito to mock the OpenGL methods since we are running headlessly
		Gdx.gl20 = Mockito.mock(GL20.class);
		Gdx.gl = Gdx.gl20;
		ShaderProgram defaultShader = Mockito.mock(ShaderProgram.class);
		Stage s = new Stage(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				new OrthographicCamera()), new SpriteBatch(1000, defaultShader));
		mapLevelVaribles = new MapLevelVaribles();
		mapLevelVaribles.setMainStage(s);
		mapLevelVaribles.setLevelStage(LevelState.MOVING);
		oldMen = new OldMen(100, 100, s);

	}

	@Test
	void createParametrCheck() {
		init();
	
		try {
			Field walkDeceleration =oldMen.getClass().getDeclaredField("walkDeceleration");
			walkDeceleration.setAccessible(true);
			float wd=walkDeceleration.getFloat(oldMen);
			
			Field maxHorizontalSpeed =oldMen.getClass().getDeclaredField("maxHorizontalSpeed");
			maxHorizontalSpeed.setAccessible(true);
			float mHS=maxHorizontalSpeed.getFloat(oldMen);
			System.out.println(mHS);
	//	TrowTraectoryParameters tp = (TrowTraectoryParameters) geTP.invoke(mapLevelLogik);
		
		assertTrue(oldMen.getWalkAcceleration() == 200, " start Walk Acceleration must be 200");
		assertTrue(wd == 200, " start Walk Acceleration must be 200");
		assertTrue(mHS == 100, " start maxHorizontalSpeed be 100");
		assertTrue(oldMen.getWalkAcceleration() == 200, " start Walk Acceleration must be 200");
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void gravityCheck() {
		init();
		try {
			//Class[] cArg = new Class[1];
	     //    cArg[0] = Float.class;
			
			Method gravityAply = oldMen.getClass().getDeclaredMethod("applyGravity", float.class);
			 
			gravityAply.setAccessible(true);
			gravityAply.invoke(oldMen,(float)0.0165);
		assertTrue(oldMen.getAccelerationVec().x==0 ,  " acseleration vector aftecr calc must bu reset ( poviden 0)");
		assertTrue(oldMen.getAccelerationVec().y==0 ,  " acseleration vector aftecr calc must bu reset ( poviden 0)");
		assertTrue(oldMen.getVelocityVec().x==0 ,  " x velocy =0");
		assertTrue(oldMen.getVelocityVec().y== (float)-11.55 ,  " y velocyty = -11.55");
		//System.out.println(oldMen.getVelocityVec().y);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
