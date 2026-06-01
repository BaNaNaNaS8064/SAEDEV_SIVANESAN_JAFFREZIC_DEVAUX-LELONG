package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.Tumeur;

import java.util.List;

public class Rayon extends EntiteAtk {
    private final Entite cible;

    private final int degats;

    private int age;
    private final int ageMaximal;

    public Rayon(Entite e1, Entite e2, int degats, int ageMaximal, List<Alteration> alterations) {
        super(e1.getEnvironnement(), e1.getLigne(), e1.getColonne(), alterations);
        this.cible = e2;

        age = 0;
        this.ageMaximal = ageMaximal;

        this.degats = degats;

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
        if (cible instanceof Maladie && !(cible instanceof Tumeur))
            for (Alteration alt : getAlterations()){
                alt.setMaladie((Maladie) cible);
                getEnvironnement().getAlterations().add(alt);
            }
    }

    @Override
    public void agir(){
        age++;
    }
}
