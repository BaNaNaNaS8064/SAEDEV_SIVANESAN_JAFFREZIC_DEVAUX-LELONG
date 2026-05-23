package fr.iut.virusdefense.modele.entitesGeneriques;

import fr.iut.virusdefense.modele.Environnement;

public class Rayon extends Entite {
    public double ligne2;
    public double colonne2;

    private int age;

    public Rayon(Environnement environnement, double ligne, double colonne, double ligne2, double colonne2){
        super(environnement, ligne, colonne);
        this.ligne2 = ligne2;
        this.colonne2 = colonne2;
        age = 0;
    }

    public int getAge() {
        return age;
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
