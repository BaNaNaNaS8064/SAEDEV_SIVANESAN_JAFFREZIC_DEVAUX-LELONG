package fr.iut.virusdefense.modele;

import java.util.ArrayList;
import java.util.Arrays;

public class Terrain {

    private int[][] map;
    private ArrayList<Maladie> maladies;

    public Terrain(){
        map = new int[10][20];
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

    public boolean dansBornes(int x, int y){
        return (0 <= x && x < getLargeur()) && (0 <= y && y < getHauteur());
    }

    public ArrayList<Maladie> getMaladies(){
        return maladies;
    }

    public void ajouter(Maladie m){
        maladies.add(m);
    }

    private void initMap(){
        Arrays.fill(map[0], 1);
        Arrays.fill(map[getHauteur()-1], 1);

    }

    public void unTour(){
        for (Maladie m : maladies)
            m.agir();
    }



}
