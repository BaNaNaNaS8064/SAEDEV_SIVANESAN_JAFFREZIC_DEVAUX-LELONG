package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

public class Lasere extends Cellule{
    private static int coutBase = 70;

    public static int getCoutBase() {
        return coutBase;
    }

    private Lasere(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 1, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecUnique(this, 3.0));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonSimple(this, 1));
    }

    public static Lasere creer(Environnement env, int ligne, int colonne){
        Lasere temp = new Lasere(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
