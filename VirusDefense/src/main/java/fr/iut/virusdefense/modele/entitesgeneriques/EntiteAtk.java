package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;

import java.util.List;

public abstract class EntiteAtk extends Entite{
    private final List<Alteration> alterations;

    public EntiteAtk(Environnement environnement, double ligne, double colonne, List<Alteration> alterations) {
        super(environnement, ligne, colonne);
        this.alterations = alterations;
    }

    public List<Alteration> getAlterations() {
        return alterations;
    }

    public abstract void infligerDegats();
    public abstract void donnerAlterations();

}
