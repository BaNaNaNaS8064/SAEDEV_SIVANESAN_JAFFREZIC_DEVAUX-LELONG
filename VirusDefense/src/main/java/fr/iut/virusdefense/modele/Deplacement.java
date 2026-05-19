package fr.iut.virusdefense.modele;

import java.util.*;

public class Deplacement {
    /** Associe à une case les coordonées de la prochaine
     * dans le chemin optimal vers l'objectif
     */
    private Map<List<Integer>, List<Integer>> predecesseurs;
    private Carte carte;

    public Deplacement(Carte map){
        predecesseurs = new HashMap<>();
        carte = map;
        faireBFS();
    }

    /**
     * Remplit {@code predecesseurs} à l'aide de l'algorithme du BFS à partir de {@code objectif}
     */
    private void faireBFS() {
        predecesseurs.clear();

        LinkedList<List<Integer>> fifo = new LinkedList<>();
        List<Integer> caseActuelle;

        fifo.add(List.of(carte.getObjectif().get(0), carte.getObjectif().get(1)));
        predecesseurs.put(List.of(carte.getObjectif().get(0), carte.getObjectif().get(1)), null);

        while (!fifo.isEmpty()){
            caseActuelle = fifo.poll();
            for (List<Integer> voisin : voisins(caseActuelle)){
                if (!predecesseurs.containsKey(voisin)){
                    fifo.add(voisin);
                    predecesseurs.put(voisin, caseActuelle);
                }
            }
        }
    }

    /**
     * Retourne la prochaine case après {@code coords}
     * dans le chemin optimal vers l'objectif
     *
     * @param coords Une liste sous forme [ligne, colonne]
     *               qui représente les coordonnées d'un case
     *
     * @return Une liste sous forme [ligne, colonne]
     * qui représente la prochaine case dans le chemin optimal vers l'objectif
     */
    public List<Integer> prochaineCase(List<Integer> coords){
        return predecesseurs.get(coords);
    }

    /**
     * Retourne tous les voisins qui sont vides de uneCase
     *
     * @param uneCase Une liste sous forme [ligne, colonne]
     *                qui représente les coordonnées d'un case
     *
     * @return une liste de listes (sous forme [[ligne, colonne], ...])
     * qui représente une liste des voisins de {@code uneCase}
     */
    private ArrayList<List<Integer>> voisins(List<Integer> uneCase){
        ArrayList<List<Integer>> voisins = new ArrayList<>();

        int[][] decalages = new int[][]{
                {-1, 0},
                {0, -1},
                {+1, 0},
                {0, +1}
        };
        int ligne, col;

        for (int[] decalage : decalages){
            ligne = uneCase.get(0) + decalage[0];
            col = uneCase.get(1) + decalage[1];
            if (carte.dansBornes(ligne, col) && !carte.getValeurCase(ligne, col))
                voisins.add(List.of(ligne, col));
        }

        return voisins;
    }
}
