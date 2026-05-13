package fr.iut.virusdefense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Maladie {
    private Terrain terrain;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;

    public Maladie(Terrain terrain, int x, int y){
        this.terrain = terrain;
        terrain.ajouter(this);
        if (terrain.dansBornes(x, y)) {
            this.xProperty = new SimpleIntegerProperty(x);
            this.yProperty = new SimpleIntegerProperty(y);
        }
        else {
            this.xProperty = new SimpleIntegerProperty(0);
            this.yProperty = new SimpleIntegerProperty(0);
        }
    }

    public final int getX(){
        return xProperty.getValue();
    }

    public final void setX(int x){
        this.xProperty.setValue(x);
    }

    public final IntegerProperty xProperty(){
        return xProperty;
    }

    public final int getY(){
        return yProperty.getValue();
    }

    public final void setY(int y){
        this.yProperty.setValue(y);
    }

    public final IntegerProperty yProperty(){
        return yProperty;
    }

    public void agir(){
        setX(Math.min(getX()+1, terrain.getLargeur()));
    }
}
