package com.thsoft.catgame.gameLevel;

import com.thsoft.catgame.game.BaseActor;
import com.thsoft.catgame.game.BaseScreen;
import com.thsoft.catgame.game.InputActionAdapter;
import com.thsoft.catgame.game.TilemapActor;
import com.thsoft.catgame.gameLogik.LaunchPadS;
import com.thsoft.catgame.gameLogik.OldMen;
import com.thsoft.catgame.gameLogik.SolidActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


public class MapLevel1 extends BaseScreen
{
	private OldMen mainCharacter;
	private LaunchPadS launch1;
	private LevelState levelStage;
	private InputActionAdapter inputActionAdapter;
	@Override
	public void initialize() {
		levelStage = LevelState.MOVING;
		TilemapActor tma = new TilemapActor("assets/maplevel1/map.tmx", mainStage);

        for (MapObject obj : tma.getRectangleList("SolidActor") )
        {
            MapProperties props = obj.getProperties();
            new SolidActor( (float)props.get("x"), (float)props.get("y"),
                (float)props.get("width"), (float)props.get("height"), 
                mainStage );
        }

        MapObject startPoint = tma.getRectangleList("start").get(0);
        MapProperties startProps = startPoint.getProperties();
        mainCharacter = new OldMen( (float)startProps.get("x"), (float)startProps.get("y"), mainStage);
        
        inputActionAdapter= new InputActionAdapter(mainCharacter);
		
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		for (BaseActor actor : BaseActor.getList(mainStage, SolidActor.class))
        {
			SolidActor solid = (SolidActor)actor;       
            if ( mainCharacter.overlaps(solid) && solid.isEnabled() )
            {
                Vector2 offset = mainCharacter.preventOverlap(solid);

                if (offset != null)
                {
                    // collided in X direction
                    if ( Math.abs(offset.x) > Math.abs(offset.y) )
                    	mainCharacter.getVelocityVec().x = 0;
                    else // collided in Y direction
                    	mainCharacter.getVelocityVec().y = 0;
                }
            }
        }
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			inputActionAdapter.LeftKey(levelStage);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			inputActionAdapter.RightKey(levelStage);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			inputActionAdapter.UpKey(levelStage);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			inputActionAdapter.DownKey(levelStage);
		}
		if(levelStage== LevelState.TARGETING) {
		if(mainCharacter.isMoveEnding()) {
			mainCharacter.setMoveAllowed(false);
        launch1 = new LaunchPadS(300, 40, 120, -30, mainCharacter.getX(), mainCharacter.getY(), mainStage);
   		launch1.createTraectory();
   		inputActionAdapter.setLauchPAd( launch1);
   		mainCharacter.setMoveEnding(false);
		}
	}
	}
	public boolean keyDown(int keyCode)
    {
        if (levelStage== LevelState.GAMEOVER)
            return false;

        if (keyCode == Keys.L)
        {
        	levelStage=LevelState.TARGETING;
        	 //launch object 
   
        }
        return false;
    }
}

