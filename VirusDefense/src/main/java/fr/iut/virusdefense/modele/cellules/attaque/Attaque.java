package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class Attaque {
    private final Cellule cellule;
    private double degats;
    private ArrayList<Alteration> alterations;

    public Attaque(Cellule cellule, double degats){
        this.cellule = cellule;
        this.degats = degats;
        this.alterations = new ArrayList<>();
    }

    public double getDegats() {
        return degats;
    }

    public void setDegats(double degats) {
        this.degats = degats;
    }

    public Cellule getCellule() {
        return cellule;
    }

    public ArrayList<Alteration> getAlterations() {
        return alterations;
    }

    public void ajouterAlteration(Alteration alt){
        alterations.add(alt);
    }

    abstract public void attaqueCibles();

}
