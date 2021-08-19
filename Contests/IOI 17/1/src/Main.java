import java.util.*;

public class Main
{
    public static void main(String[] argv)
    {
        Scanner sc = new Scanner(System.in);
        int lengthUS = Integer.parseInt(sc.nextLine());
        String teamUS = sc.nextLine(), temp = teamUS;
        // int lengthEN = Integer.parseInt(sc.nextLine());
        // String teamEN = sc.nextLine()
        String toShow = "";
        /*
         * for(int i = 0; i < teamEN.length(); i++)
         * {
         * System.out.println(toShow);
         * if(teamEN.charAt(i) == 'E')
         * {
         * if(teamUS.contains("E"))
         * {
         * toShow += 'E';
         * teamUS = teamUS.replaceFirst("E", "");
         * }
         * else
         * {
         * toShow = "-" + temp;
         * break;
         * }
         * }
         * else if(teamEN.charAt(i) == 'F')
         * {
         * if(teamUS.contains("E"))
         * {
         * toShow += 'E';
         * teamUS= teamUS.replaceFirst("E", "");
         * }
         * else
         * {
         * if(teamUS.contains("F"))
         * {
         * toShow += 'F';
         * teamUS= teamUS.replaceFirst("F", "");
         * }
         * else
         * {
         * toShow = "-" + temp;
         * break;
         * }
         * }
         * }
         * else // P
         * {
         * if(teamUS.contains("F"))
         * {
         * toShow += 'F';
         * teamUS= teamUS.replaceFirst("F", "");
         * }
         * else
         * {
         * if(teamUS.contains("P"))
         * {
         * toShow += 'P';
         * teamUS= teamUS.replaceFirst("P", "");
         * }
         * else
         * {
         * toShow = "-" + temp;
         * break;
         * }
         * }
         * }
         * }
         */

        // System.out.println(toShow);
        ArrayList<String> aleas = getAlea(teamUS);
        for(int i = 0; i < aleas.size(); i++)
        {
            System.out.println(aleas.get(i));
        }
        sc.close();
    }

    String getResult(String teamA, String teamB)
    {
        String toShow = "", temp = "";
        for(int i = 0; i < teamB.length(); i++)
            if(teamB.charAt(i) == 'E')
            {
                if(teamA.contains("E"))
                {
                    toShow += 'E';
                    teamA = teamA.replaceFirst("E", "");
                }
                else
                {
                    toShow = "-" + temp;
                    break;
                }
            }
            else if(teamB.charAt(i) == 'F')
            {
                if(teamA.contains("E"))
                {
                    toShow += 'E';
                    teamA = teamA.replaceFirst("E", "");
                }
                else
                {
                    if(teamA.contains("F"))
                    {
                        toShow += 'F';
                        teamA = teamA.replaceFirst("F", "");
                    }
                    else
                    {
                        toShow = "-" + temp;
                        break;
                    }
                }
            }
            else // P
            {
                if(teamA.contains("F"))
                {
                    toShow += 'F';
                    teamA = teamA.replaceFirst("F", "");
                }
                else
                {
                    if(teamA.contains("P"))
                    {
                        toShow += 'P';
                        teamA = teamA.replaceFirst("P", "");
                    }
                    else
                    {
                        toShow = "-" + temp;
                        break;
                    }
                }
            }
        return toShow;
    }

    static ArrayList<String> getAlea(String alea)
    {
        if(alea.length() > 3)
        {
            return assemble(getAleaInfThree(alea.substring(0, 3)), getAleaInfThree(alea.substring(3, alea.length())));
        }
        else
            return getAleaInfThree(alea);
    }

    static ArrayList<String> assemble(ArrayList<String> a, ArrayList<String> b)
    {
        ArrayList<String> c = new ArrayList<String>();
        for(int i = 0; i < a.size(); i++)
        {
            for(int j = 0; j < b.size(); j++)
            {
                c.add(a.get(i) + b.get(j));
            }
        }
        return c;
    }

    static ArrayList<String> getAleaInfThree(String alea)
    {
        ArrayList<String> a = new ArrayList<String>();
        char[] chars = alea.toCharArray();
        for(int i = 0; i < chars.length; i++)
        {
            chars = alea.toCharArray();
            char te = chars[i];
            chars[i] = chars[0];
            chars[0] = te;
            if(chars.length > 2)
                for(int j = 1; j < chars.length; j++)
                {
                    te = chars[j];
                    chars[j] = chars[1];
                    chars[1] = te;
                    if(chars.length > 3)
                    {
                        for(int l = 2; l < chars.length; l++)
                        {
                            te = chars[l];
                            chars[l] = chars[2];
                            chars[2] = te;
                            String t = "";
                            for(int k = 0; k < chars.length; k++)
                                t += chars[k];
                            a.add(t);
                        }

                    }
                    else
                    {
                        String t = "";
                        for(int k = 0; k < chars.length; k++)
                            t += chars[k];
                        a.add(t);
                    }
                }
            else
            {
                String t = "";
                for(int k = 0; k < chars.length; k++)
                    t += chars[k];
                a.add(t);
            }
        }
        return a;
    }
}