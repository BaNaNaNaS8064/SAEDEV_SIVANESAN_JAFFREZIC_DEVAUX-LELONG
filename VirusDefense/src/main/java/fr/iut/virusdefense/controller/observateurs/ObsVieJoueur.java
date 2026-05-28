package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.vue.GestionnaireBarreDeVie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Observe la vie du joueur et change la progression
 * de la barre de vie en conséquence
 */
public class ObsVieJoueur implements ChangeListener<Number> {

    /// L'objet qui gère la barre de vie
    private final GestionnaireBarreDeVie gestionnaireBarreDeVie;

    /**
     * Crée un nouvel Observateur qui communiquera
     * les changements de points de vie à gestionnaireBarreDeVie
     * @param gestionnaireBarreDeVie l'objet qui gère la barre de vie
     */
    public ObsVieJoueur(GestionnaireBarreDeVie gestionnaireBarreDeVie){
        this.gestionnaireBarreDeVie = gestionnaireBarreDeVie;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        gestionnaireBarreDeVie.setPv(newValue.intValue());
    }
}
