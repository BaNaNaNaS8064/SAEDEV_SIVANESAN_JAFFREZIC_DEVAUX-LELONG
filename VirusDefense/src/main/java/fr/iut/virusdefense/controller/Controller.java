package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Carte;
import fr.iut.virusdefense.modele.cellule.Cellule;
import fr.iut.virusdefense.modele.cellule.Sainple;
import fr.iut.virusdefense.modele.maladies.*;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.vue.SpritesTuiles;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Timeline gameLoop;
    private AfficheurDeCarte afficheurDeCarte;
    private Environnement environnement;
    private int tailleTuiles;

    @FXML
    public Pane paneMaladie;

    @FXML
    public TilePane tuiles;

    @FXML
    public ProgressBar barredevie;

    @FXML
    public Label pvActuel;

    @FXML
    public Label totalpv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        environnement = new Environnement();
        environnement.getMaladies().addListener(new ObsListeMaladies(paneMaladie));

        environnement.ajouter(new BactérieBanale(environnement, 0, 2));

        ChangeListener<Boolean> defaite = ((obs , old , nouv) -> System.out.println("defaite"));
        environnement.getDefaite().addListener(defaite);

        environnement.ajouterCellule(new Sainple(environnement , 5 , 2));
        gestionBarreDeVie();

        afficheurDeCarte = new AfficheurDeCarte(environnement, tuiles);

        initGameLoop();
        gameLoop.play();
    }

    /**
     * Créé et démmare la gameLoop
     */
    private void initGameLoop(){
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(new KeyFrame(Duration.seconds(0.017), e -> uneFrame()));
    }

    /**
     * Méthode exécutée à chaque tour
     */
    private void uneFrame(){
        environnement.unTour();
    }

    /**
     * Méthode qui gere tout ce qui concerne la Barre de Vie et qui lie les listeners et les binds
     */
    public void gestionBarreDeVie(){
        int pvTotal = environnement.getPvVal();
        totalpv.setText(""+pvTotal);
        pvActuel.textProperty().bind(environnement.getPv().asString());
        ChangeListener<Number> pv = ((obs , old , nouv) -> barredevie.setProgress(nouv.doubleValue()/pvTotal));
        environnement.getPv().addListener(pv);
    }
}