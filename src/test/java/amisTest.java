import FonctionsUtiles.Utiles;
import org.junit.jupiter.api.Test;
import Amis.Amis;
import static org.junit.jupiter.api.Assertions.*;

class AmisTest {
    public static final int TAILLE = 10;
    @Test
    void toutTest() {
        testConstructeur();
        ajouterAmis();
        find();
        isolate();
        unionGroupe();
        sizeAndOptUnio();
        tailleGroupe();
    }

    void testConstructeur() {
        Amis b = new Amis();
        Amis c = new Amis(TAILLE);

        for (int i = 0; i < TAILLE; i++) {
            b.ajouterAmis();
        }

        assertEquals(10, b.tabSize());
        assertEquals(10, c.tabSize());
    }

    void find() {
        Amis a = new Amis();

        for (int i = 0; i < TAILLE; i++) {
            a.ajouterAmis();
        }

        Utiles.ajoutUnionMax(a, TAILLE/2, TAILLE);
        assertEquals(5, a.find(TAILLE-1));
    }

    void ajouterAmis() {
        Amis a = new Amis();

        for (int i = 0; i < TAILLE; i++) {
            a.ajouterAmis();
            assertEquals(1,a.groupSize(i));
        }

        //De 2 a Max ils sont meme groupe
        Utiles.ajoutUnionMax(a, 2, TAILLE);
        assertEquals(8, a.groupSize(2));
    }

    void isolate() {
        Amis a = new Amis(TAILLE);

        //De min a 5 ils sont meme groupe
        Utiles.ajoutUnionMin(a, 5);
        assertEquals(6, a.groupSize(5));
        a.isolate(0);

        assertEquals(5, a.groupSize(5));
        assertEquals(1, a.groupSize(0));
        a.isolate(2);
        assertEquals(4, a.groupSize(3));
        assertEquals(2, a.find(2));

        //Isolement d'un representant
        a.isolate(5);
        assertEquals(1, a.groupSize(5));
        assertEquals(3,a.groupSize(4));
        assertEquals(1,a.find(3));
    }

    void unionGroupe() {
        Amis a = new Amis(TAILLE);

        Utiles.ajoutUnionMin(a, 3);
        Utiles.ajoutUnionMax(a,7,TAILLE);

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

    void tailleGroupe() {
        Amis a = new Amis(TAILLE);
        Utiles.ajoutUnionMax(a, 0,TAILLE);

        assertEquals(TAILLE, a.groupSize(6));

        for (int i = 0; i < TAILLE; i++) {
            a.ajouterAmis();
        }

        a.union(7,18);
        assertEquals(TAILLE+1,a.groupSize(0));
        a.isolate(0);
        assertEquals(TAILLE, a.groupSize(5));
        assertEquals(1,a.groupSize(0));
        assertEquals(a.plusGrandGroupe(), a.groupSize(1));
    }

    void sizeAndOptUnio() {
        Amis a = new Amis(TAILLE);

        Utiles.ajoutUnionMin(a, 3);
        Utiles.ajoutUnionMax(a, 6,TAILLE);

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
        Utiles.ajoutUnionMax(b,2,TAILLE);
        b.union(8,0);
        assertEquals(2, b.find(0));
    }
}