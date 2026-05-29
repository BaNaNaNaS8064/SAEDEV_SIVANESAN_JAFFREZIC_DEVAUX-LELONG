package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.*;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GestionnaireClickCarte {
    private final Environnement environnement;

    private final ToggleGroup toggleGrpCellules;

    private final AfficheurDeCarte afficheurDeCarte;

    public GestionnaireClickCarte(Environnement environnement, ToggleGroup toggleGrpCellules, AfficheurDeCarte afficheurDeCarte){
        this.environnement = environnement;
        this.toggleGrpCellules = toggleGrpCellules;
        this.afficheurDeCarte = afficheurDeCarte;
    }

    public void gererClick(MouseEvent mouseEvent){
        int ligne = (int)(mouseEvent.getY()/32);
        int colonne = (int)(mouseEvent.getX()/32);

        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && environnement.getCarte().emplacementVide(ligne, colonne))
            poser(ligne, colonne);

        else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && environnement.getCarte().estCellule(ligne, colonne))
            retirer(ligne, colonne);

        afficheurDeCarte.rechargerEmplacement(ligne, colonne);
    }

    public void poser(int ligne, int colonne) {
        String boutonSelectionne = ((RadioButton) toggleGrpCellules.getSelectedToggle()).getId();

        Cellule c = switch (boutonSelectionne) {
            case "RbSainple" -> Sainple.creer(environnement, ligne, colonne);
            case "RbLasere" -> Lasere.creer(environnement, ligne, colonne);
            case "RbBrouaïeuse" -> Brouaileuse.creer(environnement, ligne, colonne);
            case "RbMuleTyple" -> MuleTyple.creer(environnement, ligne, colonne);
            case "RbSnaïpeur" -> Snaipeur.creer(environnement, ligne, colonne);
            case "RbKonsantré" -> Konsantre.creer(environnement, ligne, colonne);
            case "RbPouazon" -> Pouazon.creer(environnement, ligne, colonne);
            default -> null;
        };

        environnement.ajouterSiConforme(c);
    }

    public void retirer(int ligne, int colonne){
        environnement.retirerCelluleALEmplacement(ligne, colonne, false);
    }
}
