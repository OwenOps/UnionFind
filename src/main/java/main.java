import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import Amis.Amis;
import FonctionsUtiles.Utiles;

public class main {
    public static final int MILISECONDES = 1000000;
    public static final int[] TAILLET = {10,20,30,40,50};
    public static final int[] TAILLE0 = {1000, 5000 , 10_000 , 100_000 , 300_000, 500_000, 1_000_000, 1_500_000, 5_000_000};
    public static final int[] TAILLE1 = {5_500_000, 6_000_000, 6_500_000, 7_000_000, 7_500_000, 8_000_000, 8_500_000};
    public static final int[] TAILLE2 = {9_000_000, 9_500_000, 10_000_000, 10_500_000, 11_000_000, 11_500_000, 12_000_000};
    public static final int[] TAILLE3 = {50_000_000, 100_000_000, 150_000_000, 150_500_000, 170_000_000};

    // Appels de différentes méthodes de performance
    public static void main(String[] args) {
//       perfFindUnionIsolate();
//       perfFindSolo();
//       perfUnionDeuxGroupe();
       perfIsolateRandom();
//       perfIsolateMaxGroupe();
    }

    /**
     * Mesure les performances des méthodes find, union et isolate pour différentes tailles de groupes
     * en faisant N fois les methodes.
     * Appelle les fonctions de la class performanceN.
     */
    public static void perfFindUnionIsolate() {
        List<Consumer<Integer>> func = Arrays.asList(performanceN::find, performanceN::union, performanceN::isolate);

        for (Consumer<Integer> fun : func) {
            System.out.println("-----------------------DEBUT-----------------------");
            for (int a : TAILLE3) {
                String formattedValue = String.format("%,d", a);
                System.out.println("Taille : " + formattedValue);
                long startTime = System.nanoTime();

                fun.accept(a);

                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / MILISECONDES;
                System.out.println("Performance Total : " + duration + " ms\n");
            }
            System.out.println("-----------------------FIN-----------------------");
        }
    }

    /**
     * Mesure les performances de la méthode find pour différentes tailles de groupes.
     */
    public static void perfFindSolo() {
        System.out.println("-----------------------DEBUT-----------------------");
        for (int a : TAILLE3) {
            Amis amis = new Amis(a);
            Utiles.creationNGroupes(a, amis);

            String formattedValue = String.format("%,d", a);
            System.out.println("Taille : " + formattedValue);
            long startTime = System.nanoTime();

            Random rd = new Random();
            amis.find(rd.nextInt(a));

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / MILISECONDES;
            System.out.println("Performance : " + duration + " ms\n");
        }
        System.out.println("-----------------------FIN-----------------------");
    }

    /**
     * Mesure les performances de la méthode union pour différentes tailles de groupes.
     */
    public static void perfUnionDeuxGroupe() {
        System.out.println("-----------------------DEBUT-----------------------");
        for (int a : TAILLE3) {
            Amis amis = new Amis(a);
            Utiles.creationDeuxGroupes(a, amis);

            String formattedValue = String.format("%,d", a);
            System.out.println("Taille : " + formattedValue);
            long startTime = System.nanoTime();

            amis.union(a/3, a-1);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / MILISECONDES;
            System.out.println("Performance : " + duration + " ms\n");
        }
        System.out.println("-----------------------FIN-----------------------");
    }

    /**
     * Mesure les performances de la méthode isolate en isolant le representant
     * d'un demi groupe de Taille / 2.
     */
    public static void perfIsolateRandom() {
        System.out.println("-----------------------DEBUT-----------------------");
        for (int a : TAILLE3) {
            Amis amis = new Amis(a);
            Utiles.creationDeuxGroupes(a, amis);

            //On isole le chef de chaque groupe
            String formattedValue = String.format("%,d", a);
            System.out.println("Taille : " + formattedValue);
            long startTime = System.nanoTime();

            amis.isolate(amis.find(a/3));

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / MILISECONDES;
            System.out.println("Performance : " + duration + " ms\n");
        }
        System.out.println("-----------------------FIN-----------------------");
    }

    /**
     * Mesure les performances de la méthode isolate en isolant le chef
     * d'un seul et meme grand groupe de Taille N;
     */
    public static void perfIsolateMaxGroupe() {
        System.out.println("-----------------------DEBUT-----------------------");
        for (int a : TAILLE3) {
            Amis amis = new Amis(a);
            Utiles.unGroupe(a, amis);

            String formattedValue = String.format("%,d", a);
            System.out.println("Taille : " + formattedValue);
            long startTime = System.nanoTime();

            amis.isolate(amis.find(a/3));

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / MILISECONDES;
            System.out.println("Performance : " + duration + " ms\n");
        }
        System.out.println("-----------------------FIN-----------------------");
    }
}