package Amis;

import java.util.*;

public class Amis {
    private ArrayList<Integer> listeAmis;
    private ArrayList<Integer> groupTaille;
    public static final int SOLO = 1;

    public Amis() {
        listeAmis = new ArrayList<>();
        groupTaille = new ArrayList<>();
    }

    public Amis(int taille) {
        listeAmis = new ArrayList<>(taille);
        groupTaille = new ArrayList<>(taille);

        assert (taille > 0);
        for (int i = 0; i < taille; i++) {
            listeAmis.add(i);
            groupTaille.add(SOLO);
        }
    }

    public void ajouterAmis() {
        listeAmis.add(listeAmis.size());
        groupTaille.add(SOLO);
    }

    public int find(int n) {
        enDehorsListe(n);

        int r = n;
        while (listeAmis.get(r) != r) {
            r = listeAmis.get(r);
        }

        int a = n;
        while (listeAmis.get(a) != a) {
            int tmp = a;
            a = listeAmis.get(a);
            listeAmis.set(tmp, r);
        }
        return r;
    }

    private void enDehorsListe(int n) { assert(n < listeAmis.size() && n >= 0); }

    public void isolate(int n) {
        enDehorsListe(n);
        int numGroup = listeAmis.get(n);

        if (n == numGroup) {
            int newChef = -1;
            for (int i = 0; i < listeAmis.size(); i++) {
                int currentGroup = listeAmis.get(i);

                if (numGroup == currentGroup && i != numGroup) {
                    if (newChef == -1) {
                        newChef = i;
                        groupTaille.set(newChef, groupTaille.get(n) - 1);
                    }
                    listeAmis.set(i, newChef);
                }
            }
            groupTaille.set(n, SOLO);
        } else {
            int representant = find(n);
            groupTaille.set(representant, groupTaille.get(representant) - 1);
            listeAmis.set(n, n);
        }
    }

    public void union(int g1, int g2) {
        int r1 = find(g1);
        int r2 = find(g2);

        if (r1 == r2)
            return;

        int s1 = groupTaille.get(r1);
        int s2 = groupTaille.get(r2);
        int taille = s1 + s2;

        if (s1 < s2) {
            listeAmis.set(r1, r2);
            groupTaille.set(r2, taille);
        }
        else {
            listeAmis.set(r2, r1);
            groupTaille.set(r1, taille);

            // A voir
            if (s1 == s2) {
                groupTaille.set(r1, s1 + 1);
            }
        }
    }

    public int tabSize() {
        return listeAmis.size();
    }

    public int groupSize(int n) {
        return groupTaille.get(find(n));
    }

    public int plusGrandGroupe() { return Collections.max(groupTaille); }

    //Que pour petit tableau
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int taille = listeAmis.size();

        sb.append(" ");
        for (int i = 0; i < taille; i++) {
            sb.append(i + "  ");
        }
        sb.append("- personne\n" + "[");

        for (int i = 0; i < taille; i++) {
            sb.append(listeAmis.get(i) + ", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("] - groupe");
        return sb.toString();
    }
}