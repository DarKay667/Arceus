package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Mods
{
    public NoFall()
    {
        super("NoFall", 49, Category.Movement);
    }
    
    public void onUpdate()
    {
        if (isToggled() && this.mc.thePlayer.fallDistance > 2.0F)
        {
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(true));
        }
        
        super.onUpdate();
    }
}