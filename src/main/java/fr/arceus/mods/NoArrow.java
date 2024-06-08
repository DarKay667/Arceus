package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;

public class NoArrow extends Mods
{
    public NoArrow()
    {
        super("NoArrow", 0, Category.World);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
           try {
               for (Object o : this.mc.theWorld.loadedEntityList)
               {
                   if (o instanceof EntityArrow)
                   {
                       EntityArrow e = (EntityArrow) o;
                       
                       if (this.mc.thePlayer.canEntityBeSeen((Entity) e) && this.mc.thePlayer.getDistanceSqToEntity((Entity) e) <= 5.0D && !e.isCollided && !e.onGround)
                       {
                           this.mc.thePlayer.motionX = 0.0D;
                           this.mc.thePlayer.motionY = 0.0D;
                           this.mc.thePlayer.motionZ = 0.0D;
                       }
                   }
               }
           } catch (Exception exception) {}
        }
        super.onUpdate();
    } 
}