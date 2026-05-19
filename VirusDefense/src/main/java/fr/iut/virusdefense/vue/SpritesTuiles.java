package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.Main;
import java.util.HashMap;

/**
 * La classe Tuiles gère les constantes de code tuiles et leur association avec les images
 */
public class SpritesTuiles {

    public final static int VIDE = 0;
    public final static int MUR = 1;
    public final static int OBJECTIF = 2;

    /**
     * Associe à un code tuile le chemin de son image (à partir de tuiles/ dans les ressources)
     */
    public static HashMap<Integer, String> correspondance = createMap();

    /**
     * @return une HashMap de correspondance code tuile - image
     */
    private static HashMap<Integer, String> createMap(){
        HashMap<Integer, String> temp = new HashMap<>();

        temp.put(VIDE, "Vide.png");
        temp.put(MUR, "Mur.png");
        temp.put(OBJECTIF, temp.get(VIDE));

        return temp;
    }

    /**
     * Fait le lien entre un code de tuile et son image.
     * Si le code est invalide retourne l'image de Tuiles.VIDE
     * @param codeTuile le code de la tuile dont on cherche l'image (par exemple Tuiles.MUR)
     * @return le chemin vers l'image qui correspond à codeTuile
     */
    public static String imageDe(int codeTuile){
        return String.valueOf(Main.class.getResource("tuiles/" + correspondance.getOrDefault(codeTuile, correspondance.get(VIDE))));
    }

}
