package fr.watchdogs.benjaminloison.api;

import com.flansmod.common.vector.Matrix4f;
import com.flansmod.common.vector.Vector3f;

public class RotatedAxes
{
    private float rotationYaw, rotationPitch, rotationRoll;
    private Matrix4f rotationMatrix;

    public RotatedAxes()
    {
        rotationMatrix = new Matrix4f();
    }

    public RotatedAxes(Matrix4f mat)
    {
        rotationMatrix = mat;
        convertMatrixToAngles();
    }

    public RotatedAxes(float yaw, float pitch, float roll)
    {
        setAngles(yaw, pitch, roll);
    }

    @Override
    public RotatedAxes clone()
    {
        RotatedAxes newAxes = new RotatedAxes();
        newAxes.rotationMatrix.load(getMatrix());
        newAxes.convertMatrixToAngles();
        return newAxes;
    }

    public void setAngles(float yaw, float pitch, float roll)
    {
        rotationYaw = yaw;
        rotationPitch = pitch;
        rotationRoll = roll;
        convertAnglesToMatrix();
    }

    public float getYaw()
    {
        return rotationYaw;
    }

    public float getPitch()
    {
        return rotationPitch;
    }

    public float getRoll()
    {
        return rotationRoll;
    }

    public Vector3f getXAxis()
    {
        return new Vector3f(rotationMatrix.m00, rotationMatrix.m10, rotationMatrix.m20);
    }

    public Vector3f getYAxis()
    {
        return new Vector3f(rotationMatrix.m01, rotationMatrix.m11, rotationMatrix.m21);
    }

    public Vector3f getZAxis()
    {
        return new Vector3f(-rotationMatrix.m02, -rotationMatrix.m12, -rotationMatrix.m22);
    }

    public Matrix4f getMatrix()
    {
        return rotationMatrix;
    }

    public void rotateLocalYaw(float rotateBy)
    {
        rotationMatrix.rotate(rotateBy * (float)Math.PI / 180, getYAxis().normalise(null));
        convertMatrixToAngles();
    }

    public void rotateLocalPitch(float rotateBy)
    {
        rotationMatrix.rotate(rotateBy * (float)Math.PI / 180, getZAxis().normalise(null));
        convertMatrixToAngles();
    }

    public void rotateLocalRoll(float rotateBy)
    {
        rotationMatrix.rotate(rotateBy * (float)Math.PI / 180, getXAxis().normalise(null));
        convertMatrixToAngles();
    }

    public RotatedAxes rotateGlobalYaw(float rotateBy)
    {
        rotationMatrix.rotate(rotateBy * (float)Math.PI / 180, new Vector3f(0, 1, 0));
        convertMatrixToAngles();
        return this;
    }

    public RotatedAxes rotateGlobalPitch(float rotateBy)
    {
        rotationMatrix.rotate(rotateBy * (float)Math.PI / 180, new Vector3f(0, 0, 1));
        convertMatrixToAngles();
        return this;
    }

    public RotatedAxes rotateGlobalRoll(float rotateBy)
    {
        rotationMatrix.rotate(rotateBy * (float)Math.PI / 180, new Vector3f(1, 0, 0));
        convertMatrixToAngles();
        return this;
    }

    public RotatedAxes rotateGlobalYawInRads(float rotateBy)
    {
        rotationMatrix.rotate(rotateBy, new Vector3f(0, 1, 0));
        convertMatrixToAngles();
        return this;
    }

    public RotatedAxes rotateGlobalPitchInRads(float rotateBy)
    {
        rotationMatrix.rotate(rotateBy, new Vector3f(0, 0, 1));
        convertMatrixToAngles();
        return this;
    }

    public RotatedAxes rotateGlobalRollInRads(float rotateBy)
    {
        rotationMatrix.rotate(rotateBy, new Vector3f(1, 0, 0));
        convertMatrixToAngles();
        return this;
    }

    public void rotateLocal(float rotateBy, Vector3f rotateAround)
    {
        rotationMatrix.rotate(rotateBy * (float)Math.PI / 180, findLocalVectorGlobally(rotateAround));
        convertMatrixToAngles();
    }

    public void rotateGlobal(float rotateBy, Vector3f rotateAround)
    {
        rotationMatrix.rotate(rotateBy * (float)Math.PI / 180, rotateAround);
        convertMatrixToAngles();
    }

    public Vector3f findGlobalVectorLocally(Vector3f in)
    {
        Matrix4f mat = new Matrix4f();
        mat.m00 = in.x;
        mat.m10 = in.y;
        mat.m20 = in.z;
        mat.rotate(-rotationYaw * (float)Math.PI / 180, new Vector3f(0, 1, 0));
        mat.rotate(-rotationPitch * (float)Math.PI / 180, new Vector3f(0, 0, 1));
        mat.rotate(-rotationRoll * (float)Math.PI / 180, new Vector3f(1, 0, 0));
        return new Vector3f(mat.m00, mat.m10, mat.m20);
    }

    public Vector3f findLocalVectorGlobally(Vector3f in)
    {
        Matrix4f mat = new Matrix4f();
        mat.m00 = in.x;
        mat.m10 = in.y;
        mat.m20 = in.z;
        mat.rotate(rotationRoll * (float)Math.PI / 180, new Vector3f(1, 0, 0));
        mat.rotate(rotationPitch * (float)Math.PI / 180, new Vector3f(0, 0, 1));
        mat.rotate(rotationYaw * (float)Math.PI / 180, new Vector3f(0, 1, 0));
        return new Vector3f(mat.m00, mat.m10, mat.m20);
    }

    private void convertAnglesToMatrix()
    {
        rotationMatrix = new Matrix4f();
        rotationMatrix.rotate(rotationRoll * (float)Math.PI / 180, new Vector3f(1, 0, 0));
        rotationMatrix.rotate(rotationPitch * (float)Math.PI / 180, new Vector3f(0, 0, 1));
        rotationMatrix.rotate(rotationYaw * (float)Math.PI / 180, new Vector3f(0, 1, 0));
        convertMatrixToAngles();
    }

    private void convertMatrixToAngles()
    {
        rotationYaw = (float)Math.atan2(rotationMatrix.m20, rotationMatrix.m00) * 180 / (float)Math.PI;
        rotationPitch = (float)Math.atan2(-rotationMatrix.m10, Math.sqrt(rotationMatrix.m12 * rotationMatrix.m12 + rotationMatrix.m11 * rotationMatrix.m11)) * 180 / (float)Math.PI;
        rotationRoll = (float)Math.atan2(rotationMatrix.m12, rotationMatrix.m11) * 180 / (float)Math.PI;
    }

    public RotatedAxes findLocalAxesGlobally(RotatedAxes in)
    {
        Matrix4f mat = new Matrix4f();
        mat.load(in.getMatrix());
        mat.rotate(rotationRoll * (float)Math.PI / 180, new Vector3f(1, 0, 0));
        mat.rotate(rotationPitch * (float)Math.PI / 180, new Vector3f(0, 0, 1));
        mat.rotate(rotationYaw * (float)Math.PI / 180, new Vector3f(0, 1, 0));
        return new RotatedAxes(mat);
    }

    @Override
    public String toString()
    {
        return "RotatedAxes[Yaw = " + getYaw() + ", Pitch = " + getPitch() + ", Roll = " + getRoll() + "]";
    }
}
