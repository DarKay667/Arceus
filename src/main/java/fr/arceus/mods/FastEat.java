package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.item.ItemFood;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastEat extends Mods
{
    public FastEat()
    {
        super("FastEat", 47, Category.Player);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            if (this.mc.thePlayer.isEating() && this.mc.thePlayer.getItemInUse() != null && this.mc.thePlayer.getItemInUse().getItem() instanceof ItemFood && this.mc.thePlayer.fallDistance < 3.0F)
            {
                for (int i = 0; i < 8; i++)
                {
                    this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(this.mc.thePlayer.onGround));
                }
            }
        }
    }
}