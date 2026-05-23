package fr.iut.virusdefense.modele.entitesGeneriques;

import fr.iut.virusdefense.modele.Environnement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

public abstract class Entite {

    private static int dernierID = 0;
    private final String id;

    private final Environnement environnement;

    private final DoubleProperty ligneProperty;
    private final DoubleProperty colonneProperty;

    public Entite(Environnement environnement, int ligne, int colonne){
        this(environnement, ligne + 0.5, colonne + 0.5);
    }

    public Entite(Environnement environnement, double ligne, double colonne){
        id = "" + ++dernierID;
        this.environnement = environnement;

        // Dans la plupart des cas ligne et colonne seront dans les bornes
        ligneProperty = new SimpleDoubleProperty(ligne);
        colonneProperty = new SimpleDoubleProperty(colonne);
        if (!environnement.getCarte().dansBornes(ligne, colonne)) {
            setColonne(0);
            setLigne(0);
        }
    }

    public String getId() {
        return id;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public final double getLigne(){
        return ligneProperty.getValue();
    }

    public final void setLigne(double y){
        this.ligneProperty.setValue(y);
    }

    public final DoubleProperty ligneProperty(){
        return ligneProperty;
    }

    public final double getColonne(){
        return colonneProperty.getValue();
    }

    public final void setColonne(double colonne){
        this.colonneProperty.setValue(colonne);
    }

    public final DoubleProperty colonneProperty(){
        return colonneProperty;
    }

    public List<Integer> position(){
        return List.of((int) getLigne(), (int) getColonne());
    }

    /**
     * Retourne la distance euclidienne avec une autre Entite
     * @param e une maladie
     * @return distance euclidienne
     */
    public double distanceEuclidienne(Entite e){
        return distanceEuclidienne(e.getLigne(), e.getColonne());
    }

    public double distanceEuclidienne(double ligne, double colonne){
        return Math.sqrt(Math.pow((getLigne() - ligne), 2) + Math.pow((getColonne() - colonne), 2));
    }

    /**
     * Méthode exécutée à chaque tour
     */
    public abstract void agir();

    public boolean voit(Entite e, boolean ignorerCellules){
        return voit(e.getLigne(), e.getColonne(), ignorerCellules);
    }

    public boolean voit(double ligne, double colonne, boolean ignorerCellules){
        int nombreDePoints = 100;

        double positionLigne, positionColonne = getColonne();

        double distColonne = colonne - getColonne();

        double pente = (ligne - getLigne()) / (distColonne);
        double ordoneeOrigine = getLigne() - pente * getColonne();

        boolean bloque = false;
        int i=0;

        while (!bloque && i<nombreDePoints){
            positionColonne += distColonne / nombreDePoints;
            positionLigne = pente * positionColonne + ordoneeOrigine;

            if (!getEnvironnement().getCarte().peutVoirAuTravers((int)positionLigne, (int)positionColonne, ignorerCellules) && ((int)positionLigne != (int)getLigne() || (int)positionColonne != (int)getColonne()))
                bloque = true;

            i++;
        }

        return !bloque;
    }
}
