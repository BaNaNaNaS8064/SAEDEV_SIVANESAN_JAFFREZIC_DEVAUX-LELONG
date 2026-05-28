package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une maladie
 */
public abstract class Maladie extends Entite {
    private int pv;
    private final double vitesse;
    private final int recompense;
    private ArrayList<Alteration> alterations;
    private double coefVitesse;

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
        alterations = new ArrayList<>();
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

    public void ajouter(Alteration alteration){
        alterations.add(alteration);
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
            faireJouerAlterations();
            bouger();

            if (aAtteintLObjectif()) {
                infligerDegatsAuJoueur();
                mourir();
            }
        }

        if (!estVivant()){
            if(!aAtteintLObjectif())
                getEnvironnement().getJoueur().ajouterPc(getRecompense());
            getEnvironnement().getMaladies().remove(this);
        }
    }

    /**
     * Se déplace vers la prochaine case.
     * La distance dépends de la vitesse
     */
    public void bouger(){
        List<Integer> prochaineCase = getEnvironnement().getDeplacement().prochaineCase(position());

        setLigne(getLigne() + (vitesse * coefVitesse) * Double.compare(prochaineCase.get(0) + 0.5, getLigne()));
        setColonne(getColonne() + (vitesse * coefVitesse) * Double.compare(prochaineCase.get(1) + 0.5, getColonne()));
    }

    public void infligerDegatsAuJoueur(){
        getEnvironnement().getJoueur().retirerPv(pv);
    }

    public void faireJouerAlterations(){
        coefVitesse =1;
        for (int i = alterations.size() - 1; i >= 0; i--){
            if (alterations.get(i).getDureeDeVie() <= 0)
                alterations.remove(i);
            else
                alterations.get(i).agir(this);
        }
    }

    public void ralentir(double coefRalentissement){
        coefVitesse *= coefRalentissement;
    }
}
