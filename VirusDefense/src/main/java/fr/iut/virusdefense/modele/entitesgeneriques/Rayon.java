package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;

public class Rayon extends Entite {
    private final Entite cible;

    private int age;
    private final int ageMaximal;

    public Rayon(Entite e1, Entite e2, int ageMaximal){
        this(e1.getEnvironnement(), e1.getLigne(), e1.getColonne(), e2, ageMaximal);
    }

    public Rayon(Environnement environnement, double ligne, double colonne, Entite cible, int ageMaximal) {
        super(environnement, ligne, colonne);
        this.cible = cible;
        age = 0;
        this.ageMaximal = ageMaximal;
    }

    public Entite getCible(){
        return cible;
    }

    public boolean aDepasseAgeMaximal(){
        return age > ageMaximal;
    }

    @Override
    public void agir(){
        age++;
    }
}
