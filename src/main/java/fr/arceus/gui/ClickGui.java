package fr.arceus.gui;

import java.util.ArrayList;

import fr.arceus.Category;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends GuiScreen
{
    public static ArrayList<Frame> frames;
    public static int color;
    
    public ClickGui()
    {
        color = -12632256;
        frames = new ArrayList<Frame>();
        int frameY = 5;
        
        for (Category category : Category.values())
        {
            if (category != Category.GUI)
            {
                Frame frame = new Frame(category);
                frame.setY(frameY);
                frames.add(frame);
                frameY += 24;
            }
        }
    }
    
    public void initGui() /*func_73866_w_*/
    {
        super.initGui();
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) /*func_73863_a*/
    {
        for (Frame frame : frames)
        {
            frame.renderFrame(this.fontRendererObj);
            frame.updatePosition(mouseX, mouseY);
            
            for (Components comp : frame.getComponents())
            {
                comp.updateComponent(mouseX, mouseY);
            }
        }
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) /*func_73864_a*/
    {
        for (Frame frame : frames)
        {
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0)
            {
                frame.setDrag(true);
                frame.dragX = mouseX - frame.getX();
                frame.dragY = mouseY - frame.getY();
            }
            
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1)
            {
                frame.setOpen(!frame.isOpen());
            }
            
            if (frame.isOpen() && !frame.getComponents().isEmpty())
            {
                for (Components component : frame.getComponents())
                {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
        
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    protected void keyTyped(char typedChar, int keyCode) /*func_73869_a*/
    {
        for (Frame frame : frames)
        {
            if (frame.isOpen() && keyCode != 1 && !frame.getComponents().isEmpty())
            {
                for (Components component : frame.getComponents())
                {
                    component.keyTyped(typedChar, keyCode);
                }
            }
        }
        
        if (keyCode == 1)
        {
            this.mc.displayGuiScreen(null);
        }
        
        super.keyTyped(typedChar, keyCode);
    }
    
    protected void mouseMovedOrUp(int mouseX, int mouseY, int state) /*func_146286_b*/
    {
        for (Frame frame : frames)
        {
            frame.setDrag(false);
        }
        
        for (Frame frame : frames)
        {
            if (frame.isOpen() && !frame.getComponents().isEmpty())
            {
                for (Components component : frame.getComponents())
                {
                    component.mouseReleased();
                }
            }
        }
    }
    
    public boolean doesGuiPauseGame() /*func_73868_f*/
    {
        return false;
    }
}