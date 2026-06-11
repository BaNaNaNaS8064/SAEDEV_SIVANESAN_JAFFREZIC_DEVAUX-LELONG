package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonRicochet;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecRicochet;

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
        setReconnaissance(new RecRicochet(getEnvironnement(), getLigne(), getColonne(), 3.0, 3));
    }

    @Override
    public void initGestionnaireAttaque() {
        setGestionnaireAttaque(new GestionnaireAttaqueRayonRicochet(getEnvironnement(), getLigne(), getColonne(), 75, getReconnaissance().getCibles()));
    }

    public static RizCocher creer(Environnement env, int ligne, int colonne){
        RizCocher temp = new RizCocher(env, ligne, colonne);
        temp.initRec();
        temp.initGestionnaireAttaque();
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
