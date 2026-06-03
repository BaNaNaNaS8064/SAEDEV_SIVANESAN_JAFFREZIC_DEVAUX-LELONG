package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public abstract class Reconnaissance {
    private final List<Maladie> maladies;
    private final double ligne, colonne;

    private final double portee;

    private int nombreCiblesMax;
    private final ArrayList<Maladie> cibles;

    public Reconnaissance (double ligne, double colonne, List<Maladie> maladies, double portee, int nombreCiblesMax){
        this.ligne = ligne;
        this.colonne = colonne;
        this.maladies = maladies;
        this.portee = portee;
        this.nombreCiblesMax = nombreCiblesMax;
        cibles = new ArrayList<>();
    }

    public ArrayList<Maladie> getCibles() {
        return cibles;
    }

    public void setNombreCiblesMax(int nombreCiblesMax){
        this.nombreCiblesMax = nombreCiblesMax;
    }

    public double getLigne() {
        return ligne;
    }

    public double getColonne() {
        return colonne;
    }

    public double getPortee() {
        return portee;
    }

    public final boolean aPortee(Maladie m){
        return m.distanceEuclidienne(ligne, colonne) <= portee;
    }

    public final boolean aAuMoinsUneCible(){
        return !cibles.isEmpty();
    }

    public final boolean aAssezDeCibles(){
        return cibles.size() >= nombreCiblesMax;
    }

    public final boolean ciblesValides(){
         return aAssezDeCibles() && cibles.stream().allMatch(this::estValide);
    }

    public abstract boolean estValide(Maladie m);

    public final void changerCibles(){
        int i = 0;
        Maladie m;

        cibles.clear();

        while (!aAssezDeCibles() && i < maladies.size()){
            m = maladies.get(i);

            if (estValide(m))
                cibles.add(m);

            i++;
        }
    }

}
