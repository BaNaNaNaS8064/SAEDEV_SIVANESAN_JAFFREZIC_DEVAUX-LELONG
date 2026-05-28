package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.*;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class GestionnaireClickCarte {
    private final Environnement environnement;

    private final ToggleGroup toggleGrpCellules;
    private final Pane paneDessin;

    private final AfficheurDeCarte afficheurDeCarte;

    public GestionnaireClickCarte(Environnement environnement, ToggleGroup toggleGrpCellules, AfficheurDeCarte afficheurDeCarte, Pane paneDessin){
        this.environnement = environnement;
        this.toggleGrpCellules = toggleGrpCellules;
        this.afficheurDeCarte = afficheurDeCarte;
        this.paneDessin = paneDessin;
    }

    public void ajoutEventPane(){
        paneDessin.setOnMousePressed(mouseEvent -> {

            int ligne = (int)(mouseEvent.getY()/32);
            int colonne = (int)(mouseEvent.getX()/32);

            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && environnement.getCarte().emplacementVide(ligne, colonne))
                poser(ligne, colonne);

            else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && environnement.getCarte().estCellule(ligne, colonne))
                retirer(ligne, colonne);

        });
    }

    public void poser(int ligne, int colonne) {
        String boutonSelectionne = ((RadioButton) toggleGrpCellules.getSelectedToggle()).getId();

        Cellule c = switch (boutonSelectionne) {
            case "RbSainple" -> Sainple.creer(environnement, ligne, colonne);
            case "RbLasere" -> Lasere.creer(environnement, ligne, colonne);
            default -> null;
        };

        environnement.ajouterSiConforme(c);
        afficheurDeCarte.rechargerEmplacementCarte(ligne, colonne);
    }

    public void retirer(int ligne, int colonne){
        environnement.retirerCelluleALEmplacement(ligne, colonne, false);
        afficheurDeCarte.rechargerEmplacementCarte(ligne, colonne);
    }
}
