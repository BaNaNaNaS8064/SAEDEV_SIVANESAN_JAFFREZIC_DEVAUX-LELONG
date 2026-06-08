package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class MuleTyple extends Cellule{

    private static int coutBase = 80;

    public static int getCoutBase() {
        return coutBase;
    }

    private MuleTyple(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 50, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getLigne(), getColonne(), getEnvironnement().getMaladies(), 3.0 , 3));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonSimple(getEnvironnement(), getLigne(), getColonne(), 30, getReconnaissance().getCibles()));
    }

    public static MuleTyple creer(Environnement env, int ligne, int colonne){
        MuleTyple temp = new MuleTyple(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "Mule-typle";
    }

    @Override
    public int coutNiveau2() {
        return 120;
    }

    @Override
    public int coutNiveau3() {
        return 250;
    }

    @Override
    public void ameliorerAuNiveau2() {
        getReconnaissance().setPortee(getReconnaissance().getPortee()+0.5);
    }

    @Override
    public void ameliorerAuNiveau3() {
        getReconnaissance().setNombreCiblesMax(5);
    }
}
