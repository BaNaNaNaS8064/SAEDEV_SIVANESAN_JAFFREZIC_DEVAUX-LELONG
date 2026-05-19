package fr.iut.virusdefense.modele.cellule;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Cellule{
    private Environnement env;
    private int x;
    private int y;
    private String id;
    private static int dernierId = 0;
    private int degats ;
    private double portée;
    private int frequenceAttaque;
    private int delaiAttaque;
    private int niveau;
    private int cout ;
    private Maladie cible;

    public Cellule(Environnement env, int  x , int  y , int degats , double portée , int frequenceAttaque , int cout ){
        this.env = env;
        id = "" + ++dernierId;
        this.x = x;
        this.y = y;
        this.degats = degats;
        this.portée = portée;
        this.frequenceAttaque = frequenceAttaque;
        this.delaiAttaque = frequenceAttaque;
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

    /**
     * Retourne la distance euclidienne entre une maladie et cette cellule
     * @param m une maladie
     * @return distance euclidienne
     */
    public double distanceEuclidienne(Maladie m){
        return Math.sqrt(Math.pow((this.getX() - m.getX()),2) + Math.pow((this.getY() - m.getY()),2));
    }

    /**
     * Methode que fait une cellule chaque tour
     */
    public void agir(){
        delaiAttaque--;

        if (delaiAttaque<=0){
            if (!aCible())
                cible = reconnaissanceEnnemi();

            if (aCible()) {
                attaque();
                delaiAttaque = frequenceAttaque;
            }
        }
    }

    /**
     * Methode qui permet de reconnaitre une bacterie de la prendre comme cible
     * @return return une maladie ou null si aucune maladie est trouvé
     */
    public Maladie reconnaissanceEnnemi(){
        for (Maladie m : env.getMaladies()){
            if (distanceEuclidienne(m)<this.getPortée()){
                return m;
            }
        }
        return null;
    }

    /**
     * Methode qui permet a la cellule de voir si sa cible est toujours dans sa portée
     */
    public boolean aPorteeDeCible(){
        return distanceEuclidienne(cible) < this.getPortée();
    }

    /**
     * Methode qui attaque quand la cible est a portée et vivante
     */
    public void attaque(){
        if (this.aPorteeDeCible() && cible.estVivant()){
            cible.prendreDegats(degats);
        }
    }
}
