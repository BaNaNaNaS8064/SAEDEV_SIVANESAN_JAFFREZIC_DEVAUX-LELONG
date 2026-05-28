package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.Lasere;
import fr.iut.virusdefense.modele.cellules.Sainple;
import fr.iut.virusdefense.modele.utilitaires.CodeTuile;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import fr.iut.virusdefense.vue.sprites.Tuile;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class TuileEvent {
    private Environnement env;
    private ToggleGroup toggleGrpCellules;
    private AfficheurDeCarte ac;
    Pane paneDessin;

    public TuileEvent(Environnement env, ToggleGroup toggleGrpCellules, AfficheurDeCarte ac, Pane paneDessin){
        this.env = env;
        this.toggleGrpCellules = toggleGrpCellules;
        this.ac = ac;
        this.paneDessin = paneDessin;
    }

    public void ajoutEventPane(){

        paneDessin.setOnMousePressed(mouseEvent -> {
            int ligne = (int)(mouseEvent.getY()/32);
            int colonne = (int)(mouseEvent.getX()/32);
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && env.getCarte().emplacementVide(ligne, colonne))
                poser(ligne, colonne);
            else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && env.getCarte().estCellule(ligne, colonne))
            {
                env.retirerCelluleA(ligne, colonne, false);
                ac.reloadEmplacementCarte(ligne, colonne);
            }

        });
    }

    public void poser(int ligne, int colonne) {
        Cellule c = null;

        String type = ((RadioButton) toggleGrpCellules.getSelectedToggle()).getId();

        switch (type){
            case "RbSainple":
                c = Sainple.creer(env, ligne, colonne);
                break;
        }

        if (c != null && env.getJoueur().getPc() >= c.getCout()) {
            env.ajouterCellule(c);
            env.vérifierPoserCellules(c);
        }

        ac.reloadEmplacementCarte(ligne, colonne);
    }
}
