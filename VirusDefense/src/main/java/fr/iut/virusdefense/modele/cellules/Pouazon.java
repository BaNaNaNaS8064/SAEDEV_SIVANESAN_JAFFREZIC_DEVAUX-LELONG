package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Dot;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

public class Pouazon extends Cellule{
    private static int coutBase = 130;

    public static int getCoutBase() {
        return coutBase;
    }

    private Pouazon(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 50, coutBase, 5);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecUnique(this, 3.0));
    }

    @Override
    public void initAttaque(){
        AtkRayon temp = new AtkRayonSimple(this, 10);
        temp.ajouterAlteration(new Dot(15,1));
        setAttaque(temp);
    }

    public static Pouazon creer(Environnement env, int ligne, int colonne){
        Pouazon temp = new Pouazon(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "Pouazon";
    }

    @Override
    public int coutNiveau2() {
        return 0;
    }

    @Override
    public int coutNiveau3() {
        return 0;
    }

    @Override
    public void ameliorerAuNiveau2() {

    }

    @Override
    public void ameliorerAuNiveau3() {

    }
}
