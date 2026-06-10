package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class GestionnaireAttaque {
    private final ArrayList<Maladie> cibles;
    private final Environnement environnement;
    private double ligne, colonne;
    private double degats;
    private final ArrayList<Alteration> alterations;

    public GestionnaireAttaque(Environnement environnement, double ligne, double colonne, double degats, ArrayList<Maladie> cibles){
        this.environnement = environnement;
        this.ligne = ligne;
        this.colonne = colonne;
        this.degats = degats;
        this.alterations = new ArrayList<>();
        this.cibles = cibles;
    }

    public ArrayList<Maladie> getCibles(){
        return cibles;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public double getDegats() {
        return degats;
    }

    public void setDegats(double degats) {
        this.degats = degats;
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

    public void setLigne(double ligne) {
        this.ligne = ligne;
    }

    public void setColonne(double colonne) {
        this.colonne = colonne;
    }

    public void ajouterAlteration(Alteration alt){
        alterations.add(alt);
    }

    abstract public void attaqueCibles();

}
