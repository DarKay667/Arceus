package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;

public class AutoDeco extends Mods
{
    public AutoDeco()
    {
        super("Auto-Deco", 0, Category.Player);
    }
    
    public static double leaveHealth = 2.0D;
    
    public void onUpdate()
    {
        if (isToggled() && this.mc.thePlayer.getHealth() <= leaveHealth)
        {
            boolean flag = this.mc.isIntegratedServerRunning();
            this.mc.theWorld.sendQuittingDisconnectingPacket();
            this.mc.loadWorld((WorldClient) null);
            
            if (flag)
            {
                this.mc.displayGuiScreen((GuiScreen) new GuiMainMenu());
            }
        }
    }
}