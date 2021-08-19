package fr.watchdogs.benjaminloison.common.guns;

public enum EnumFireMode
{
    SEMIAUTO,
    FULLAUTO,
    MINIGUN,
    BURST;

    public static EnumFireMode getFireMode(String s)
    {
        s = s.toLowerCase();
        if(s.equals("fullauto"))
            return FULLAUTO;
        else if(s.equals("minigun"))
            return MINIGUN;
        else if(s.equals("burst"))
            return BURST;
        return SEMIAUTO;
    }
}