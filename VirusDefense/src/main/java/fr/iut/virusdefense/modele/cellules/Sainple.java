package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Sainple extends Cellule {

    private static int coutBase = 50;

    public static int getCoutBase() {
        return coutBase;
    }

    private Sainple(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 60, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getLigne(), getColonne(), getEnvironnement().getMaladies(), 3.0, 1));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonSimple(getEnvironnement(), getLigne(), getColonne(), 40, getReconnaissance().getCibles()));
    }

    public static Sainple creer(Environnement env, int ligne, int colonne){
        Sainple temp = new Sainple(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "Sainple";
    }

    @Override
    public int coutNiveau2() {
        return 75;
    }

    @Override
    public int coutNiveau3() {
        return 150;
    }

    @Override
    public void ameliorerAuNiveau2(){
        getAttaque().setDegats(70);
    }

    @Override
    public void ameliorerAuNiveau3(){
        getReconnaissance().setPortee(getReconnaissance().getPortee()+1.5);
    }
}
