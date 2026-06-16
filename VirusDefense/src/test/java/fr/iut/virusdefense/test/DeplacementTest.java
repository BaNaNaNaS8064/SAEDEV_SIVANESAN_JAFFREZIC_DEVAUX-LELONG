package fr.iut.virusdefense.test;

import fr.iut.virusdefense.modele.Deplacement;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.apparition.PointApparition;
import fr.iut.virusdefense.modele.carte.Carte;
import fr.iut.virusdefense.modele.carte.ConstructeurDeCarte;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeplacementTest {
    boolean[][] carteStatique;
    ArrayList<PointApparition> pointApp = new ArrayList<>();
    Carte carte;
    Deplacement dep;

    public void miseEnSituation(boolean[][] carteStatique, List<Integer> objectif, List<Integer> pointApparition){
        pointApp.add(new PointApparition(null, pointApparition.get(0), pointApparition.get(1)));
        carte = new Carte(carteStatique, List.of(2,4), pointApp);
        dep = new Deplacement(carte);
    }

    @Test
    void voisins() {

    }

    @Test
    void prochaineCase() {

    }

    @Test
    void estBloquee() {
        carteStatique = new boolean[][]{ //possède un grand mur qui sépare le point d'apparition et l'objectif
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false}
        };
        miseEnSituation(carteStatique, List.of(2,4), List.of(2,0));
        assertTrue(dep.estBloquee(List.of(2,0)));// Un mur sépare entièrement le chemin vers l'objectif depuis le spawn
        assertTrue(dep.estBloquee(List.of(2,2)));// Un mur sépare entièrement le chemin vers l'objectif depuis le mur
        assertFalse(dep.estBloquee(List.of(2,3))); // aucune séparation

        carteStatique = new boolean[][]{
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false}
        };
        miseEnSituation(carteStatique, List.of(4,4), List.of(0,0));
        assertTrue(dep.estBloquee(List.of(-1,0))); //hors carte (haut) donc pas d'accès
        assertTrue(dep.estBloquee(List.of(5,0))); //hors carte (bas) donc pas d'accès
        assertTrue(dep.estBloquee(List.of(0,-1))); //hors carte (gauche) donc pas d'accès
        assertTrue(dep.estBloquee(List.of(0,5))); //hors carte (droite) donc pas d'accès
        assertFalse(dep.estBloquee(List.of(0,0)));

        //possède un petit mur qui bloque l'accès le plus directe entre le point d'apparition et l'objectif mais laisse un chemin disponible
        carteStatique = new boolean[][]{
                {false, false, false, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, false, false, false}
        };

        miseEnSituation(carteStatique, List.of(2,4), List.of(2,0));
        assertFalse(dep.estBloquee(List.of(2,0))); // entre le petit mur
    }
}