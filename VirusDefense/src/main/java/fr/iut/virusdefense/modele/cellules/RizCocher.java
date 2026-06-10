package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonRicochet;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecRicochet;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class RizCocher extends Cellule{
    private static int coutBase = 100;

    public static int getCoutBase() {
        return coutBase;
    }

    private RizCocher(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 90, coutBase);
    }

    @Override
    public void initRec() {
        setReconnaissance(new RecRicochet(getLigne(), getColonne(), getEnvironnement().getMaladies(), 3.0, 3));
    }

    @Override
    public void initAttaque() {
        setAttaque(new AtkRayonRicochet(getEnvironnement(), getLigne(), getColonne(), 75, getReconnaissance().getCibles()));
    }

    public static RizCocher creer(Environnement env, int ligne, int colonne){
        RizCocher temp = new RizCocher(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "Riz Co-cher";
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
