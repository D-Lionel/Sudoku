import java.lang.reflect.Array;
import java.util.Arrays;

// Cet algorithme n'est pas optimiser, le but de l'exercice est de manipuler les boucles et les tableaux

public class sudoku {
    static boolean TousDifferents(int []tab9){ // vérifie qu'un tableau de 9 chiffres ne contient pas de doublons
        int i;
        int j;
       // System.out.println("Matrice de tousDifferents : " + Arrays.toString(tab9));
        for (i =0; i<9; i++){
            for (j =1; j<9; j++){
                if (tab9[i] == tab9[j] && i != j){
                    return false;
                }
            }
        }


        return true;
    }

    public static int[][] RemplitGrilleNonConforme(){ // remplit une grille de 9 par 9 aléatoirement, avec possiblement des doublons
        int x = 0;
        int y = 0;
        int [][]grille = new int[9][9];

        for (x =0; x<=8; x++){
            for (y=0; y<=8; y++){
                grille[x][y] = (int)(Math.random() *100 + 1); // on teste avec des nombres allant jusqu'à 100 pour avoir plus de chances de trouver une grille conforme
            }
        }

        return grille;
    }

    public static int[][] RemplitGrilleConforme(){
        int [][]grille = new int[9][9];
        int i;
        for (i=0; i<1000000; i++) { // on teste un grand nombre de fois pour trouver la solution
            //On remplit une grille alétaoirement
            grille = RemplitGrilleNonConforme();
            //On vérifie que chaque ligne, colonne et sous-grille sont conformes
            if (VerifLignes(grille) && VerifColonnes(grille) && VerifSousGrilles(grille)) {
                return grille;
            }
        }
        return null;
    }

    static boolean VerifLignes(int [][]grille){
        int i; //ligne
        int j; //colonnes
        int []tab9 = new int[9];
        boolean ligne_conforme;
        for (i = 0; i <=8; i++){
            //On extrait la ligne du sudoku
            for (j = 0; j <=8; j++){
                tab9[j] = grille[i][j];
            }
            //On envoie cette ligne dans la fonction tousDifferents
            ligne_conforme = TousDifferents(tab9);
            if (ligne_conforme == false){
                return false;
            }
        }
        return true;
    }

    static boolean VerifColonnes(int [][]grille){
        int i; //ligne
        int j; //colonnes
        int []tab9 = new int[9];
        boolean colonne_conforme;
        for (i = 0; i <=8; i++){
            //On extrait la ligne du sudoku
            for (j = 0; j <=8; j++){
                tab9[j] = grille[j][i];
            }
            //On envoie cette ligne dans la fonction tousDifferents
            colonne_conforme = TousDifferents(tab9);
            if (colonne_conforme == false){
                return false;
            }
        }
        return true;
    }

    static boolean VerifSousGrilles(int [][]grille){
        int i; //ligne
        int j; //colonnes
        int x;
        int y;
        int a = 0;
        int []tab9 = new int[9];
        boolean sous_grille_conforme;
        for (i = 0; i <=2; i++) {
            for (j = 0; j <= 2; j++){
                a = 0;
                //extraire la sous grille en question pour la placer dans tab9
                for (x = 0; x <= 2; x++) {
                    for (y = 0; y <= 2; y++) {

                        tab9[a] = grille[(i*3) + x][(j*3) + y];
                        a++;
                    }
                }
               // System.out.println("Sous-grille " + i + "," + j + " : " + Arrays.toString(tab9));
                 //On envoie tab9 dans la fonction tousDifferents
                sous_grille_conforme = TousDifferents(tab9);
                if (sous_grille_conforme == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void afficheGrille(int [][]grille){
        System.out.println(Arrays.deepToString(grille).replace("], ", "]\n"));

    }

    public static void main(String[] args) {
        int [][]grille = new int[9][9];
        grille = RemplitGrilleConforme();
        if (grille != null) {
            afficheGrille(grille);
        }else{
            System.out.println("Grille introuvable.");
        }


    }
}
