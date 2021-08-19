package com.flansmod.common.vector;

import java.io.Serializable;
import java.nio.FloatBuffer;

public abstract class Vector implements Serializable, ReadableVector
{
    protected Vector()
    {
        super();
    }

    @Override
    public final float length()
    {
        return (float)Math.sqrt(lengthSquared());
    }

    @Override
    public abstract float lengthSquared();

    public abstract Vector load(FloatBuffer buf);

    public abstract Vector negate();

    public final Vector normalise()
    {
        float len = length();
        if(len != 0)
            return scale(1 / len);
        else
            throw new IllegalStateException("Zero length com.flansmod.common.vector");
    }

    @Override
    public abstract Vector store(FloatBuffer buf);

    public abstract Vector scale(float scale);
}