package fr.arceus.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.arceus.utils.EventReceiver;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends ServerProxy
{
    public void registerRenders()
    {
        EventReceiver eventReceiver = new EventReceiver();
        MinecraftForge.EVENT_BUS.register(eventReceiver);
        
        FMLCommonHandler.instance().bus().register(eventReceiver);
    }
    
    public ClientProxy()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onActionPerformed(GuiScreenEvent.ActionPerformedEvent event)
    {
        if (event.gui instanceof GuiMainMenu)
        {
            if (event.button.id == 50)
            {
                FMLClientHandler.instance().connectToServerAtStartup("proxy.paladium-pvp.fr", 25565);
            }
        }
    }
    
    @SubscribeEvent
    public void onOpenGui(GuiOpenEvent event)
    {
        if (event.gui != null && (event.gui.getClass() == GuiMainMenu.class || event.gui.getClass().getName().equalsIgnoreCase("fr.paladium.palamod.client.gui.PGuiMainMenu")))
        {
            event.gui = (GuiScreen) new GuiMainMenu();
        }
    }
    
    @SubscribeEvent
    public void onInitGuiEvent(GuiScreenEvent.InitGuiEvent.Post event)
    {
        if (event.gui instanceof GuiMainMenu)
        {
            for (Object b : event.buttonList)
            {
                if (((GuiButton) b).id == 14)
                {
                    ((GuiButton) b).visible = false;
                }
                
                if (((GuiButton) b).id == 5)
                {
                    ((GuiButton) b).visible = false;
                }
                
                if (((GuiButton) b).id == 1)
                {
                    ((GuiButton) b).visible = false;
                }
                
                if (((GuiButton) b).id == 2)
                {
                    ((GuiButton) b).visible = false;
                }
                
                if (((GuiButton) b).id == 6)
                {
                    ((GuiButton) b).visible = false;
                }
            }
            
            int i = event.gui.height / 4 + 48;
            
            event.buttonList.add(new GuiButton(50, event.gui.width / 2 - 100, i + 6, "Se connecter sur Paladium-PvP"));
            event.buttonList.add(new GuiButton(1, event.gui.width / 2 - 100, i + 20 + 12, 98, 20, I18n.format("Singleplayer", new Object[0])));
            event.buttonList.add(new GuiButton(2, event.gui.width / 2 - -2, i + 20 + 12, 98, 20, I18n.format("Multiplayer", new Object[0])));
            event.buttonList.add(new GuiButton(6, event.gui.width / 2 - 100, i + 58, "Mods"));
        }
    }
}