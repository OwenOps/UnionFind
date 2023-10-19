import org.junit.jupiter.api.Test;

import java.nio.file.AccessMode;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AmisTest {
    public static final int TAILLE = 10;
    public static final int PETIT = 50;
    public static final int MOYEN = 500;
    public static final int GRAND = 5000;

    //Fonctionne que avec TAILLE = 10
    @Test
    void toutTest() {
        testConstructeur();
        ajouterAmis();
        isolate();
        unionGroupe();
        sizeAndOptUnio();
    }


    void testConstructeur() {
        Amis b = new Amis();
        Amis c = new Amis(TAILLE);

        for (int i = 0; i < TAILLE; i++) {
            b.ajouterAmis(i);
        }

        assertEquals(10, b.friendSize());
        assertEquals(10, c.friendSize());
    }

    void ajoutUnionMax(Amis t, int commencement) {
        for (int i = commencement; i < TAILLE; i++) {
            if ((i + 1) == TAILLE)
                break;
            t.union(i, i+1);
        }
    }

    void ajoutUnionMin(Amis t, int commencement) {
        for (int i = commencement; i > 0; i--) {
            t.union(i, i-1);
        }
    }

    void ajouterAmis() {
        Amis a = new Amis();

        for (int i = 0; i < TAILLE; i++) {
            a.ajouterAmis(i);
            assertEquals(1,a.groupSize(i));
        }

        //De 2 a Max ils sont meme groupe
        ajoutUnionMax(a, 2);
        assertEquals(8, a.groupSize(2));
    }

    void isolate() {
        Amis a = new Amis(TAILLE);

        //De min a 5 ils sont meme groupe
        ajoutUnionMin(a, 5);

        assertEquals(5,a.find(2));
        assertEquals(9,a.find(9));
        a.isolate(0);
        a.isolate(5);
        assertEquals(5,a.find(5));
        assertEquals(1,a.find(2));
        assertEquals(1,a.find(3));
        assertEquals(0,a.find(0));
    }

    void unionGroupe() {
        Amis a = new Amis(TAILLE);

        ajoutUnionMin(a, 3);
        ajoutUnionMax(a,7 );

        assertEquals(3, a.find(0));
        assertEquals(7,a.find(9));

        //Plus que 4 groupe
        a.union(0,9);
        assertEquals(3, a.find(0));
        assertEquals(4, a.find(4));
        assertEquals(3,a.find(8));
        assertEquals(3,a.find(9));

        //Plus que 3 groupe
        a.union(4,5);
        assertEquals(4, a.find(5));

        a.union(4, 1);
        a.union(6,9);
        for (int i = 0; i < TAILLE; i++) {
            assertEquals(3, a.find(i));
        }
    }

    void sizeAndOptUnio() {
        Amis a = new Amis(TAILLE);

        ajoutUnionMin(a, 3);
        ajoutUnionMax(a, 6);

        assertEquals(4, a.groupSize(0));
        assertEquals(4, a.groupSize(6));
        assertEquals(1, a.groupSize(4));

        //Met pas a jour les num groupes, faut utiliser find.
        a.union(3,6);
        assertEquals(5, a.groupSize(2));
        assertEquals(5, a.groupSize(9));
        assertEquals(3,a.find(8));
        assertEquals(3,a.find(9));

        Amis b = new Amis(TAILLE);
        ajoutUnionMax(b,2);
        b.union(8,0);
        assertEquals(2, b.find(0));
    }

    @Test
    void autre() {
        Amis a = new Amis(PETIT);
        addRandomGroups(a);
        System.out.println(a);
    }

    //Il y aura entre x et x + 1 groupes
    //Dans chaque groupe entre x et x + 1 personnes
    //Et on choisi une personne au hasard
    void addRandomGroups(Amis a) {
        Random nbrGroupe = new Random();
        Random nbrPersGroupe = new Random();
        Random index = new Random();
        int tailleListe = a.friendSize();
        int nbrGr = nbrGroupe.nextInt(tailleListe / 5,tailleListe / 2);

        for (int i = 0; i < nbrGr; i++) {
            int nbrPers = nbrPersGroupe.nextInt(tailleListe / 3);
            int ind = index.nextInt(tailleListe);
            for (int j = 0; j < nbrPers; j++) {
                if (j + 1 == tailleListe)
                    break;
                a.union(ind,ind + 1);
            }
        }
    }
}