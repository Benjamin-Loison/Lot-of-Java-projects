package fr.title.benjaminloison.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.title.benjaminloison.api.ConfigurationAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class ForgeEventHandler
{
    @SubscribeEvent
    public void onMessage(ClientChatReceivedEvent event)
    {
        Minecraft.getMinecraft().fontRenderer.drawString("%TITLE%", (float)(-Minecraft.getMinecraft().fontRenderer.getStringWidth("%TITLE%") / 2), -10, 16777215 | 10/*WTF*/, true);
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        String args[] = event.message.getUnformattedText().split(" ");
        if(args[0].equals("%TITLE%"))
        {
            int vanishTime = ConfigurationAPI.getConfiguration("vanish.time"), dissipateTime = ConfigurationAPI.getConfiguration("dissipate.time");
            if(args[1].equals("title"))
            {
                //args[2];
            }
            else
            {
                
            }
            //gameController.ingameGUI.displayTitle(s, s1, packetIn.getFadeInTime(), packetIn.getDisplayTime(), packetIn.getFadeOutTime());
            event.setCanceled(true);
        }
    }
    
    public void displayTitle(String title, String subtitle, int fadeInTime, int displayTime, int fadeOutTime)
    {
        /*if (title == null && subtitle == null && fadeInTime < 0 && displayTime < 0 && fadeOutTime < 0)
        {
            this.field_175201_x = "";
            this.field_175200_y = "";
            this.field_175195_w = 0;
        }
        else if (title != null)
        {
            this.field_175201_x = title;
            this.field_175195_w = this.field_175199_z + this.field_175192_A + this.field_175193_B;
        }
        else if (subtitle != null)
        {
            this.field_175200_y = subtitle;
        }
        else
        {
            if (fadeInTime >= 0)
            {
                this.field_175199_z = fadeInTime;
            }

            if (displayTime >= 0)
            {
                this.field_175192_A = displayTime;
            }

            if (fadeOutTime >= 0)
            {
                this.field_175193_B = fadeOutTime;
            }

            if (this.field_175195_w > 0)
            {
                this.field_175195_w = this.field_175199_z + this.field_175192_A + this.field_175193_B;
            }
        }*/
    }
}