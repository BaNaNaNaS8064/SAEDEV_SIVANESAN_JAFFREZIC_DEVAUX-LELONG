package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkZone;
import fr.iut.virusdefense.modele.cellules.attaque.Attaque;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

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
        setReconnaissance(new RecSimple(getLigne(), getColonne(), getEnvironnement().getMaladies(), 1.5, 1));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkZone(getEnvironnement(), getLigne(), getColonne(), 70, getReconnaissance().getCibles(), getReconnaissance().getPortee()));
    }

    public static Brouaileuse creer(Environnement env, int ligne, int colonne){
        Brouaileuse temp = new Brouaileuse(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
