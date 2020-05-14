package fr.phonegui.benjaminloison.gui;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.lwjgl.opengl.GL11;

import fr.phonegui.benjaminloison.api.FileAPI;
import fr.phonegui.benjaminloison.common.PhoneGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;

public class GuiPhone extends GuiScreen
{
    int guiWidth, guiHeight = 200, toGo = 255, mv = 75;
    CustomInvisibleButton back;
    CustomButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11;
    private GuiTextField message;
    boolean moving;
    
    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        int guiX = (width - guiWidth) / 2, guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1, 1, 1, 1);
        mc.renderEngine.bindTexture(new ResourceLocation(PhoneGui.MODID, "textures/items/Phone.png"));
        drawTexturedModalRect(guiX, toGo, 208, 0, 1024, 255);
        if(toGo > 0 && !moving)
            toGo -= 20;
        else if(moving)
            if(toGo < 255)
                toGo += 20;
            else
                mc.displayGuiScreen(null);
        message.yPosition = toGo + 75;
        back.yPosition = toGo + 145 + mv;
        button0.yPosition = toGo + 16 + mv;
        button1.yPosition = toGo + 36 + mv;
        button2.yPosition = toGo + 56 + mv;
        button3.yPosition = toGo + 76 + mv;
        button4.yPosition = toGo + 96 + mv;
        button5.yPosition = toGo + 116 + mv;
        button6.yPosition = toGo + 16 + mv;
        button7.yPosition = toGo + 36 + mv;
        button8.yPosition = toGo + 56 + mv;
        button9.yPosition = toGo + 76 + mv;
        button10.yPosition = toGo + 96 + mv;
        button11.yPosition = toGo + 116 + mv;
        message.drawTextBox();
        super.drawScreen(x, y, ticks);
    }

    @Override
    public void initGui()
    {
        mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation(PhoneGui.MODID + ":PhoneOpen"), 1));
        int guiX = (width - guiWidth) / 2, guiY = (height - guiHeight) / 2;
        buttonList.clear();
        back = new CustomInvisibleButton(0, guiX + 162, 0, "", 24, 25);
        buttonList.add(back);
        button0 = new CustomButton(1, guiX + 119, 0, I18n.format("phone.button0"), 55, 20);
        buttonList.add(button0);
        button1 = new CustomButton(2, guiX + 119, 0, I18n.format("phone.button1"), 55, 20);
        buttonList.add(button1);
        button2 = new CustomButton(3, guiX + 119, 0, I18n.format("phone.button2"), 55, 20);
        buttonList.add(button2);
        button3 = new CustomButton(4, guiX + 119, 0, I18n.format("phone.button3"), 55, 20);
        buttonList.add(button3);
        button4 = new CustomButton(5, guiX + 119, 0, I18n.format("phone.button4"), 55, 20);
        buttonList.add(button4);
        button5 = new CustomButton(6, guiX + 119, 0, I18n.format("phone.button5"), 55, 20);
        buttonList.add(button5);
        button6 = new CustomButton(7, guiX + 174, 0, I18n.format("phone.button6"), 55, 20);
        buttonList.add(button6);
        button7 = new CustomButton(8, guiX + 174, 0, I18n.format("phone.button7"), 55, 20);
        buttonList.add(button7);
        button8 = new CustomButton(9, guiX + 174, 0, I18n.format("phone.button8"), 55, 20);
        buttonList.add(button8);
        button9 = new CustomButton(10, guiX + 174, 0, I18n.format("phone.button9"), 55, 20);
        buttonList.add(button9);
        button10 = new CustomButton(11, guiX + 174, 0, I18n.format("phone.button10"), 55, 20);
        buttonList.add(button10);
        button11 = new CustomButton(12, guiX + 174, 0, I18n.format("phone.button11"), 55, 20);
        buttonList.add(button11);
        message = new GuiTextField(fontRendererObj, guiX + 120, toGo + 75, 108, 15);
        message.setMaxStringLength(FileAPI.configNumberInt("phone.message.maximum.length"));
        message.setText(I18n.format("phone.default.message.field"));
        message.setFocused(true);
        super.initGui();
    }

    protected void keyTyped(char c, int i)
    {
        if(i == 1)
            return;
        super.keyTyped(c, i);
        mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation(PhoneGui.MODID + ":PhoneType"), 1));
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
                        if (MinecraftForge.EVENT_BUS.post(event))
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
        moving = true;
        if(btn.id != 0)
        {
            mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation(PhoneGui.MODID + ":PhoneMessage"), 1));
            mc.thePlayer.sendChatMessage(I18n.format("phone.command" + btn.id) + message.getText());
        }
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation(PhoneGui.MODID + ":PhoneClose"), 1));
    }
}