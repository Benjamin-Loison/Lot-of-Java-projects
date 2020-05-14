package com.flansmod.common.vector;

import java.io.Serializable;
import java.nio.FloatBuffer;

public class Matrix4f extends Matrix implements Serializable
{
    private static final long serialVersionUID = 1;
    public float m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33;

    public Matrix4f()
    {
        super();
        setIdentity();
    }

    public Matrix4f(final Matrix4f src)
    {
        super();
        load(src);
    }

    @Override
    public String toString()
    {
        return String.valueOf(m00) + ' ' + m10 + ' ' + m20 + ' ' + m30 + '\n' + m01 + ' ' + m11 + ' ' + m21 + ' ' + m31 + '\n' + m02 + ' ' + m12 + ' ' + m22 + ' ' + m32 + '\n' + m03 + ' ' + m13 + ' ' + m23 + ' ' + m33 + '\n';
    }

    @Override
    public Matrix setIdentity()
    {
        return setIdentity(this);
    }

    public static Matrix4f setIdentity(Matrix4f m)
    {
        m.m00 = 1;
        m.m01 = 0;
        m.m02 = 0;
        m.m03 = 0;
        m.m10 = 0;
        m.m11 = 1;
        m.m12 = 0;
        m.m13 = 0;
        m.m20 = 0;
        m.m21 = 0;
        m.m22 = 1;
        m.m23 = 0;
        m.m30 = 0;
        m.m31 = 0;
        m.m32 = 0;
        m.m33 = 1;
        return m;
    }

    @Override
    public Matrix setZero()
    {
        return setZero(this);
    }

    public static Matrix4f setZero(Matrix4f m)
    {
        m.m00 = 0;
        m.m01 = 0;
        m.m02 = 0;
        m.m03 = 0;
        m.m10 = 0;
        m.m11 = 0;
        m.m12 = 0;
        m.m13 = 0;
        m.m20 = 0;
        m.m21 = 0;
        m.m22 = 0;
        m.m23 = 0;
        m.m30 = 0;
        m.m31 = 0;
        m.m32 = 0;
        m.m33 = 0;
        return m;
    }

    public Matrix4f load(Matrix4f src)
    {
        return load(src, this);
    }

    public static Matrix4f load(Matrix4f src, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        dest.m00 = src.m00;
        dest.m01 = src.m01;
        dest.m02 = src.m02;
        dest.m03 = src.m03;
        dest.m10 = src.m10;
        dest.m11 = src.m11;
        dest.m12 = src.m12;
        dest.m13 = src.m13;
        dest.m20 = src.m20;
        dest.m21 = src.m21;
        dest.m22 = src.m22;
        dest.m23 = src.m23;
        dest.m30 = src.m30;
        dest.m31 = src.m31;
        dest.m32 = src.m32;
        dest.m33 = src.m33;
        return dest;
    }

    @Override
    public Matrix load(FloatBuffer buf)
    {
        m00 = buf.get();
        m01 = buf.get();
        m02 = buf.get();
        m03 = buf.get();
        m10 = buf.get();
        m11 = buf.get();
        m12 = buf.get();
        m13 = buf.get();
        m20 = buf.get();
        m21 = buf.get();
        m22 = buf.get();
        m23 = buf.get();
        m30 = buf.get();
        m31 = buf.get();
        m32 = buf.get();
        m33 = buf.get();
        return this;
    }

    @Override
    public Matrix loadTranspose(FloatBuffer buf)
    {
        m00 = buf.get();
        m10 = buf.get();
        m20 = buf.get();
        m30 = buf.get();
        m01 = buf.get();
        m11 = buf.get();
        m21 = buf.get();
        m31 = buf.get();
        m02 = buf.get();
        m12 = buf.get();
        m22 = buf.get();
        m32 = buf.get();
        m03 = buf.get();
        m13 = buf.get();
        m23 = buf.get();
        m33 = buf.get();
        return this;
    }

    @Override
    public Matrix store(FloatBuffer buf)
    {
        buf.put(m00);
        buf.put(m01);
        buf.put(m02);
        buf.put(m03);
        buf.put(m10);
        buf.put(m11);
        buf.put(m12);
        buf.put(m13);
        buf.put(m20);
        buf.put(m21);
        buf.put(m22);
        buf.put(m23);
        buf.put(m30);
        buf.put(m31);
        buf.put(m32);
        buf.put(m33);
        return this;
    }

    @Override
    public Matrix storeTranspose(FloatBuffer buf)
    {
        buf.put(m00);
        buf.put(m10);
        buf.put(m20);
        buf.put(m30);
        buf.put(m01);
        buf.put(m11);
        buf.put(m21);
        buf.put(m31);
        buf.put(m02);
        buf.put(m12);
        buf.put(m22);
        buf.put(m32);
        buf.put(m03);
        buf.put(m13);
        buf.put(m23);
        buf.put(m33);
        return this;
    }

    public Matrix store3f(FloatBuffer buf)
    {
        buf.put(m00);
        buf.put(m01);
        buf.put(m02);
        buf.put(m10);
        buf.put(m11);
        buf.put(m12);
        buf.put(m20);
        buf.put(m21);
        buf.put(m22);
        return this;
    }

    public static Matrix4f add(Matrix4f left, Matrix4f right, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        dest.m00 = left.m00 + right.m00;
        dest.m01 = left.m01 + right.m01;
        dest.m02 = left.m02 + right.m02;
        dest.m03 = left.m03 + right.m03;
        dest.m10 = left.m10 + right.m10;
        dest.m11 = left.m11 + right.m11;
        dest.m12 = left.m12 + right.m12;
        dest.m13 = left.m13 + right.m13;
        dest.m20 = left.m20 + right.m20;
        dest.m21 = left.m21 + right.m21;
        dest.m22 = left.m22 + right.m22;
        dest.m23 = left.m23 + right.m23;
        dest.m30 = left.m30 + right.m30;
        dest.m31 = left.m31 + right.m31;
        dest.m32 = left.m32 + right.m32;
        dest.m33 = left.m33 + right.m33;
        return dest;
    }

    public static Matrix4f sub(Matrix4f left, Matrix4f right, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        dest.m00 = left.m00 - right.m00;
        dest.m01 = left.m01 - right.m01;
        dest.m02 = left.m02 - right.m02;
        dest.m03 = left.m03 - right.m03;
        dest.m10 = left.m10 - right.m10;
        dest.m11 = left.m11 - right.m11;
        dest.m12 = left.m12 - right.m12;
        dest.m13 = left.m13 - right.m13;
        dest.m20 = left.m20 - right.m20;
        dest.m21 = left.m21 - right.m21;
        dest.m22 = left.m22 - right.m22;
        dest.m23 = left.m23 - right.m23;
        dest.m30 = left.m30 - right.m30;
        dest.m31 = left.m31 - right.m31;
        dest.m32 = left.m32 - right.m32;
        dest.m33 = left.m33 - right.m33;
        return dest;
    }

    public static Matrix4f mul(Matrix4f left, Matrix4f right, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        float m00 = left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02 + left.m30 * right.m03, m01 = left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02 + left.m31 * right.m03, m02 = left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02 + left.m32 * right.m03,
                m03 = left.m03 * right.m00 + left.m13 * right.m01 + left.m23 * right.m02 + left.m33 * right.m03, m10 = left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12 + left.m30 * right.m13, m11 = left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12 + left.m31 * right.m13,
                m12 = left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12 + left.m32 * right.m13, m13 = left.m03 * right.m10 + left.m13 * right.m11 + left.m23 * right.m12 + left.m33 * right.m13, m20 = left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22 + left.m30 * right.m23,
                m21 = left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22 + left.m31 * right.m23, m22 = left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22 + left.m32 * right.m23, m23 = left.m03 * right.m20 + left.m13 * right.m21 + left.m23 * right.m22 + left.m33 * right.m23,
                m30 = left.m00 * right.m30 + left.m10 * right.m31 + left.m20 * right.m32 + left.m30 * right.m33, m31 = left.m01 * right.m30 + left.m11 * right.m31 + left.m21 * right.m32 + left.m31 * right.m33, m32 = left.m02 * right.m30 + left.m12 * right.m31 + left.m22 * right.m32 + left.m32 * right.m33,
                m33 = left.m03 * right.m30 + left.m13 * right.m31 + left.m23 * right.m32 + left.m33 * right.m33;
        dest.m00 = m00;
        dest.m01 = m01;
        dest.m02 = m02;
        dest.m03 = m03;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m12 = m12;
        dest.m13 = m13;
        dest.m20 = m20;
        dest.m21 = m21;
        dest.m22 = m22;
        dest.m23 = m23;
        dest.m30 = m30;
        dest.m31 = m31;
        dest.m32 = m32;
        dest.m33 = m33;
        return dest;
    }

    public static Vector4f transform(Matrix4f left, Vector4f right, Vector4f dest)
    {
        if(dest == null)
            dest = new Vector4f();
        float x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z + left.m30 * right.w, y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z + left.m31 * right.w, z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z + left.m32 * right.w, w = left.m03 * right.x + left.m13 * right.y + left.m23 * right.z + left.m33 * right.w;
        dest.x = x;
        dest.y = y;
        dest.z = z;
        dest.w = w;
        return dest;
    }

    @Override
    public Matrix transpose()
    {
        return transpose(this);
    }

    public Matrix4f translate(Vector2f vec)
    {
        return translate(vec, this);
    }

    public Matrix4f translate(Vector3f vec)
    {
        return translate(vec, this);
    }

    public Matrix4f scale(Vector3f vec)
    {
        return scale(vec, this, this);
    }

    public static Matrix4f scale(Vector3f vec, Matrix4f src, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        dest.m00 = src.m00 * vec.x;
        dest.m01 = src.m01 * vec.x;
        dest.m02 = src.m02 * vec.x;
        dest.m03 = src.m03 * vec.x;
        dest.m10 = src.m10 * vec.y;
        dest.m11 = src.m11 * vec.y;
        dest.m12 = src.m12 * vec.y;
        dest.m13 = src.m13 * vec.y;
        dest.m20 = src.m20 * vec.z;
        dest.m21 = src.m21 * vec.z;
        dest.m22 = src.m22 * vec.z;
        dest.m23 = src.m23 * vec.z;
        return dest;
    }

    public Matrix4f rotate(float angle, Vector3f axis)
    {
        return rotate(angle, axis, this);
    }

    public Matrix4f rotate(float angle, Vector3f axis, Matrix4f dest)
    {
        return rotate(angle, axis, this, dest);
    }

    public static Matrix4f rotate(float angle, Vector3f axis, Matrix4f src, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        float c = (float)Math.cos(angle), s = (float)Math.sin(angle), oneminusc = 1 - c, xy = axis.x * axis.y, yz = axis.y * axis.z, xz = axis.x * axis.z, xs = axis.x * s, ys = axis.y * s, zs = axis.z * s, f00 = axis.x * axis.x * oneminusc + c, f01 = xy * oneminusc + zs, f02 = xz * oneminusc - ys, f10 = xy * oneminusc - zs, f11 = axis.y * axis.y * oneminusc + c, f12 = yz * oneminusc + xs,
                f20 = xz * oneminusc + ys, f21 = yz * oneminusc - xs, f22 = axis.z * axis.z * oneminusc + c, t00 = src.m00 * f00 + src.m10 * f01 + src.m20 * f02, t01 = src.m01 * f00 + src.m11 * f01 + src.m21 * f02, t02 = src.m02 * f00 + src.m12 * f01 + src.m22 * f02, t03 = src.m03 * f00 + src.m13 * f01 + src.m23 * f02, t10 = src.m00 * f10 + src.m10 * f11 + src.m20 * f12,
                t11 = src.m01 * f10 + src.m11 * f11 + src.m21 * f12, t12 = src.m02 * f10 + src.m12 * f11 + src.m22 * f12, t13 = src.m03 * f10 + src.m13 * f11 + src.m23 * f12;
        dest.m20 = src.m00 * f20 + src.m10 * f21 + src.m20 * f22;
        dest.m21 = src.m01 * f20 + src.m11 * f21 + src.m21 * f22;
        dest.m22 = src.m02 * f20 + src.m12 * f21 + src.m22 * f22;
        dest.m23 = src.m03 * f20 + src.m13 * f21 + src.m23 * f22;
        dest.m00 = t00;
        dest.m01 = t01;
        dest.m02 = t02;
        dest.m03 = t03;
        dest.m10 = t10;
        dest.m11 = t11;
        dest.m12 = t12;
        dest.m13 = t13;
        return dest;
    }

    public Matrix4f translate(Vector3f vec, Matrix4f dest)
    {
        return translate(vec, this, dest);
    }

    public static Matrix4f translate(Vector3f vec, Matrix4f src, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        dest.m30 += src.m00 * vec.x + src.m10 * vec.y + src.m20 * vec.z;
        dest.m31 += src.m01 * vec.x + src.m11 * vec.y + src.m21 * vec.z;
        dest.m32 += src.m02 * vec.x + src.m12 * vec.y + src.m22 * vec.z;
        dest.m33 += src.m03 * vec.x + src.m13 * vec.y + src.m23 * vec.z;
        return dest;
    }

    public Matrix4f translate(Vector2f vec, Matrix4f dest)
    {
        return translate(vec, this, dest);
    }

    public static Matrix4f translate(Vector2f vec, Matrix4f src, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        dest.m30 += src.m00 * vec.x + src.m10 * vec.y;
        dest.m31 += src.m01 * vec.x + src.m11 * vec.y;
        dest.m32 += src.m02 * vec.x + src.m12 * vec.y;
        dest.m33 += src.m03 * vec.x + src.m13 * vec.y;
        return dest;
    }

    public Matrix4f transpose(Matrix4f dest)
    {
        return transpose(this, dest);
    }

    public static Matrix4f transpose(Matrix4f src, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        dest.m00 = src.m00;
        dest.m01 = src.m10;
        dest.m02 = src.m20;
        dest.m03 = src.m30;
        dest.m10 = src.m01;
        dest.m11 = src.m11;
        dest.m12 = src.m21;
        dest.m13 = src.m31;
        dest.m20 = src.m02;
        dest.m21 = src.m12;
        dest.m22 = src.m22;
        dest.m23 = src.m32;
        dest.m30 = src.m03;
        dest.m31 = src.m13;
        dest.m32 = src.m23;
        dest.m33 = src.m33;
        return dest;
    }

    @Override
    public float determinant()
    {
        float f = m00 * ((m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32) - m13 * m22 * m31 - m11 * m23 * m32 - m12 * m21 * m33);
        f -= m01 * ((m10 * m22 * m33 + m12 * m23 * m30 + m13 * m20 * m32) - m13 * m22 * m30 - m10 * m23 * m32 - m12 * m20 * m33);
        f += m02 * ((m10 * m21 * m33 + m11 * m23 * m30 + m13 * m20 * m31) - m13 * m21 * m30 - m10 * m23 * m31 - m11 * m20 * m33);
        f -= m03 * ((m10 * m21 * m32 + m11 * m22 * m30 + m12 * m20 * m31) - m12 * m21 * m30 - m10 * m22 * m31 - m11 * m20 * m32);
        return f;
    }

    private static float determinant3x3(float t00, float t01, float t02, float t10, float t11, float t12, float t20, float t21, float t22)
    {
        return t00 * (t11 * t22 - t12 * t21) + t01 * (t12 * t20 - t10 * t22) + t02 * (t10 * t21 - t11 * t20);
    }

    @Override
    public Matrix invert()
    {
        return invert(this, this);
    }

    public static Matrix4f invert(Matrix4f src, Matrix4f dest)
    {
        float determinant = src.determinant();

        if(determinant != 0)
        {
            if(dest == null)
                dest = new Matrix4f();
            float determinant_inv = 1f / determinant;
            dest.m00 = determinant3x3(src.m11, src.m12, src.m13, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33) * determinant_inv;
            dest.m11 = determinant3x3(src.m00, src.m02, src.m03, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33) * determinant_inv;
            dest.m22 = determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m30, src.m31, src.m33) * determinant_inv;
            dest.m33 = determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m20, src.m21, src.m22) * determinant_inv;
            dest.m01 = -determinant3x3(src.m01, src.m02, src.m03, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33) * determinant_inv;
            dest.m10 = -determinant3x3(src.m10, src.m12, src.m13, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33) * determinant_inv;
            dest.m20 = determinant3x3(src.m10, src.m11, src.m13, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33) * determinant_inv;
            dest.m02 = determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m31, src.m32, src.m33) * determinant_inv;
            dest.m12 = -determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m30, src.m32, src.m33) * determinant_inv;
            dest.m21 = -determinant3x3(src.m00, src.m01, src.m03, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33) * determinant_inv;
            dest.m03 = -determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m21, src.m22, src.m23) * determinant_inv;
            dest.m30 = -determinant3x3(src.m10, src.m11, src.m12, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32) * determinant_inv;
            dest.m13 = determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m20, src.m22, src.m23) * determinant_inv;
            dest.m31 = determinant3x3(src.m00, src.m01, src.m02, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32) * determinant_inv;
            dest.m32 = -determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m30, src.m31, src.m32) * determinant_inv;
            dest.m23 = -determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m20, src.m21, src.m23) * determinant_inv;
            return dest;
        }
        else
            return null;
    }

    @Override
    public Matrix negate()
    {
        return negate(this);
    }

    public Matrix4f negate(Matrix4f dest)
    {
        return negate(this, dest);
    }

    public static Matrix4f negate(Matrix4f src, Matrix4f dest)
    {
        if(dest == null)
            dest = new Matrix4f();
        dest.m00 = -src.m00;
        dest.m01 = -src.m01;
        dest.m02 = -src.m02;
        dest.m03 = -src.m03;
        dest.m10 = -src.m10;
        dest.m11 = -src.m11;
        dest.m12 = -src.m12;
        dest.m13 = -src.m13;
        dest.m20 = -src.m20;
        dest.m21 = -src.m21;
        dest.m22 = -src.m22;
        dest.m23 = -src.m23;
        dest.m30 = -src.m30;
        dest.m31 = -src.m31;
        dest.m32 = -src.m32;
        dest.m33 = -src.m33;
        return dest;
    }
}