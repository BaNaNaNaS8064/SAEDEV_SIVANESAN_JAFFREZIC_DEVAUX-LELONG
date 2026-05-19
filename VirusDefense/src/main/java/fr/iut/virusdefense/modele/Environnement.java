package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.maladies.Maladie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Représente le terrain dans lequel il y aura les cellules et maladies
 */
public class Environnement {

    /// La carte, indique où sont les murs et emplacements vides
    private Carte map;

    private Deplacement dep;


    /**
     * La liste des maladies dans le terrain
     */
    private final ObservableList<Maladie> maladies;

    private int tour;

    /**
     * Créé un terrain sans maladies
     */
    public Environnement(){
        maladies = FXCollections.observableArrayList();
        map = new Carte();
        dep = new Deplacement(map);
        tour = 0;
    }

    public int getTour() {
        return tour;
    }

    public Carte getMap() {
        return map;
    }

    public Deplacement getDep() {
        return dep;
    }

    public ObservableList<Maladie> getMaladies(){
        return maladies;
    }

    /**
     * Ajoute {@code m} à {@code maladies}
     * @param m une maladie à ajouter
     */
    public void ajouter(Maladie m){
        maladies.add(m);
    }



    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour(){
        for (Maladie m : maladies)
            m.agir();
        tour++;
    }

}
