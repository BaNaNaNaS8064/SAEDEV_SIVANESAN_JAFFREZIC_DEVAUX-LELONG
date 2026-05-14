package fr.iut.virusdefense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Maladie {
    private final Terrain terrain;
    private final DoubleProperty xProperty;
    private final DoubleProperty yProperty;
    private final String id;
    private static int dernierId = 0;

    public Maladie(Terrain terrain, double x, double y){
        this.terrain = terrain;
        id = "" + ++dernierId;

        if (terrain.dansBornes(x, y)) {
            this.xProperty = new SimpleDoubleProperty(x);
            this.yProperty = new SimpleDoubleProperty(y);
        }
        else {
            this.xProperty = new SimpleDoubleProperty(0);
            this.yProperty = new SimpleDoubleProperty(0);
        }
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

    public void agir(){
        setX(Math.min(getX()+0.03, terrain.getLargeur()));

        if (getX() >= terrain.getLargeur())
            setX(0);
    }
}
