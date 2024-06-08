package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.client.renderer.entity.RenderManager;

public class MobHitbox extends Mods
{
    public MobHitbox()
    {
        super("MobHitbox", 0, Category.Render);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            RenderManager.debugBoundingBox = true;
        } else {
            RenderManager.debugBoundingBox = false;
        }
    }
}