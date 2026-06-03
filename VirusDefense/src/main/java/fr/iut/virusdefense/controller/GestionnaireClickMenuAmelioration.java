package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.vue.AfficheurDuMenuAmelioration;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GestionnaireClickMenuAmelioration {
    public Pane menu;

    private Button quitter;

    private Button amelioration;

    private Button supprimer;

    private AfficheurDuMenuAmelioration afficheurDuMenuAmelioration;


    public GestionnaireClickMenuAmelioration(Pane pane, Button quitter, Button amelioration, Button supprimer){
        this.menu = pane;
        this.quitter = quitter;
        this.amelioration = amelioration;
        this.supprimer = supprimer;
    }

    public void setAfficheurDuMenuAmelioration(AfficheurDuMenuAmelioration afficheurDuMenuAmelioration) {
        this.afficheurDuMenuAmelioration = afficheurDuMenuAmelioration;
    }
}
