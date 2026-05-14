package fr.iut.virusdefense.modele.cellule;

import fr.iut.virusdefense.modele.Terrain;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Cellule{
    private Terrain terrain;
    private int x;
    private int y;
    private String id;
    private static int dernierId = 0;
    private int degats ;
    private double portée;
    private int frequenceAttaque;
    private int cout ;

    public Cellule(Terrain terrain, int  x , int  y , int degats , double portée , int frequenceAttaque ,int cout ){
        this.terrain = terrain;
        id = "" + ++dernierId;
        this.x = x;
        this.y = y;
        this.degats = degats;
        this.portée = portée;
        this.frequenceAttaque = frequenceAttaque;
        this.cout = cout;
    }

    public double getPortée() {
        return portée;
    }

    public int getDegats() {
        return degats;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCout() {
        return cout;
    }

    public String getId() {
        return id;
    }
    
}
