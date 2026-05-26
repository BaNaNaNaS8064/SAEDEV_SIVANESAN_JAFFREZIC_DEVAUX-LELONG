package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Attaque {
    private final Cellule cellule;
    private int degats;
    private Alteration alteration;

    public Attaque(Cellule cellule, int degats , Alteration alteration){
        this.cellule = cellule;
        this.degats = degats;
        this.alteration = alteration;
    }

    public int getDegats() {
        return degats;
    }

    public Cellule getCellule() {
        return cellule;
    }

    public Alteration getAlteration() {
        return alteration;
    }

    public void attaqueCibles(){
        getCellule().getReconnaissance().getCibles().forEach(this::attaque);
    }

    public abstract void attaque(Maladie m);

}
