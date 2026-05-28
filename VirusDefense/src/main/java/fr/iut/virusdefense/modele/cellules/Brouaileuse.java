package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayon;
import fr.iut.virusdefense.modele.cellules.attaque.AtkZone;
import fr.iut.virusdefense.modele.cellules.attaque.Attaque;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecTous;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

public class Brouaileuse extends Cellule {
    private static int coutBase = 500;

    public static int getCoutBase() {
        return coutBase;
    }

    private Brouaileuse(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 120, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecTous(this, 3.0));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkZone(this, 50));
    }

    public static Brouaileuse creer(Environnement env, int ligne, int colonne){
        Brouaileuse temp = new Brouaileuse(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }
}
