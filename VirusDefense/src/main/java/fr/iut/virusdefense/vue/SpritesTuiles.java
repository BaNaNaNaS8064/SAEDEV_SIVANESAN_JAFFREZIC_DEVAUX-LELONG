package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.Params;

import java.util.HashMap;

/**
 * La classe SpritesTuiles gère l'association entre les codes de Tuiles et les images
 */
public class SpritesTuiles {

    /**
     * Associe à un code tuile le chemin de son image (à partir de tuiles/ dans les ressources)
     */
    public static HashMap<Integer, String> correspondance = createMap();

    /**
     * @return une HashMap de correspondance code tuile - image
     */
    private static HashMap<Integer, String> createMap(){
        HashMap<Integer, String> temp = new HashMap<>();

        temp.put(Params.codeTuile.VIDE, "Vide.png");
        temp.put(Params.codeTuile.MUR, "Mur.png");
        temp.put(Params.codeTuile.SAINPLE, "cellules/Sainple.png");
        temp.put(Params.codeTuile.GENERATEUR, "Gen.png");

        return temp;
    }

    /**
     * Fait le lien entre un code de tuile et son image.
     * Si le code est invalide retourne l'image de VIDE
     * @param codeTuile le code de la tuile dont on cherche l'image (par exemple MUR)
     * @return le chemin vers l'image qui correspond à codeTuile
     */
    public static String imageDe(int codeTuile){
        return String.valueOf(Main.class.getResource("tuiles/" + correspondance.getOrDefault(codeTuile, correspondance.get(Params.codeTuile.VIDE))));
    }

}
