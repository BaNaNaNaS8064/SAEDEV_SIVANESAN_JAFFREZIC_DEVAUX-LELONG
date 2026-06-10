package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.cellules.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEncyclopedie implements Initializable{
    @FXML public ToggleGroup toggleGrpEncyclopedie;

    @FXML public Label labelTitre;

    @FXML public Label labelDesc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < toggleGrpEncyclopedie.getToggles().size(); i++) {
            toggleGrpEncyclopedie.selectedToggleProperty().addListener((observable,  oldValue,  newValue) -> update());
        }
        update();
    }

    public void update(){
        String boutonSelectionne = ((RadioButton) toggleGrpEncyclopedie.getSelectedToggle()).getId();
        switch (boutonSelectionne) {
            //cellules
            case "RbEncSainple":
                labelTitre.setText("Sainple");
                break;
            case "RbEncLasere":
                labelTitre.setText("L'asère");
                break;
            case "RbEncBrouaieuse":
                labelTitre.setText("Brouaïeuse");
                break;
            case "RbEncMuleTyple":
                labelTitre.setText("Mule-typle");
                break;
            case "RbEncSnaipeur":
                labelTitre.setText("Snaï-peur");
                break;
            case "RbEncRizCoCher":
                labelTitre.setText("Riz co-cher");
                break;
            case "RbEncKonsantre":
                labelTitre.setText("Konsantré");
                break;
            case "RbEncPouazon":
                labelTitre.setText("Pouazon");
                break;
            //maladies
            case "RbEncBactBanale":
                labelTitre.setText("Bactérie Banale");
                break;
            case "RbEncParasite":
                labelTitre.setText("Parasite");
                break;
            case "RbEncVirus":
                labelTitre.setText("Virus");
                break;
            case "RbEncPetitChamp":
                labelTitre.setText("Petit Champignon");
                break;
            case "RbEncGrosChamp":
                labelTitre.setText("Gros Champignon");
                break;
            case "RbEncVirusComp":
                labelTitre.setText("Virus Composé");
                break;
            case "RbEncTumeur":
                labelTitre.setText("Tumeur");
                break;
        };
    }
}
