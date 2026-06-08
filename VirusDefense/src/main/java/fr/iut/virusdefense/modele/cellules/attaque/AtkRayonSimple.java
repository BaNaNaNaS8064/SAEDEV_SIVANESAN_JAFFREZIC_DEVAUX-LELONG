package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class AtkRayonSimple extends AtkRayon {
    int degats;

    public AtkRayonSimple(Environnement environnement, double ligne, double colonne, int degats, ArrayList<Maladie> cibles){
        super(environnement, ligne, colonne, cibles);
        this.degats = degats;
    }

    @Override
    public final void attaqueCibles() {
        for (Maladie m : getCibles())
            attaque(m, degats);
    }
}
