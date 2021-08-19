package com.flansmod.common.vector;

import java.nio.FloatBuffer;

public interface ReadableVector
{
    float length();

    float lengthSquared();

    Vector store(FloatBuffer buf);
}