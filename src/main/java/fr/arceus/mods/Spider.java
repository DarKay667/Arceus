package fr.arceus.mods;

import fr.arceus.Category;

public class Spider extends Mods
{
    public Spider()
    {
        super("Spider", 24, Category.Movement);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            if (this.mc.thePlayer.motionY < 0.0D && this.mc.thePlayer.isCollidedHorizontally)
                this.mc.thePlayer.jump();
        }
    }
}