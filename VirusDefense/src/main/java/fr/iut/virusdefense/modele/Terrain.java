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

    private List<Integer> objectif;

    private Map<List<Integer>, List<Integer>> predecesseurs;

    private final ObservableList<Maladie> maladies;

    private int tour;

    /**
     * Créé un terrain sans maladies ni cellules
     */
    public Terrain(){
        maladies = FXCollections.observableArrayList();
        initMap();
        objectif = chercherObjectif();
        predecesseurs = new HashMap<>();
        faireBFS();
        tour = 0;
    }

    public List<Integer> getObjectif() {
        return objectif;
    }

    public int getTour() {
        return tour;
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
                {m, v, v, v, m, v, v, v, m, m, v, v, m, m, m, m, v, v, v, Tuiles.OBJECTIF},
                {m, v, v, v, m, m, v, m, m, m, m, v, v, v, v, v, v, v, v, m},
                {m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m}
        };
    }

    private List<Integer> chercherObjectif(){
        for (int i = 0; i < getHauteur(); i++)
            for (int j = 0; j < getLargeur(); j++)
                if (map[i][j] == Tuiles.OBJECTIF)
                    return List.of(i, j);

        return null;
    }

    private void faireBFS() {
        predecesseurs.clear();

        LinkedList<List<Integer>> fifo = new LinkedList<>();
        List<Integer> caseActuelle;

        fifo.add(List.of(objectif.get(0), objectif.get(1)));
        predecesseurs.put(List.of(objectif.get(0), objectif.get(1)), null);

        while (!fifo.isEmpty()){
            caseActuelle = fifo.poll();
            for (List<Integer> voisin : voisins(caseActuelle)){
                if (!predecesseurs.containsKey(voisin)){
                    fifo.add(voisin);
                    predecesseurs.put(voisin, caseActuelle);
                }
            }
        }
    }

    public List<Integer> predecesseurDe(List<Integer> coords){
        return predecesseurs.get(coords);
    }

    private ArrayList<List<Integer>> voisins(List<Integer> s){
        ArrayList<List<Integer>> voisins = new ArrayList<>();

        int[][] decalages = new int[][]{
                {-1, 0},
                {0, -1},
                {+1, 0},
                {0, +1}
        };
        int ligne, col;

        for (int[] decalage : decalages){
            ligne = s.get(0) + decalage[0];
            col = s.get(1) + decalage[1];
            if (dansBornes(ligne, col) && map[ligne][col] == Tuiles.VIDE)
                voisins.add(List.of(ligne, col));
        }

        return voisins;
    }

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour(){
        //algoBFS();
        for (Maladie m : maladies)
            m.agir();
        tour++;
    }

}
