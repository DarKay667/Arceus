package fr.arceus.mods;

import fr.arceus.Category;

public class FullBright extends Mods
{
    public FullBright()
    {
        super("FullBright", 48, Category.Render);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            this.mc.gameSettings.gammaSetting = 100.0F;
        } else {
            this.mc.gameSettings.gammaSetting = 0.0F;
        }
    }
}