package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkProjectile;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Sainple extends Cellule {

    private static int coutBase = 50;

    public static int getCoutBase() {
        return coutBase;
    }

    private Sainple(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 60, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getLigne(), getColonne(), getEnvironnement().getMaladies(), 3.0, 1));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkProjectile(getEnvironnement(), getLigne(), getColonne(), 45, getReconnaissance().getCibles()));
    }

    public static Sainple creer(Environnement env, int ligne, int colonne){
        Sainple temp = new Sainple(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
