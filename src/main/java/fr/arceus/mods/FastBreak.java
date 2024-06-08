package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FastBreak extends Mods
{
    public FastBreak()
    {
        super("FastBreak", 50, Category.World);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            this.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 840, 10));
        }
    }
    
    public void onDisable()
    {
        this.mc.thePlayer.removePotionEffect(Potion.digSpeed.id);
        super.onDisable();
    }
}