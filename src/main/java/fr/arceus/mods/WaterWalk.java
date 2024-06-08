package fr.arceus.mods;

import fr.arceus.Category;

public class WaterWalk extends Mods
{
    public WaterWalk()
    {
        super("WaterWalk", 0, Category.Movement);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            if (this.mc.thePlayer.isInWater())
            {
                this.mc.thePlayer.jump();
            }
        }
        
        super.onUpdate();
    }
}   