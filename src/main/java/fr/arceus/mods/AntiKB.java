package fr.arceus.mods;

import fr.arceus.Category;

public class AntiKB extends Mods
{
    public AntiKB()
    {
        super("AntiKB", 45, Category.Combat);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            if (this.mc.thePlayer.hurtResistantTime > 0 && this.mc.thePlayer.hurtTime > 0)
            {
                this.mc.thePlayer.motionX = 0.0D;
                this.mc.thePlayer.motionZ = 0.0D;
            }
        }
        
        super.onUpdate();
    }
}