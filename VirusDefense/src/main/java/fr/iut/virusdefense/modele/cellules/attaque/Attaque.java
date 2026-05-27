package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class Attaque {
    private final Cellule cellule;
    private int degats;
    private ArrayList<Alteration> alterations;

    public Attaque(Cellule cellule, int degats){
        this.cellule = cellule;
        this.degats = degats;
        this.alterations = new ArrayList<>();
    }

    public int getDegats() {
        return degats;
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

    public void attaqueCibles(){
        getCellule().getReconnaissance().getCibles().forEach(this::attaque);
    }

    public abstract void attaque(Maladie m);

}
