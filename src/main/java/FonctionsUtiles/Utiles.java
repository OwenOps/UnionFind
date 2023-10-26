package FonctionsUtiles;
import Amis.Amis;

import java.util.Random;

public class Utiles {
    public static void ajoutUnionMax(Amis t, int commencement, int taille) {
        for (int i = commencement; i < taille; i++) {
            if ((i + 1) == taille)
                break;
            t.union(i, i+1);
        }
    }

    public static void ajoutUnionMin(Amis t, int commencement) {
        for (int i = commencement; i > 0; i--) {
            t.union(i, i-1);
        }
    }

     public static void unGroupe(int taille, Amis amis) {
        for (int i = 0; i < taille; i++) {
            if (i+1 == taille)
                break;
            amis.union(i, i+1);
        }
    }

    public static void creationDeuxGroupes(int taille, Amis amis) {
        for (int i = 0; i < taille / 2; i++) {
            amis.union(i, i+1);
        }

        for (int i = (taille/2) + 1; i < taille; i++) {
            if (i+1 == taille)
                break;
            amis.union(i,i+1);
        }
    }

    public static void creationNGroupes(int taille, Amis amis) {
        Random rd = new Random();
        int randomGroupe = rd.nextInt(taille);

        for (int i = 0; i < taille; i++) {
            amis.union(randomGroupe, randomGroupe/2);
        }
    }
}
