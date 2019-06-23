package com.THsoft;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
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
import com.thsoft.catgame.game.TilemapActorBuilder;
import com.thsoft.catgame.gameLevel.LevelState;
import com.thsoft.catgame.gameLevel.MapLevelLogik;
import com.thsoft.catgame.gameLevel.MapLevelVaribles;

class TilemapActorBuilderT {
	 private static Application application;
	   private static MapLevelVaribles mapLevelVaribles;
	   private static MapLevelLogik mapLevelLogik;
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
      ShaderProgram defaultShader =Mockito.mock(ShaderProgram.class); 
		Stage s =new Stage(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera()),
				new SpriteBatch(1000, defaultShader));
		mapLevelVaribles= new MapLevelVaribles();
		//mapLevelVaribles.setMainCharacter(new OldMen(64,64,s));
		mapLevelVaribles.setMainStage(s);
		mapLevelLogik= new MapLevelLogik(mapLevelVaribles);
		mapLevelVaribles.setLevelStage(LevelState.MOVING);
		
      

  }
	@Test
	void test() {
		init();
		TilemapActorBuilder tilemapActorBuilder = new TilemapActorBuilder("assets/maplevel1/map.tmx", mapLevelVaribles);
	}
	
	// After we are done, clean up the application
    @AfterClass
    public static void cleanUp() {
        // Exit the application first
        application.exit();
        application = null;
    }

}
