package com.THsoft;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.thsoft.catgame.game.BaseActor;
import com.thsoft.catgame.gameLevel.LevelState;
import com.thsoft.catgame.gameLevel.MapLevel1;
import com.thsoft.catgame.gameLevel.MapLevelLogik;
import com.thsoft.catgame.gameLogik.OldMen;
import com.thsoft.catgame.gameLogik.SolidActor;

class MapLevelT {
	 // This is our "test" application
    private static Application application;
	 // Before running any tests, initialize the application with the headless backend
    @BeforeClass
    public static void init() {
        // Note that we don't need to implement any of the listener's methods
        application = new HeadlessApplication(new ApplicationListener() {
        	
            @Override public void create() {
      
        		
            }
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });
 

        // Use Mockito to mock the OpenGL methods since we are running headlessly
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
 
    }

	// After we are done, clean up the application
    @AfterClass
    public static void cleanUp() {
        // Exit the application first
        application.exit();
        application = null;
    }
    
 
   
	@Test
	void initializeTest() {
		init();
		ShaderProgram defaultShader =Mockito.mock(ShaderProgram.class); 
		Stage s =new Stage(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera()),
				new SpriteBatch(1000, defaultShader));
		new SolidActor(0, 0, 32,32, s);
		new SolidActor(0, 0, 32,32, s);
		for (BaseActor actor : BaseActor.getList(s, SolidActor.class)) {
			SolidActor solid = (SolidActor) actor;
    		System.out.println("solid actor");
		}
		OldMen mainCharacter  = new OldMen(64,64,s);
		
		LevelState levelStage= LevelState.MOVING;
		MapLevelLogik testLevel = new MapLevelLogik(s, mainCharacter,1000);
		/*  try {
		//	  System.out.println(testLevel.getClass().getDeclaredField("mainStage"));
			  Field fl = MapLevelLogik.class.getDeclaredField("mainStage");
			  fl.setAccessible(true);
			  FieldSetter fSet = new FieldSetter(testLevel, fl);
			  fSet.set(s);
			  
			 fl = MapLevelLogik.class.getDeclaredField("mainCharacter");
			  fl.setAccessible(true);
			  fSet = new FieldSetter(testLevel, fl);
			  fSet.set(mainCharacter);
			  
			  fl = MapLevelLogik.class.getDeclaredField("levelStage");
			  fl.setAccessible(true);
			  fSet = new FieldSetter(testLevel, fl);
			  fSet.set(levelStage);
			testLevel.swichLevelMode();
			  testLevel.update();
			  System.out.println(testLevel.getIputActionWork());
			  
			  
			  
			  
		//	new FieldSetter(mockedPerson, MapLevelLogik.class.getDeclaredField("mainStage"),s);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		testLevel.update();
	}

}
