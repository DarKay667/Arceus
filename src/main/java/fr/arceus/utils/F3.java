package fr.arceus.utils;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class F3
{
    public static final String[] directions = new String[] { "Sud", "Ouest", "Nord", "Est" };
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onOverlay(RenderGameOverlayEvent.Pre event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        
        if (event.type == RenderGameOverlayEvent.ElementType.DEBUG)
        {
            FontRenderer v2 = (Minecraft.getMinecraft()).fontRenderer;
            
            v2.drawStringWithShadow("x: " + (int) mc.thePlayer.posX , 30, 30, Keyboard.isKeyDown(19) ? 16711680 : 16777215);
            v2.drawStringWithShadow("y: " + (int)mc.thePlayer.posY, 30, 40, 16777215);
            v2.drawStringWithShadow("z: " + (int)mc.thePlayer.posZ, 30, 50, Keyboard.isKeyDown(19) ? 16711680 : 16777215);
            v2.drawStringWithShadow("yawl: " + (int)mc.thePlayer.rotationYaw, 83, 30, 16777215);
            v2.drawStringWithShadow("pitch: " + (((int)mc.thePlayer.rotationPitch == -90) ? "666" : Integer.valueOf((int)mc.thePlayer.rotationPitch)), 83, 40, 16777215);
            v2.drawStringWithShadow((Minecraft.getMinecraft()).debug, 30, 60, 16777215);
            v2.drawStringWithShadow("direction: " + directions[MathHelper.floor_double(((Minecraft.getMinecraft()).thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3], 83, 50, 16777215);
            
            if (Keyboard.isKeyDown(50) && Keyboard.isKeyDown(25))
            {
                v2.drawStringWithShadow("DarKay__ beau gosses", 50, 100, 16711680);
            }
            event.setCanceled(true);
        }
    }
}