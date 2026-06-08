package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class Reconnaissance {
    private Cellule cellule;
    private double portee;

    private int nombreCiblesMax;
    ArrayList<Maladie> cibles;

    public Reconnaissance (Cellule cellule, double portee, int nombreCiblesMax){
        this.cellule = cellule;
        this.portee = portee;
        this.nombreCiblesMax = nombreCiblesMax;
        cibles = new ArrayList<>();
    }

    public Cellule getCellule() {
        return cellule;
    }

    public ArrayList<Maladie> getCibles() {
        return cibles;
    }

    public double getPortee() {
        return portee;
    }

    public void setPortee(double portee) {
        this.portee = portee;
    }

    /**
     * Methode qui permet a la cellule de voir si sa cible est toujours dans sa portée
     */
    public boolean aPortee(Maladie m){
        return getCellule().distanceEuclidienne(m) <= portee;
    }

    public boolean aAuMoinsUneCible(){
        return !cibles.isEmpty();
    }

    public boolean aAssezDeCibles(){
        return cibles.size() >= nombreCiblesMax;
    }

    public boolean ciblesValides(){
         return aAssezDeCibles() && cibles.stream().allMatch(this::valide);
    }

    public abstract boolean valide(Maladie m);

    /**
     * Methode qui permet de reconnaitre une bacterie de la prendre comme cible
     */
    public void changerCibles(){
        int i = 0;
        Maladie m;

        cibles.clear();

        while (!aAssezDeCibles() && i < getCellule().getEnvironnement().getMaladies().size()){
            m = getCellule().getEnvironnement().getMaladies().get(i);

            if (valide(m))
                cibles.add(m);

            i++;
        }
    }

}
