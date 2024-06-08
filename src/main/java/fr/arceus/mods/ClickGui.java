package fr.arceus.mods;

import fr.arceus.Category;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends Mods
{
    private fr.arceus.gui.ClickGui gui;
    
    public ClickGui()
    {
        super("ClickGui", 54, Category.Render);
    }
    
    public void onEnable()
    {
        if (this.gui == null)
        {
            this.gui = new fr.arceus.gui.ClickGui();
        }
        
        this.mc.displayGuiScreen((GuiScreen) this.gui);
        
        toggle();
        super.onEnable();
    }
}