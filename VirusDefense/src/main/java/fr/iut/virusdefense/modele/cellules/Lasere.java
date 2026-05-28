package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

public class Lasere extends Cellule{

    private Lasere(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 1, 50);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecUnique(this, 3.0));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayon(this, 1));
    }

    public static Lasere creer(Environnement env, int ligne, int colonne){
        Lasere temp = new Lasere(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
