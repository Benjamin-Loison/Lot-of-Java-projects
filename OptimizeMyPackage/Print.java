

import java.text.DateFormat;
import java.util.Date;

public class Print
{
    public static void main(String message)
    {
        System.out.println(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date()) + " : " + message);
    }

    public static void main(EnumSpecial special, String message)
    {
        System.out.println(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date()) + " [" + special.toString().toUpperCase() + "] : " + message);
    }

    public static void main(EnumSpecial specialFirst, EnumSpecial specialSecond, String message)
    {
        System.out.println(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date()) + " [" + specialFirst.toString().toUpperCase() + "] [" + specialSecond.toString().toUpperCase() + "] : " + message);
    }
}