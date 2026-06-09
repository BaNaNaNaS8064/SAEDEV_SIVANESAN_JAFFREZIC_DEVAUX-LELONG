package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;
import fr.iut.virusdefense.modele.utilitaires.CodeTuile;

import java.util.HashMap;

/**
 * La classe AssociationImage gère l'association entre les codes de Tuiles et les images
 */
public class AssociationImage {

    /**
     * Associe à un code tuile le chemin de son image (à partir de tuiles/ dans les ressources)
     */
    public static HashMap<CodeTuile, String> correspondanceTuile = creerMapTuile();

    /**
     * @return une HashMap de correspondance code tuile - image
     */
    private static HashMap<CodeTuile, String> creerMapTuile(){
        HashMap<CodeTuile, String> temp = new HashMap<>();

        temp.put(CodeTuile.VIDE, "Vide.png");
        temp.put(CodeTuile.MUR, "Mur.png");
        temp.put(CodeTuile.GENERATEUR, "Gen.png");
        temp.put(CodeTuile.OBJECTIF, "Obj.gif");

        temp.put(CodeTuile.SAINPLE, "cellules/Sainple.png");
        temp.put(CodeTuile.LASERE, "cellules/Sainple.png");
        temp.put(CodeTuile.BROUAIEUSE, "cellules/Sainple.png");
        temp.put(CodeTuile.MULETYPLE, "cellules/Sainple.png");
        temp.put(CodeTuile.SNAIPEUR, "cellules/Sainple.png");
        temp.put(CodeTuile.RIZCOCHER, "cellules/Sainple.png");
        temp.put(CodeTuile.KONSANTRE, "cellules/Sainple.png");
        temp.put(CodeTuile.POUAZON, "cellules/Sainple.png");

        return temp;
    }

    /**
     * Fait le lien entre un code de tuile et son image.
     * Si le code est invalide retourne l'image de VIDE
     * @param codeTuile le code de la tuile dont on cherche l'image (par exemple MUR)
     * @return le chemin vers l'image qui correspond à CodeTuile
     */
    public static String imageDe(CodeTuile codeTuile){
        return String.valueOf(Main.class.getResource("images/tuiles/" + correspondanceTuile.getOrDefault(codeTuile, correspondanceTuile.get(CodeTuile.VIDE))));
    }



    public static HashMap<CodeMaladie, String> correspondanceMaladie = creerMapMaladie();

    private static HashMap<CodeMaladie, String> creerMapMaladie(){
        HashMap<CodeMaladie, String> temp = new HashMap<>();

        temp.put(CodeMaladie.BACTERIEBANALE, "BB.gif");
        temp.put(CodeMaladie.PARASITE, "Pa.gif");
        temp.put(CodeMaladie.VIRUS, "Vi.gif");
        temp.put(CodeMaladie.VIRUSCOMPOSE, "VC.png");
        temp.put(CodeMaladie.PETITCHAMPIGNON, "PC.png");
        temp.put(CodeMaladie.GRANDCHAMPIGNON, "GC.png");
        temp.put(CodeMaladie.TUMEUR, "Tu.png");

        return temp;
    }

    public static String imageDe(CodeMaladie codeMaladie){
        return String.valueOf(Main.class.getResource("images/maladies/" + correspondanceMaladie.get(codeMaladie)));
    }

}
