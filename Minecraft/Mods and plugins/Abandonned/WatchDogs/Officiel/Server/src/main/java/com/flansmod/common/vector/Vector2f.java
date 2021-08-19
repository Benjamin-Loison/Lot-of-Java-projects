package com.flansmod.common.vector;

import java.io.Serializable;
import java.nio.FloatBuffer;

public class Vector2f extends Vector implements Serializable, ReadableVector2f, WritableVector2f
{
    private static final long serialVersionUID = 1;
    public float x, y;

    public Vector2f()
    {
        super();
    }

    public Vector2f(ReadableVector2f src)
    {
        set(src);
    }

    public Vector2f(float x, float y)
    {
        set(x, y);
    }

    @Override
    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2f set(ReadableVector2f src)
    {
        x = src.getX();
        y = src.getY();
        return this;
    }

    @Override
    public float lengthSquared()
    {
        return x * x + y * y;
    }

    public Vector2f translate(float x, float y)
    {
        this.x += x;
        this.y += y;
        return this;
    }

    @Override
    public Vector negate()
    {
        x = -x;
        y = -y;
        return this;
    }

    public Vector2f negate(Vector2f dest)
    {
        if(dest == null)
            dest = new Vector2f();
        dest.x = -x;
        dest.y = -y;
        return dest;
    }

    public Vector2f normalise(Vector2f dest)
    {
        float l = length();
        if(dest == null)
            dest = new Vector2f(x / l, y / l);
        else
            dest.set(x / l, y / l);
        return dest;
    }

    public static float dot(Vector2f left, Vector2f right)
    {
        return left.x * right.x + left.y * right.y;
    }

    public static float angle(Vector2f a, Vector2f b)
    {
        float dls = dot(a, b) / (a.length() * b.length());
        if(dls < -1f)
            dls = -1f;
        else if(dls > 1.0f)
            dls = 1.0f;
        return (float)Math.acos(dls);
    }

    public static Vector2f add(Vector2f left, Vector2f right, Vector2f dest)
    {
        if(dest == null)
            return new Vector2f(left.x + right.x, left.y + right.y);
        else
        {
            dest.set(left.x + right.x, left.y + right.y);
            return dest;
        }
    }

    public static Vector2f sub(Vector2f left, Vector2f right, Vector2f dest)
    {
        if(dest == null)
            return new Vector2f(left.x - right.x, left.y - right.y);
        else
        {
            dest.set(left.x - right.x, left.y - right.y);
            return dest;
        }
    }

    @Override
    public Vector store(FloatBuffer buf)
    {
        buf.put(x);
        buf.put(y);
        return this;
    }

    @Override
    public Vector load(FloatBuffer buf)
    {
        x = buf.get();
        y = buf.get();
        return this;
    }

    @Override
    public Vector scale(float scale)
    {
        x *= scale;
        y *= scale;
        return this;
    }

    @Override
    public String toString()
    {
        return "Vector2f[" + x + ", " + y + ']';
    }

    @Override
    public final float getX()
    {
        return x;
    }

    @Override
    public final float getY()
    {
        return y;
    }

    @Override
    public final void setX(float x)
    {
        this.x = x;
    }

    @Override
    public final void setY(float y)
    {
        this.y = y;
    }
}