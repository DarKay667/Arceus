package fr.arceus.gui;

import fr.arceus.Arceus;
import fr.arceus.Category;
import fr.arceus.mods.Mods;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class Frame
{
    public ArrayList<Components> components;
    public Category category;
    private boolean open, isDragging;
    private int width, x, y, z, barHeight;
    public int dragX, dragY;
    
    public Frame(Category cat)
    {
        this.components = new ArrayList<Components>();
        this.category = cat;
        this.width = 80;
        this.x = 10;
        this.y = 8;
        this.barHeight = 15;
        this.dragX = 0;
        this.open = false;
        this.isDragging = false;
        int tY = this.barHeight;
        
        for (Mods mod : Arceus.getModulesInCategory(this.category)) 
        {
            Button modButton = new Button(mod, this, tY);
            this.components.add(modButton);
            tY += 12;
        }
    }
    
    public ArrayList<Components> getComponents()
    {
        return this.components;
    }
    
    public void setX(int newX)
    {
        this.x = newX;
    }
    
    public void setY(int newY)
    {
        this.y = newY;
    }
    
    public void setDrag(boolean drag)
    {
        this.isDragging = drag;
    }
    
    public boolean isOpen()
    {
        return this.open;
    }
    
    public void setOpen(boolean open)
    {
        this.open = open;
    }
    
    public void renderFrame(FontRenderer fontRenderer)
    {
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.barHeight, ClickGui.color);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        fontRenderer.drawStringWithShadow(this.category.name(), (this.x + 28) * 2 + 4, (int) (this.y + 2.8F) * 2 + 5, -1);
        
        if (!this.components.isEmpty())
        {
            fontRenderer.drawStringWithShadow(this.open ? "" : "", (this.x + this.width - 15) * 2 + 5, (int) (this.y + 3.4F) * 2 + 5, -1);
        }
        
        GL11.glPopMatrix();
        
        if (this.open && !this.components.isEmpty())
        {
            Gui.drawRect(this.x, this.y + this.barHeight, this.x + 1, this.y + this.barHeight + 12 * this.components.size(), (new Color(48, 48, 48)).getRGB());
            Gui.drawRect(this.x, this.y + this.barHeight + 12 * this.components.size(), this.x + this.width, this.y + this.barHeight + 12 * this.components.size() + 1, (new Color(48, 48, 48)).getRGB());
            Gui.drawRect(this.x + this.width, this.y + this.barHeight, this.x + this.width - 1, this.y + this.barHeight + 12 * this.components.size(), (new Color(48, 48, 48)).getRGB());
            
            for (Components component : this.components)
            {
                component.renderComponent();
            }
        }
    }
    
    public void refresh()
    {
        int off = this.barHeight;
        
        for (Components comp : this.components)
        {
            comp.setOff(off);
            off += comp.getHeight();
        }
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public void updatePosition(int mouseX, int mouseY)
    {
        if (this.isDragging)
        {
            setX(mouseX - this.dragX);
            setY(mouseY - this.dragY);
        }
    }
    
    public boolean isWithinHeader(int x, int y)
    {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight)
        {
            return true;
        }
        return false;
    }
}