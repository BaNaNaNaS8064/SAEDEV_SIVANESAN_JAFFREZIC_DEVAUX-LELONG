package fr.iut.virusdefense.modele.utilitaires;

import fr.iut.virusdefense.modele.maladies.*;

public enum CodeMaladie {
    MALADIEINCONNUE,
    BACTERIEBANALE,
    PARASITE,
    VIRUS;

    public CodeMaladie codeDe(Maladie m) {
        if (m instanceof BacterieBanale)
            return BACTERIEBANALE;

        if (m instanceof Parasite)
            return PARASITE;

        if (m instanceof Virus)
            return VIRUS;

        return MALADIEINCONNUE;
    }

    public CodeMaladie codeAleatoire(){
        return switch ((int) (Math.random() * 3)){
            case 0 -> BACTERIEBANALE;
            case 1 -> PARASITE;
            case 2 -> VIRUS;
            
            default -> MALADIEINCONNUE;
        };
    }
}
