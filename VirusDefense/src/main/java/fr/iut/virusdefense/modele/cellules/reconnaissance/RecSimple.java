package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;


public class RecSimple extends Reconnaissance{

    public RecSimple(Environnement environnement, double ligne, double colonne, double portee, int nombreCiblesMax){
        super(environnement, ligne, colonne, portee, nombreCiblesMax);
    }

    @Override
    public boolean estValide(Maladie m) {
        return m.estVivant() && aPortee(m) && voit(m,true);
    }
}
