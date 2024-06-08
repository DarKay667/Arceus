package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.world.World;

public class KillAura extends Mods
{
    private int cps, delay;
    
    public KillAura()
    {
        super("KillAura", 37, Category.Combat);
    }
    
    public void onUpdate()
    {
        if (!isToggled())
        {
            return;
        }
        
        this.cps = 13;
        
        try {
            this.delay++;
            
            if (this.delay >= -1 / this.cps)
            {
                this.delay = 0;
                
                for (Object o : this.mc.theWorld.loadedEntityList)
                {
                    if (o instanceof EntityLivingBase)
                    {
                        EntityLivingBase e = (EntityLivingBase) o;
                        
                        if (e != null && e.isEntityAlive() && e != this.mc.thePlayer && e.getDistanceToEntity((Entity) this.mc.thePlayer) <= 4.0F)
                        {
                            this.mc.thePlayer.swingItem();
                            this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C02PacketUseEntity((Entity) e, C02PacketUseEntity.Action.ATTACK));
                        }
                        
                        if ((this.mc.pointedEntity != null && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword) || this.mc.thePlayer.isBlocking())
                        {
                           this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C08PacketPlayerBlockPlacement());
                           this.mc.playerController.sendUseItem((EntityPlayer) this.mc.thePlayer, (World) this.mc.theWorld, this.mc.thePlayer.getHeldItem());   
                        }
                    }
                }
            } 
        } catch (Exception exception) {}
        super.onUpdate();
    }
}