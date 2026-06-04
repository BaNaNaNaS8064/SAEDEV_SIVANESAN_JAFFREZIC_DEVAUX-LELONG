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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void setMenuAmelioration(AfficheurDuMenuAmelioration afficheurDuMenuAmelioration){
        this.afficheurAmelioration = afficheurDuMenuAmelioration;
        for (Cellule c : afficheurAmelioration.getEnvironnement().getCarte().getCellules()) {
            if ((int) c.getLigne() == afficheurAmelioration.getLigne() && (int) c.getColonne() == afficheurAmelioration.getColonne()) {
                initLabel(c);
                niveauMax(c);
            }
        }
    }

    public void initLabel(Cellule cellule){
        labelNomCellule.setText(cellule.nomCellule()); ;
        labelDegatsCellule.setText("" + cellule.getAttaque().getDegats());
        labelPorteeCellule.setText("" + cellule.getReconnaissance().getPortee());
        labelFrequenceCellule.setText("" + cellule.getFrequenceAttaque());
        labelNiveauCellule.setText("Niveau " + cellule.getNiveau());
        labelCoutAmelioration.setText("" + cellule.getCoutAmelioration());
    }

    public void niveauMax(Cellule cellule){
        if (cellule.getNiveau() == 3) {
            boutonAmelioration.setDisable(true);
            boutonAmelioration.setText("Niveau Max");
        }
    }

    public void clickBoutonQuitter(MouseEvent mouseEvent){
        fermetureMenu();
    }

    public void clickBoutonAmelioration(){
        for (Cellule c : afficheurAmelioration.getEnvironnement().getCarte().getCellules()){
            if ((int) c.getLigne() == afficheurAmelioration.getLigne() && (int) c.getColonne() == afficheurAmelioration.getColonne()) {
                c.niveauSuperieur();
            }
        }
        setMenuAmelioration(afficheurAmelioration);
    }

    public void clickBoutonSupprimer(){
        afficheurAmelioration.getEnvironnement().retirerCelluleALEmplacement(afficheurAmelioration.getLigne(), afficheurAmelioration.getColonne(), false);
        afficheurAmelioration.getAfficheurDeCarte().rechargerEmplacement(afficheurAmelioration.getLigne(), afficheurAmelioration.getColonne());
        fermetureMenu();
    }

    public void fermetureMenu(){
        afficheurAmelioration.retirerMenu();

    }


}
