package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

public class Snaipeur extends Cellule{
    private Snaipeur(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 200, 50);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecUnique(this, 12.0));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayon(this, 100));
    }

    public static Snaipeur creer(Environnement env, int ligne, int colonne){
        Snaipeur temp = new Snaipeur(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
