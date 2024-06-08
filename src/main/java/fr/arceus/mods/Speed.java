package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;

public class Speed extends Mods
{
    public Speed()
    {
        super("Speed", 0, Category.Movement);
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            float yaw = this.mc.thePlayer.rotationYaw * 0.017453292F;
            this.mc.thePlayer.motionX -= MathHelper.sin(yaw) * 0.6D;
            this.mc.thePlayer.motionZ += MathHelper.cos(yaw) / 0.6D;
            
            if (this.mc.thePlayer.fallDistance > 2.0F)
            {
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.boundingBox.minY, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, true));
            }
        }
        
        super.onUpdate();
    }
}