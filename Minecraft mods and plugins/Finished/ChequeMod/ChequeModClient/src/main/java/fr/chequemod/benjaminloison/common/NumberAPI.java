package fr.chequemod.benjaminloison.common;

import net.minecraft.client.resources.I18n;

public class NumberAPI
{
    public static String main(long number)
    {
        if(I18n.format("amount.with.spaces").equalsIgnoreCase("false"))
            return "" + number;
        String nbr = new Long(number).toString();
        if(nbr.length() == 4)
            nbr = nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 5)
            nbr = nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 6)
            nbr = nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 7)
            nbr = nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 8)
            nbr = nbr.charAt(nbr.length() - 8) + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 9)
            nbr = nbr.charAt(nbr.length() - 9) + nbr.charAt(nbr.length() - 8) + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 10)
            nbr = nbr.charAt(nbr.length() - 10) + " " + nbr.charAt(nbr.length() - 9) + nbr.charAt(nbr.length() - 8) + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 11)
            nbr = nbr.charAt(nbr.length() - 11) + nbr.charAt(nbr.length() - 10) + " " + nbr.charAt(nbr.length() - 9) + nbr.charAt(nbr.length() - 8) + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        return nbr;
    }
}