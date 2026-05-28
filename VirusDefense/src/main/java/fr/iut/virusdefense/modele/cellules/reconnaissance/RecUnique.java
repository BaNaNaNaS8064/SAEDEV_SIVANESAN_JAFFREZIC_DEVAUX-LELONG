package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.maladies.Maladie;

public class RecUnique extends Reconnaissance{

    public RecUnique(Cellule cellule , double portee){
        super(cellule , portee, 1);
    }

    @Override
    public boolean valide(Maladie m) {
        return m.estVivant() && aPortee(m) && getCellule().voit(m, true);
    }
}
