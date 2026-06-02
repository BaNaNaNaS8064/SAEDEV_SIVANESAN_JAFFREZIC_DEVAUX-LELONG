package fr.iut.virusdefense.modele.carte;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.apparition.Generateur;
import java.util.ArrayList;
import java.util.List;

public class ConstructeurDeCarte {

    private boolean[][] carteStatique;
    private List<Integer> objectif;
    private Environnement environnement;
    private ArrayList<Generateur> generateurs;

    public ConstructeurDeCarte(Environnement environnement){
        this.environnement = environnement;
        generateurs = new ArrayList<>();
    }

    public void setTaille(int hauteur, int largeur){
        carteStatique = new boolean[hauteur][largeur];
    }

    public void setObjectif(int ligne, int colonne){
        objectif = List.of(ligne, colonne);
    }

    public void ajouterGenerateur(int ligne, int colonne){
        generateurs.add(new Generateur(environnement, ligne, colonne));
    }

    public void changerValeur(int ligne, int colonne, boolean valeur){
        carteStatique[ligne][colonne] = valeur;
    }

    public Carte recupCarte(){
        return new Carte(carteStatique, objectif, generateurs);
    }

}
