package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurVague {
    private File fichier;
    private Scanner sc;
    private Vague[] vagues;

    private int nbVague;
    private int nbGen;

    public LecteurVague(String s, int nbGenerateur){
        try{
            fichier = new File(Main.class.getResource("vagues.txt").toURI());
            nbGen = nbGenerateur;
            lire();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int[] prochaineLigne(String s){
        String[] ligneString = s.split(" ");
        int[] ligneInt = new int[ligneString.length];

        for (int i = 0; i < ligneString.length; i++)
            ligneInt[i] = Integer.parseInt(ligneString[i]);

        return ligneInt;
    }

    public int getNbVague() {
        return nbVague;
    }

    public void lire() throws FileNotFoundException {
        sc = new Scanner(fichier).useDelimiter("\n");
        int[] maladiesInfo;
        String ligne;

        nbVague = sc.nextInt();
        vagues = new Vague[nbVague];
        sc.next();
        for (int indVague = 0; indVague < nbVague; indVague++) {
            ligne = sc.next();
            while(!ligne.equals("#")){
                maladiesInfo = prochaineLigne(ligne);
                for (int indGenerateur = 0; indGenerateur < nbGen; indGenerateur++) {
                    for (int nombreMemeMaladie = 0; nombreMemeMaladie < maladiesInfo[0]; nombreMemeMaladie++) {
                        vagues[indVague].getListeApparitions().get(indGenerateur).ajouter(CodeMaladie.values()[maladiesInfo[1]], maladiesInfo[2]);
                    }
                }
                ligne = sc.next();
            }
        }
    }
}
