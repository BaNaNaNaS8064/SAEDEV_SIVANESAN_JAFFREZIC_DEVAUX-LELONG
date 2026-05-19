package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class BactérieBanale extends Maladie{

    public BactérieBanale(Environnement environnement, int x, int y){
        super(environnement, x, y, 10, 0.03);
    }
}
