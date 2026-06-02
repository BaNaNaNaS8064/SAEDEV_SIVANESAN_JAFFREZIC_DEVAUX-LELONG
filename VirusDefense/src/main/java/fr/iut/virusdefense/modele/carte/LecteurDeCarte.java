package fr.iut.virusdefense.modele.carte;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.Environnement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurDeCarte {

    private final File fichier;
    private final ConstructeurDeCarte constructeurDeCarte;

    public LecteurDeCarte(Environnement environnement){
        try {
            fichier = new File(Main.class.getResource("cartes/carte.txt").toURI());

            constructeurDeCarte = new ConstructeurDeCarte(environnement);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        lire();
    }

    private void lire() {
        try(Scanner scanner = new Scanner(fichier)){
            String[] ligne;
            int hauteur, largeur;
            int nbGenerateurs;

            ligne = scanner.nextLine().split(" ");
            hauteur = Integer.parseInt(ligne[0]);
            largeur = Integer.parseInt(ligne[1]);
            constructeurDeCarte.setTaille(hauteur, largeur);

            ligne = scanner.nextLine().split(" ");
            constructeurDeCarte.setObjectif(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1]));

            nbGenerateurs = Integer.parseInt(scanner.nextLine());
            for (int i=0; i<nbGenerateurs; i++){
                ligne = scanner.nextLine().split(" ");
                constructeurDeCarte.ajouterGenerateur(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1]));
            }

            for (int indLigne = 0; indLigne < hauteur; indLigne++){
                ligne = scanner.nextLine().split(" ");
                for (int indColonne = 0; indColonne < largeur; indColonne++){
                    constructeurDeCarte.changerValeur(indLigne, indColonne, Integer.parseInt(ligne[indColonne]) == 1);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public Carte creer(){
        return constructeurDeCarte.recupCarte();
    }
}
