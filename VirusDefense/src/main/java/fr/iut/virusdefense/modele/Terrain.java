package fr.iut.virusdefense.modele;

public class Terrain {

    private int[][] map;

    public Terrain(){
        map = new int[10][21];
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

    private void initMap(){
        int v = 0;
        for (int i=0; i<map.length; i++)
            for (int j=0; j<map[i].length; j++)
                map[i][j] = (v++ % 2);
    }

}
