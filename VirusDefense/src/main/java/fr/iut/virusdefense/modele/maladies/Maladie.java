package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;
import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import fr.iut.virusdefense.modele.utilitaires.CodeTuile;

import java.util.List;

/**
 * Représente une maladie
 */
public abstract class Maladie extends Entite {
    private int pv;
    private final double vitesse;
    private final int recompense;

    /**
     * Créé un nouvelle maladie
     * @param environnement le terrain dans lequel la maladie se trouve
     * @param ligne sa position x dans {@code terrain}
     * @param colonne sa position y dans {@code terrain}
     * @param pv ses points de vie initiaux
     * @param vitesse sa vitesse de déplacement
     */
    public Maladie(Environnement environnement, int ligne, int colonne, int pv, double vitesse, int recompense){
        super(environnement, ligne, colonne);

        this.vitesse = vitesse;
        this.pv = pv;
        this.recompense = recompense;
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
    public void agir(){
        if (estVivant()) {
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
        List<Integer> destination = position();
        List<Integer> caseApres = getEnvironnement().getDeplacement().prochaineCase(destination);

        while (getEnvironnement().getCarte().getCode(destination.get(0), destination.get(1)) != CodeTuile.OBJECTIF && voitCase(caseApres.get(0), caseApres.get(1), false)){
            destination = caseApres;
            caseApres = getEnvironnement().getDeplacement().prochaineCase(destination);
        }

        double distLigne = Math.abs((destination.get(0) + 0.5 - getLigne()));
        double distColonne = Math.abs((destination.get(1) + 0.5 - getColonne()));
        setLigne(getLigne() + vitesse * Double.compare(destination.get(0) + 0.5, getLigne()) * distLigne / Math.max(distLigne, distColonne));
        setColonne(getColonne() + vitesse * Double.compare(destination.get(1) + 0.5, getColonne()) * distColonne / Math.max(distLigne, distColonne));
    }

    public void infligerDegatsAuJoueur(){
        getEnvironnement().getJoueur().retirerPv(pv);
    }
}
