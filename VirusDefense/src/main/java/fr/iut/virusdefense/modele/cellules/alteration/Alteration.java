package fr.iut.virusdefense.modele.cellules.alteration;

import fr.iut.virusdefense.modele.Acteur;
import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Alteration implements Acteur {
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

    @Override
    public final void agir(){
        duree--;
        affecter();
    }

    public abstract void affecter();
}
