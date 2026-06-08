package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Dot;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Pouazon extends Cellule{
    private static int coutBase = 130;

    public static int getCoutBase() {
        return coutBase;
    }

    private Pouazon(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 50, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getLigne(), getColonne(), getEnvironnement().getMaladies(), 3.0, 1));
    }

    @Override
    public void initAttaque(){
        AtkRayon temp = new AtkRayonSimple(getEnvironnement(), getLigne(), getColonne(), 10, getReconnaissance().getCibles());
        temp.ajouterAlteration(new Dot(15,1));
        setAttaque(temp);
    }

    public static Pouazon creer(Environnement env, int ligne, int colonne){
        Pouazon temp = new Pouazon(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
