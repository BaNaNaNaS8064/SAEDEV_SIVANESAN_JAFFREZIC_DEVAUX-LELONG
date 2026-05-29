package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.apparition.Generateur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.List;

public class AfficheurDeChemin {

    private Environnement environnement;
    private Pane paneLignes;

    public AfficheurDeChemin(Environnement environnement, Pane paneLignes){
        this.environnement = environnement;
        this.paneLignes = paneLignes;
    }

    public void dessinerChemin(){
        paneLignes.getChildren().clear();
        List<Integer> caseActuelle;
        List<Integer> prochaineCase;
        List<Integer> objectif = environnement.getCarte().getObjectif();
        Line ligne;

        for (Generateur g : environnement.getCarte().getGenerateurs()) {
            caseActuelle = g.position();
            prochaineCase = environnement.getDeplacement().prochaineCase(caseActuelle);
            while (!caseActuelle.equals(objectif)){
                ligne = new Line((caseActuelle.get(1)+0.5) * 32, (caseActuelle.get(0)+0.5) * 32, (prochaineCase.get(1)+0.5) * 32, (prochaineCase.get(0)+0.5) * 32);
                ligne.setStroke(Color.GREEN);
                paneLignes.getChildren().add(ligne);
                caseActuelle = prochaineCase;
                prochaineCase = environnement.getDeplacement().prochaineCase(caseActuelle);
            }
        }

    }

}
