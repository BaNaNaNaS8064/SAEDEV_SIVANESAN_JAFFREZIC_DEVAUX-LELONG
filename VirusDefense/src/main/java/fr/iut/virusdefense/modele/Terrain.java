package fr.iut.virusdefense.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Arrays;

public class Terrain {

    private int[][] map;
    private ObservableList<Maladie> maladies;

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
        Arrays.fill(map[0], 1);
        Arrays.fill(map[getHauteur()-1], 1);
        map[3][16] = 1;
        map[4][4] = 1;
        map[8][13] = 1;
        map[1][0] = 1;
        map[2][0] = 1;
        map[3][0] = 1;
        map[4][0] = 1;
        map[6][0] = 1;
        map[7][0] = 1;
        map[8][0] = 1;
        map[9][0] = 1;


    }

    public void unTour(){
        for (Maladie m : maladies)
            m.agir();
    }



}
