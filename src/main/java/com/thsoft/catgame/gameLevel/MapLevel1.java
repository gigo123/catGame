package com.thsoft.catgame.gameLevel;

import com.thsoft.catgame.game.BaseActor;
import com.thsoft.catgame.game.BaseScreen;
import com.thsoft.catgame.game.TilemapActor;
import com.thsoft.catgame.gameLogik.OldMen;
import com.thsoft.catgame.gameLogik.SolidActor;
import com.badlogic.gdx.Gdx;
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
	OldMen MainCharacter;

	@Override
	public void initialize() {
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
        MainCharacter = new OldMen( (float)startProps.get("x"), (float)startProps.get("y"), mainStage);


		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		for (BaseActor actor : BaseActor.getList(mainStage, SolidActor.class))
        {
			SolidActor solid = (SolidActor)actor;       
            if ( MainCharacter.overlaps(solid) && solid.isEnabled() )
            {
                Vector2 offset = MainCharacter.preventOverlap(solid);

                if (offset != null)
                {
                    // collided in X direction
                    if ( Math.abs(offset.x) > Math.abs(offset.y) )
                    	MainCharacter.getVelocityVec().x = 0;
                    else // collided in Y direction
                    	MainCharacter.getVelocityVec().y = 0;
                }
            }
        }

		
	}

}
