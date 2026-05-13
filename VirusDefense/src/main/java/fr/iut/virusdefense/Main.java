package fr.iut.virusdefense;

import fr.iut.virusdefense.modele.Terrain;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Terrain t = new Terrain();
        System.out.println(Arrays.deepToString(t.getMap()));
    }
}
