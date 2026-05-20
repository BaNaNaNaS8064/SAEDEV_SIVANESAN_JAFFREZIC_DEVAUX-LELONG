package fr.iut.virusdefense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

public abstract class Entite {

    private static int dernierID = 0;
    private final String id;

    private final Environnement environnement;

    private final DoubleProperty xProperty;
    private final DoubleProperty yProperty;

    public Entite(Environnement environnement, double x, double y){
        id = "" + ++dernierID;
        this.environnement = environnement;

        // Dans la plupart des cas x et y seront dans les bornes
        xProperty = new SimpleDoubleProperty(x);
        yProperty = new SimpleDoubleProperty(y);
        if (!environnement.getCarte().dansBornes(y, x)) {
            setX(0);
            setY(0);
        }
    }

    public String getId() {
        return id;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public final double getX(){
        return xProperty.getValue();
    }

    public final void setX(double x){
        this.xProperty.setValue(x);
    }

    public final DoubleProperty xProperty(){
        return xProperty;
    }

    public final double getY(){
        return yProperty.getValue();
    }

    public final void setY(double y){
        this.yProperty.setValue(y);
    }

    public final DoubleProperty yProperty(){
        return yProperty;
    }

    public List<Integer> position(){
        return List.of((int)getY(), (int)getX());
    }

    /**
     * Retourne la distance euclidienne avec une autre Entite
     * @param e une maladie
     * @return distance euclidienne
     */
    public double distanceEuclidienne(Entite e){
        return Math.sqrt(Math.pow((getX() - e.getX()), 2) + Math.pow((getY() - e.getY()), 2));
    }

    /**
     * Méthode exécutée à chaque tour
     */
    public abstract void agir();
}
