package fr.arceus;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.arceus.mods.*;
import fr.arceus.proxy.ServerProxy;
import fr.arceus.utils.F3;

@Mod(modid = "arceus", name = "Arceus", version = "1.5.3")
public class Arceus 
{
	@Instance("arceus")
	public static Arceus instance;
	@SidedProxy(clientSide = "fr.arceus.proxy.ClientProxy", serverSide = "fr.arceus.proxy.ServerProxy")
	public static ServerProxy proxy;
	private static ArrayList<Mods> mods;
	private static Minecraft mc = Minecraft.getMinecraft();
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	    MinecraftForge.EVENT_BUS.register(new F3());
	    proxy.registerRenders();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	    Display.setTitle(Minecraft.getMinecraft().getSession().getUsername() + " - Arceus V1.5.3");
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
	    event.registerServerCommand((ICommand) new CommandFPS());
	}
	
	public Arceus()
	{
	    mods = new ArrayList<Mods>();
	    
	    addMod((Mods) new AutoDeco());
	    addMod((Mods) new ClickGui());
	    addMod((Mods) new FastEat());
	    addMod((Mods) new Fly());
	    addMod((Mods) new FullBright());
	    addMod((Mods) new Glide());
	    addMod((Mods) new HUD());
	    addMod((Mods) new KillAura());
	    addMod((Mods) new NoFall());
	    addMod((Mods) new Speed());
	    addMod((Mods) new Sprint());
	    addMod((Mods) new FastPlace());
	    addMod((Mods) new AntiAFK());
	    addMod((Mods) new PlayersHider());
	    addMod((Mods) new MobHitbox());
	    addMod((Mods) new WaterWalk());
	    addMod((Mods) new NoArrow());
	    addMod((Mods) new AntiKB());
	    addMod((Mods) new Chicken());
	    addMod((Mods) new FastBreak());
	    addMod((Mods) new FastLadder());
	    addMod((Mods) new Spider());
	}
	
	private void addMod(Mods m)
	{
	    mods.add(m);
	}
	
	public static ArrayList<Mods> getModules()
	{
	    return mods;
	}
	
	public static void onUpdate()
	{
	    for (Mods m : mods)
	    {
	        m.onUpdate();
	    }
	}
	
	public static void onRender()
	{
	    for (Mods m : mods)
	    {
	        m.onRender();
	    }
	}
	
	public static void onKeyPressed(int k)
	{
	    for (Mods m : mods)
	    {
	        if (m.getKey() == k)
	        {
	            m.toggle();
	        }
	    }
	}
	
	public static ArrayList<Mods> getModulesInCategory(Category categoryIn)
	{
	    ArrayList<Mods> modz = new ArrayList<Mods>();
	    
	    for (Mods m : mods)
	    {
	        if (m.getCategory() == categoryIn)
	            modz.add(m);
	    }
	    return modz;
	}
}