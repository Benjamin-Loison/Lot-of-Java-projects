class Main
{
    static boolean ia;
    static byte size = 3;
    static char player = 'X', slots[][] = new char[size][size];

    public static void main(String[] args)
    {
        if(args.length >= 1)
            if(args[0].equals("ia"))
                ia = true;
        for(byte y = 0; y < size; y++)
            for(byte x = 0; x < size; x++)
                slots[x][y] = '.';
        if(ia)
        {
            // TODO
        }
        else
        {
            
        }
    }

    boolean set(byte x, byte y)
    {
        if(slots[x][y] == '.')
        {
            slots[x][y] = player;
            return true;
        }
        return false;
    }

    boolean isTerminated()
    {
        for(byte y = 0; y < size; y++)
        {
            char yBuffer = slots[0][y];
            if(yBuffer == '.')
                continue;
            for(byte x = 1; x < size; x++)
                if(yBuffer != slots[x][y])
                {
                    yBuffer = 0;
                    break;
                }
            if(yBuffer != 0)
                return true;
        }
        for(byte x = 0; x < size; x++)
        {
            char xBuffer = slots[x][0];
            if(xBuffer == '.')
                continue;
            for(byte y = 1; y < size; y++)
                if(xBuffer != slots[x][y])
                {
                    xBuffer = 0;
                    break;
                }
            if(xBuffer != 0)
                return true;
        }
        byte i;
        char iBuffer = slots[0][0];
        if(iBuffer == '.')
        {
            i = size;
            iBuffer = 0;
        }
        for(i = 0; i < size; i++)
            if(iBuffer != slots[i][i])
            {
                iBuffer = 0;
                break;
            }
        if(iBuffer != 0)
            return true;
        return false;
    }
}