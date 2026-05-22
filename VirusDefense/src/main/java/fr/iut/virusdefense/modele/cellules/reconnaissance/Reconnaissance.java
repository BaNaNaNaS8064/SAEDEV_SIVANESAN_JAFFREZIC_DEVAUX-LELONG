package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesGeneriques.Rayon;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class Reconnaissance {
    private Cellule cellule;
    private double portee ;
    ArrayList<Maladie> cibles;

    public Reconnaissance (Cellule cellule , double portee){
        this.cellule = cellule;
        this.portee = portee;
        cibles = new ArrayList<>();
    }

    public Cellule getCellule() {
        return cellule;
    }

    public double getPortee() {
        return portee;
    }

    public ArrayList<Maladie> getCibles() {
        return cibles;
    }

    /**
     * Methode qui permet a la cellule de voir si sa cible est toujours dans sa portée
     */
    public boolean aPortee(Maladie m){
        return getCellule().distanceEuclidienne(m) <= getPortee();
    }

    public boolean aUneCible(){
        return !cibles.isEmpty();
    }

    public boolean voit(Maladie m){
        return new Rayon(cellule.getEnvironnement(), cellule.getLigne(), cellule.getColonne(), m.getLigne(), m.getColonne()).peutRelierExtremitees(false);
    }

    public boolean valide(){
         return aUneCible() && cibles.get(0).estVivant() && aPortee(cibles.get(0)) && voit(cibles.get(0));
    }

    abstract public void changerCible();

}
