package fr.iut.virusdefense.modele;

import java.util.Arrays;

public class Terrain {

    private int[][] map;

    public Terrain(){
        map = new int[10][20];
        initMap();
    }

    public int[][] getMap(){
        return map;
    }

    private void initMap(){
        for (int[] ligne : map)
            Arrays.fill(ligne, 0);
    }

}
