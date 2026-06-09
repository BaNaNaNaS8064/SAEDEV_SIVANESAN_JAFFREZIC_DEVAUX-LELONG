package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public abstract class Reconnaissance {
    private final List<Maladie> maladies;
    private double ligne, colonne;

    private double portee;

    private int nombreCiblesMax;
    private final ArrayList<Maladie> cibles;

    public Reconnaissance(double ligne, double colonne, List<Maladie> maladies, double portee, int nombreCiblesMax){
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

    public void setLigne(double ligne) {
        this.ligne = ligne;
    }

    public double getColonne() {
        return colonne;
    }

    public void setColonne(double colonne) {
        this.colonne = colonne;
    }

    public double getPortee() {
        return portee;
    }

    public void setPortee(double portee) {
        this.portee = portee;
    }

    public List<Maladie> getMaladies() {
        return maladies;
    }

    public final boolean aPortee(Maladie m){
        return m.distanceEuclidienne(ligne, colonne) <= portee;
    }

    public final boolean aAuMoinsUneCible(){
        return !cibles.isEmpty();
    }

    public boolean aAssezDeCibles(){
        return cibles.size() >= nombreCiblesMax;
    }

    public final boolean ciblesValides(){
         return aAssezDeCibles() && cibles.stream().allMatch(this::estValide);
    }

    public abstract boolean estValide(Maladie m);

    public void ajoutCible(Maladie maladie){
        cibles.add(maladie);
    }

    public void changerCibles(){
        int i = 0;
        Maladie m;

        cibles.clear();

        while (!aAssezDeCibles() && i < maladies.size()) {
            m = maladies.get(i);

            if (estValide(m))
                ajoutCible(m);

            i++;
        }
    }

}
