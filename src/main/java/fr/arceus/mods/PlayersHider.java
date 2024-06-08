package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.entity.player.EntityPlayer;

public class PlayersHider extends Mods
{
    public PlayersHider()
    {
        super("PlayersHider", 0, Category.Render);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            this.mc.thePlayer.setInvisible(true);
            this.mc.thePlayer.isInvisible();
        } else {
            this.mc.thePlayer.setInvisible(false);
            this.mc.thePlayer.isInvisibleToPlayer(null);
        }
        
        super.onUpdate();
    }
}   