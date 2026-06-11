package fr.iut.virusdefense.modele;

public abstract class Acteur extends Identifiable{

    public Acteur(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne);
    }

    public Acteur(Environnement environnement, double ligne, double colonne){
        super(environnement, ligne, colonne);
    }

    /**
     * Méthode exécutée à chaque tour
     */
    public abstract void agir();

}
