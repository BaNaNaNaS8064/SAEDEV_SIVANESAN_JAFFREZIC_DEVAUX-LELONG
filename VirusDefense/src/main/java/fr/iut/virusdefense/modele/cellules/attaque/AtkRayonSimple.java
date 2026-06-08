package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class AtkRayonSimple extends AtkRayon {

    public AtkRayonSimple(Environnement environnement, double ligne, double colonne, double degats, ArrayList<Maladie> cibles){
        super(environnement, ligne, colonne, degats, cibles);
    }

    @Override
    public final void attaqueCibles() {
        for (Maladie m : getCibles())
            attaque(m, getDegats());

    }
}
