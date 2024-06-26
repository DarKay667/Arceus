package fr.arceus.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Keybind extends Components
{
    private boolean hovered, binding;
    private Button parent;
    private int offset, x , y;
    
    public Keybind(Button button, int offset)
    {
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    } 
    
    public void setOff(int newOff)
    {
        this.offset = newOff;
    }
    
    public void renderComponent()
    {
        Gui.drawRect(this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth() * 1, this.parent.parent.getY() + this.offset + 12, this.hovered ? -14540254 : -15658735);
        Gui.drawRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 12, -15658735);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        (Minecraft.getMinecraft()).fontRenderer.drawStringWithShadow(" " + Keyboard.getKeyName(this.parent.mod.getKey()), (this.parent.parent.getX() + 7) * 2, (this.parent.parent.getY() + this.offset + 2) * 2 + 5, -1);
        GL11.glPopMatrix();
    }
    
    public void updateComponent(int mouseX, int mouseY)
    {
        this.hovered = isMouseOnButton(mouseX, mouseY);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }
    
    public void mouseClicked(int mouseX, int mouseY, int button)
    {
        if (isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open)
        {
            this.binding = !this.binding;
        }
    }
    
    public void keyTyped(char typedChar, int key)
    {
        if (this.binding)
        {
            this.parent.mod.setKey(key);
            this.binding = false;
        }
    }
    
    public boolean isMouseOnButton(int x, int y)
    {
        if (x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12)
        {
            return true;
        }
        return false;
    }
}