package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.maladie.Maladie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Représente le terrain dans lequel il y aura les cellules et maladies
 */
public class Terrain {

    /// La carte, indique où sont les murs et emplacements vides
    private int[][] map;

    private int[] objectif;

    private Map<int[], int[]> predecesseurs;

    private final ObservableList<Maladie> maladies;

    /**
     * Créé un terrain sans maladies ni cellules
     */
    public Terrain(int[] objectif){
        maladies = FXCollections.observableArrayList();
        initMap();
        this.objectif = objectif;
        predecesseurs = new HashMap<>();
        algoBFS();
    }

    public int[][] getMap(){
        return map;
    }
    
    public int getHauteur(){
        return map.length;
    }

    public int getLargeur(){
        return map[0].length;
    }

    public ObservableList<Maladie> getMaladies(){
        return maladies;
    }

    public void ajouter(Maladie m){
        maladies.add(m);
    }

    /**
     * Retourne vrai si (ligne;colonne) se trouve dans les bornes du terrain
     * @param ligne une ligne
     * @param col une colonne
     * @return si (i;y) se trouve dans les bornes du terrain
     */
    public boolean dansBornes(double ligne, double col){
        return (0 <= ligne && ligne < getHauteur()) && (0 <= col && col < getLargeur());
    }

    /**
     * Créé la map (pour l'instant une seul map possible faite à la main)
     */
    private void initMap(){
        // des variables avec des noms plus courts pour la lisibilité
        int v = Tuiles.VIDE;
        int m = Tuiles.MUR;

        map = new int[][]{
                {m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m},
                {m, v, v, v, v, v, v, m, m, m, v, v, v, v, m, m, m, m, m, m},
                {v, v, m, m, v, v, v, m, m, m, v, m, m, v, v, m, m, m, m, m},
                {m, v, v, m, v, v, v, v, m, m, v, m, m, v, v, v, v, v, m, m},
                {m, v, v, v, v, m, v, v, v, m, v, v, v, v, v, m, m, v, v, m},
                {m, v, v, v, v, m, m, v, v, v, v, v, v, v, v, v, m, m, v, m},
                {m, v, v, v, v, v, v, v, m, v, v, v, v, v, m, v, v, m, v, m},
                {m, v, v, v, m, v, v, v, m, m, v, v, m, m, m, m, v, v, v, v},
                {m, v, v, v, m, m, v, m, m, m, m, v, v, v, v, v, v, v, v, m},
                {m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m}
        };
    }

    private void algoBFS() {
        LinkedList<int[]> fifo = new LinkedList<>();
        int[] s;
        fifo.add(objectif);
        predecesseurs.put(objectif, null);
        while (!fifo.isEmpty()){
            s = fifo.poll();
            for (int[] t : adjacents(s)){
                if (!predecesseurContient(t)){
                    fifo.add(t);
                    predecesseurs.put(t, s);
                }
            }
        }
    }

    public int[] predecesseurDe(int[] coords){
        for (int[] key : predecesseurs.keySet())
            if (Arrays.equals(key, coords))
                return predecesseurs.get(key);
        return null;
    }

    private boolean predecesseurContient(int[] coords){
        for (int[] key : predecesseurs.keySet())
            if (Arrays.equals(key, coords))
                return true;
        return false;
    }

    private ArrayList<int[]> adjacents(int[] s){
        ArrayList<int[]> adj = new ArrayList<>();

        int[][] decalages = new int[][]{
                {-1, 0},
                {0, -1},
                {+1, 0},
                {0, +1}
        };
        int x, y;

        for (int[] decalage : decalages){
            x = s[0] + decalage[0];
            y = s[1] + decalage[1];
            if (dansBornes(x, y) && map[x][y] == Tuiles.VIDE)
                adj.add(new int[]{x, y});
        }

        return adj;
    }

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour(){
        //algoBFS();
        for (Maladie m : maladies)
            m.agir();
    }

}
