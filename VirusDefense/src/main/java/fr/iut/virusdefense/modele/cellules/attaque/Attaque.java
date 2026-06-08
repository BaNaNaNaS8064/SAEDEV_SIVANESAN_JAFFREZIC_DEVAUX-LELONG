package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class Attaque {
    private final ArrayList<Maladie> cibles;
    private final Environnement environnement;
    private final double ligne, colonne;
    private final ArrayList<Alteration> alterations;

    public Attaque(Environnement environnement, double ligne, double colonne, ArrayList<Maladie> cibles){
        this.environnement = environnement;
        this.ligne = ligne;
        this.colonne = colonne;
        this.alterations = new ArrayList<>();
        this.cibles = cibles;
    }

    public ArrayList<Maladie> getCibles(){
        return cibles;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public double getLigne() {
        return ligne;
    }

    public double getColonne() {
        return colonne;
    }

    public ArrayList<Alteration> getAlterations() {
        return alterations;
    }

    public void ajouterAlteration(Alteration alt){
        alterations.add(alt);
    }

    abstract public void attaqueCibles();

}
