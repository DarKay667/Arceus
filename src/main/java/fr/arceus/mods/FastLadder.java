package fr.arceus.mods;

import fr.arceus.Category;
import fr.arceus.utils.MovementUtils;

public class FastLadder extends Mods
{
    public FastLadder()
    {
        super("FastLadder", 22, Category.Movement);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            if (MovementUtils.isMoving() && this.mc.thePlayer.isOnLadder() && this.mc.thePlayer.isCollidedHorizontally)
            {
                this.mc.thePlayer.motionY = 1.0D;
            }
        }
    }
}