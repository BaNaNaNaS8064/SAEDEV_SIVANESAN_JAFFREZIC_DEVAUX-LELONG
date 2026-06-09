package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public class RecRicochet extends Reconnaissance{
    private int nbRicochets;

    public RecRicochet(double ligne, double colonne, List<Maladie> maladies, double portee, int nbRicochets){
        super(ligne, colonne, maladies, portee, 1);
        this.nbRicochets = nbRicochets;
    }

    @Override
    public boolean estValide(Maladie m) {
        return m.estVivant() && aPortee(m) && m.voit(getLigne(), getColonne(),true);
    }

    private boolean aAssezDeRicochets(){
        return getCibles().size() >= 1 + nbRicochets;
    }

    @Override
    public boolean aAssezDeCibles() {
        return super.aAssezDeCibles() && aAssezDeRicochets();
    }

    @Override
    public void changerCibles() {
        super.changerCibles();
        if (aAuMoinsUneCible()){
            double posLigne = getLigne(), posColonne = getColonne();
            int i = 0;
            Maladie m;

            setLigne(getCibles().get(0).getLigne());
            setColonne(getCibles().get(0).getColonne());
            while (!aAssezDeRicochets() && i < getMaladies().size()){
                m = getMaladies().get(i);

                if (estValide(m) && !getCibles().contains(m)) {
                    ajoutCible(m);
                    setLigne(m.getLigne());
                    setColonne(m.getColonne());
                }

                i++;
            }

            setLigne(posLigne);
            setColonne(posColonne);
        }
    }
}
