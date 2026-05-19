package fr.iut.virusdefense.modele;

import java.util.List;

public class Carte {
    private boolean[][] carteStatique;
    private List<Integer> objectif;

    public Carte(){
        // des variables avec des noms plus courts pour la lisibilité
        boolean f = false;
        boolean t = true;

        this.carteStatique = new boolean[][]{
                {t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t},
                {t, f, f, f, f, f, f, t, t, t, f, f, f, f, t, t, t, t, t, t},
                {f, f, t, t, f, f, f, t, t, t, f, t, t, f, f, t, t, t, t, t},
                {t, f, f, t, f, f, f, f, t, t, f, t, t, f, f, f, f, f, t, t},
                {t, f, f, f, f, t, f, f, f, t, f, f, f, f, f, t, t, f, f, t},
                {t, f, f, f, f, t, t, f, f, f, f, f, f, f, f, f, t, t, f, t},
                {t, f, f, f, f, f, f, f, t, f, f, f, f, f, t, f, f, t, f, t},
                {t, f, f, f, t, f, f, f, t, t, f, f, t, t, t, t, f, f, f, f},
                {t, f, f, f, t, t, f, t, t, t, t, f, f, f, f, f, f, f, f, t},
                {t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t}
        };

        objectif.add(7);
        objectif.add(19);
    }

    public boolean[][] getMap(){
        return carteStatique;
    }

    public int getHauteur(){
        return carteStatique.length;
    }

    public int getLargeur(){
        return carteStatique[0].length;
    }

    public List<Integer> getObjectif(){ return objectif; }

    public boolean getValeurCase(int ligne, int col) {
        return this.carteStatique[ligne][col];
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
}
