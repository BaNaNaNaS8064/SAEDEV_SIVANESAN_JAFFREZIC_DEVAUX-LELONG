package fr.iut.virusdefense.modele.cellule;

import fr.iut.virusdefense.modele.Terrain;
import fr.iut.virusdefense.modele.maladies.Maladie;
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
    private int niveau;
    private int cout ;
    private Maladie cible;

    public Cellule(Terrain terrain, int  x , int  y , int degats , double portée , int frequenceAttaque ,int cout ){
        this.terrain = terrain;
        id = "" + ++dernierId;
        this.x = x;
        this.y = y;
        this.degats = degats;
        this.portée = portée;
        this.frequenceAttaque = frequenceAttaque;
        this.niveau = 1 ;
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

    public int getNiveau() {
        return niveau;
    }

    public boolean aCible(){
        return cible != null;
    }

    public double distanceEuclidienne(Maladie m){
        return Math.sqrt(Math.pow((this.getX() - m.getX()),2) + Math.pow((this.getY() - m.getY()),2));
    }

    public Maladie reconnaissanceEnnemi(){
        for (Maladie m : terrain.getMaladies()){
            if (distanceEuclidienne(m)<this.getPortée()){
                System.out.println("Attaque");
                cible = m;
            }
        }
        return null;
    }

    public void attaque(){
        cible.prendreDegats(degats);
    }

}
