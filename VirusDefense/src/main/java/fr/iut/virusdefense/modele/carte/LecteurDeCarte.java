package fr.iut.virusdefense.modele.carte;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.Environnement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurDeCarte {

    private final File fichier;
    private final ConstructeurDeCarte constructeurDeCarte;
    private Scanner scanner;

    public LecteurDeCarte(Environnement environnement){
        try {
            fichier = new File(Main.class.getResource("cartes/carte.txt").toURI());

            constructeurDeCarte = new ConstructeurDeCarte(environnement);

            lire();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int[] prochaineLigne(){
        String[] ligneString = scanner.nextLine().split(" ");
        int[] ligneInt = new int[ligneString.length];

        for (int i = 0; i < ligneString.length; i++)
            ligneInt[i] = Integer.parseInt(ligneString[i]);

        return ligneInt;
    }

    private void lire() throws FileNotFoundException {
        scanner = new Scanner(fichier);
        int[] ligne;
        int hauteur, largeur;
        int nbGenerateurs;

        ligne = prochaineLigne();
        hauteur = ligne[0];
        largeur = ligne[1];
        constructeurDeCarte.setTaille(hauteur, largeur);

        constructeurDeCarte.setObjectif(prochaineLigne());

        nbGenerateurs = Integer.parseInt(scanner.nextLine());
        for (int i=0; i<nbGenerateurs; i++)
            constructeurDeCarte.ajouterGenerateur(prochaineLigne());

        for (int indLigne = 0; indLigne < hauteur; indLigne++){
            ligne = prochaineLigne();

            for (int indColonne = 0; indColonne < largeur; indColonne++)
                constructeurDeCarte.changerValeur(indLigne, indColonne, ligne[indColonne] == 1);

        }
    }

    public Carte creer(){
        return constructeurDeCarte.recupCarte();
    }
}
