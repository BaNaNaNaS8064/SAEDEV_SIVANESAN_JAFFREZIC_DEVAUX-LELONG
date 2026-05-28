package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.vue.GereurBarreDeVie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Observe la vie du joueur et change la progression
 * de la barre de vie en conséquence
 */
public class ObsVieJoueur implements ChangeListener<Number> {

    /// L'objet qui gère la barre de vie
    private final GereurBarreDeVie gereurBarreDeVie;

    /**
     * Crée un nouvel Observateur qui communiquera
     * les changements de points de vie à gereurBarreDeVie
     * @param gereurBarreDeVie l'objet qui gère la barre de vie
     */
    public ObsVieJoueur(GereurBarreDeVie gereurBarreDeVie){
        this.gereurBarreDeVie = gereurBarreDeVie;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        gereurBarreDeVie.setPv(newValue.intValue());
    }
}
