package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.alteration;

import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Alteration {
    private int duree;
    private Maladie maladie;

    public Alteration(int duree){
        this.duree = duree;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public void setMaladie(Maladie m) {
        this.maladie = m;
    }

    public boolean estFinie(){
        return (0 >= duree || !maladie.estVivant());
    }

    public final void agir(){
        duree--;
        affecter();
    }

    public abstract void affecter();
}
