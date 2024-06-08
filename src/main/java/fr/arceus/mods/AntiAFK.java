package fr.arceus.mods;

import fr.arceus.Category;

public class AntiAFK extends Mods
{
    private int tick;
    
    public AntiAFK()
    {
        super("AntiAFK", 0, Category.Player);
        
        this.tick = 0;
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            this.tick++;
            
            if (this.tick > 200)
            {
                this.tick = 0;
                this.mc.thePlayer.rotationYaw -= 180.0F;
                this.mc.thePlayer.moveForward = 1.0F;
                
                if (this.mc.thePlayer.onGround)
                    this.mc.thePlayer.jump();
            }
        }
    }
}