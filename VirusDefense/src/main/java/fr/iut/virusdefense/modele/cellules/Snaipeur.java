package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

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
        setReconnaissance(new RecUnique(this, 12.0));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonSimple(this, 100));
    }

    public static Snaipeur creer(Environnement env, int ligne, int colonne){
        Snaipeur temp = new Snaipeur(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
