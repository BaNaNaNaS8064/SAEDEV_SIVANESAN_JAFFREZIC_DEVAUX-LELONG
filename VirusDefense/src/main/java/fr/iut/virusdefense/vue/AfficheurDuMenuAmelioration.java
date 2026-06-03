package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.controller.ControllerMenuAmelioration;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class AfficheurDuMenuAmelioration {
    private int colonne;
    private int ligne;
    private Pane paneDessin;
    private Pane amelioration;

    public AfficheurDuMenuAmelioration(int colonne, int ligne, Pane paneDessin){
        this.colonne = colonne;
        this.ligne = ligne;
        this.paneDessin = paneDessin;
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


    public void retirerMenu(){
        paneDessin.getChildren().remove(amelioration);
    }

}
