package fr.arceus.mods;

import fr.arceus.Category;

public class Sprint extends Mods
{
    public Sprint()
    {
        super("Sprint", 0, Category.Movement);
        setToggled(true);
    }
    
    public void onUpdate()
    {
        if (!isToggled())
        {
            return;
        }
        
        if (!this.mc.thePlayer.isCollidedHorizontally && this.mc.thePlayer.moveForward > 0.0F)
        {
            this.mc.thePlayer.setSprinting(true);
        } else {
            this.mc.thePlayer.setSprinting(false);
        }
    }
}