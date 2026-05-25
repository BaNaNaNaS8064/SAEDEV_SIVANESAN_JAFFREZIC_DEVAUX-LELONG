package fr.iut.virusdefense.modele.utilitaires;

import fr.iut.virusdefense.modele.cellules.*;

public enum CodeTuile {
    VIDE,
    MUR,
    OBJECTIF,
    GENERATEUR,

    CELLULEINCONNUE,
    SAINPLE;

    public static CodeTuile codeDe(Cellule c){
        if (c instanceof Sainple)
            return SAINPLE;

        return CELLULEINCONNUE;
    }

    public static boolean peutMarcher(CodeTuile codeTuile){
        return codeTuile == CodeTuile.VIDE
                || codeTuile == CodeTuile.OBJECTIF
                || codeTuile == CodeTuile.GENERATEUR;
    }

    public static boolean peutVoirAuTravers(CodeTuile codeTuile, boolean ignorerCellules){
        if (ignorerCellules)
            return codeTuile != CodeTuile.MUR;
        else
            return peutMarcher(codeTuile);
    }

    public static boolean estVide(CodeTuile codeTuile){
        return codeTuile == VIDE;
    }
}