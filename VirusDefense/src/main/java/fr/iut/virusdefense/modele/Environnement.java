package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.apparition.Generateur;
import fr.iut.virusdefense.modele.apparition.Niveau;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesGeneriques.Rayon;
import fr.iut.virusdefense.modele.maladies.Maladie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Représente le terrain dans lequel il y aura les cellules et maladies
 */
public class Environnement {

    /// La carte, indique où sont les murs et emplacements vides
    private final Carte carte;

    private final Deplacement deplacement;

    private final Joueur joueur;

    private final Niveau niveau;

    /**
     * La liste des maladies dans le terrain
     */
    private final ObservableList<Maladie> maladies;

    private final ObservableList<Rayon> rayons;

    private boolean fin = false;
    private boolean victoire = false;

    /**
     * Créé un terrain sans maladies
     */
    public Environnement() {
        maladies = FXCollections.observableArrayList();
        rayons = FXCollections.observableArrayList();
        carte = new Carte(this);
        carte.initGenerateurs();
        deplacement = new Deplacement(carte);
        joueur = new Joueur();
        niveau = new Niveau(this);
    }

    public Carte getCarte() {
        return carte;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public Deplacement getDeplacement() {
        return deplacement;
    }

    public ObservableList<Maladie> getMaladies() {
        return maladies;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ObservableList<Rayon> getTirs() {
        return rayons;
    }

    public boolean isFin() {
        return fin;
    }

    public boolean isVictoire() {
        return victoire;
    }

    /**
     * Ajoute {@code m} à {@code maladies}
     *
     * @param m une maladie à ajouter
     */
    public void ajouter(Maladie m) {
        maladies.add(m);
    }

    public void ajouterCellule(Cellule c){
        carte.getCellules().add(c);
        this.joueur.retirerPC(c.getCout());
        deplacement.faireAlgo();
    }

    public void retirerCellule(Cellule c, boolean rendrePC){
        carte.getCellules().remove(c);
        if (rendrePC) this.joueur.ajouterPC(c.getCout());
        deplacement.faireAlgo();
    }

    public void retirerCelluleA(int ligne, int colonne, boolean rendrePC){
        int i=0;
        boolean trouvé = false;
        while (i < carte.getCellules().size() || !trouvé){
            if ((int)carte.getCellules().get(i).getLigne() == ligne && (int)carte.getCellules().get(i).getColonne() == colonne){
                trouvé = true;
                retirerCellule(carte.getCellules().get(i), rendrePC);
            }
            i++;
        }
        deplacement.faireAlgo();
    }

    public void ajouterRayon(Rayon r){
        rayons.add(r);
    }

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour() {
        if (joueur.getPv() > 0 && niveau.getNumVague()<=20) {
            for (int i = rayons.size()-1; i >= 0; i--)
                if (rayons.get(i).getAge() >= 2)
                    rayons.remove(i);

            for (Rayon r : rayons)
                r.agir();


            for (Cellule c : carte.getCellules())
                c.agir();

            niveau.update();

            for (Generateur g : carte.getGenerateurs())
                g.agir();

            for (Maladie m : maladies)
                m.agir();

            for (int i=maladies.size()-1; i >= 0; i--)
                if (!maladies.get(i).estVivant()) {
                    this.joueur.ajouterPC(maladies.get(i).getRecompense());
                    maladies.remove(i);
                }
        }else{
            fin = true;
            if(joueur.getPv()>0)
                victoire=true;
        }
    }


}
