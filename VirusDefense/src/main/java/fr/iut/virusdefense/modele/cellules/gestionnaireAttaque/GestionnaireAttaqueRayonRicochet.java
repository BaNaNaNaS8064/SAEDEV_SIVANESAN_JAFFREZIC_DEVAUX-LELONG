package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueRayonRicochet extends GestionnaireAttaqueRayon {

    public GestionnaireAttaqueRayonRicochet(Environnement environnement, double ligne, double colonne, double degats, ArrayList<Maladie> cibles){
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
}
