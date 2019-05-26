package com.THsoft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;
import  com.thsoft.catgame.gameLogik.TraectoryActor;

//@RunWith(GdxTestRunner.class)
public class TraectoryActorT
{
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
	public void createTrowActor() {
		init();
		Stage testStage = Mockito.mock(Stage.class);  //mock object to Stage
		TraectoryActor tActor= new TraectoryActor(testStage);
		ArrayList<BaseActor> list = tActor.getTraectory();
		assertEquals(300, list.size(),"size must be 300");
	}
	@Test
	public void  hideTraectory() {
		init();
		Stage testStage = Mockito.mock(Stage.class);  //mock object to Stage
		TraectoryActor tActor= new TraectoryActor(testStage);
		ArrayList<BaseActor> list = tActor.getTraectory();
		Iterator<BaseActor> iter = list.iterator();
		while (iter.hasNext()) {
			BaseActor circle = iter.next();
			circle.setVisible(true);
		}
		tActor.hideTraectoryFromNuber(200);
		int hideSize=0;
		iter = list.iterator();
		while (iter.hasNext()) {
			BaseActor circle = iter.next();
			if(circle.isVisible()) {
				hideSize++;
			}
		}
		assertEquals(200,hideSize,"must visible 200 item");
	}
	
}
