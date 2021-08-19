

public enum EnumSpecial
{
    param,
    info,
    moreInfo,
    warning,
    error,
    fatalerror;

    public static EnumSpecial get(String s)
    {
        for(EnumSpecial type : values())
        {
            if(type.toString().equals(s))
                return type;
        }
        return warning;
    }
}
