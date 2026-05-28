package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonBase;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

public class Sainple extends Cellule {

    private Sainple(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 60, 50);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecUnique(this, 3.0));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonBase(this, 45));
    }

    public static Sainple creer(Environnement env, int ligne, int colonne){
        Sainple temp = new Sainple(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
