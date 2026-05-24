package fr.iut.virusdefense.vue.sprites;

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

        temp.put(CodeTuile.VIDE, "Vide");
        temp.put(CodeTuile.MUR, "Mur");
        temp.put(CodeTuile.GENERATEUR, "Gen");

        temp.put(CodeTuile.SAINPLE, "cellules/Sainple");

        return temp;
    }

    /**
     * Fait le lien entre un code de tuile et son image.
     * Si le code est invalide retourne l'image de VIDE
     * @param codeTuile le code de la tuile dont on cherche l'image (par exemple MUR)
     * @return le chemin vers l'image qui correspond à CodeTuile
     */
    public static String imageDe(CodeTuile codeTuile){
        return String.valueOf(Main.class.getResource("images/tuiles/" + correspondanceTuile.getOrDefault(codeTuile, correspondanceTuile.get(CodeTuile.VIDE)) + ".png"));
    }



    public static HashMap<CodeMaladie, String> correspondanceMaladie = creerMapMaladie();

    private static HashMap<CodeMaladie, String> creerMapMaladie(){
        HashMap<CodeMaladie, String> temp = new HashMap<>();

        temp.put(CodeMaladie.BACTERIEBANALE, "BB");
        temp.put(CodeMaladie.PARASITE, "Pa");
        temp.put(CodeMaladie.VIRUS, "Vi");

        return temp;
    }

    public static String imageDe(CodeMaladie codeMaladie){
        return String.valueOf(Main.class.getResource("images/maladies/" + correspondanceMaladie.getOrDefault(codeMaladie, correspondanceTuile.get(CodeTuile.VIDE)) + ".png"));
    }

}
