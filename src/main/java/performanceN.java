import Amis.Amis;
import java.util.Random;

/**
 * La classe performanceN fournit des méthodes pour évaluer les performances des opérations de la classe Amis.
 */
public class performanceN {
    public static final int MILISECONDES = 1000000;

    /**
     * Mesure la performance de la méthode union de la classe Amis pour un nombre spécifié de fois.
     * @param taille Taille du groupe d'amis à créer et sur lequel l'opération sera évaluée
     */
    public static void union(int taille) {
        Amis amis = new Amis(taille);
        Random random = new Random();

        long startTime = System.nanoTime();
        for (int i = 0; i < taille; i++) {
            int friend1 = random.nextInt(taille);
            int friend2 = random.nextInt(taille);
            amis.union(friend1, friend2);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILISECONDES;
        System.out.println("Performance de la fonction union N fois : " + duration + " ms");
    }

    /**
     * Mesure la performance de la méthode find de la classe Amis pour un nombre spécifié de fois.
     * @param taille Taille du groupe d'amis à créer et sur lequel l'opération sera évaluée
     */
    public static void find(int taille) {
        Amis amis = new Amis(taille);
        Random random = new Random();

        long startTime = System.nanoTime();
        for (int i = 0; i < taille; i++) {
            int randomFriend = random.nextInt(taille);
            amis.find(randomFriend);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILISECONDES;
        System.out.println("Performance de la fonction find N fois : " + duration + " ms");
    }

    /**
     * Mesure la performance de la méthode isolate de la classe Amis pour un nombre spécifié de fois.
     * @param taille Taille du groupe d'amis à créer et sur lequel l'opération sera évaluée
     */
    public static void isolate(int taille) {
        Amis amis = new Amis(taille);
        Random random = new Random();

        long startTime = System.nanoTime();
        for (int i = 0; i < taille; i++) {
            int randomFriend = random.nextInt(taille);
            amis.isolate(randomFriend);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILISECONDES;
        System.out.println("Performance de la fonction isolate N fois : " + duration + " ms");
    }
}