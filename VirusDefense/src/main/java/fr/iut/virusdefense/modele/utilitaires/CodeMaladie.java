package fr.iut.virusdefense.modele.utilitaires;

import fr.iut.virusdefense.modele.maladies.*;

public enum CodeMaladie {
    MALADIEINCONNUE,
    BACTERIEBANALE,
    PARASITE,
    VIRUS;

    public static CodeMaladie codeDe(Maladie m) {
        if (m instanceof BacterieBanale)
            return BACTERIEBANALE;

        if (m instanceof Parasite)
            return PARASITE;

        if (m instanceof Virus)
            return VIRUS;

        return MALADIEINCONNUE;
    }

    public static CodeMaladie codeAleatoire(){
        return switch ((int) (Math.random() * 3)){
            case 0 -> BACTERIEBANALE;
            case 1 -> PARASITE;
            case 2 -> VIRUS;

            default -> MALADIEINCONNUE;
        };
    }
}
