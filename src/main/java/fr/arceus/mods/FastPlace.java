package fr.arceus.mods;

import java.lang.reflect.Field;

import fr.arceus.Category;
import net.minecraft.client.Minecraft;

public class FastPlace extends Mods
{
    public FastPlace()
    {
        super("FastPlace", 38, Category.World);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            try {
                Field f = Minecraft.getMinecraft().getClass().getDeclaredField("rightClickOnDelayTimer");
                f.setAccessible(true);
                f.set(Minecraft.getMinecraft(), Integer.valueOf(0));
            } catch (Exception ex) {
                
                try {
                    Field f = Minecraft.getMinecraft().getClass().getDeclaredField("rightClickOnDelayTimer");
                    f.setAccessible(true);
                    f.set(Minecraft.getMinecraft(), Integer.valueOf(0));
                } catch (Exception exx) {
                    ex.printStackTrace();
                    exx.printStackTrace();
                }
            }
        }
    }
}