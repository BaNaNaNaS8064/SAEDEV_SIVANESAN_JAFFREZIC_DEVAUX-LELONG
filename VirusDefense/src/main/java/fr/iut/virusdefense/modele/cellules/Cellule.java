package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Entite;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Cellule extends Entite {
    private final double portee;
    private Maladie cible;

    private int degats ;

    private final int frequenceAttaque;
    private int delai;

    private int niveau;
    private final int cout;

    public Cellule(Environnement environnement, int ligne, int colonne, int degats, double portee, int frequenceAttaque , int cout ){
        super(environnement, ligne, colonne);
        this.degats = degats;
        this.portee = portee;
        this.frequenceAttaque = frequenceAttaque;
        this.delai = frequenceAttaque;
        this.niveau = 1 ;
        this.cout = cout;
    }

    public double getPortee() {
        return portee;
    }
    public int getCout() {
        return cout;
    }

    public boolean aUneCible(){
        return cible != null;
    }

    @Override
    public void agir(){
        delai--;

        if (delai <=0){
            if (!aUneCible() || !cible.estVivant() || !aPortee(cible))
                changerCible();

            if (aUneCible()) {
                attaque();
                delai = frequenceAttaque;
            }
        }
    }

    /**
     * Methode qui permet de reconnaitre une bacterie de la prendre comme cible
     */
    public void changerCible(){
        int i = 0;
        Maladie m;

        cible = null;

        while (!aUneCible() && i < getEnvironnement().getMaladies().size()){
            m = getEnvironnement().getMaladies().get(i);

            if (m.estVivant() && aPortee(m))
                cible = m;

            i++;
        }
    }

    /**
     * Methode qui permet a la cellule de voir si sa cible est toujours dans sa portée
     */
    public boolean aPortee(Maladie m){
        return distanceEuclidienne(m) <= getPortee();
    }

    /**
     * Methode qui attaque quand la cible est a portée et vivante
     */
    public void attaque(){
        cible.prendreDegats(degats);
    }
}
