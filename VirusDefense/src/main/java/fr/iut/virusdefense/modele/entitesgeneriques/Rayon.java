package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class Rayon extends Entite implements EntiteAtk {
    private final Entite cible;

    private final int degats;
    private final List<Alteration> alterations;

    private int age;
    private final int ageMaximal;

    public Rayon(Entite e1, Entite e2, int degats, int ageMaximal, List<Alteration> alterations) {
        super(e1.getEnvironnement(), e1.getLigne(), e1.getColonne());
        this.cible = e2;

        age = 0;
        this.ageMaximal = ageMaximal;

        this.degats = degats;
        this.alterations = alterations;

        donnerAlterations();
        infligerDegats();
    }

    public Entite getCible(){
        return cible;
    }

    public boolean aDepasseAgeMaximal(){
        return age > ageMaximal;
    }

    public void infligerDegats(){
        if (cible instanceof Maladie)
            ((Maladie) cible).prendreDegats(degats);
    }

    public void donnerAlterations(){
        if (cible instanceof Maladie)
            for (Alteration alteration : alterations)
                ((Maladie) cible).ajouter(alteration.copieAlteration());
    }

    @Override
    public void agir(){
        age++;
    }
}
