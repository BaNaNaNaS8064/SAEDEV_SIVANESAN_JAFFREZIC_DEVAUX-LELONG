package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.entitesgeneriques.Rayon;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class AtkRayonRicochet extends AtkRayon{

    public AtkRayonRicochet(Environnement environnement, double ligne, double colonne, double degats, ArrayList<Maladie> cibles){
        super(environnement, ligne, colonne, degats, cibles);
    }

    @Override
    public void attaqueCibles() {
        double posLigne = getLigne(), posColonne = getColonne();
        for (Maladie m : getCibles()) {
            attaque(m, getDegats());
            setLigne(m.getLigne());
            setColonne(m.getColonne());
        }
        setLigne(posLigne);
        setColonne(posColonne);
    }

//    public void attaqueRicochet(double ligne, double colonne, Maladie m){
//        getEnvironnement().ajouterRayon(new Rayon(getEnvironnement(), ligne, colonne, m, getDegats(), 2, getAlterations()));
//    }
}
