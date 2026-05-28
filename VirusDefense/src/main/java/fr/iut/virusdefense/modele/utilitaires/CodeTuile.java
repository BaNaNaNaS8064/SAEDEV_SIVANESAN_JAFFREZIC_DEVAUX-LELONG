package fr.iut.virusdefense.modele.utilitaires;

import fr.iut.virusdefense.modele.cellules.*;

public enum CodeTuile {
    VIDE,
    MUR,
    OBJECTIF,
    GENERATEUR,

    CELLULEINCONNUE,
    SAINPLE,
    LASERE;

    public boolean peutMarcher(){
        return this == CodeTuile.VIDE
                || this == CodeTuile.OBJECTIF
                || this == CodeTuile.GENERATEUR;
    }

    public boolean peutVoirAuTravers(boolean ignorerCellules){
        if (ignorerCellules)
            return this != CodeTuile.MUR;
        else
            return peutMarcher();
    }

    public boolean estVide(){
        return this == VIDE;
    }

    public boolean estCodeCellule(){
        return !peutMarcher() && this != MUR;
    }

    public static CodeTuile codeDe(Cellule c){
        if (c instanceof Sainple)
            return SAINPLE;

        if (c instanceof Lasere)
            return LASERE;

        return CELLULEINCONNUE;
    }
}