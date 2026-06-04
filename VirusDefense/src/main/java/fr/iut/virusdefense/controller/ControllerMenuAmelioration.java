package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.vue.AfficheurDuMenuAmelioration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuAmelioration implements Initializable {

    @FXML public Pane paneMenu;
    @FXML public Button boutonQuitter;
    @FXML public Button boutonAmelioration;
    @FXML public Button boutonSupprimer;
    @FXML public Label labelNomCellule;
    @FXML public Label labelDegatsCellule;
    @FXML public Label labelPorteeCellule;
    @FXML public Label labelFrequenceCellule;
    @FXML public Label labelNiveauCellule;
    @FXML public Label labelCoutAmelioration;

    private AfficheurDuMenuAmelioration afficheurAmelioration;

    private GestionnaireClickMenuAmelioration gestionnaireAmelioration;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gestionnaireAmelioration = new GestionnaireClickMenuAmelioration(paneMenu,boutonQuitter,boutonAmelioration,boutonSupprimer);

    }

    public void setMenuAmelioration(AfficheurDuMenuAmelioration afficheurDuMenuAmelioration){
        this.afficheurAmelioration = afficheurDuMenuAmelioration;
    }

    public void clickBoutonQuitter(MouseEvent mouseEvent){
        fermetureMenu();
    }

    public void clickBoutonSupprimer(){
        afficheurAmelioration.getEnvironnement().retirerCelluleALEmplacement(afficheurAmelioration.getLigne(), afficheurAmelioration.getColonne(), false);
        afficheurAmelioration.getAfficheurDeCarte().rechargerEmplacement(afficheurAmelioration.getLigne(), afficheurAmelioration.getColonne());
        fermetureMenu();
    }

    public void fermetureMenu(){
        afficheurAmelioration.retirerMenu();
        gestionnaireAmelioration.setAfficheurDuMenuAmelioration(afficheurAmelioration);
    }

    public void initLabel(){
        for (Cellule c : afficheurAmelioration.getEnvironnement().getCarte().getCellules()){
            if ((int) c.getLigne() == afficheurAmelioration.getLigne() && (int) c.getColonne() == afficheurAmelioration.getColonne()) {
                labelNomCellule.setText(c.nomCellule()); ;
                labelDegatsCellule.setText("" + c.getAttaque().getDegats());
                labelPorteeCellule.setText("" + c.getReconnaissance().getPortee());
                labelFrequenceCellule.setText("" + c.getFrequenceAttaque());
                labelNiveauCellule.setText("Niveau " + c.getNiveau());
                labelCoutAmelioration.setText("" + c.getCoutAmelioration());
            }
        }
    }
}
