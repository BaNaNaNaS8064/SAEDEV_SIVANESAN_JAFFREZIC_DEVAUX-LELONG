package fr.iut.virusdefense.modele.cellules.attaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class AtkRayonConcentre extends AtkRayon{

    private final int degatsInitiaux;
    private ArrayList<Maladie> ciblesTourPrecedent;
    private ArrayList<Integer> dureeCiblageTourPrecedent;

    public AtkRayonConcentre(Environnement environnement, double ligne, double colonne, int degatsInitiaux, ArrayList<Maladie> cibles){
        super(environnement, ligne, colonne, cibles);
        this.degatsInitiaux = degatsInitiaux;
        ciblesTourPrecedent = new ArrayList<>();
        dureeCiblageTourPrecedent = new ArrayList<>();
    }

    @Override
    public final void attaqueCibles() {
        ArrayList<Integer> dureeCiblageTourActuel = new ArrayList<>();
        Maladie m;

        for (int i=0; i<getCibles().size(); i++){
            m = getCibles().get(i);
            if (ciblesTourPrecedent.contains(m))
                dureeCiblageTourActuel.add(dureeCiblageTourPrecedent.get(ciblesTourPrecedent.indexOf(m)) + 1);
            else
                dureeCiblageTourActuel.add(0);

            attaque(m, calculerDegats(dureeCiblageTourActuel.get(i)));
        }

        ciblesTourPrecedent = getCibles();
        dureeCiblageTourPrecedent = dureeCiblageTourActuel;
    }

    private int calculerDegats(int dureeCiblage){
        if (dureeCiblage < 120)
            return degatsInitiaux;
        else if (dureeCiblage < 300)
            return degatsInitiaux * 3;
        else
            return degatsInitiaux * 9;
    }
}
