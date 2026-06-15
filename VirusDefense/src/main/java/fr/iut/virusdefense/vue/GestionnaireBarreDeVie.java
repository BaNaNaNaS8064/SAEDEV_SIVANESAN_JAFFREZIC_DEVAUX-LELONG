package fr.iut.virusdefense.vue;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class GestionnaireBarreDeVie {

    private final ProgressBar barre;
    private final Label labelPvActuels;
    private final int pvMax;

    public GestionnaireBarreDeVie(ProgressBar barre, Label labelPvActuels, Label labelPvMax, int pvMax){
        super();

        this.barre = barre;
        this.labelPvActuels = labelPvActuels;
        this.pvMax = pvMax;
        labelPvMax.setText("" + pvMax);

        setPv(pvMax);
    }

    public void setPv(int montant){
        labelPvActuels.setText("" + montant);
        barre.setProgress((double) montant / pvMax);
    }
}
