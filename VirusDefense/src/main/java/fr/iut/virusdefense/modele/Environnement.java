package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.apparition.Generateur;
import fr.iut.virusdefense.modele.apparition.Niveau;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import fr.iut.virusdefense.modele.entitesgeneriques.Zone;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.StatutPartie;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

    private final ObservableList<Zone> zones;

    private ObjectProperty<StatutPartie> statutPartieProperty;

    /**
     * Créé un terrain sans maladies
     */
    public Environnement() {
        maladies = FXCollections.observableArrayList();
        rayons = FXCollections.observableArrayList();
        zones =  FXCollections.observableArrayList();
        carte = new Carte(this);
        carte.initGenerateurs();
        deplacement = new Deplacement(carte);
        joueur = new Joueur();
        niveau = new Niveau(this);
        statutPartieProperty = new SimpleObjectProperty<>(StatutPartie.PASTERMINEE);
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

    public ObservableList<Rayon> getRayons() {
        return rayons;
    }

    public ObservableList<Zone> getZones() {
        return zones;
    }

    public final StatutPartie getStatutPartie(){
        return statutPartieProperty.getValue();
    }

    public final ObjectProperty<StatutPartie> statutPartieProperty(){
        return statutPartieProperty;
    }

    public final void setStatutPartie(StatutPartie statutPartie){
        statutPartieProperty.setValue(statutPartie);
    }

    /**
     * Ajoute {@code m} à {@code maladies}
     *
     * @param m une maladie à ajouter
     */
    public void ajouterMaladie(Maladie m) {
        maladies.add(m);
    }

    public void ajouterCellule(Cellule c){
        carte.getCellules().add(c);
        this.joueur.retirerPc(c.getCout());
        deplacement.faireAlgo();
    }

    public void retirerCellule(Cellule c, boolean rendrePC){
        carte.getCellules().remove(c);
        if (rendrePC) this.joueur.ajouterPc(c.getCout());
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

    public void ajouterZone(Zone z){
        zones.add(z);
    }

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour() {
        if (getStatutPartie() == StatutPartie.PASTERMINEE) {
            if (joueur.getPv() > 0 && (!niveau.estTermine() || !maladies.isEmpty())) {
                for (int i = rayons.size() - 1; i >= 0; i--)
                    if (rayons.get(i).aDepasseAgeMaximal())
                        rayons.remove(i);

                for (int i = zones.size() - 1; i >= 0; i--)
                    if (zones.get(i).aDepasseAgeMaximal())
                        zones.remove(i);

                for (Rayon r : rayons)
                    r.agir();

                for (Zone z : zones)
                    z.agir();

                for (Cellule c : carte.getCellules())
                    c.agir();

                niveau.update();

                for (Generateur g : carte.getGenerateurs())
                    g.agir();

                for (int i = maladies.size() - 1; i >= 0; i--)
                    maladies.get(i).agir();
            }
            else{
                if(joueur.getPv()>0)
                    setStatutPartie(StatutPartie.GAGNEE);
                else
                    setStatutPartie(StatutPartie.PERDUE);
            }
        }
    }

    public boolean maladiesBloquées(){
        int i = 0;
        while( i < getMaladies().size()){
            if(getDeplacement().estBloquee(getMaladies().get(i).position()))
                return true;
            i++;
        }
        return false;
    }

    public void vérifierPoserCellules(Cellule c){
        if (maladiesBloquées() || getCarte().générateursBloqués()){
            retirerCellule(c, true);
        }
    }
}
