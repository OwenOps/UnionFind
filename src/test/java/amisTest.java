import org.junit.jupiter.api.Test;
import Amis.Amis;
import static org.junit.jupiter.api.Assertions.*;

class AmisTest {
    public static final int TAILLE = 10;
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
        b.ajouterAmis(2);
        assertEquals(2, b.find(b.friendSize()-1));
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
        assertEquals(6, a.groupSize(5));
        a.isolate(0);
        assertEquals(5, a.groupSize(5));
        assertEquals(1, a.groupSize(0));
        a.isolate(2);
        assertEquals(4, a.groupSize(3));
        System.out.println(a);

        //Isolement d'un representant
        a.isolate(5);
        assertEquals(1, a.groupSize(5));
        assertEquals(3,a.groupSize(4));
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
}