package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaque.AttaqueRayon;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecUnique;

public class Sainple extends Cellule {

    private Sainple(Environnement env , int ligne , int colonne){
        super(env , ligne ,colonne, 60 , 50 );
    }

    @Override
    public void initRec() {
        setReconnaissance(new RecUnique(this , 3.0));
    }

    @Override
    public void initTypeAttaque() {
        setTypeAttaque(new AttaqueRayon(this , 45));
    }

    public static Sainple creer(Environnement env , int ligne , int colonne){
        Sainple temp = new Sainple(env , ligne , colonne);
        temp.initRec();
        temp.initTypeAttaque();
        return temp;
    }
}
