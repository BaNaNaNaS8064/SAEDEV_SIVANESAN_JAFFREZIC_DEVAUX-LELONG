package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;
import java.util.Objects;

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

        ligneProperty = new SimpleDoubleProperty(ligne);
        colonneProperty = new SimpleDoubleProperty(colonne);
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

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        else if (!(o instanceof Entite))
            return false;
        else
            return ((Entite) o).getId().equals(id);
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
        if (Math.abs(distColonne) < 1E-10)
            return voitVertical((int)ligne, ignorerCellules);

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

    private boolean voitVertical(int ligne, boolean ignorerCellules){
        int positionLigne = (int)getLigne();
        int direction;
        if (ligne < getLigne())
            direction = -1;
        else if (ligne > getLigne())
            direction = 1;
        else
            return getEnvironnement().getCarte().peutVoirAuTravers((int)getLigne(), (int)getColonne(), ignorerCellules);

        boolean bloque = false;

        while (!bloque && positionLigne != ligne + direction){
            positionLigne += direction;
            if (!getEnvironnement().getCarte().peutVoirAuTravers(positionLigne, (int)getColonne(), ignorerCellules))
                bloque = true;
        }
        
        return !bloque;
    }
}
