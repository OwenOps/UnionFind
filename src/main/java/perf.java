import Amis.Amis;

import java.util.Random;

public class perf {
    public static void tout(int taille) {
        Amis amis = new Amis(taille);
        Random random = new Random();

        // Test de l'ajout d'amis
        long startTime = System.nanoTime();
        for (int i = 0; i < taille; i++) {
            int randomFriend = random.nextInt(taille);
            amis.ajouterAmis(randomFriend);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // en millisecondes
        System.out.println("Ajout d'amis : " + duration + " ms");

        // Test de la recherche
        startTime = System.nanoTime();
        for (int i = 0; i < taille; i++) {
            int randomFriend = random.nextInt(taille);
            amis.find(randomFriend);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // en millisecondes
        System.out.println("Recherche : " + duration + " ms");

        // Test de l'union
        startTime = System.nanoTime();
        for (int i = 0; i < taille / 2; i++) {
            int friend1 = random.nextInt(taille);
            int friend2 = random.nextInt(taille);
            amis.union(friend1, friend2);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // en millisecondes
        System.out.println("Union de groupes : " + duration + " ms");

        // Test de l'isolement
        startTime = System.nanoTime();
        for (int i = 0; i < taille / 2; i++) {
            int randomFriend = random.nextInt(taille);
            amis.isolate(randomFriend);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // en millisecondes
        System.out.println("Isolement : " + duration + " ms");
    }

    public static void union(int taille) {
        Amis amis = new Amis(taille);
        Random random = new Random();

        for (int i = 0; i < taille / 2; i++) {
            int friend1 = random.nextInt(taille);
            int friend2 = random.nextInt(taille);
            amis.union(friend1, friend2);
        }
    }

    public static void find(int taille) {
        Amis amis = new Amis(taille);
        Random random = new Random();

        for (int i = 0; i < taille; i++) {
            int randomFriend = random.nextInt(taille);
            amis.find(randomFriend);
        }
    }

    public static void findSolo(int taille) {
        Amis amis = new Amis(taille);
        Random rd = new Random();

        addRandomGroups(amis);
        amis.find(rd.nextInt(taille));
    }

    public static void isolate(int taille) {
        Amis amis = new Amis(taille);
        Random random = new Random();

        for (int i = 0; i < taille / 2; i++) {
            int randomFriend = random.nextInt(taille);
            amis.isolate(randomFriend);
        }
    }

    static void unionDeuxGrosGroupes(int taille) {
        Amis amis = new Amis(taille);
        for (int i = 0; i < taille / 2; i++) {
            amis.union(i, i+1);
        }

        for (int i = taille/2; i < taille; i++) {
            if (i+1 == taille)
                break;
            amis.union(i,i+1);
        }

        amis.union(0,taille-1);
    }

    //Il y aura entre x et x + 1 groupes
    //Dans chaque groupe entre x et x + 1 personnes
    //Et on choisi une personne au hasard
    public static void addRandomGroups(Amis a) {
        Random random = new Random();
        int tailleListe = a.friendSize();
        int nbrGr = random.nextInt(tailleListe / 5, tailleListe / 2);

        for (int i = 0; i < nbrGr; i++) {
            int nbrPers = random.nextInt(tailleListe / 3);
            int ind = random.nextInt(tailleListe);

            // Pour ajouter les personnes au groupe, nous allons choisir une personne au hasard
            // (représentant du groupe) et ajouter les autres membres au groupe en utilisant l'opération Union.
            int representant = a.find(ind);

            for (int j = 0; j < nbrPers; j++) {
                int membre = random.nextInt(tailleListe);
                if (membre != representant) {
                    a.union(representant, membre);
                }
            }
        }
    }
}
