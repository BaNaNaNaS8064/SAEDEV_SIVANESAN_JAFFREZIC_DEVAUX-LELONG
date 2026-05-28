package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.maladies.Maladie;

public class RecPlusieurs extends Reconnaissance{
    public RecPlusieurs(Cellule cellule , double portee , int n){
        super(cellule , portee, n);
    }

    @Override
    public boolean valide(Maladie m) {
        return m.estVivant() && aPortee(m) && getCellule().voit(m,true);
    }
}
