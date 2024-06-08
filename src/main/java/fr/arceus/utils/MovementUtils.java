package fr.arceus.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;

public class MovementUtils
{
    private static Minecraft mc = Minecraft.getMinecraft();
    
    public static float getDirection(EntityLivingBase e)
    {
        float yaw = e.rotationYaw;
        float forward = e.moveForward;
        float strafe = e.moveStrafing;
        
        /*yaw += ((forward < 0.0F) ? '´' : false);*/
        
        if (strafe < 0.0F)
        {
            yaw += ((forward == 0.0F) ? 90 : ((forward < 0.0F) ? -45 : 45));
        }
        
        if (strafe > 0.0F)
        {
            yaw -= ((forward == 0.0F) ? 90 : ((forward < 0.0F) ? -45 : 45));
        }
        
        return yaw * 0.017453292F;
    }
    
    public static void setSpeed(Entity e, double speed)
    {
        e.motionX = -MathHelper.sin(getDirection()) * speed;
        e.motionZ = MathHelper.cos(getDirection()) * speed;
    }
    
    public static double getSpeed(EntityLivingBase e)
    {
        return Math.sqrt(square(e.motionX) + square(e.motionZ));
    }
    
    public static float getDirection()
    {
        return getDirection((EntityLivingBase) mc.thePlayer);
    }
    
    public static void setSpeed(double speed)
    {
        setSpeed((Entity) mc.thePlayer, speed);
    }
    
    public static double getSpeed()
    {
        return getSpeed((EntityLivingBase) mc.thePlayer);
    }
    
    public static double square(double in)
    {
        return in * in;
    }
    
    public static boolean isMoving()
    {
        return (mc.thePlayer.moveForward != 0.0F || mc.thePlayer.moveStrafing != 0.0F);
    }
    
    public static void setRaycastedPositionOffsetted(double offset)
    {
        mc.thePlayer.setPosition(mc.thePlayer.posX + -MathHelper.sin(getDirection()) * offset, mc.thePlayer.posY, mc.thePlayer.posZ + MathHelper.cos(getDirection()) * offset);
        mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + -MathHelper.sin(getDirection()) * offset, mc.thePlayer.boundingBox.minY, mc.thePlayer.posY, mc.thePlayer.posZ + MathHelper.cos(getDirection()) * offset, mc.thePlayer.onGround));
    }
}