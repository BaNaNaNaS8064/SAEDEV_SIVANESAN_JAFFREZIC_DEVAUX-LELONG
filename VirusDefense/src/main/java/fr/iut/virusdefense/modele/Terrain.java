package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.maladie.Maladie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Représente le terrain dans lequel il y aura les cellules et maladies
 */
public class Terrain {

    /// La carte, indique où sont les murs et emplacements vides
    private int[][] map;

    private final ObservableList<Maladie> maladies;

    /**
     * Créé un terrain sans maladies ni cellules
     */
    public Terrain(){
        map = new int[10][20];
        maladies = FXCollections.observableArrayList();
        initMap();
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

    /**
     * Retourne vrai si (x;y) se trouve dans les bornes du terrain
     * @param x une position x
     * @param y une position y
     * @return si (x;y) se trouve dans les bornes du terrain
     */
    public boolean dansBornes(double x, double y){
        return (0 <= x && x < getLargeur()) && (0 <= y && y < getHauteur());
    }

    public ObservableList<Maladie> getMaladies(){
        return maladies;
    }

    public void ajouter(Maladie m){
        maladies.add(m);
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

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour(){
        for (Maladie m : maladies)
            m.agir();
    }

}
