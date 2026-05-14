package fr.iut.virusdefense.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Arrays;

public class Terrain {

    private final int[][] map;
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
        Arrays.fill(map[0], Tuiles.MUR);
        Arrays.fill(map[getHauteur()-1], Tuiles.MUR);
        map[3][16] = Tuiles.MUR;
        map[4][4] = Tuiles.MUR;
        map[8][13] = Tuiles.MUR;
        map[1][0] = Tuiles.MUR;
        map[2][0] = Tuiles.MUR;
        map[3][0] = Tuiles.MUR;
        map[4][0] = Tuiles.MUR;
        map[6][0] = Tuiles.MUR;
        map[7][0] = Tuiles.MUR;
        map[8][0] = Tuiles.MUR;
        map[9][0] = Tuiles.MUR;


    }

    public void unTour(){
        for (Maladie m : maladies)
            m.agir();
    }



}
