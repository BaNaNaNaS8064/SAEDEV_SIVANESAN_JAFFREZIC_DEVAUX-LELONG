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

    /*
     * 1 = Mur
     * 0 = Vide
     */
    private void initMap(){
        map= new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
    }

    public void unTour(){
        for (Maladie m : maladies)
            m.agir();
    }

}
