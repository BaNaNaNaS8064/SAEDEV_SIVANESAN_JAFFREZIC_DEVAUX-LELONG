package fr.iut.virusdefense.modele.maladie;

import fr.iut.virusdefense.modele.Terrain;

public class BactérieBanale extends Maladie{

    public BactérieBanale(Terrain terrain, int x, int y){
        super(terrain, x, y, 10, 0.03);
    }
}
