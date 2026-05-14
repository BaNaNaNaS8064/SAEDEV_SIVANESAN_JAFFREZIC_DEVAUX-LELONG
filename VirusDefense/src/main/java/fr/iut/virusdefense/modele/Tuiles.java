package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.Main;
import java.util.HashMap;

public class Tuiles {

    public final static int VIDE = 0;
    public final static int MUR = 1;

    public static HashMap<Integer, String> correspondance = createMap();

    private static HashMap<Integer, String> createMap(){
        HashMap<Integer, String> temp = new HashMap<>();

        temp.put(VIDE, "Vide.png");
        temp.put(MUR, "Mur.png");

        return temp;
    }

    public static String imageDe(int codeTuile){
        return String.valueOf(Main.class.getResource("tuiles/" + correspondance.getOrDefault(codeTuile, correspondance.get(VIDE))));
    }

}
