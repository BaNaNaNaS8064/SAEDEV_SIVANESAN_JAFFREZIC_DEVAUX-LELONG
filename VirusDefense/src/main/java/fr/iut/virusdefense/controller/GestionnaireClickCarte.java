package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.*;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import fr.iut.virusdefense.vue.AfficheurDuMenuAmelioration;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class GestionnaireClickCarte {
    private final Environnement environnement;

    private final ToggleGroup toggleGrpCellules;

    private final AfficheurDeCarte afficheurDeCarte;

    private Pane paneDessin;

    private AfficheurDuMenuAmelioration menuAmelioration;

    private Pane paneCentre;


    public GestionnaireClickCarte(Environnement environnement, ToggleGroup toggleGrpCellules, AfficheurDeCarte afficheurDeCarte , Pane panedessin , Pane paneCentre){
        this.environnement = environnement;
        this.toggleGrpCellules = toggleGrpCellules;
        this.afficheurDeCarte = afficheurDeCarte;
        this.paneDessin = panedessin;
        this.paneCentre = paneCentre;
    }

    public void gererClick(MouseEvent mouseEvent){
        int ligne = (int)(mouseEvent.getY()/32);
        int colonne = (int)(mouseEvent.getX()/32);

        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && environnement.getCarte().emplacementVide(ligne, colonne))
            poser(ligne, colonne);

        else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && environnement.getCarte().estCellule(ligne, colonne)){
            creationMenu(ligne,colonne);
        }

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
        environnement.retirerCelluleALEmplacement(ligne,colonne,false);
    }

    public void creationMenu(int ligne, int colonne){
        for (Cellule c : environnement.getCarte().getCellules()){
            if ((int) c.getLigne() == ligne && (int) c.getColonne() == colonne) {
                menuAmelioration(ligne, colonne);
            }
        }
    }

    public void menuAmelioration(int ligne, int colonne ){
        menuAmelioration = new AfficheurDuMenuAmelioration(ligne, colonne, paneCentre, environnement, afficheurDeCarte );
    }
}
