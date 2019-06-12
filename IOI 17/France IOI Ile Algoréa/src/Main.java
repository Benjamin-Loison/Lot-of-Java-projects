import java.io.File;
import java.util.Scanner;

public class Main
{
    static int resolution = 0, lignes = 0, colonnes = 0, numero = 0, best = 0, nombre = 0;
    int ligne[], colonne[];
    static Case[] cases, casesUn;
    static Main instance;
    static boolean pasFini = true, premiereFois = true;
    static String path = new File("").getAbsolutePath();
    static File file = new File(path + "\\donnees.txt");

    public static void main(String[] args) throws Exception
    {
        // Instanciation
        instance = new Main();
        // Lecture du fichier donnees.txt
        Scanner scan = new Scanner(file);
        resolution = scan.nextInt();
        lignes = scan.nextInt();
        colonnes = scan.nextInt();
        // Création de la liste d'objets Case
        cases = new Case[lignes * colonnes];
        for(int i = 0; i < lignes; i++)
        {
            for(int j = 0; j < colonnes; j++)
            {
                cases[i * colonnes + j] = new Case(i, j, scan.nextInt());
            }
        }
        // Remplissage de la liste d'objets Case et fermeture de la lecture
        scan.close();
        // Création et remplissage de la liste d'objets CaseUn
        int nombreCasesUn = nombreCasesUn();
        casesUn = new Case[nombreCasesUn];
        numero = 0;
        for(int i = 0; i < cases.length; i++)
        {
            if(cases[i].isUn)
            {
                casesUn[numero] = cases[i];
                numero++;
            }
        }
        // Boucle sur le travail des photos
        while(pasFini)
        {
            // Création de la liste de toutes les cases avec leur densité
            Case[] casesDensitees = Case.photosDensitees(cases);
            // Recherche de la meilleur densité parmis la liste
            best = 0;
            numero = 0;
            for(int i = 0; i < casesDensitees.length; i++)
            {
                if(casesDensitees[i].densite > best)
                {
                    numero = i;
                }
            }
            // System.out.println() des coordonnées pour la réponse et enlèvement des coordonnées imprimmées
            ecrire(casesDensitees[numero].ligne, casesDensitees[numero].colonne);
            // Si le travail qui vient d'être fait ne portait que sur un objet, arrêter, on a fini
            if(casesDensitees.length <= 1)
            {
                pasFini = false;
            }
        }
    }

    public static int nombreCasesUn()
    {
        int tailleCasesUn = 0;
        for(int i = 0; i < cases.length; i++)
        {
            if(cases[i].isUn)
            {
                tailleCasesUn++;
            }
        }
        return tailleCasesUn;
    }

    public static void ecrire(int ligne, int colonne)
    {
        for(int i = 0; i < cases.length; i++)
            System.out.println(cases[i].ligne + " " + cases[i].colonne);
        // Sortie résultats
        System.out.println(ligne + " " + colonne);
        // Création de variables tampons, utiles à remplir à nouveau les listes avec les nouvelles tailles
        int[] ligneTampon = null, colonneTampon = null;
        if(!premiereFois)
        {
            ligneTampon = instance.ligne;
            colonneTampon = instance.colonne;
        }
        Case[] casesTampon = cases;
        if(premiereFois)
        {
            instance.ligne = new int[1];
            instance.colonne = new int[1];
        }
        else
        {
            // On ajoute une position dans chaque liste
            instance.ligne = new int[instance.ligne.length + 1];
            instance.colonne = new int[instance.colonne.length + 1];
        }
        // On enlève une case qui a été transmise dans les BlackListes
        cases = new Case[casesTampon.length - 1];
        numero = 0;
        if(premiereFois)
        {
            instance.ligne[numero] = ligne;
            instance.colonne[numero] = colonne;
            for(int i = 0; i < casesTampon.length; i++)
            {
                if(!(casesTampon[i].ligne == ligne && casesTampon[i].colonne == colonne))
                {
                    cases[numero] = casesTampon[i];
                    numero++;
                }
            }
            premiereFois = false;
        }
        else
        {
            for(int i = 0; i < ligneTampon.length; i++)
            {
                if(!(ligneTampon[i] == ligne && colonneTampon[i] == colonne))
                {
                    instance.ligne[numero] = ligneTampon[numero];
                    instance.colonne[numero] = colonneTampon[numero];
                    cases[numero] = casesTampon[numero];
                    numero++;
                }
            }
        }
        nombre++;
    }

    public static class Case
    {
        int ligne = 0, colonne = 0, un = 0, densite = 0;
        boolean isUn = false;

        public Case(int ligne, int colonne, boolean isUn)
        {
            this.ligne = ligne;
            this.colonne = colonne;
            this.isUn = isUn;
            if(isUn)
            {
                this.un = 1;
            }
        }

        public Case(int ligne, int colonne, int un)
        {
            this.ligne = ligne;
            this.colonne = colonne;
            this.un = un;
            if(un == 1)
            {
                this.isUn = true;
            }
        }

        public void metDensite(int densite)
        {
            this.densite = densite;
        }

        public static Case[] photosDensitees(Case[] cases)
        {
            Case[] photosDensitees = cases;
            for(int i = 0; i < photosDensitees.length; i++)
            {
                int densite = 0;
                for(int j = 0; j < photosDensitees.length; j++)
                {
                    System.out.println(i+ "k" + j);
                    if(estDansPhoto(photosDensitees[i].ligne, 
                            photosDensitees[i].colonne, 
                            photosDensitees[j].ligne, 
                            photosDensitees[j].colonne))
                    {
                        densite++;
                    }
                }
                photosDensitees[i].metDensite(densite);
            }
            return photosDensitees;
        }

        // return true; si position donnée, supérieure ou égale à celle de base et inférieure ou égale à la somme de celle de base et la résolution

        public static boolean estDansPhoto(int ligneBase, int colonneBase, int ligne, int colonne)
        {
            if(ligne >= ligneBase && ligne <= ligneBase + resolution && colonne >= colonneBase && colonne <= colonneBase + resolution)
            {
                return true;
            }
            return false;
        }
    }
}