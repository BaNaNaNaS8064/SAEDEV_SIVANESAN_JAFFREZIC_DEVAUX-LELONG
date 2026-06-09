package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonConcentre;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Konsantre extends Cellule{
    private static int coutBase = 100;

    public static int getCoutBase() {
        return coutBase;
    }


    private Konsantre(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 1, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getLigne(), getColonne(), getEnvironnement().getMaladies(), 3.0, 1));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonConcentre(getEnvironnement(), getLigne(), getColonne(), 1, 60, getReconnaissance().getCibles()));
    }

    public static Konsantre creer(Environnement env, int ligne, int colonne){
        Konsantre temp = new Konsantre(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "Konsantré";
    }

    @Override
    public int coutNiveau2() {
        return 175;
    }

    @Override
    public int coutNiveau3() {
        return 200;
    }

    @Override
    public void ameliorerAuNiveau2() {
        getAttaque().setDegats(getAttaque().getDegats()+0.5);
    }

    @Override
    public void ameliorerAuNiveau3() {
        ((AtkRayonConcentre)getAttaque()).setDelaiAugmentation(((AtkRayonConcentre)getAttaque()).getDelaiAugmentation()-50);
    }
}
