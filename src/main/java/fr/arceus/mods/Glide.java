package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.block.material.Material;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Glide extends Mods
{
    public Glide()
    {
        super("Glide", 34, Category.Movement);
    }
    
    public void onUpdate()
    {
        double oldY = this.mc.thePlayer.motionY;
        float oldJ = this.mc.thePlayer.jumpMovementFactor;
        
        if (isToggled())
        {
            if (this.mc.thePlayer.motionY < 0.0D && this.mc.thePlayer.isAirBorne && !this.mc.thePlayer.isInWater() && !this.mc.thePlayer.isOnLadder())
            {
                if (!this.mc.thePlayer.isInsideOfMaterial(Material.lava))
                {
                    this.mc.thePlayer.motionY = -0.01D;
                    this.mc.thePlayer.jumpMovementFactor *= 1.12337F;
                }
                
                if (this.mc.thePlayer.fallDistance > 2.0F)
                {
                    this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(true));
                }
            } else {
                this.mc.thePlayer.motionY = oldY;
                this.mc.thePlayer.jumpMovementFactor = oldJ;
            }
        }
    }
}