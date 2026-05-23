package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.entitesGeneriques.Entite;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.Attaque;
import fr.iut.virusdefense.modele.cellules.reconnaissance.Reconnaissance;

public abstract class Cellule extends Entite {
    private Reconnaissance reconnaissance ;
    private Attaque typeAttaque;

    private final int frequenceAttaque;
    private int delai;

    private int niveau;
    private final int cout;

    public Cellule(Environnement environnement, int ligne, int colonne, int frequenceAttaque , int cout ){
        super(environnement, ligne, colonne);
        this.frequenceAttaque = frequenceAttaque;
        this.delai = frequenceAttaque;
        this.niveau = 1 ;
        this.cout = cout;
    }

    public Reconnaissance getReconnaissance() {
        return reconnaissance;
    }

    public void setReconnaissance(Reconnaissance reconnaissance){
        this.reconnaissance = reconnaissance;
    }

    public void setTypeAttaque(Attaque typeAttaque){
        this.typeAttaque = typeAttaque;
    }

    public abstract void initRec();

    public abstract void initTypeAttaque();

    public int getCout() {
        return cout;
    }

    @Override
    public void agir(){
        delai--;

        if (delai <=0){
            if (!reconnaissance.ciblesValides())
                reconnaissance.changerCibles();

            typeAttaque.attaqueCibles();
            delai = frequenceAttaque;
        }
    }
}
