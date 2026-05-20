package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Entite;
import fr.iut.virusdefense.modele.Environnement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

/**
 * Représente une maladie
 */
public abstract class Maladie extends Entite {
    private int pv;
    private final double vitesse;

    /**
     * Créé un nouvelle maladie
     * @param environnement le terrain dans lequel la maladie se trouve
     * @param x sa position x dans {@code terrain}
     * @param y sa position y dans {@code terrain}
     * @param pv ses points de vie initiaux
     * @param vitesse sa vitesse de déplacement
     */
    public Maladie(Environnement environnement, int x, int y, int pv, double vitesse){
        super(environnement, x+0.25, y+0.25);

        this.vitesse = vitesse;
        this.pv = pv;
    }

    @Override
    public void agir(){
        bouger();

        // pour boucler l'animation
        if (getEnvironnement().getCarte().getObjectif().equals(position())) {
            setY(2.25);
            setX(0.25);
        }
    }

    /**
     * Se déplace vers la prochaine case.
     * La distance dépends de la vitesse
     */
    public void bouger(){
        List<Integer> prochaineCase = getEnvironnement().getDeplacement().prochaineCase(position());

        setY(getY() + vitesse*Double.compare(prochaineCase.get(0) + 0.25, getY()));
        setX(getX() + vitesse*Double.compare(prochaineCase.get(1) + 0.25, getX()));
    }

    public void prendreDegats(int degats){
        pv = pv-degats;
        System.out.println(this.pv);
    }

    public boolean estVivant(){
        return pv > 0;
    }
}
