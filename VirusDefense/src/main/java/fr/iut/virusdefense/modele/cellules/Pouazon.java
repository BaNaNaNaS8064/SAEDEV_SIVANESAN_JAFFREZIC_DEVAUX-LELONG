package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonBase;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Dot;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

public class Pouazon extends Cellule{
    private Pouazon(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 50, 50);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecUnique(this, 3.0));
    }

    @Override
    public void initAttaque(){
        AtkRayon temp = new AtkRayonBase(this, 1);
        temp.ajouterAlteration(new Dot(999,1));
        setAttaque(temp);
    }

    public static Pouazon creer(Environnement env, int ligne, int colonne){
        Pouazon temp = new Pouazon(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
