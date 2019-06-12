

public enum EnumLang
{
    EN_en,
    US_us,
    FR_fr;

    public static EnumLang get(String s)
    {
        for(EnumLang type : values())
        {
            if(type.toString().equals(s))
                return type;
        }
        return EN_en;
    }
}
