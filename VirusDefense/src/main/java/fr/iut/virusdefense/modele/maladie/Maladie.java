package fr.iut.virusdefense.modele.maladie;

import fr.iut.virusdefense.modele.Terrain;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Arrays;

/**
 * Représente une maladie
 */
public abstract class Maladie {
    private final Terrain terrain;
    private final DoubleProperty xProperty;
    private final DoubleProperty yProperty;
    private final String id;
    private static int dernierId = 0;
    private int pv;
    private final int vitesse;

    /**
     * Créé un nouvelle maladie
     * @param terrain le terrain dans lequel la maladie se trouve
     * @param x sa position x dans {@code terrain}
     * @param y sa position y dans {@code terrain}
     * @param pv ses points de vie initiaux
     * @param vitesse sa vitesse de déplacement
     */
    public Maladie(Terrain terrain, double x, double y, int pv, int vitesse){
        this.terrain = terrain;
        id = "" + ++dernierId;

        // Dans la plupart des cas x et y seront dans les bornes
        xProperty = new SimpleDoubleProperty(x);
        yProperty = new SimpleDoubleProperty(y);
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
        if (terrain.getTour() % vitesse == 0)
            bouger();
        if (Arrays.equals(new int[]{(int) getY(), (int) getX()}, terrain.getObjectif())) {
            setY(2);
            setX(0);
        }
    }

    public void bouger(){
        int[] prochainCase = terrain.predecesseurDe(new int[]{(int) getY(), (int) getX()});
        setY(prochainCase[0] + 0.24);
        setX(prochainCase[1] + 0.24);
    }
}
