package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkZone;
import fr.iut.virusdefense.modele.cellules.attaque.Attaque;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecTous;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

public class Brouaileuse extends Cellule {
    private static int coutBase = 150;

    public static int getCoutBase() {
        return coutBase;
    }

    private Brouaileuse(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 200, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecTous(this, 1.5));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkZone(this, 70));
    }

    public static Brouaileuse creer(Environnement env, int ligne, int colonne){
        Brouaileuse temp = new Brouaileuse(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "Brouaïlleuse";
    }

    @Override
    public int coutNiveau2() {
        return 200;
    }

    @Override
    public int coutNiveau3() {
        return 275;
    }

    @Override
    public void ameliorerAuNiveau2() {
        setFrequenceAttaque(getFrequenceAttaque()-50);
    }

    @Override
    public void ameliorerAuNiveau3() {
        getReconnaissance().setPortee(getReconnaissance().getPortee()+1);
    }
}
