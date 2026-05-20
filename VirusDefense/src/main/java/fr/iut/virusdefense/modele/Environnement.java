package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.cellule.Cellule;
import fr.iut.virusdefense.modele.maladies.BactérieBanale;
import fr.iut.virusdefense.modele.maladies.Maladie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Représente le terrain dans lequel il y aura les cellules et maladies
 */
public class Environnement {

    /// La carte, indique où sont les murs et emplacements vides
    private Carte carte;

    private Deplacement deplacement;

    /**
     * La liste des maladies dans le terrain
     */
    private final ObservableList<Maladie> maladies;

    private int tour;

    /**
     * Créé un terrain sans maladies
     */
    public Environnement() {
        maladies = FXCollections.observableArrayList();
        carte = new Carte();
        deplacement = new Deplacement(carte);
        tour = 0;
    }

    public int getTour() {
        return tour;
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

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour() {
        for (Cellule c : carte.getCellules())
            c.agir();

        for (Maladie m : maladies)
            m.agir();

        for (int i = maladies.size()-1 ; i >= 0 ; i--)
            if (!maladies.get(i).estVivant())
                maladies.remove(maladies.get(i));

        if (tour % 60 == 0)
            ajouter(new BactérieBanale(this, 0, 2));

        tour++;
    }
}
