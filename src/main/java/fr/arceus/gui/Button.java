package fr.arceus.gui;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import fr.arceus.mods.Mods;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Button extends Components
{
    public Mods mod;
    public Frame parent;
    public int offset, height;
    private boolean isHovered;
    public boolean open;
    private ArrayList<Components> subcomponents;
    
    public Button(Mods mod, Frame parent, int offset)
    {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<Components>();
        this.open = false;
        this.height = 12;
        int opY = offset + 12;
        this.subcomponents.add(new Keybind(this, opY));
    }
    
    public void setOff(int newOff)
    {
        this.offset = newOff;
        int opY = this.offset + 12;
        
        for (Components comp : this.subcomponents)
        {
            comp.setOff(opY);
            opY += 12;
        }
    }
    
    public void renderComponent()
    {
        Gui.drawRect(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + 12 + this.offset, this.mod.isToggled() ? (new Color(80, 170, 155)).getRGB() : (this.isHovered ? -13619923 : (this.mod.isToggled() ? (new Color(-13619923)).darker().getRGB() : -13619923)));
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        (Minecraft.getMinecraft()).fontRenderer.drawStringWithShadow(this.mod.getName(), (this.parent.getX() + 2) * 2, (this.parent.getY() + this.offset + 2) * 2 + 4, this.mod.isToggled() ? 10066329 : -1);
        
        if (this.subcomponents.size() > 1)
            (Minecraft.getMinecraft()).fontRenderer.drawStringWithShadow(this.open ? "" : "", (this.parent.getX() + this.parent.getWidth() - 10) * 2, (this.parent.getY() + this.offset + 2) * 2 + 4, -1); 
        GL11.glPopMatrix();
        
        if (this.open && !this.subcomponents.isEmpty())
        {
            for (Components comp : this.subcomponents)
            {
                comp.renderComponent();
            }
            
            Gui.drawRect(this.parent.getX() + 2, this.parent.getY() + this.offset + 12, this.parent.getX() + 3, this.parent.getY() + this.offset + (this.subcomponents.size() + 1) * 12, ClickGui.color);
        }
    }
    
    public int getHeight()
    {
        if (this.open)
        {
            return 12 * (this.subcomponents.size() + 1);
        }
        
        return 12;
    }
    
    public void updateComponent(int mouseX, int mouseY)
    {
        this.isHovered = isMouseOnButton(mouseX, mouseY);
        
        if (!this.subcomponents.isEmpty())
        {
            for (Components comp : this.subcomponents)
            {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }
    
    public void mouseClicked(int mouseX, int mouseY, int button)
    {
        if (isMouseOnButton(mouseX, mouseY) && button == 0)
        {
            this.mod.toggle();
        }
        
        if (isMouseOnButton(mouseX, mouseY) && button == 1)
        {
            this.open = !this.open;
            this.parent.refresh();
        }
        
        for (Components comp : this.subcomponents)
        {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }
    
    public void mouseReleased()
    {
        for (Components comp : this.subcomponents)
        {
            comp.mouseReleased();
        }
    }
    
    public void keyTyped(char typedChar, int key)
    {
        for (Components comp : this.subcomponents)
        {
            comp.keyTyped(typedChar, key);
        }
    }
    
    public boolean isMouseOnButton(int x, int y)
    {
        if (x > this.parent.getX() && x < this.parent.getX() + this.parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset)
        {
            return true;
        }
        return false;
    }
}