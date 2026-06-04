package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Snaipeur extends Cellule{
    private static int coutBase = 90;

    public static int getCoutBase() {
        return coutBase;
    }

    private Snaipeur(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 200, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getLigne(), getColonne(), getEnvironnement().getMaladies(), 12.0, 1));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonSimple(getEnvironnement(), getLigne(), getColonne(), 100, getReconnaissance().getCibles()));
    }

    public static Snaipeur creer(Environnement env, int ligne, int colonne){
        Snaipeur temp = new Snaipeur(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
