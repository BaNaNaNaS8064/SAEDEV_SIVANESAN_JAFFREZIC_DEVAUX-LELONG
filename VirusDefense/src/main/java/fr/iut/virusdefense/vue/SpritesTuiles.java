package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.CodeTuile;

import java.util.HashMap;

/**
 * La classe SpritesTuiles gère l'association entre les codes de Tuiles et les images
 */
public class SpritesTuiles {

    /**
     * Associe à un code tuile le chemin de son image (à partir de tuiles/ dans les ressources)
     */
    public static HashMap<CodeTuile, String> correspondance = createMap();

    /**
     * @return une HashMap de correspondance code tuile - image
     */
    private static HashMap<CodeTuile, String> createMap(){
        HashMap<CodeTuile, String> temp = new HashMap<>();

        temp.put(CodeTuile.VIDE, "Vide.png");
        temp.put(CodeTuile.MUR, "Mur.png");
        temp.put(CodeTuile.SAINPLE, "cellules/Sainple.png");
        temp.put(CodeTuile.GENERATEUR, "Gen.png");

        return temp;
    }

    /**
     * Fait le lien entre un code de tuile et son image.
     * Si le code est invalide retourne l'image de VIDE
     * @param codeTuile le code de la tuile dont on cherche l'image (par exemple MUR)
     * @return le chemin vers l'image qui correspond à CodeTuile
     */
    public static String imageDe(CodeTuile codeTuile){
        return String.valueOf(Main.class.getResource("tuiles/" + correspondance.getOrDefault(codeTuile, correspondance.get(CodeTuile.VIDE))));
    }

}
