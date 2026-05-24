package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;

public class Rayon extends Entite {
    public double ligne2;
    public double colonne2;

    private int age;
    private final int ageMaximal;

    public Rayon(Entite e1, Entite e2, int ageMaximal){
        this(e1.getEnvironnement(), e1.getLigne(), e1.getColonne(), e2.getLigne(), e2.getColonne(), ageMaximal);
    }

    public Rayon(Environnement environnement, double ligne, double colonne, double ligne2, double colonne2, int ageMaximal){
        super(environnement, ligne, colonne);
        this.ligne2 = ligne2;
        this.colonne2 = colonne2;
        age = 0;
        this.ageMaximal = ageMaximal;
    }

    public boolean aDepasseAgeMaximal(){
        return age > ageMaximal;
    }

    public double getLigne2() {
        return ligne2;
    }

    public double getColonne2() {
        return colonne2;
    }

    @Override
    public void agir(){
        age++;
    }
}
