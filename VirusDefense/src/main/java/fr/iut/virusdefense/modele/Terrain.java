package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.maladie.Maladie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Terrain {

    private int[][] map;
    private final ObservableList<Maladie> maladies;

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

    public boolean dansBornes(double x, double y){
        return (0 <= x && x < getLargeur()) && (0 <= y && y < getHauteur());
    }

    public ObservableList<Maladie> getMaladies(){
        return maladies;
    }

    public void ajouter(Maladie m){
        maladies.add(m);
    }

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

    public void unTour(){
        for (Maladie m : maladies)
            m.agir();
    }

}
