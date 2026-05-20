package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.cellule.Cellule;
import fr.iut.virusdefense.modele.spawn.Generateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Carte {
    public static final int VIDE = 0;
    public static final int MUR = 1;
    public static final int OBJECTIF = 12;
    public static final int GENERATEUR = 15;
    public static final int SAINPLE = 101;

    private Environnement environnement;
    private boolean[][] carteStatique;
    private List<Integer> objectif;
    private final ObservableList<Cellule> cellules;
    private final ObservableList<Generateur> generateurs;

    public Carte(Environnement environnement) {
        this.environnement = environnement;

        initCarteStatique();
        cellules = FXCollections.observableArrayList();
        objectif = List.of(7, 19);

        generateurs = FXCollections.observableArrayList();
    }

    public void initGenerateurs(){
        getGenerateurs().add(new Generateur(environnement, 0, 2));
    }

    private void initCarteStatique(){
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
    }

    public int getHauteur(){
        return carteStatique.length;
    }

    public int getLargeur(){
        return carteStatique[0].length;
    }

    public List<Integer> getObjectif(){
        return objectif;
    }

    public ObservableList<Cellule> getCellules() {
        return cellules;
    }

    public ObservableList<Generateur> getGenerateurs() {
        return generateurs;
    }

    public int getValeurCase(int ligne, int col) {
        if (List.of(ligne, col).equals(objectif))
            return OBJECTIF;

        for (Generateur g : generateurs)
            if (g.getX() == col && g.getY() == ligne)
                return GENERATEUR;

        for (Cellule c : cellules)
            if (c.getX() == col && c.getY() == ligne)
                return SAINPLE;

        if (this.carteStatique[ligne][col])
            return MUR;
        else
            return VIDE;
    }

    public boolean peutMarcher(int ligne, int colonne){
        int val = getValeurCase(ligne, colonne);
        return val == VIDE || val == OBJECTIF || val == GENERATEUR;
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
