import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import Amis.Amis;

public class main {
    public static final int MILISECONDES = 1000000;
    public static final int[] TAILLET = {10,20,30,40,50};
    public static final int[] TAILLE0 = {1000, 5000 , 10_000 , 100_000 , 300_000, 500_000, 1_000_000, 1_500_000, 5_000_000};
    public static final int[] TAILLE1 = {5_500_000, 6_000_000, 6_500_000, 7_000_000, 7_500_000, 8_000_000, 8_500_000};
    public static final int[] TAILLE2 = {9_000_000, 9_500_000, 10_000_000, 10_500_000, 11_000_000, 11_500_000, 12_000_000};
    public static final int[] TAILLE3 = {50_000_000, 100_000_000, 150_000_000, 150_500_000, 170_000_000};
    //Depasse la memoire
//    public static final int [] TAILLE4 = {200_000_000, 250_000_000, 300_000_000, 350_000_000, 500_000_000, 1_000_000_000};

    public static void main(String[] args) {
        perf1();
    }

    public static void perf1() {
        List<Consumer<Integer>> func = Arrays.asList(perf::find, perf::union);

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

    public static void perfUnionGroupe() {
        System.out.println("-----------------------DEBUT-----------------------");
        for (int a : TAILLE3) {
            Amis amis = new Amis(a);
            creationDeuxGroupes(a, amis);

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

    private static void creationDeuxGroupes(int taille, Amis amis) {
        for (int i = 0; i < taille / 2; i++) {
            amis.union(i, i+1);
        }

        for (int i = (taille/2) + 1; i < taille; i++) {
            if (i+1 == taille)
                break;
            amis.union(i,i+1);
        }
    }
}
