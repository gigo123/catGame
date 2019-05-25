package com.thsoft.catgame.gameLogik;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class SolidActor extends BaseActor{
    private boolean enabled;
  
    public SolidActor(float x, float y, float width, float height, Stage mainStage)
    {
        super(x,y,mainStage);
        setSize(width, height);
        setBoundaryRectangle();
        enabled = true;
     
    }
    public static boolean overlapBarierActor(BaseActor colider,Stage mainStage) {
    	for (BaseActor actor : BaseActor.getList(mainStage, SolidActor.class)) {
    		SolidActor solid = (SolidActor) actor;
    		if (colider.overlaps(solid) && solid.isEnabled()) {
    			
    			Vector2 offset = colider.preventOverlap(solid);
				if (offset != null) {
					// collided in X direction
					if (Math.abs(offset.x) > Math.abs(offset.y))
						colider.getVelocityVec().x = 0;
					else // collided in Y direction
						colider.getVelocityVec().y = 0;
				}
				return true;
    		}
    	}
    	return false;
    }
    public void setEnabled(boolean b)
    {
        enabled = b;
    }

    public boolean isEnabled()
    {
        return enabled;
    }
}
