package fr.iut.virusdefense.modele.cellule;

import fr.iut.virusdefense.modele.Entite;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Cellule extends Entite {
    private int degats ;
    private double portée;
    private int frequenceAttaque;
    private int delaiAttaque;
    private int niveau;
    private int cout ;
    private Maladie cible;

    public Cellule(Environnement environnement, int x, int y, int degats, double portée, int frequenceAttaque , int cout ){
        super(environnement, x, y);
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

    public int getCout() {
        return cout;
    }

    public int getNiveau() {
        return niveau;
    }

    public boolean aCible(){
        return cible != null;
    }

    @Override
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
        for (Maladie m : getEnvironnement().getMaladies()){
            if (distanceEuclidienne(m)<this.getPortée() && m.estVivant()){
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
