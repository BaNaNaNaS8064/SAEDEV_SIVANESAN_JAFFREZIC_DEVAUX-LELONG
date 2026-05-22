package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;

public abstract class Attaque {
    private Cellule cellule;
    private int degats;

    public Attaque(Cellule cellule , int degats){
        this.cellule = cellule;
        this.degats = degats;
    }

    public int getDegats() {
        return degats;
    }

    public Cellule getCellule() {
        return cellule;
    }

    abstract public void attaque();

}
