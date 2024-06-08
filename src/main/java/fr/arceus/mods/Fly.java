package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Fly extends Mods
{
    public static float flySpeed = 0.4F;
    
    public Fly()
    {
        super("Fly", 33, Category.Movement);
    }
    
    public void onDisable()
    {
        this.mc.thePlayer.capabilities.isFlying = false;
        this.mc.thePlayer.capabilities.allowFlying = false;
        super.onDisable();
    }
    
    public void onUpdate()
    {
        if (isToggled())
        {
            this.mc.thePlayer.capabilities.isFlying = true;
            this.mc.thePlayer.capabilities.allowFlying = true;
            
            if (this.mc.thePlayer.fallDistance > 2.0F)
            {
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.boundingBox.minY, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, true));;
            }
            
            if (this.mc.gameSettings.keyBindJump.isPressed())
            {
                this.mc.thePlayer.motionY += 0.3D;
            }
            
            if (this.mc.gameSettings.keyBindSneak.isPressed())
            {
                this.mc.thePlayer.motionY -= 0.3D;
            }
            
            if (this.mc.gameSettings.keyBindForward.isPressed())
            {
                this.mc.thePlayer.capabilities.setFlySpeed(flySpeed);
            }
        }
        
        super.onUpdate();
    }
}   