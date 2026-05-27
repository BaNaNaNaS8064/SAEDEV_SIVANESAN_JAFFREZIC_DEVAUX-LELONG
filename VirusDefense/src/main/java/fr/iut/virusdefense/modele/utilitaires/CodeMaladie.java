package fr.iut.virusdefense.modele.utilitaires;

import fr.iut.virusdefense.modele.maladies.*;

public enum CodeMaladie {
    MALADIEINCONNUE,
    BACTERIEBANALE,
    PARASITE,
    VIRUS,
    VIRUSCOMPOSE,
    PETITCHAMPIGNON,
    GRANDCHAMPIGNON,
    TUMEUR;

    public static CodeMaladie codeDe(Maladie m) {
        if (m instanceof BacterieBanale)
            return BACTERIEBANALE;

        if (m instanceof Parasite)
            return PARASITE;

        if (m instanceof Virus)
            return VIRUS;

        if (m instanceof VirusComposé)
            return VIRUSCOMPOSE;

        if (m instanceof PetitChampignon)
            return PETITCHAMPIGNON;

        if (m instanceof GrandChampignon)
            return GRANDCHAMPIGNON;

        if (m instanceof Tumeur)
            return TUMEUR;

        return MALADIEINCONNUE;
    }

    public static CodeMaladie codeAleatoire(){
        return switch ((int) (Math.random() * CodeMaladie.values().length - 2)){
            case 0 -> BACTERIEBANALE;
            case 1 -> PARASITE;
            case 2 -> VIRUS;
            case 3 -> VIRUSCOMPOSE;
            case 4 -> PETITCHAMPIGNON;
            case 5 -> GRANDCHAMPIGNON;

            default -> MALADIEINCONNUE;
        };
    }
}
