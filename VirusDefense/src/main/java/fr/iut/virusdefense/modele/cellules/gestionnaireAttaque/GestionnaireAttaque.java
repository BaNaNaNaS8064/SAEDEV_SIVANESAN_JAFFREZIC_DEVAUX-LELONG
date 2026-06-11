package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class GestionnaireAttaque extends Positionnable {
    private final ArrayList<Maladie> cibles;
    private double degats;
    private final ArrayList<Alteration> alterations;

    public GestionnaireAttaque(Environnement environnement, double ligne, double colonne, double degats, ArrayList<Maladie> cibles){
        super(environnement, ligne, colonne);
        this.degats = degats;
        this.alterations = new ArrayList<>();
        this.cibles = cibles;
    }

    public ArrayList<Maladie> getCibles(){
        return cibles;
    }

    public double getDegats() {
        return degats;
    }

    public void setDegats(double degats) {
        this.degats = degats;
    }

    public ArrayList<Alteration> getAlterations() {
        return alterations;
    }

    public void ajouterAlteration(Alteration alt){
        alterations.add(alt);
    }

    abstract public void attaqueCibles();

}
