package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.Main;
import java.util.HashMap;

/**
 * La classe SpritesTuiles gère l'association entre les codes de Tuiles et les images
 */
public class SpritesTuiles {

    public final static boolean VIDE = false;
    public final static boolean MUR = true;

    /**
     * Associe à un code tuile le chemin de son image (à partir de tuiles/ dans les ressources)
     */
    public static HashMap<Boolean, String> correspondance = createMap();

    /**
     * @return une HashMap de correspondance code tuile - image
     */
    private static HashMap<Boolean, String> createMap(){
        HashMap<Boolean, String> temp = new HashMap<>();

        temp.put(VIDE, "Vide.png");
        temp.put(MUR, "Mur.png");

        return temp;
    }

    /**
     * Fait le lien entre un code de tuile et son image.
     * Si le code est invalide retourne l'image de VIDE
     * @param codeTuile le code de la tuile dont on cherche l'image (par exemple MUR)
     * @return le chemin vers l'image qui correspond à codeTuile
     */
    public static String imageDe(boolean codeTuile){
        return String.valueOf(Main.class.getResource("tuiles/" + correspondance.getOrDefault(codeTuile, correspondance.get(VIDE))));
    }

}
