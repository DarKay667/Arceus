package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;

public class Chicken extends Mods
{
    public Chicken()
    {
        super("Chicken", 23, Category.World);
    }
    
    public void onEnable()
    {
        EntityChicken chicken = new EntityChicken(this.mc.thePlayer.worldObj);
        chicken.setPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 1.0D, this.mc.thePlayer.posZ);
        
        this.mc.thePlayer.worldObj.spawnEntityInWorld((Entity) chicken);
        chicken.moveForward = 1.5F;
        chicken.moveStrafing = 0.5F;
        super.onEnable();
    }
}