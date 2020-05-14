package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;

import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.driveables.TypeFile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AttachmentType extends InfoType implements IScope
{
    public static ArrayList<AttachmentType> attachments = new ArrayList<AttachmentType>();
    public EnumAttachmentType type = EnumAttachmentType.generic;
    public boolean hasScopeOverlay, silencer, flashlight;
    public int maxStackSize = 1, flashlightStrength = 12;
    public float modelScale = 1, zoomLevel = 1, FOVZoomLevel = 1, flashlightRange = 10, spreadMultiplier = 1, recoilMultiplier = 1, damageMultiplier = 1, meleeDamageMultiplier = 1, bulletSpeedMultiplier = 1, reloadTimeMultiplier = 1;
    public EnumFireMode modeOverride;
    public String zoomOverlay;

    public AttachmentType(TypeFile file)
    {
        super(file);
        attachments.add(this);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("AttachmentType"))
                type = EnumAttachmentType.get(split[1]);
            else if(split[0].equals("ModelScale"))
                modelScale = Float.parseFloat(split[1]);
            else if(split[0].equals("Silencer"))
                silencer = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("Flashlight"))
                flashlight = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("FlashlightRange"))
                flashlightRange = Float.parseFloat(split[1]);
            else if(split[0].equals("FlashlightStrength"))
                flashlightStrength = Integer.parseInt(split[1]);
            else if(split[0].equals("ModeOverride"))
                modeOverride = EnumFireMode.getFireMode(split[1]);
            else if(split[0].equals("MeleeDamageMultiplier"))
                meleeDamageMultiplier = Float.parseFloat(split[1]);
            else if(split[0].equals("DamageMultiplier"))
                damageMultiplier = Float.parseFloat(split[1]);
            else if(split[0].equals("SpreadMultiplier"))
                spreadMultiplier = Float.parseFloat(split[1]);
            else if(split[0].equals("RecoilMultiplier"))
                recoilMultiplier = Float.parseFloat(split[1]);
            else if(split[0].equals("BulletSpeedMultiplier"))
                bulletSpeedMultiplier = Float.parseFloat(split[1]);
            else if(split[0].equals("ReloadTimeMultiplier"))
                reloadTimeMultiplier = Float.parseFloat(split[1]);
            else if(split[0].equals("ZoomLevel"))
                zoomLevel = Float.parseFloat(split[1]);
            else if(split[0].equals("FOVZoomLevel"))
                FOVZoomLevel = Float.parseFloat(split[1]);
            else if(split[0].equals("ZoomOverlay"))
            {
                hasScopeOverlay = true;
                if(split[1].equals("None"))
                    hasScopeOverlay = false;
                else
                    zoomOverlay = split[1];
            }
        }
        catch(Exception e)
        {
            System.out.println("Reading attachment file failed.");
            e.printStackTrace();
        }
    }

    public static AttachmentType getFromNBT(NBTTagCompound tags)
    {
        ItemStack stack = ItemStack.loadItemStackFromNBT(tags);
        if(stack != null && stack.getItem() instanceof ItemAttachment)
            return ((ItemAttachment)stack.getItem()).type;
        return null;
    }

    @Override
    public float getZoomFactor()
    {
        return zoomLevel;
    }

    @Override
    public boolean hasZoomOverlay()
    {
        return hasScopeOverlay;
    }

    @Override
    public String getZoomOverlay()
    {
        return zoomOverlay;
    }

    @Override
    public float getFOVFactor()
    {
        return FOVZoomLevel;
    }

    public static AttachmentType getAttachment(String s)
    {
        for(AttachmentType attachment : attachments)
            if(attachment.shortName.equals(s))
                return attachment;
        return null;
    }
}