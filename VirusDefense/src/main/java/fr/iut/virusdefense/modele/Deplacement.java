package fr.iut.virusdefense.modele;

import java.util.*;

public class Deplacement {
    /**
     * Associe à une case les coordonées de la prochaine
     * dans le chemin optimal vers l'objectif
     */
    private final Map<List<Integer>, List<Integer>> prochaineCase;

    private final Carte carte;

    public Deplacement(Carte carte){
        prochaineCase = new HashMap<>();
        this.carte = carte;
        faireAlgo();
    }

    /**
     * Remplit {@code prochain} à l'aide de l'algorithme du BFS à partir de {@code objectif}
     */
    public void faireAlgo() {
        prochaineCase.clear();

        LinkedList<List<Integer>> fifo = new LinkedList<>();
        List<Integer> caseActuelle = List.of(carte.getObjectif().get(0), carte.getObjectif().get(1));

        fifo.add(caseActuelle);
        prochaineCase.put(caseActuelle, null);

        while (!fifo.isEmpty()){
            caseActuelle = fifo.poll();
            for (List<Integer> voisin : voisins(caseActuelle)){
                if (!prochaineCase.containsKey(voisin)){
                    fifo.add(voisin);
                    prochaineCase.put(voisin, caseActuelle);
                }
            }
        }
    }

    /**
     * Retourne tous les voisins qui sont vides de uneCase
     *
     * @param coordsCase Une liste sous forme [ligne, colonne]
     *                qui représente les coordonnées d'un case
     *
     * @return une liste de listes (sous forme [[ligne, colonne], ...])
     * qui représente une liste des voisins de {@code uneCase}
     */
    private ArrayList<List<Integer>> voisins(List<Integer> coordsCase){
        ArrayList<List<Integer>> voisins = new ArrayList<>();
        int ligne, col;
        int[][] decalages = new int[][]{
                {-1, 0},
                {0, -1},
                {+1, 0},
                {0, +1}
        };

        for (int[] decalage : decalages){
            ligne = coordsCase.get(0) + decalage[0];
            col = coordsCase.get(1) + decalage[1];
            if (carte.dansBornes(ligne, col) && carte.peutMarcher(ligne, col))
                voisins.add(List.of(ligne, col));
        }

        return voisins;
    }

    /**
     * Retourne la prochaine case après {@code coords}
     * dans le chemin optimal vers l'objectif
     *
     * @param coordsCase Une liste sous forme [ligne, colonne]
     *               qui représente les coordonnées d'un case
     *
     * @return Une liste sous forme [ligne, colonne]
     * qui représente la prochaine case dans le chemin optimal vers l'objectif
     */
    public List<Integer> prochaineCase(List<Integer> coordsCase){
        return prochaineCase.get(coordsCase);
    }
    
    public boolean estBloquee(List<Integer> coordsCase){
        return !prochaineCase.containsKey(coordsCase);
    }
}
