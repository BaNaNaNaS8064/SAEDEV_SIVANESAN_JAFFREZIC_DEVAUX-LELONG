package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.*;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class GestionnaireClickCarte {
    private final Environnement environnement;

    private final ToggleGroup toggleGrpCellules;

    private final AfficheurDeCarte afficheurDeCarte;

    private final Pane panedessin;

    public GestionnaireClickCarte(Environnement environnement, ToggleGroup toggleGrpCellules, AfficheurDeCarte afficheurDeCarte , Pane panedessin){
        this.environnement = environnement;
        this.toggleGrpCellules = toggleGrpCellules;
        this.afficheurDeCarte = afficheurDeCarte;
        this.panedessin = panedessin;
    }

    public void gererClick(MouseEvent mouseEvent){
        int ligne = (int)(mouseEvent.getY()/32);
        int colonne = (int)(mouseEvent.getX()/32);

        if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && environnement.getCarte().estCellule(ligne, colonne)){
            for (Cellule c : environnement.getCarte().getCellules()){
                if (c.getLigne() == ligne+0.5 && c.getColonne() == colonne+0.5){
                    try {
                        Pane amelioration = new FXMLLoader(Main.class.getResource("paneAmélioration.fxml")).load();
                        amelioration.setTranslateX((colonne+0.5)*32);
                        amelioration.setTranslateY((ligne+0.5)*32);
                        amelioration.setStyle("-fx-background-color: gray");
                        panedessin.getChildren().add(amelioration);
                    }
                    catch (Exception ignored){

                    }
                    c.niveauSuperieur();
                }
            }
        }

        else if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && environnement.getCarte().emplacementVide(ligne, colonne))
            poser(ligne, colonne);

        afficheurDeCarte.rechargerEmplacement(ligne, colonne);
    }

    public void poser(int ligne, int colonne) {
        String boutonSelectionne = ((RadioButton) toggleGrpCellules.getSelectedToggle()).getId();

        Cellule c = switch (boutonSelectionne) {
            case "RbSainple" -> Sainple.creer(environnement, ligne, colonne);
            case "RbLasere" -> Lasere.creer(environnement, ligne, colonne);
            case "RbBrouaieuse" -> Brouaileuse.creer(environnement, ligne, colonne);
            case "RbMuleTyple" -> MuleTyple.creer(environnement, ligne, colonne);
            case "RbSnaipeur" -> Snaipeur.creer(environnement, ligne, colonne);
            case "RbKonsantre" -> Konsantre.creer(environnement, ligne, colonne);
            case "RbPouazon" -> Pouazon.creer(environnement, ligne, colonne);
            default -> null;
        };

        environnement.ajouterSiConforme(c);
    }

    public void retirer(int ligne, int colonne){
        environnement.retirerCelluleALEmplacement(ligne, colonne, false);
    }
}
