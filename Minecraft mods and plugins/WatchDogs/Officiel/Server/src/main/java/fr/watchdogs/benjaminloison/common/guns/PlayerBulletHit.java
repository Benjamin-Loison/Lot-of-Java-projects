package fr.watchdogs.benjaminloison.common.guns;

public class PlayerBulletHit extends BulletHit
{
    public PlayerHitbox hitbox;

    public PlayerBulletHit(PlayerHitbox box, float f)
    {
        super(f);
        hitbox = box;
    }
}