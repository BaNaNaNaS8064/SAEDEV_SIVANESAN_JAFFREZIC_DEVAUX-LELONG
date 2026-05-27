package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.maladies.Maladie;

public class RecTous extends Reconnaissance{

    public RecTous(Cellule cellule , double portee){
        super(cellule , portee, Integer.MAX_VALUE);
    }

    @Override
    public boolean valide(Maladie m) {
        return m.estVivant() && aPortee(m);
    }
}
