package fr.stringobfuscator.benjaminloison.common;

import static fr.stringobfuscator.benjaminloison.common.Charsets.UTF_8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main
{
    static final String nameStr = "myObfusactedString";

    public static void main(final String[] args)
    {
        int len = args.length;
        if(len > 0)
            for(int i = 0; i < len; i++)
                System.out.println(toSourceCode(nameStr, args[i].getBytes(UTF_8)));
        else
            System.out.println("Please provide a string to obfuscate !");
    }

    public static String toSourceCode(final String label, final byte[] data)
    {
        final ByteArrayInputStream bais = new ByteArrayInputStream(data);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length * 5 + 23 + data.length);
        toSourceCode(label, bais, baos);
        return new String(baos.toByteArray(), UTF_8);
    }

    public static void toSourceCode(final String label, final InputStream is, final OutputStream os)
    {
        final byte[] zx = "0x".getBytes();
        try
        {
            os.write("final String ".getBytes(UTF_8));
            os.write(label.getBytes(UTF_8));
            os.write(" = new String(new byte[] {".getBytes());
            int i = is.read();
            while(i != -1)
            {
                os.write(zx);
                os.write(Integer.toHexString(i).getBytes());
                i = is.read();
                if(i != -1)
                    os.write(", ".getBytes());
            }
            os.write("}, Charsets.UTF_8);".getBytes());
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}