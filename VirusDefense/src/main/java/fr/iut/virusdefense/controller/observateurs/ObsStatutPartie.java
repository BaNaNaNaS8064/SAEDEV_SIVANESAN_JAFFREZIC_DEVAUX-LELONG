package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.modele.utilitaires.StatutPartie;
import fr.iut.virusdefense.vue.GereurEcranDeFin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Observe le statut de la partie et démarre les animations
 * de l'écran de fin en conséquence
 */
public class ObsStatutPartie implements ChangeListener<StatutPartie> {

    /// L'objet qui gere l'ecran de fin
    private final GereurEcranDeFin gereurEcranDeFin;

    /**
     * Créé un nouvel Observateur qui démarrera les animations de gereurEcranDeFin
     * @param gereurEcranDeFin l'objet qui gere l'ecran de fin
     */
    public ObsStatutPartie(GereurEcranDeFin gereurEcranDeFin){
        this.gereurEcranDeFin = gereurEcranDeFin;
    }

    @Override
    public void changed(ObservableValue<? extends StatutPartie> observable, StatutPartie oldValue, StatutPartie newValue) {
        switch (newValue){
            case GAGNEE :
                gereurEcranDeFin.demarrerAnimationVictoire();
                break;
            case PERDUE :
                gereurEcranDeFin.demarrerAnimationDefaite();
                break;
        }
    }
}
