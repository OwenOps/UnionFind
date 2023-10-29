package Amis;

import java.util.*;

/**
 * La classe Amis permet de gérer des groupes d'amis et d'effectuer des opérations
 * telles que l'ajout d'amis, la recherche de groupes, l'isolation, l'union de groupes, etc.
 */
public class Amis {
    private ArrayList<Integer> listeAmis; // Liste des amis
    private ArrayList<Integer> groupTaille; // Taille des groupes
    public static final int SOLO = 1;

    /**
     * Constructeur par défaut de la classe Amis.
     * Initialise les listes d'amis et de tailles de groupes.
     */
    public Amis() {
        listeAmis = new ArrayList<>();
        groupTaille = new ArrayList<>();
    }

    /**
     * Constructeur de la classe Amis avec une taille spécifiée.
     * Initialise les listes avec la taille donnée et affecte chaque ami à un groupe.
     * @param taille Taille initiale des amis à créer
     */
    public Amis(int taille) {
        listeAmis = new ArrayList<>(taille);
        groupTaille = new ArrayList<>(taille);

        assert (taille > 0);
        for (int i = 0; i < taille; i++) {
            listeAmis.add(i);
            groupTaille.add(SOLO);
        }
    }

    /**
     * Ajoute un nouvel ami à la liste, l'affecte à un groupe seul.
     */
    public void ajouterAmis() {
        listeAmis.add(listeAmis.size());
        groupTaille.add(SOLO);
    }

    /**
     * Recherche le représentant du groupe auquel appartient l'ami donné.
     * @param n Indice de l'ami dans la liste
     * @return Le représentant du groupe de l'ami
     */
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

    /**
     * Vérifie si l'indice de l'ami est en dehors des limites de la liste.
     * @param n Indice de l'ami à vérifier
     */
    private void enDehorsListe(int n) { assert(n < listeAmis.size() && n >= 0); }

    /**
     * Isole l'ami donné en créant un nouveau groupe ou en rejoignant un groupe existant.
     * @param n Indice de l'ami à isoler
     */
    public void isolate(int n) {
        enDehorsListe(n);
        int numGroup = listeAmis.get(n);

        if (n != numGroup) {
            int representant = find(n);
            groupTaille.set(representant, groupTaille.get(representant) - SOLO);
            listeAmis.set(n, n);
        } else {
            int newChef = -1;
            for (int i = 0; i < listeAmis.size(); i++) {
                int currentGroup = listeAmis.get(i);

                if (numGroup == currentGroup && i != numGroup) {
                    if (newChef == -1) {
                        newChef = i;
                        groupTaille.set(newChef, groupTaille.get(n) - SOLO);
                    }
                    listeAmis.set(i, newChef);
                }
            }
            groupTaille.set(n, SOLO);
        }
    }

    /**
     * Réalise l'union de deux groupes d'amis distincts.
     * @param g1 Indice du premier ami/groupe
     * @param g2 Indice du deuxième ami/groupe
     */
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
        } else {
            listeAmis.set(r2, r1);
            groupTaille.set(r1, taille);

            if (s1 == s2) {
                groupTaille.set(r1, s1 + 1);
            }
        }
    }

    /**
     * Retourne la taille de la liste des amis.
     * @return La taille de la liste des amis
     */
    public int tabSize() {
        return listeAmis.size();
    }

    /**
     * Retourne la taille du groupe auquel l'ami donné appartient.
     * @param n Indice de l'ami dans la liste
     * @return La taille du groupe de l'ami
     */
    public int groupSize(int n) {
        return groupTaille.get(find(n));
    }

    /**
     * Retourne la taille du plus grand groupe.
     * @return La taille du plus grand groupe
     */
    public int plusGrandGroupe() {
        return Collections.max(groupTaille);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères des groupes d'amis.
     * Fonctionne uniquement pour de petits tableaux.
     * @return Une représentation des groupes d'amis
     */
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