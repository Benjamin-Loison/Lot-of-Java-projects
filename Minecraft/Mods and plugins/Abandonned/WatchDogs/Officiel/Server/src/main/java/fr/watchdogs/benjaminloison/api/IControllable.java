package fr.watchdogs.benjaminloison.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public interface IControllable
{
    public void onMouseMoved(int deltaX, int deltaY);

    public boolean pressKey(int key, EntityPlayer player);

    public void updateKeyHeldState(int key, boolean held);

    public Entity getControllingEntity();

    public boolean isDead();

    public float getPlayerRoll();

    public float getCameraDistance();
}