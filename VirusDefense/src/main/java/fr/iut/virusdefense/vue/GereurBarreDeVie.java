package fr.iut.virusdefense.vue;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class GereurBarreDeVie {

    private final ProgressBar barre;
    private final Label labelPvActuels;
    private final int pvMax;

    public GereurBarreDeVie(ProgressBar barre, Label labelPvActuels, Label labelPvMax, int pvMax){
        super();

        this.barre = barre;
        this.labelPvActuels = labelPvActuels;
        this.pvMax = pvMax;
        labelPvMax.setText("" + pvMax);

        setPv(pvMax);
    }

    public void setPv(int montant){
        labelPvActuels.setText("" + montant);
        changerProgressionBarre((double) montant / pvMax);
    }

    private void changerProgressionBarre(double progression){
        barre.setProgress(progression);
        barre.setStyle("-fx-accent: " + couleurBarre(progression));
    }

    private String couleurBarre(double valeur){
        if (valeur < 0.25) return "red";
        else if (valeur < 0.5) return "orange";
        else if (valeur < 0.75) return "yellow";
        else return "green";
    }
}
