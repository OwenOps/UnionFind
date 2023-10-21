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

    public void ajouterAmis(int n) {
        listeAmis.add(n);
        groupTaille.add(SOLO);
    }

    public void ajouterAmis(List<Integer> liste) {
        if (liste.isEmpty())
            return;

        listeAmis.addAll(liste);
    }

    public int find(int n) {
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

    public void isolate(int n) {
        listeAmis.set(n, null);

        //Veut dire que c'etait le chef vu que on a mis a null et il reste encore son groupe
        if (listeAmis.contains(n)) {
            int index = listeAmis.indexOf(n);
            int newChef = listeAmis.indexOf(n);
            groupTaille.set(newChef, groupTaille.get(n) - 1);
            while (index != -1) {
                listeAmis.set(index, newChef);
                index = listeAmis.indexOf(n);
            }
        }
        groupTaille.set(n , SOLO);
        listeAmis.set(n, n);
    }

    public void isolate2(int n) {
        if (groupTaille.get(n) > 1) {
            int currentIndex = listeAmis.get(n);
            int newChef = listeAmis.indexOf(n);

            // Vérifie que l'index suivant n'est pas égal à l'index actuel
            if (listeAmis.indexOf(newChef) == currentIndex) {
                newChef = listeAmis.lastIndexOf(n);
            }

            //Change le groupe valeur du chef pour rediriger vers un autre chef
            listeAmis.set(n, newChef);

            //Met le nouveau chef
            listeAmis.set(newChef, newChef);

            //On fait un find de chaque element du groupe pour mettre a jour le groupe
            int maj = 0;
            while (maj != -1) {
                find(maj);
                maj = listeAmis.indexOf(n);
            }
            groupTaille.set(newChef, groupTaille.get(n) - 1);
        }
        else {
            int chef = find(n);
            groupTaille.set(chef, groupSize(n) - 1);
        }
        groupTaille.set(n , SOLO);
        listeAmis.set(n, n);
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

    public int friendSize() {
        return listeAmis.size();
    }

    public int groupSize(int n) {
        return groupTaille.get(find(n));
    }

    public int plusGrandGroupe() { return Collections.max(groupTaille); }

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
