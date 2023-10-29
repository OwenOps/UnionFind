package FonctionsUtiles;
import Amis.Amis;
import java.util.Random;

/**
 * La classe Utiles fournit des méthodes utilitaires pour effectuer différentes opérations sur la classe Amis.
 */
public class Utiles {

    /**
     * Ajoute une union maximale sur une instance de la classe Amis, reliant chaque ami à son successeur.
     * @param t Instance de la classe Amis
     * @param commencement Index à partir duquel débuter l'union
     * @param taille Taille du groupe d'amis
     */
    public static void ajoutUnionMax(Amis t, int commencement, int taille) {
        for (int i = commencement; i < taille; i++) {
            if ((i + 1) == taille)
                break;
            t.union(i, i+1);
        }
    }

    /**
     * Ajoute une union minimale sur une instance de la classe Amis, reliant chaque ami à son prédécesseur.
     * @param t Instance de la classe Amis
     * @param commencement Index à partir duquel débuter l'union
     */
    public static void ajoutUnionMin(Amis t, int commencement) {
        for (int i = commencement; i > 0; i--) {
            t.union(i, i-1);
        }
    }

    /**
     * Crée un groupe unique.
     * @param taille Taille du groupe d'amis
     * @param amis Instance de la classe Amis
     */
    public static void unGroupe(int taille, Amis amis) {
        for (int i = 0; i < taille; i++) {
            if (i+1 == taille)
                break;
            amis.union(i, i+1);
        }
    }

    /**
     * Crée deux groupes équivalents.
     * @param taille Taille du groupe d'amis
     * @param amis Instance de la classe Amis
     */
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

    /**
     * Crée plusieurs groupes.
     * @param taille Taille du groupe d'amis
     * @param amis Instance de la classe Amis
     */
    public static void creationNGroupes(int taille, Amis amis) {
        Random rd = new Random();
        int randomGroupe = rd.nextInt(taille);

        for (int i = 0; i < taille; i++) {
            amis.union(randomGroupe, randomGroupe/2);
        }
    }
}
