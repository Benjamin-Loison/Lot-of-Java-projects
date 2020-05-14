package fr.phonegui.benjaminloison.gui;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import fr.phonegui.benjaminloison.api.FileAPI;
import fr.phonegui.benjaminloison.common.PhoneGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;

public class GuiTalkieWalkie extends GuiScreen
{
    int guiWidth, guiHeight = 200, toGo = 255, mv = 75, number, command, lAct;
    CustomInvisibleButton back, previous, next, action;
    private GuiTextField message;
    boolean moving;
    String act;

    public GuiTalkieWalkie(int number)
    {
        this.number = number;
        act = I18n.format("talkiewalkie" + number + ".button0");
        int t = 0;
        while(!I18n.format("talkiewalkie" + number + ".button" + t).equals("talkiewalkie" + number + ".button" + t))
        {
            t++;
            lAct++;
        }
    }

    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        int guiX = (width - guiWidth) / 2, guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1, 1, 1, 1);
        mc.renderEngine.bindTexture(new ResourceLocation(PhoneGui.MODID, "textures/items/Talkie Walkie " + number + ".png"));
        drawTexturedModalRect(guiX, toGo, 208, 0, 1024, 255);
        if(toGo > 0 && !moving)
            toGo -= 20;
        else if(moving)
            if(toGo < 255)
                toGo += 20;
            else
                mc.displayGuiScreen(null);
        drawCenteredString(mc.fontRenderer, act, guiX + 177, toGo + 127, 0xFFFFFF);
        message.yPosition = toGo + 100;
        back.yPosition = toGo + 85 + mv;
        previous.yPosition = toGo + 62 + mv;
        next.yPosition = toGo + 85 + mv;
        action.yPosition = toGo + 62 + mv;
        message.drawTextBox();
        super.drawScreen(x, y, ticks);
    }

    @Override
    public void initGui()
    {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation(PhoneGui.MODID + ":TalkieWalkieOpen"), 1));
        int guiX = (width - guiWidth) / 2, guiY = (height - guiHeight) / 2;
        buttonList.clear();
        back = new CustomInvisibleButton(0, guiX + 152, 0, "", 17, 17);
        buttonList.add(back);
        previous = new CustomInvisibleButton(1, guiX + 185, 0, "", 17, 17);
        buttonList.add(previous);
        next = new CustomInvisibleButton(2, guiX + 185, 0, "", 17, 17);
        buttonList.add(next);
        action = new CustomInvisibleButton(3, guiX + 152, 0, "", 17, 17);
        buttonList.add(action);
        message = new GuiTextField(fontRendererObj, guiX + 153, toGo + 75, 50, 25);
        message.setMaxStringLength(FileAPI.configNumberInt("talkiewalkie" + number + ".message.maximum.length"));
        message.setText(I18n.format("talkiewalkie" + number + ".default.message.field"));
        message.setFocused(true);
        super.initGui();
    }

    protected void keyTyped(char c, int i)
    {
        if(i == 1)
            return;
        super.keyTyped(c, i);
        mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation(PhoneGui.MODID + ":TalkieWalkieType" + new Random().nextInt(5)), 1));
        message.textboxKeyTyped(c, i);
    }

    protected void mouseClicked(int x, int y, int btn)
    {
        if(btn == 0)
            for(int l = 0; l < buttonList.size(); ++l)
            {
                GuiButton guibutton = (GuiButton)buttonList.get(l);
                if(guibutton.mousePressed(mc, x, y))
                    try
                    {
                        ActionPerformedEvent.Pre event = new ActionPerformedEvent.Pre(this, guibutton, buttonList);
                        if(MinecraftForge.EVENT_BUS.post(event))
                            break;
                        actionPerformed(event.button);
                        if(equals(mc.currentScreen))
                            MinecraftForge.EVENT_BUS.post(new ActionPerformedEvent.Post(this, event.button, buttonList));
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        message.mouseClicked(x, y, btn);
    }

    protected void actionPerformed(GuiButton btn)
    {
        if(btn.id == 0 || btn.id == 3)
        {
            moving = true;
            mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation(PhoneGui.MODID + ":TalkieWalkieClose"), 1));
            if(btn.id == 3)
            {
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation(PhoneGui.MODID + ":TalkieWalkieMessage"), 1));
                mc.thePlayer.sendChatMessage(I18n.format("talkiewalkie" + number + ".command" + command) + message.getText());
            }
        }
        else if(btn.id == 1 || btn.id == 2)
        {
            mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation(PhoneGui.MODID + ":TalkieWalkieChange"), 1));
            if(btn.id == 1)
                if(command == 0)
                {
                    command = lAct - 1;
                    act = I18n.format("talkiewalkie" + number + ".button" + command);
                }
                else
                {
                    command--;
                    act = I18n.format("talkiewalkie" + number + ".button" + command);
                }
            else if(btn.id == 2)
                if(command == lAct - 1)
                {
                    command = 0;
                    act = I18n.format("talkiewalkie" + number + ".button" + command);
                }
                else
                {
                    command++;
                    act = I18n.format("talkiewalkie" + number + ".button" + command);
                }
        }
    }
}