package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.apparition.Generateur;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.utilitaires.CodeTuile;

import java.util.ArrayList;
import java.util.List;

public class Carte {
    private final Environnement environnement;
    private boolean[][] carteStatique;
    private final List<Integer> objectif;
    private final ArrayList<Cellule> cellules;
    private final ArrayList<Generateur> generateurs;

    public Carte(Environnement environnement) {
        this.environnement = environnement;

        initCarteStatique();
        cellules = new ArrayList<>();
        objectif = List.of(7, 29); // hard coded pour l'instant

        generateurs = new ArrayList<>();
    }

    public void initGenerateurs(){
        // hard coded pour l'instant
        getGenerateurs().add(new Generateur(environnement, 2, 0));
    }

    private void initCarteStatique(){
        // hard coded pour l'instant
        // des variables avec des noms plus courts pour la lisibilité
        boolean f = false;
        boolean t = true;

        this.carteStatique = new boolean[][]{
                {t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t},
                {t, f, t, f, f, f, f, f, t, f, f, f, f, t, f, f, f, t, f, t, t, t, f, f, f, f, f, f, f, t},
                {f, f, f, f, f, t, t, f, f, f, t, f, f, t, f, f, f, t, f, f, f, f, f, f, t, t, f, f, f, t},
                {t, t, f, t, f, f, t, t, f, f, f, t, f, f, f, f, f, t, f, t, f, f, t, f, f, t, t, f, f, t},
                {t, f, f, t, t, f, t, f, f, t, f, t, f, f, f, t, f, f, f, t, t, f, f, f, f, f, f, f, f, t},
                {t, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, f, f, f, f, t, t, f, t, f, f, t},
                {t, f, f, t, t, f, f, f, t, f, f, f, t, f, f, f, f, f, f, f, f, f, t, f, f, f, f, t, f, t},
                {t, f, f, f, f, f, f, t, f, f, t, f, t, f, f, t, f, f, t, f, t, f, f, f, f, f, f, f, f, f},
                {t, t, f, t, f, t, f, f, f, f, f, f, t, f, t, f, f, f, f, f, f, f, f, f, t, f, f, t, f, t},
                {t, f, f, f, f, f, f, t, f, t, f, f, f, f, t, f, f, t, t, f, f, f, t, t, f, f, t, f, f, t},
                {t, f, f, t, f, f, t, f, f, f, t, f, f, f, f, f, f, f, f, f, t, f, f, f, f, t, f, f, f, t},
                {t, f, t, f, t, f, f, f, f, f, f, f, t, f, t, f, f, f, f, f, f, f, t, f, f, t, f, t, f, t},
                {t, f, f, f, f, f, t, f, f, f, f, f, f, f, f, f, t, f, t, f, t, f, f, t, f, t, f, f, f, t},
                {t, t, f, f, f, f, f, f, f, t, f, f, f, t, f, f, f, f, f, f, t, f, f, f, f, f, f, f, f, t},
                {t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t}
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

    public ArrayList<Cellule> getCellules() {
        return cellules;
    }

    public ArrayList<Generateur> getGenerateurs() {
        return generateurs;
    }

    public CodeTuile getCode(int ligne, int colonne) {
        if (List.of(ligne, colonne).equals(objectif))
            return CodeTuile.OBJECTIF;

        for (Generateur g : generateurs)
            if ((int)g.getLigne() == ligne && (int)g.getColonne() == colonne)
                return CodeTuile.GENERATEUR;

        for (Cellule c : cellules)
            if ((int)c.getLigne() == ligne && (int)c.getColonne() == colonne)
                return CodeTuile.codeDe(c);

        if (carteStatique[ligne][colonne])
            return CodeTuile.MUR;
        else
            return CodeTuile.VIDE;
    }

    public boolean emplacementVide(int ligne, int colonne){
        return getCode(ligne, colonne).estVide();
    }

    public boolean peutMarcher(int ligne, int colonne) {
        return getCode(ligne, colonne).peutMarcher();
    }

    public boolean estCellule(int ligne, int colonne) {
        return getCode(ligne, colonne).estCodeCellule();
    }

    public boolean peutVoirAuTravers(int ligne, int colonne, boolean ignorerCellules){
        return getCode(ligne, colonne).peutVoirAuTravers(ignorerCellules);
    }

    /**
     * Retourne vrai si (ligne;colonne) se trouve dans les bornes du terrain
     * @param ligne une ligne
     * @param colonne une colonne
     * @return si (i;y) se trouve dans les bornes du terrain
     */
    public boolean dansBornes(double ligne, double colonne){
        return (0 <= ligne && ligne < getHauteur()) && (0 <= colonne && colonne < getLargeur());
    }

    /**
     * Vérifie si les générateurs ont un chemins disponible pour les maladies vers la fin.
     * @return Vrai si bloqué
     */
    public boolean generateursBloques(){
        for (Generateur generateur : generateurs) {
            if(environnement.getDeplacement().estBloquee(generateur.position()))
                return true;
        }
        return false;
    }


}
