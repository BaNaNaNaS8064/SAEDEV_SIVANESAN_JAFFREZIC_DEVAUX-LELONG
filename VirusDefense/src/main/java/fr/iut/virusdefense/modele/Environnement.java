package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.maladies.Maladie;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import fr.iut.virusdefense.modele.apparition.Generateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Représente le terrain dans lequel il y aura les cellules et maladies
 */
public class Environnement {

    /// La carte, indique où sont les murs et emplacements vides
    private final Carte carte;

    private final Deplacement deplacement;

    private final Joueur joueur;

    /**
     * La liste des maladies dans le terrain
     */
    private final ObservableList<Maladie> maladies;

    /**
     * Créé un terrain sans maladies
     */
    public Environnement() {
        maladies = FXCollections.observableArrayList();
        carte = new Carte(this);
        carte.initGenerateurs();
        deplacement = new Deplacement(carte);
        joueur = new Joueur();
    }

    public Carte getCarte() {
        return carte;
    }

    public Deplacement getDeplacement() {
        return deplacement;
    }

    public ObservableList<Maladie> getMaladies() {
        return maladies;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Ajoute {@code m} à {@code maladies}
     *
     * @param m une maladie à ajouter
     */
    public void ajouter(Maladie m) {
        maladies.add(m);
    }

    public void ajouterCellule(Cellule c){
        carte.getCellules().add(c);
    }

    public void retirerCellule(Cellule c){
        carte.getCellules().remove(c);
    }

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour() {
        if (joueur.getPv() != 0) {
            for (Cellule c : carte.getCellules())
                c.agir();

            for (Generateur g : carte.getGenerateurs())
                g.agir();

            for (Maladie m : maladies)
                m.agir();

            for (int i=maladies.size()-1; i >= 0; i--)
                if (!maladies.get(i).estVivant())
                    maladies.remove(i);
        }
    }


}
