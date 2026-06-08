package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonConcentre;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;

public class Konsantre extends Cellule{
    private static int coutBase = 100;

    public static int getCoutBase() {
        return coutBase;
    }

    private Entite cible;


    private Konsantre(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 1, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getLigne(), getColonne(), getEnvironnement().getMaladies(), 3.0, 1));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonConcentre(getEnvironnement(), getLigne(), getColonne(), 1, getReconnaissance().getCibles()));
    }

    public static Konsantre creer(Environnement env, int ligne, int colonne){
        Konsantre temp = new Konsantre(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
