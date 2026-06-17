package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurVagues {
    private File fichier;
    private Scanner sc;
    private Vague[] vagues;

    private int nbVague;
    private int nbPointsApparition;

    public LecteurVagues(int nbPointsApparition, String idNiveau){
        try{
            fichier = new File(Main.class.getResource("niveaux/" + idNiveau + "/vagues.txt").toURI());
            this.nbPointsApparition = nbPointsApparition;
            lire();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private double[] prochaineLigne(String s){
        String[] ligneString = s.split(" ");
        double[] ligneDouble = new double[ligneString.length];

        for (int i = 0; i < ligneString.length; i++)
            ligneDouble[i] = Double.parseDouble(ligneString[i]);

        return ligneDouble;
    }

    public int getNbVague() {
        return nbVague;
    }

    public Vague[] getVagues() {
        return vagues;
    }

    public void lire() throws FileNotFoundException {
        sc = new Scanner(fichier);
        double[] maladiesInfo;
        String ligne;

        nbVague = Integer.parseInt(sc.nextLine());
        vagues = new Vague[nbVague];
        sc.nextLine();
        for (int indVague = 0; indVague < nbVague; indVague++) {
            vagues[indVague] = new Vague();
            ligne = sc.nextLine();
            while(!ligne.equals("#")){
                maladiesInfo = prochaineLigne(ligne);
                for (int indPointApparition = 0; indPointApparition < nbPointsApparition; indPointApparition++) {
                    vagues[indVague].ajouter(new ListeApparition());
                    for (int nombreMemeMaladie = 0; nombreMemeMaladie < (int)maladiesInfo[0]; nombreMemeMaladie++) {
                        vagues[indVague].getListeApparitions().get(indPointApparition).ajouter(CodeMaladie.values()[(int)maladiesInfo[1]], (int)(maladiesInfo[2] *60));
                    }
                }
                ligne = sc.nextLine();
            }
        }
    }
}
