package fr.iut.virusdefense.modele.utilitaires;

import fr.iut.virusdefense.modele.maladies.*;

public enum CodeMaladie {
    MALADIEINCONNUE,
    BACTERIEBANALE,
    PARASITE,
    VIRUS,
    VIRUSCOMPOSE;

    public static CodeMaladie codeDe(Maladie m) {
        if (m instanceof BacterieBanale)
            return BACTERIEBANALE;

        if (m instanceof Parasite)
            return PARASITE;

        if (m instanceof Virus)
            return VIRUS;

        if (m instanceof VirusComposé)
            return VIRUSCOMPOSE;

        return MALADIEINCONNUE;
    }

    public static CodeMaladie codeAleatoire(){
        return switch ((int) (Math.random() * CodeMaladie.values().length - 1)){
            case 0 -> BACTERIEBANALE;
            case 1 -> PARASITE;
            case 2 -> VIRUS;
            case 3 -> VIRUSCOMPOSE;

            default -> MALADIEINCONNUE;
        };
    }
}
