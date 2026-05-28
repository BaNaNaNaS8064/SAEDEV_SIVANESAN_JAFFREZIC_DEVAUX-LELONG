package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonConcentre;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;

public class Konsantre extends Cellule{
    private Entite cible;


    private Konsantre(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 1, 50);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecUnique(this, 3.0));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonConcentre(this, 1));
    }

    public static Konsantre creer(Environnement env, int ligne, int colonne){
        Konsantre temp = new Konsantre(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
