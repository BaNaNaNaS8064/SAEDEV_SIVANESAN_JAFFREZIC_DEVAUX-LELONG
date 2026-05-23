package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Attaque {
    private final Cellule cellule;
    private int degats;

    public Attaque(Cellule cellule, int degats){
        this.cellule = cellule;
        this.degats = degats;
    }

    public int getDegats() {
        return degats;
    }

    public Cellule getCellule() {
        return cellule;
    }

    public void attaqueCibles(){
        getCellule().getReconnaissance().getCibles().forEach(this::attaque);
    }

    public abstract void attaque(Maladie m);

}
