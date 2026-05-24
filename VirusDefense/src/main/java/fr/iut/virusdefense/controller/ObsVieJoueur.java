package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.vue.GereurBarreDeVie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObsVieJoueur implements ChangeListener<Number> {

    private final GereurBarreDeVie gereurBarreDeVie;

    public ObsVieJoueur(GereurBarreDeVie gereurBarreDeVie){
        this.gereurBarreDeVie = gereurBarreDeVie;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        gereurBarreDeVie.setPv(newValue.intValue());
    }
}
