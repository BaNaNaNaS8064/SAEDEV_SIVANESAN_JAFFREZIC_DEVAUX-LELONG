package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.modele.utilitaires.StatutPartie;
import fr.iut.virusdefense.vue.GereurEcranDeFin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObsStatutPartie implements ChangeListener<StatutPartie> {

    private final GereurEcranDeFin gereurEcranDeFin;

    public ObsStatutPartie(GereurEcranDeFin gereurEcranDeFin){
        this.gereurEcranDeFin = gereurEcranDeFin;
    }

    @Override
    public void changed(ObservableValue<? extends StatutPartie> observable, StatutPartie oldValue, StatutPartie newValue) {
        gereurEcranDeFin.demarrerAnimation();
    }
}
