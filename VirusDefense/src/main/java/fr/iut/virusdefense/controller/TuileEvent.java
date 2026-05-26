package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.Sainple;
import fr.iut.virusdefense.modele.utilitaires.CodeTuile;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import fr.iut.virusdefense.vue.sprites.Tuile;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.TilePane;

public class TuileEvent {
    private Environnement env;
    private ToggleGroup toggleGrpCellules;
    private AfficheurDeCarte ac;
    TilePane tuiles;

    public TuileEvent(Environnement env, ToggleGroup toggleGrpCellules, AfficheurDeCarte ac, TilePane tuiles){
        this.env = env;
        this.toggleGrpCellules = toggleGrpCellules;
        this.ac = ac;
        this.tuiles = tuiles;
    }

    public void ajouterEventTuile(){
        for (int i = 0; i<(env.getCarte().getHauteur() * env.getCarte().getLargeur()); i++) {
            Tuile t = (Tuile) tuiles.getChildren().get(i);

            t.setOnMousePressed(event -> {});

            if(env.getCarte().getCode(t.getLigne(), t.getColonne()) != CodeTuile.MUR)
                t.setOnMousePressed(mouseEvent -> {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && env.getCarte().getCode(t.getLigne(), t.getColonne()) == CodeTuile.VIDE)
                        poser(t.getLigne(), t.getColonne());
                    else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && env.getCarte().getCode(t.getLigne(), t.getColonne()) != CodeTuile.VIDE) {
                        env.retirerCelluleA(t.getLigne(), t.getColonne(), false);
                        ac.reloadEmplacementCarte(t.getLigne(), t.getColonne());
                    }
                });
        }
    }

    public void poser(int ligne, int colonne) {
        Cellule c = null;

        String type = ((RadioButton) toggleGrpCellules.getSelectedToggle()).getId();

        switch (type){
            case "RbSainple":
                c = Sainple.creer(env, ligne, colonne);
                break;
        }

        if (c != null && env.getJoueur().getPc() >= c.getCout()) {
            env.ajouterCellule(c);
            env.vérifierPoserCellules(ligne, colonne, c);
        }

        ajouterEventTuile();
        ac.reloadEmplacementCarte(ligne, colonne);
    }
}
