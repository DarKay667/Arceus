package fr.arceus.mods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fr.arceus.Arceus;
import fr.arceus.Category;
import fr.arceus.utils.RainbowUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class HUD extends Mods
{
    private float hue;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    public HUD()
    {
        super("HUD", 35, Category.Render);
        
        this.hue = 0.0F;
        setToggled(true);
    }
    
    public void onRender()
    {
        if (!isToggled())
        {
            return;
        }
        
        float hue1 = this.hue;
        this.hue += 0.005F;
        
        if (this.hue >= 255.0F)
        {
            this.hue = 0.0F;
        }
        
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime now2 = LocalDateTime.now();
        String date = dateFormat.format(now);
        String time = timeFormat.format(now2);
        
        drawStringRW(this.mc.getSession().getUsername() + " - Arceus 1.5.3", 2, 2, 0.8F);
        drawStringRW(date, 2, 12, 0.0F);
        drawStringRW(time, 65, 12, 0.0F);
        
        ArrayList<Mods> mods = Arceus.getModules();
        Collections.sort(mods, new Comparator<Mods>() {
            public int compare(Mods m1, Mods m2)
            {
                if ((Minecraft.getMinecraft()).fontRenderer.getStringWidth(m1.getName()) > (Minecraft.getMinecraft()).fontRenderer.getStringWidth(m2.getName()))
                {
                    return -1;
                }
                
                if ((Minecraft.getMinecraft()).fontRenderer.getStringWidth(m1.getName()) > (Minecraft.getMinecraft()).fontRenderer.getStringWidth(m2.getName()))
                {
                    return 1;
                }
                return 0;
            }
        });
        
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), (Minecraft.getMinecraft()).displayWidth, (Minecraft.getMinecraft()).displayHeight);
        
        int count = 0;
        
        for (Mods m : mods)
        {
            if (m.isToggled() && !m.isCategory(Category.GUI))
            {
                Gui.drawRect(sr.getScaledWidth() - (Minecraft.getMinecraft()).fontRenderer.getStringWidth(m.getName()) - 2, count * 10, sr.getScaledWidth(), (count + 1) * 10, 0);
                (Minecraft.getMinecraft()).fontRenderer.drawString(m.getName(), sr.getScaledWidth() - (Minecraft.getMinecraft()).fontRenderer.getStringWidth(m.getName()) - 1, count * 10 + 1, 127255);
                count++;
                hue1 += 0.1F;
                
                if (hue1 >= 255.0F)
                {
                    hue1 = 0.0F;
                }
            }
        }
        super.onRender();
    }
    
    public void drawStringRW(String string, int x, int y, float brightness)
    {
        int xpos = x;
        
        for (int i = 0; i < string.length(); i++)
        {
            String s = string.charAt(i) + "";
            this.mc.fontRenderer.drawStringWithShadow(s, xpos, y, RainbowUtils.effect(i * 3500000L, brightness, 100).getRGB());
            xpos += this.mc.fontRenderer.getStringWidth(s);
        }
    }
}