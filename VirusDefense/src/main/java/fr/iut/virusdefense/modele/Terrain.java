package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.vue.SpritesTuiles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Représente le terrain dans lequel il y aura les cellules et maladies
 */
public class Terrain {

    /// La carte, indique où sont les murs et emplacements vides
    private int[][] map;

    /// L'endroit à protéger des maladies
    private List<Integer> objectif;

    /** Associe à une case les coordonées de la prochaine
     * dans le chemin optimal vers l'objectif
     */
    private Map<List<Integer>, List<Integer>> predecesseurs;

    /**
     * La liste des maladies dans le terrain
     */
    private final ObservableList<Maladie> maladies;

    private int tour;

    /**
     * Créé un terrain sans maladies
     */
    public Terrain(){
        maladies = FXCollections.observableArrayList();
        initMap();
        objectif = chercherObjectif();
        predecesseurs = new HashMap<>();
        faireBFS();
        tour = 0;
    }

    public List<Integer> getObjectif() {
        return objectif;
    }

    public int getTour() {
        return tour;
    }

    public int[][] getMap(){
        return map;
    }
    
    public int getHauteur(){
        return map.length;
    }

    public int getLargeur(){
        return map[0].length;
    }

    public ObservableList<Maladie> getMaladies(){
        return maladies;
    }

    /**
     * Ajoute {@code m} à {@code maladies}
     * @param m une maladie à ajouter
     */
    public void ajouter(Maladie m){
        maladies.add(m);
    }

    /**
     * Retourne vrai si (ligne;colonne) se trouve dans les bornes du terrain
     * @param ligne une ligne
     * @param col une colonne
     * @return si (i;y) se trouve dans les bornes du terrain
     */
    public boolean dansBornes(double ligne, double col){
        return (0 <= ligne && ligne < getHauteur()) && (0 <= col && col < getLargeur());
    }

    /**
     * Créé la map (pour l'instant une seul map possible faite à la main)
     */
    private void initMap(){
        map = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 2},
                {1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
    }

    /**
     * Cherche {@code Tuiles.OBJECTIF} dans la map et renvoie ses coords
     * sous forme de List de [ligne, colonne]
     *
     * @return Les coords de la première occurence trouvée de
     * {@code Tuiles.OBJECTIF}, null sinon
     */
    private List<Integer> chercherObjectif(){
        for (int i = 0; i < getHauteur(); i++)
            for (int j = 0; j < getLargeur(); j++)
                if (map[i][j] == 2)
                    return List.of(i, j);

        return null;
    }

    /**
     * Remplit {@code predecesseurs} à l'aide de l'algorithme du BFS à partir de {@code objectif}
     */
    private void faireBFS() {
        predecesseurs.clear();

        LinkedList<List<Integer>> fifo = new LinkedList<>();
        List<Integer> caseActuelle;

        fifo.add(List.of(objectif.get(0), objectif.get(1)));
        predecesseurs.put(List.of(objectif.get(0), objectif.get(1)), null);

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
            if (dansBornes(ligne, col) && map[ligne][col] == 0)
                voisins.add(List.of(ligne, col));
        }

        return voisins;
    }

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour(){
        for (Maladie m : maladies)
            m.agir();
        tour++;
    }

}
