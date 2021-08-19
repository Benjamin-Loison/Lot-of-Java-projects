package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flansmod.common.vector.Vector3f;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.driveables.TypeFile;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GunType extends InfoType implements IScope
{
    public List<ShootableType> ammo = new ArrayList<ShootableType>();
    public EnumFireMode mode = EnumFireMode.FULLAUTO;
    public EnumSecondaryFunction secondaryFunction = EnumSecondaryFunction.ADS_ZOOM;
    public ArrayList<Vector3f> meleePath = new ArrayList<Vector3f>(), meleePathAngles = new ArrayList<Vector3f>(), meleeDamagePoints = new ArrayList<Vector3f>();
    public Vector3f shieldOrigin, shieldDimensions;
    public String texture, deployableTexture, defaultScopeTexture, dropItemOnShoot = null, shootSound, reloadSound, warmupSound, loopedSound, cooldownSound, meleeSound, idleSound;
    public int numGenericAttachmentSlots = 0, meleeTime = 1, numBurstRounds = 3, reloadTime, recoil, numBullets = 1, shootDelay, numAmmoItemsInGun = 1, shootSoundLength, warmupSoundLength = 20, loopedSoundLength = 20, idleSoundLength;
    public float standBackDist = 1.5F, topViewLimit = -60, bottomViewLimit = 30, sideViewLimit = 45, pivotHeight = 0.375F, shieldDamageAbsorption, minigunStartSpeed = 15, knockback, bulletSpread, damage, meleeDamage = 1, bulletSpeed = 5, moveSpeedModifier = 1, knockbackModifier, modelScale = 1, zoomLevel = 1, FOVFactor = 1.5F;
    public ArrayList<AttachmentType> allowedAttachments = new ArrayList<AttachmentType>();
    public boolean hasScopeOverlay, canShootUnderwater = true, canForceReload = true, usableByPlayers = true, usableByMechas = true, oneHanded, consumeGunUponUse, shield, distortSound = true, useLoopingSounds, deployable, allowAllAttachments, allowBarrelAttachments, allowScopeAttachments, allowStockAttachments, allowGripAttachments;
    public ArrayList<Paintjob> paintjobs = new ArrayList<Paintjob>();
    public Paintjob defaultPaintjob;
    public static HashMap<String, GunType> guns = new HashMap<String, GunType>();
    public static ArrayList<GunType> gunList = new ArrayList<GunType>();

    public GunType(TypeFile file)
    {
        super(file);
    }

    @Override
    public void postRead(TypeFile file)
    {
        gunList.add(this);
        guns.put(shortName, this);
        defaultPaintjob = new Paintjob(iconPath, texture, new ItemStack[0]);
        ArrayList<Paintjob> newPaintjobList = new ArrayList<Paintjob>();
        newPaintjobList.add(defaultPaintjob);
        newPaintjobList.addAll(paintjobs);
        paintjobs = newPaintjobList;
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("Damage"))
                damage = Float.parseFloat(split[1]);
            else if(split[0].equals("MeleeDamage"))
            {
                meleeDamage = Float.parseFloat(split[1]);
                if(meleeDamage > 0)
                    secondaryFunction = EnumSecondaryFunction.MELEE;
            }
            else if(split[0].equals("CanForceReload"))
                canForceReload = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("ReloadTime"))
                reloadTime = Integer.parseInt(split[1]);
            else if(split[0].equals("Recoil"))
                recoil = Integer.parseInt(split[1]);
            else if(split[0].equals("Knockback"))
                knockback = Float.parseFloat(split[1]);
            else if(split[0].equals("Accuracy") || split[0].equals("Spread"))
                bulletSpread = Float.parseFloat(split[1]);
            else if(split[0].equals("NumBullets"))
                numBullets = Integer.parseInt(split[1]);
            else if(split[0].equals("ConsumeGunOnUse"))
                consumeGunUponUse = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("DropItemOnShoot"))
                dropItemOnShoot = split[1];
            else if(split[0].equals("NumBurstRounds"))
                numBurstRounds = Integer.parseInt(split[1]);
            else if(split[0].equals("MinigunStartSpeed"))
                minigunStartSpeed = Float.parseFloat(split[1]);
            else if(split[0].equals("ShootDelay"))
                shootDelay = Integer.parseInt(split[1]);
            else if(split[0].equals("SoundLength"))
                shootSoundLength = Integer.parseInt(split[1]);
            else if(split[0].equals("DistortSound"))
                distortSound = split[1].equals("True");
            else if(split[0].equals("ShootSound"))
                shootSound = split[1];
            else if(split[0].equals("ReloadSound"))
                reloadSound = split[1];
            else if(split[0].equals("IdleSound"))
                idleSound = split[1];
            else if(split[0].equals("IdleSoundLength"))
                idleSoundLength = Integer.parseInt(split[1]);
            else if(split[0].equals("MeleeSound"))
                meleeSound = split[1];
            else if(split[0].equals("WarmupSound"))
                warmupSound = split[1];
            else if(split[0].equals("WarmupSoundLength"))
                warmupSoundLength = Integer.parseInt(split[1]);
            else if(split[0].equals("LoopedSound") || split[0].equals("SpinSound"))
            {
                loopedSound = split[1];
                useLoopingSounds = true;
            }
            else if(split[0].equals("LoopedSoundLength") || split[0].equals("SpinSoundLength"))
                loopedSoundLength = Integer.parseInt(split[1]);
            else if(split[0].equals("CooldownSound"))
                cooldownSound = split[1];
            else if(split[0].equals("Mode"))
                mode = EnumFireMode.getFireMode(split[1]);
            else if(split[0].equals("Scope"))
            {
                hasScopeOverlay = true;
                if(split[1].equals("None"))
                    hasScopeOverlay = false;
                else
                    defaultScopeTexture = split[1];
            }
            else if(split[0].equals("ZoomLevel"))
            {
                zoomLevel = Float.parseFloat(split[1]);
                if(zoomLevel > 1)
                    secondaryFunction = EnumSecondaryFunction.ZOOM;
            }
            else if(split[0].equals("FOVZoomLevel"))
            {
                FOVFactor = Float.parseFloat(split[1]);
                if(FOVFactor > 1)
                    secondaryFunction = EnumSecondaryFunction.ADS_ZOOM;
            }
            else if(split[0].equals("Deployable"))
                deployable = split[1].equals("True");
            else if(split[0].equals("ModelScale"))
                modelScale = Float.parseFloat(split[1]);
            else if(split[0].equals("Texture"))
                texture = split[1];
            else if(split[0].equals("DeployedTexture"))
                deployableTexture = split[1];
            else if(split[0].equals("StandBackDistance"))
                standBackDist = Float.parseFloat(split[1]);
            else if(split[0].equals("TopViewLimit"))
                topViewLimit = -Float.parseFloat(split[1]);
            else if(split[0].equals("BottomViewLimit"))
                bottomViewLimit = Float.parseFloat(split[1]);
            else if(split[0].equals("SideViewLimit"))
                sideViewLimit = Float.parseFloat(split[1]);
            else if(split[0].equals("PivotHeight"))
                pivotHeight = Float.parseFloat(split[1]);
            else if(split[0].equals("Ammo"))
            {
                ShootableType type = ShootableType.getShootableType(split[1]);
                if(type != null)
                    ammo.add(type);
            }
            else if(split[0].equals("NumAmmoSlots") || split[0].equals("NumAmmoItemsInGun") || split[0].equals("LoadIntoGun"))
                numAmmoItemsInGun = Integer.parseInt(split[1]);
            else if(split[0].equals("BulletSpeed"))
                bulletSpeed = Float.parseFloat(split[1]);
            else if(split[0].equals("CanShootUnderwater"))
                canShootUnderwater = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("OneHanded"))
                oneHanded = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("SecondaryFunction"))
                secondaryFunction = EnumSecondaryFunction.get(split[1]);
            else if(split[0].equals("UsableByPlayers"))
                usableByPlayers = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("UsableByMechas"))
                usableByMechas = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("UseCustomMelee") && Boolean.parseBoolean(split[1]))
                secondaryFunction = EnumSecondaryFunction.CUSTOM_MELEE;
            else if(split[0].equals("MeleeTime"))
                meleeTime = Integer.parseInt(split[1]);
            else if(split[0].equals("AddNode"))
            {
                meleePath.add(new Vector3f(Float.parseFloat(split[1]) / 16, Float.parseFloat(split[2]) / 16, Float.parseFloat(split[3]) / 16));
                meleePathAngles.add(new Vector3f(Float.parseFloat(split[4]), Float.parseFloat(split[5]), Float.parseFloat(split[6])));
            }
            else if(split[0].equals("MeleeDamagePoint") || split[0].equals("MeleeDamageOffset"))
            {
                meleeDamagePoints.add(new Vector3f(Float.parseFloat(split[1]) / 16, Float.parseFloat(split[2]) / 16, Float.parseFloat(split[3]) / 16));
            }
            else if(split[0].equals("MoveSpeedModifier") || split[0].equals("Slowness"))
                moveSpeedModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("KnockbackReduction") || split[0].equals("KnockbackModifier"))
                knockbackModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("AllowAllAttachments"))
                allowAllAttachments = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("AllowAttachments"))
                for(int i = 1; i < split.length; i++)
                    allowedAttachments.add(AttachmentType.getAttachment(split[i]));
            else if(split[0].equals("AllowBarrelAttachments"))
                allowBarrelAttachments = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("AllowScopeAttachments"))
                allowScopeAttachments = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("AllowStockAttachments"))
                allowStockAttachments = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("AllowGripAttachments"))
                allowGripAttachments = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("NumGenericAttachmentSlots"))
                numGenericAttachmentSlots = Integer.parseInt(split[1]);
            else if(split[0].toLowerCase().equals("paintjob"))
            {
                ItemStack[] dyeStacks = new ItemStack[(split.length - 3) / 2];
                for(int i = 0; i < (split.length - 3) / 2; i++)
                    dyeStacks[i] = new ItemStack(Items.dye, Integer.parseInt(split[i * 2 + 4]), getDyeDamageValue(split[i * 2 + 3]));
                paintjobs.add(new Paintjob(split[1], split[2], dyeStacks));
            }
            else if(split[0].toLowerCase().equals("shield"))
            {
                shield = true;
                shieldDamageAbsorption = Float.parseFloat(split[1]);
                shieldOrigin = new Vector3f(Float.parseFloat(split[2]) / 16, Float.parseFloat(split[3]) / 16, Float.parseFloat(split[4]) / 16);
                shieldDimensions = new Vector3f(Float.parseFloat(split[5]) / 16, Float.parseFloat(split[6]) / 16, Float.parseFloat(split[7]) / 16);
            }
        }
        catch(Exception e)
        {
            System.out.println("Reading gun file failed.");
            e.printStackTrace();
        }

    }

    private int getDyeDamageValue(String dyeName)
    {
        int damage = -1;
        for(int i = 0; i < ItemDye.field_150923_a.length; i++)
            if(ItemDye.field_150923_a[i].equals(dyeName))
                damage = i;
        if(damage == -1)
            WatchDogs.print("Failed to find dye colour : " + dyeName + " while adding " + contentPack);

        return damage;
    }

    public boolean isAmmo(ShootableType type)
    {
        return ammo.contains(type);
    }

    public boolean isAmmo(ItemStack stack)
    {
        if(stack == null)
            return false;
        else if(stack.getItem() instanceof ItemBullet)
            return isAmmo(((ItemBullet)stack.getItem()).type);
        else if(stack.getItem() instanceof ItemGrenade)
            return isAmmo(((ItemGrenade)stack.getItem()).type);
        return false;
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
        return defaultScopeTexture;
    }

    @Override
    public float getFOVFactor()
    {
        return FOVFactor;
    }

    public IScope getCurrentScope(ItemStack gunStack)
    {
        IScope attachedScope = getScope(gunStack);
        return attachedScope == null ? this : attachedScope;
    }

    public ArrayList<AttachmentType> getCurrentAttachments(ItemStack gun)
    {
        checkForTags(gun);
        ArrayList<AttachmentType> attachments = new ArrayList<AttachmentType>();
        for(int i = 0; i < numGenericAttachmentSlots; i++)
            appendToList(gun, "generic_" + i, attachments);
        appendToList(gun, "barrel", attachments);
        appendToList(gun, "scope", attachments);
        appendToList(gun, "stock", attachments);
        appendToList(gun, "grip", attachments);
        return attachments;
    }

    private void appendToList(ItemStack gun, String name, ArrayList<AttachmentType> attachments)
    {
        AttachmentType type = getAttachment(gun, name);
        if(type != null)
            attachments.add(type);
    }

    public AttachmentType getBarrel(ItemStack gun)
    {
        return getAttachment(gun, "barrel");
    }

    public AttachmentType getScope(ItemStack gun)
    {
        return getAttachment(gun, "scope");
    }

    public AttachmentType getStock(ItemStack gun)
    {
        return getAttachment(gun, "stock");
    }

    public AttachmentType getGrip(ItemStack gun)
    {
        return getAttachment(gun, "grip");
    }

    public AttachmentType getGeneric(ItemStack gun, int i)
    {
        return getAttachment(gun, "generic_" + i);
    }

    public ItemStack getBarrelItemStack(ItemStack gun)
    {
        return getAttachmentItemStack(gun, "barrel");
    }

    public ItemStack getScopeItemStack(ItemStack gun)
    {
        return getAttachmentItemStack(gun, "scope");
    }

    public ItemStack getStockItemStack(ItemStack gun)
    {
        return getAttachmentItemStack(gun, "stock");
    }

    public ItemStack getGripItemStack(ItemStack gun)
    {
        return getAttachmentItemStack(gun, "grip");
    }

    public ItemStack getGenericItemStack(ItemStack gun, int i)
    {
        return getAttachmentItemStack(gun, "generic_" + i);
    }

    public AttachmentType getAttachment(ItemStack gun, String name)
    {
        checkForTags(gun);
        return AttachmentType.getFromNBT(gun.stackTagCompound.getCompoundTag("attachments").getCompoundTag(name));
    }

    public ItemStack getAttachmentItemStack(ItemStack gun, String name)
    {
        checkForTags(gun);
        return ItemStack.loadItemStackFromNBT(gun.stackTagCompound.getCompoundTag("attachments").getCompoundTag(name));
    }

    private void checkForTags(ItemStack gun)
    {
        if(!gun.hasTagCompound())
            gun.stackTagCompound = new NBTTagCompound();
        if(!gun.stackTagCompound.hasKey("attachments"))
        {
            NBTTagCompound attachmentTags = new NBTTagCompound();
            for(int i = 0; i < numGenericAttachmentSlots; i++)
                attachmentTags.setTag("generic_" + i, new NBTTagCompound());
            attachmentTags.setTag("barrel", new NBTTagCompound());
            attachmentTags.setTag("scope", new NBTTagCompound());
            attachmentTags.setTag("stock", new NBTTagCompound());
            attachmentTags.setTag("grip", new NBTTagCompound());
            gun.stackTagCompound.setTag("attachments", attachmentTags);
        }
    }

    public float getMeleeDamage(ItemStack stack)
    {
        float stackMeleeDamage = meleeDamage;
        for(AttachmentType attachment : getCurrentAttachments(stack))
            stackMeleeDamage *= attachment.meleeDamageMultiplier;
        return stackMeleeDamage;
    }

    public float getDamage(ItemStack stack)
    {
        float stackDamage = damage;
        for(AttachmentType attachment : getCurrentAttachments(stack))
            stackDamage *= attachment.damageMultiplier;
        return stackDamage;
    }

    public float getSpread(ItemStack stack)
    {
        float stackSpread = bulletSpread;
        for(AttachmentType attachment : getCurrentAttachments(stack))
            stackSpread *= attachment.spreadMultiplier;
        return stackSpread;
    }

    public float getRecoil(ItemStack stack)
    {
        float stackRecoil = recoil;
        for(AttachmentType attachment : getCurrentAttachments(stack))
            stackRecoil *= attachment.recoilMultiplier;
        return stackRecoil;
    }

    public float getBulletSpeed(ItemStack stack)
    {
        float stackBulletSpeed = bulletSpeed;
        for(AttachmentType attachment : getCurrentAttachments(stack))
            stackBulletSpeed *= attachment.bulletSpeedMultiplier;
        return stackBulletSpeed;
    }

    public float getReloadTime(ItemStack stack)
    {
        float stackReloadTime = reloadTime;
        for(AttachmentType attachment : getCurrentAttachments(stack))
            stackReloadTime *= attachment.reloadTimeMultiplier;
        return stackReloadTime;
    }

    public EnumFireMode getFireMode(ItemStack stack)
    {
        for(AttachmentType attachment : getCurrentAttachments(stack))
            if(attachment.modeOverride != null)
                return attachment.modeOverride;
        return mode;
    }

    public static GunType getGun(String s)
    {
        return guns.get(s);
    }

    public Paintjob getPaintjob(String s)
    {
        for(Paintjob paintjob : paintjobs)
            if(paintjob.iconName.equals(s))
                return paintjob;
        return defaultPaintjob;
    }
}