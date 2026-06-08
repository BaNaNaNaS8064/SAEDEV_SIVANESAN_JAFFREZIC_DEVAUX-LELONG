package fr.iut.virusdefense.modele.entitesgeneriques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.attaque.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public class Zone extends EntiteAtk{
    private int age;
    private final int ageMaximal;
    private final double rayonZone;

    public Zone(Environnement environnement, double ligne, double colonne, ArrayList<Maladie> cibles, int degats, int ageMaximal, List<Alteration> alterations, double rayonZone) {
        super(environnement, ligne, colonne, degats, alterations, cibles);

        age = 0;
        this.ageMaximal = ageMaximal;

        this.rayonZone = rayonZone;

        donnerAlterations();
        infligerDegats();
    }

    public double getRayonZone() {
        return rayonZone;
    }

    public boolean aDepasseAgeMaximal(){
        return age > ageMaximal;
    }

    @Override
    public void agir() {
        age++;
    }
}
