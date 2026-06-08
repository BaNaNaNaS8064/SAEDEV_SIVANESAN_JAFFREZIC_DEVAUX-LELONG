package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;
import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import fr.iut.virusdefense.modele.utilitaires.CodeTuile;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une maladie
 */
public abstract class Maladie extends Entite {
    private int pv;
    private final double vitesse;
    private final int recompense;
    private double coefVitesse;

    /**
     * Créé un nouvelle maladie
     * @param environnement le terrain dans lequel la maladie se trouve
     * @param ligne sa position x dans {@code terrain}
     * @param colonne sa position y dans {@code terrain}
     * @param pv ses points de vie initiaux
     * @param vitesse sa vitesse de déplacement
     */
    public Maladie(Environnement environnement, double ligne, double colonne, int pv, double vitesse, int recompense){
        super(environnement, (int) ligne, (int) colonne);

        this.vitesse = vitesse;
        this.pv = pv;
        this.recompense = recompense;
        coefVitesse = 1;
    }

    public final int getRecompense(){
        return recompense;
    }


    public boolean estVivant(){
        return pv > 0;
    }

    public void mourir(){
        this.pv = 0;
    }

    public int getPv() {
        return pv;
    }

    /**
     * Méthode qui calcule les PV de la maladie apres avoir subis des degats de la cellule
     * @param degats degats subis par la maladie
     */
    public void prendreDegats(int degats){
        if (degats > 0)
            pv -= degats;
    }

    public boolean aAtteintLObjectif(){
        return getEnvironnement().getCarte().getObjectif().equals(position());
    }

    @Override
    public final void agir(){
        if (estVivant()) {
            coefVitesse = 1;
            capaciteActive();
            bouger();

            if (aAtteintLObjectif()) {
                infligerDegatsAuJoueur();
                mourir();
            }
        }
    }

    /**
     * Se déplace vers la prochaine case.
     * La distance dépends de la vitesse
     */
    public void bouger(){
        List<Integer> destination = getEnvironnement().getDeplacement().prochaineCase(position());
        if (destination != null) {
            double distLigne = Math.abs(destination.get(0) + 0.5 - getLigne());
            double distColonne = Math.abs(destination.get(1) + 0.5 - getColonne());

            double distanceMax = Math.max(distLigne, distColonne);

            int directionLigne = Double.compare(destination.get(0) + 0.5, getLigne());
            int directionColonne = Double.compare(destination.get(1) + 0.5, getColonne());

            setLigne(getLigne() + vitesse * coefVitesse * directionLigne * distLigne / distanceMax);
            setColonne(getColonne() + vitesse * coefVitesse * directionColonne * distColonne / distanceMax);
        }
    }

    public void infligerDegatsAuJoueur(){
        getEnvironnement().getJoueur().retirerPv(pv);
    }

    public void ralentir(double coefRalentissement){
        coefVitesse *= coefRalentissement;
    }

    public void capaciteActive(){}

    public void capaciteALaMort(){}
}
