package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.cellule.Cellule;
import fr.iut.virusdefense.modele.maladies.Maladie;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    private IntegerProperty pv;

    /**
     * Créé un terrain sans maladies
     */
    public Environnement() {
        maladies = FXCollections.observableArrayList();
        carte = new Carte();
        deplacement = new Deplacement(carte);
        tour = 0;
        pv = new SimpleIntegerProperty(1000);
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

    public int getPvVal() {
        return pv.get();
    }

    public IntegerProperty getPv(){
        return pv;
    }



    /**
     * Ajoute {@code m} à {@code maladies}
     *
     * @param m une maladie à ajouter
     */
    public void ajouter(Maladie m) {
        maladies.add(m);
    }

    public void retirer(Maladie m) {
        maladies.remove(m);
    }

    public void ajouterCellule(Cellule c){
        carte.getCellules().add(c);
    }

    public void subisDegats(int degats){
        this.pv.setValue(this.getPvVal() - degats);
        System.out.println(pv);
    }

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour() {
        for (Cellule c : carte.getCellules()) {
            c.agir();
        }
        for (Maladie m : maladies) {
            m.agir();
        }
        for (int i = maladies.size()-1 ; i >= 0 ; i--){
            if (!maladies.get(i).estVivant()) {
                this.retirer(maladies.get(i));
            }
        }
        tour++;
    }


}
