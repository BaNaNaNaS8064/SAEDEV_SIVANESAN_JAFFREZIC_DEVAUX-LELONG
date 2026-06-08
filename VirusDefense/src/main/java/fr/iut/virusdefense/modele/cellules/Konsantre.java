package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonConcentre;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;
import fr.iut.virusdefense.modele.entitesgeneriques.Entite;

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
        setReconnaissance(new RecUnique(this, 3.0));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonConcentre(this, 1 , 150));
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
        ((AtkRayonConcentre)getAttaque()).setDegatBase(getAttaque().getDegats());
    }

    @Override
    public void ameliorerAuNiveau3() {
        ((AtkRayonConcentre)getAttaque()).setDelaiAugmentationAttaque(((AtkRayonConcentre)getAttaque()).getDelaiAugmentationAttaque()-50);
    }
}
