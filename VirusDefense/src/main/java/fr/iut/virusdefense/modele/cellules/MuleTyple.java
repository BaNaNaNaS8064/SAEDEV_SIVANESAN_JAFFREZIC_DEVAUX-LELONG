package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AtkRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecPlusieurs;

public class MuleTyple extends Cellule{

    private static int coutBase = 80;

    public static int getCoutBase() {
        return coutBase;
    }

    private MuleTyple(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 50, coutBase,5);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecPlusieurs(this, 3.0 , 3));
    }

    @Override
    public void initAttaque(){
        setAttaque(new AtkRayonSimple(this, 30));
    }

    public static MuleTyple creer(Environnement env, int ligne, int colonne){
        MuleTyple temp = new MuleTyple(env, ligne, colonne);
        temp.initRec();
        temp.initAttaque();
        return temp;
    }

    @Override
    public String nomCellule() {
        return "Mule-typle";
    }

    @Override
    public void niveau2() {

    }

    @Override
    public void niveau3() {

    }
}
