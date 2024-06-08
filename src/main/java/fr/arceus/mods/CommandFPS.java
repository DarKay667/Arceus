package fr.arceus.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CommandFPS extends CommandBase
{
    public String getCommandName()
    {
        return ".fps";
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return ".fps (affiche votre nombre d'fps en jeu)";
    }
    
    public void processCommand(ICommandSender sender, String[] player)
    {
        EntityPlayerMP entityPlayerMP = getCommandSenderAsPlayer(sender);
        entityPlayerMP.addChatComponentMessage((IChatComponent) new ChatComponentText("" + (Minecraft.getMinecraft()).debug.split(",")[0]));
    }
}