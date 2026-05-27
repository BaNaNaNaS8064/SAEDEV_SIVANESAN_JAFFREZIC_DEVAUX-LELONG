package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.maladies.Maladie;

public class Rayon extends Entite {
    private final Entite cible;

    private int age;
    private final int ageMaximal;

    public Rayon(Entite e1, Entite e2, int degats, int ageMaximal){
        super(e1.getEnvironnement(), e1.getLigne(), e1.getLigne());
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

    @Override
    public void agir(){
        age++;
    }
}
