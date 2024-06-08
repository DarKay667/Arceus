package fr.arceus.utils;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import fr.arceus.Arceus;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EventReceiver
{
    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent event)
    {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT)
        {
            try
            {
                Arceus.onRender();
            } catch (Exception exception) {}
        }
    }
    
    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if (event.entityLiving == null)
        {
            return;
        }
        
        if (event.entityLiving instanceof EntityPlayerSP)
        {
            try
            {
                Arceus.onUpdate();
            } catch (Exception exception) {}
        }
    }
    
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (!Keyboard.getEventKeyState())
        {
            return;
        }
        
        try
        {
            Arceus.onKeyPressed(Keyboard.getEventKey());
        } catch (Exception exception) {}
    }
}   