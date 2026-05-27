package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class Rayon extends Entite {
    private final Entite cible;

    private int age;
    private final int ageMaximal;

    public Rayon(Entite e1, Entite e2, int degats, int ageMaximal, List<Alteration> alterations) {
        this(e1, e2, degats, ageMaximal);
        donnerAlterations(alterations);
    }

    public Rayon(Entite e1, Entite e2, int degats, int ageMaximal){
        super(e1.getEnvironnement(), e1.getLigne(), e1.getColonne());
        this.cible = e2;
        age = 0;
        this.ageMaximal = ageMaximal;
        infligerDegats(degats);
    }

    public Entite getCible(){
        return cible;
    }

    public boolean aDepasseAgeMaximal(){
        return age > ageMaximal;
    }

    private void infligerDegats(int degats){
        if (cible instanceof Maladie)
            ((Maladie) cible).prendreDegats(degats);
    }

    public void donnerAlterations(List<Alteration> alterations){
        if (cible instanceof Maladie)
            for (Alteration alteration : alterations)
                ((Maladie) cible).ajouter(alteration.copieAlteration());
    }

    @Override
    public void agir(){
        age++;
    }
}
