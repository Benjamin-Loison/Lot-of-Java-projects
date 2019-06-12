import java.util.Scanner;

public class Main
{
    static int lignes, colonnes, etat[], taille[];

    public static void main(String[] a)
    {
        int ligne[], colonne[], number = 0;
        Scanner scan = new Scanner(System.in);
        lignes = scan.nextInt();
        colonnes = scan.nextInt();
        ligne = new int[colonnes * lignes];
        colonne = new int[colonnes * lignes];
        etat = new int[colonnes * lignes];
        taille = new int[colonnes * lignes];
        for(int i = 0; i < lignes; i++)
        {
            for(int j = 0; j < colonnes; j++)
            {
                ligne[i * colonnes + j] = i * colonnes + j;
                colonne[i * colonnes + j] = i;
                etat[i * colonnes + j] = scan.nextInt();
            }
        }
        scan.close();
        for(int g = 0; g < lignes; g++)
        {
            for(int h = 0; h < colonnes; h++)
            {
                if(etat[g * colonnes + h] == 1)
                    break;
                int tail = 0;
                for(int i = 1; i < Math.min(Math.min(g + i, lignes), Math.min(h + i, colonnes)); i++)
                {
                    if(isValid(g, h, i))
                    {
                        tail++;
                    }
                    else
                    {
                        break;
                    }
                }
                taille[g * colonnes + h] = tail;
            }
        }
        for(int i = 0, meilleur = 0; i < taille.length; i++)
        {
            if(taille[i] > meilleur)
            {
                meilleur = taille[i];
                number = i;
            }
        }
        System.out.println(taille[number]);
    }

    public static boolean isValid(int lign, int col, int rayon)
    {
        for(int i = lign; i < Math.min(lign + rayon, lignes); i++)
        {
            for(int j = col; j < Math.min(col + rayon, colonnes); j++)
            {
                if(etat[i * colonnes + j] == 1)
                {
                    return false;
                }
            }
        }
        return true;
    }
}