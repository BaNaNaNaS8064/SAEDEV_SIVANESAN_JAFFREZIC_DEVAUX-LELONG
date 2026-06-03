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
    private Pane paneDessin;
    private Pane amelioration;
    private AfficheurDeCarte afficheurDeCarte;

    public AfficheurDuMenuAmelioration(int ligne, int colonne, Pane paneDessin, Environnement environnement , AfficheurDeCarte afficheurDeCarte){
        this.colonne = colonne;
        this.ligne = ligne;
        this.paneDessin = paneDessin;
        this.environnement = environnement;
        this.afficheurDeCarte = afficheurDeCarte;
        creeMenuAmelioration();
    }

    public void creeMenuAmelioration(){
          try {
              FXMLLoader loader = new FXMLLoader(Main.class.getResource("paneAmélioration.fxml"));
              Pane amelioration = loader.load();
              this.amelioration = amelioration;

              ControllerMenuAmelioration controllerMenuAmelioration = loader.getController();
              controllerMenuAmelioration.setMenuAmelioration(this);
              amelioration.setTranslateX((colonne+0.5)*32);
              amelioration.setTranslateY((ligne+0.5)*32);
              paneDessin.getChildren().add(amelioration);
          }
          catch (Exception ignored){}
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

    public void retirerMenu(){
        paneDessin.getChildren().remove(amelioration);
    }

}
