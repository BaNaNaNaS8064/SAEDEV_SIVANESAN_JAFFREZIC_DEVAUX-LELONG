package fr.iut.virusdefense.modele.maladie;

import fr.iut.virusdefense.modele.Terrain;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

/**
 * Représente une maladie
 */
public abstract class Maladie {
    private static int dernierId = 0;
    private final String id;

    private final Terrain terrain;

    private final DoubleProperty xProperty;
    private final DoubleProperty yProperty;

    private int pv;
    private final double vitesse;

    /**
     * Créé un nouvelle maladie
     * @param terrain le terrain dans lequel la maladie se trouve
     * @param x sa position x dans {@code terrain}
     * @param y sa position y dans {@code terrain}
     * @param pv ses points de vie initiaux
     * @param vitesse sa vitesse de déplacement
     */
    public Maladie(Terrain terrain, int x, int y, int pv, double vitesse){
        this.terrain = terrain;
        id = "" + ++dernierId;

        // Dans la plupart des cas x et y seront dans les bornes
        xProperty = new SimpleDoubleProperty(x + 0.25);
        yProperty = new SimpleDoubleProperty(y + 0.25);
        if (!terrain.dansBornes(x, y)) {
            setX(0);
            setY(0);
        }

        this.vitesse = vitesse;
        this.pv = pv;
    }

    public String getId() {
        return id;
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

    /**
     * La méthode exécutée à chaque tour.
     * Par défaut elle ne se charge que du déplacement
     */
    public void agir(){
        bouger();

        // pour boucler l'animation
        if (terrain.getObjectif().equals(List.of((int)getY(), (int)getX()))) {
            setY(2.25);
            setX(0.25);
        }
    }

    public void bouger(){
        List<Integer> prochaineCase = terrain.prochaineCase(List.of((int)getY(), (int)getX()));

        setY(getY() + vitesse*Double.compare(prochaineCase.get(0) +0.25, getY()));
        setX(getX() + vitesse*Double.compare(prochaineCase.get(1) +0.25, getX()));
    }
}
