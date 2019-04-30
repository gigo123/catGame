package com.thsoft.catgame.gameLogik;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thsoft.catgame.game.BaseActor;

public class SolidActor extends BaseActor{
    private boolean enabled;
    
    public SolidActor(float x, float y, float width, float height, Stage s)
    {
        super(x,y,s);
        setSize(width, height);
        setBoundaryRectangle();
        enabled = true;
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
