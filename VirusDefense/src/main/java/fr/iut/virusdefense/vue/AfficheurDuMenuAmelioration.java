package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.controller.ControllerMenuAmelioration;
import fr.iut.virusdefense.modele.Environnement;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class AfficheurDuMenuAmelioration {
    private Environnement environnement;
    private int colonne;
    private int ligne;
    private Pane paneCentre;
    private Pane amelioration;
    private AfficheurDeCarte afficheurDeCarte;



    public AfficheurDuMenuAmelioration(int ligne, int colonne, Pane paneCentre, Environnement environnement , AfficheurDeCarte afficheurDeCarte){
        this.colonne = colonne;
        this.ligne = ligne;
        this.paneCentre = paneCentre;
        this.environnement = environnement;
        this.afficheurDeCarte = afficheurDeCarte;
        creeMenuAmelioration();
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public AfficheurDeCarte getAfficheurDeCarte() {
        return afficheurDeCarte;
    }

    public void creeMenuAmelioration(){
          try {
              FXMLLoader loader = new FXMLLoader(Main.class.getResource("paneAmélioration.fxml"));
              Pane amelioration = loader.load();
              this.amelioration = amelioration;

              ControllerMenuAmelioration controllerMenuAmelioration = loader.getController();
              controllerMenuAmelioration.setMenuAmelioration(this);
              coteApparitionMenu(ligne,colonne);
              paneCentre.getChildren().add(amelioration);
          }
          catch (Exception ignored){}
    }

    public void retirerMenu(){
        paneCentre.getChildren().remove(amelioration);
    }

    public void coteApparitionMenu(int Ligne, int Colonne){
        if (paneCentre.getPrefHeight() > ((ligne+0.5)*32)+amelioration.getPrefHeight()) {
            amelioration.setTranslateY((ligne + 0.5) * 32);
        }
        else{
            amelioration.setTranslateY(((ligne + 0.5) * 32)-128);
        }

        if (paneCentre.getPrefWidth() > ((colonne+0.5)*32)+amelioration.getPrefWidth()) {
            amelioration.setTranslateX((colonne + 0.5) * 32);
        }
        else{
            amelioration.setTranslateX(((colonne + 0.5) * 32)-160);
        }
    }
}
