import Amis.Amis;

import java.util.Random;

public class performanceN {
    public static final int MILISECONDES = 1000000;
    public static void union(int taille) {
        Amis amis = new Amis(taille);
        Random random = new Random();

        long startTime = System.nanoTime();
        for (int i = 0; i < taille / 2; i++) {
            int friend1 = random.nextInt(taille);
            int friend2 = random.nextInt(taille);
            amis.union(friend1, friend2);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILISECONDES;

        System.out.println("Performance de la fonction union N fois : " + duration + " ms");
    }

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

    public static void isolate(int taille) {
        Amis amis = new Amis(taille);
        Random random = new Random();

        for (int i = 0; i < taille / 2; i++) {
            int randomFriend = random.nextInt(taille);
            amis.isolate(randomFriend);
        }
    }
}