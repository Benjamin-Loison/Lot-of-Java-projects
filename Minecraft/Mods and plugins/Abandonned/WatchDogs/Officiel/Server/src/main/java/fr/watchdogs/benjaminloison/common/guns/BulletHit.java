package fr.watchdogs.benjaminloison.common.guns;

public class BulletHit implements Comparable<BulletHit>
{
    public float intersectTime;

    public BulletHit(float f)
    {
        intersectTime = f;
    }

    @Override
    public int compareTo(BulletHit other)
    {
        if(intersectTime < other.intersectTime)
            return -1;
        else if(intersectTime > other.intersectTime)
            return 1;
        return 0;
    }
}